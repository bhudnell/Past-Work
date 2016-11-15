"""
Brendon Hudnell
Section Leader: Will Zielinski
4/20/16
ISTA 350 Hw9

Complete program that goes to the web, retrieves data, saves it in various formats, cleans it, analyzes it,
and plots it.
"""

import requests, gzip, os, json
from bs4 import BeautifulSoup
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
from pandas import Series, DataFrame

def get_soup(url=None, fname=None, gzipped=False):
    """
    creates and returns a BeautifulSoup object from a local file or a url, either zipped or unzipped.

    url: a string representation of the url to be scraped into a soup object
    fname: a string representation of the file to be scraped into a soup object
    gzipped: a boolean telling if the url is zipped or not

    returns a soup object
    """
    if fname:
        with open(fname) as fp:
            return BeautifulSoup(fp, "html.parser")
    elif url:
        r = requests.get(url)
        if gzipped:
            return BeautifulSoup(gzip.decompress(r.content), "html.parser")
        return BeautifulSoup(r.content, "html.parser")
    raise RuntimeError('Either url or filename must be specified.')

def save_soup(fname, soup):
    """
    saves a textual representation of a soup object to the file specified

    fname: a string representation of the file to be scraped into a soup object
    soup: a soup object to be saved

    returns nothing
    """
    with open(fname, 'w') as fp:
        fp.write(repr(soup))
        
def scrape_and_save():
    """
    scrapes the data from the three websites and saves them to html files
    """
    url = 'http://www.wrcc.dri.edu/WRCCWrappers.py?sodxtrmts+028815+por+por+pcpn+none+msum+5+01+F'
    save_soup('wrcc_pcpn.html', get_soup(url))

    url = 'http://www.wrcc.dri.edu/WRCCWrappers.py?sodxtrmts+028815+por+por+mint+none+mave+5+01+F'
    save_soup('wrcc_mint.html', get_soup(url))

    url = 'http://www.wrcc.dri.edu/WRCCWrappers.py?sodxtrmts+028815+por+por+maxt+none+mave+5+01+F'
    save_soup('wrcc_maxt.html', get_soup(url))

def is_num(str):
    """
    takes a string an returns true of the string is a number, false otherwise

    str: a string

    returns a boolean
    """
    try:
        float(str)
        return True
    except:
        return False

def load_lists(soup, flag):
    """
    takes a soup object and returns a transposed list of lists of the data

    soup: a BeautifulSoup object
    flag: an int used to denote blank data

    returns a transposed list of lists
    """
    lists = []
    for tr in soup.find_all('tr'):
        list = []
        for td in tr.find_all('td'):
            if td.get_text() == '-----':
                list.append(flag)
            elif is_num(td.get_text()):
                list.append(float(td.get_text()))
        lists.append(list)

    lists.pop(0)
    temp = []
    count = 0
    while lists[count] != []:
        temp.append(lists[count])
        count+=1
    lists = temp

    transposed = []
    for i in range(len(lists[0])):
        transposed.append([])
    for lst in lists:
        for i in range(len(lists[0])):
            transposed[i].append(lst[i])
    for i in range(len(transposed[0])):
        transposed[0][i] = int(transposed[0][i])
    return transposed

def replace_na(list, row, col, flag, precision=5):
    """
    takes the previous 5 values before col and next 5 values after col
    and returns the average with flagged values omitted rounded to precision

    list: a list of lists
    row: the row index of the value to be replaced
    col: the col index of the value to be replaced
    flag: the int value of the flag to be ignored
    precision: the number of decimals for rounding

    returns the average
    """
    lst = []
    for i in range(len(list[row])):
        if i != col and i >= col-5 and i <= col+5 and list[row][i] != flag:
            lst.append(list[row][i])

    return round(sum(lst)/float(len(lst)), precision)

def clean_data(list, flag, precision=5):
    """
    goes through a list of lists and replaces all flagged values with the average of those around it

    list: a list of lists
    flag: the int value to be replaced
    precision: the rounding value

    returns nothing
    """
    for r in range(len(list)):
        for c in range(len(list[r])):
            if list[r][c] == flag:
                list[r][c] = replace_na(list, r, c, flag, precision)

def recalculate_annual_data(list, averages=False, precision=5):
    """
    recalculates the average of each column in a list of lists

    list: a list of lists
    averages: a boolean. True if it should calculate averages, False if it should calculate totals
    precision: the rounding value

    returns nothing
    """
    for c in range(len(list[0])):
        lst=[]
        for r in range(1, len(list)-1):
            lst.append(list[r][c])
        if averages:
            list[len(list)-1][c] = round(round(sum(lst), precision)/float(len(lst)), precision)
        else:
            list[len(list)-1][c] = round(sum(lst), precision)

def clean_and_jsonify(fnames, flag, precision=5):
    """
    takes a list of html files, cleans them, then saves the data as JSON files

    fnames: the list of html files to be cleaned
    flag: the int value to replace empty data
    precision: the rounding value

    returns nothing
    """
    for file in fnames:
        list = load_lists(get_soup(None, file), flag)
        clean_data(list, flag, precision)

        if file == 'wrcc_pcpn.html':
            recalculate_annual_data(list, False, precision)
            with open('wrcc_pcpn.json', 'w') as fp:
                json.dump(list, fp)

        if file == 'wrcc_mint.html':
            recalculate_annual_data(list, True, precision)
            with open('wrcc_mint.json', 'w') as fp:
                json.dump(list, fp)

        if file == 'wrcc_maxt.html':
            recalculate_annual_data(list, True, precision)
            with open('wrcc_maxt.json', 'w') as fp:
                json.dump(list, fp)

def get_panda(fname):
    """
    takes a filename and returns a DataFrame with months as rows and years as columns

    fname: a string representation of the file name 

    returns a DataFrame
    """
    with open(fname) as fp:
        lst = json.load(fp)

    index = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec', 'Ann']
    columns = lst[0]
    data = lst[1:]

    return DataFrame(data, index, columns)

def get_stats(frame):
    """
    takes a DataFrame and returns a DataFrame containing a statistical summary of the argument

    frame: a DataFrame

    returns a DataFrame
    """
    array = frame.values
    columns = frame.index
    index = ['mean', 'sigma', 's', 'r']

    data= [[], [], [], []]
    for i in range(len(array)):
        data[0].append(np.mean(array[i]))
        data[1].append(np.std(array[i]))
        data[2].append(np.std(array[i], None, None, None, 1))
        data[3].append(Series(array[i]).corr(Series(frame.columns)))
    
    return DataFrame(data, index, columns)

def print_stats(fname):
    """
    takes a filename and prints the statistical summary of the data 

    fname: a string representation of the file name

    returns nothing
    """
    print ('----- Statistics for ' + fname + ' -----\n')
    print (repr(get_stats(get_panda(fname))))
    print ()

def smooth_data(frame, precision=5):
    """
    takes a DataFrame and returns a DataFrame with each data point replaced with an 11 year average of the
    surrounding data, including itself.

    frame: a DataFrame
    precision: the rounding value

    returns a DataFrame
    """
    index = frame.index
    columns = frame.columns
    data = []
    for i in range(len(frame.values)):
        data.append([])
    for row in range(len(frame.values)):
        for i in range(len(frame.values[row])):
            temp = []
            for j in range(i-5, i+6):
                if j>=0 and j<len(frame.values[row]):
                    temp.append(frame.values[row][j])
            data[row].append(round(sum(temp)/float(len(temp)),precision))
    return DataFrame(data, index, columns)

def make_plot(fname, month=None, precision=5):
    """
    takes a dataframe and produces plots of the data and the smoothed version of the data

    fname: a string representation of the file name
    month: the 3-letter abbreviation of the month, with a default value of None
    precision: the rounding value

    returns nothing
    """
    norm_frame = get_panda(fname)
    smooth_frame = smooth_data(norm_frame, precision)
    norm_frame = norm_frame.T
    smooth_frame = smooth_frame.T
    if month == None:
        p = norm_frame.plot(subplots=True, legend=None, color='g', yticks=[], title=fname)
        for i in range(len(norm_frame.columns)):
            p[i].set_ylabel(norm_frame.columns[i])
            curve_like_line = Series(smooth_frame.values[:,i], smooth_frame.index)
            curve_like_line.plot(ax=p[i], color='b')
    else:
        for i in range(len(norm_frame.columns)):
            if norm_frame.columns[i] == month:
                norm = Series(norm_frame.values[:,i], norm_frame.index)
                p = norm.plot(legend=None, color='b', title=fname + ": " + month)
                curve_like_line = Series(smooth_frame.values[:,i], smooth_frame.index)
                curve_like_line.plot(ax=p, color='g')

def main():
    """
    if the files in the files list are not saved to disk, it calls scrape_and_save()
    then calls clean_and_jsonify on the list of files
    finally it prints the statistical summary of the data and outputs the plots to the screen
    """
    fnames = ['wrcc_pcpn.html', 'wrcc_mint.html', 'wrcc_maxt.html']
    for fname in fnames:
        if fname not in os.listdir():
            print ('---- scraping and saving ----')
            scrape_and_save()
    clean_and_jsonify(fnames, -999, 2)
    for fname in fnames:
        json_fname = fname.split('.')[0] + '.json'
        print_stats(json_fname)
        make_plot(json_fname, precision=2)
    plt.figure()
    make_plot(fnames[0].split('.')[0] + '.json', 'Jan')
    input('Enter to end: ')

if __name__ == "__main__":
    main()