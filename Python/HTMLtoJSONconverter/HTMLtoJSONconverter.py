"""
Brendon Hudnell
Section Leader: Will Zielinski
4/13/16
ISTA 350 Hw8

Takes 3 html documents, scrapes and saves them, extracts their data and
converts that data into a usable form, cleans the data, and saves the
data as JSON objects.
"""

import requests, gzip, os, json
from bs4 import BeautifulSoup

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


def main():
    """
    if the files in the files list are not saved to disk, it calls scrape_and_save()
    then calls clean_and_jsonify on the list of files
    """
    files = ['wrcc_pcpn.html', 'wrcc_mint.html', 'wrcc_maxt.html']
    for file in files:
        if file not in os.listdir():
            print ('---- scraping and saving ----')
            scrape_and_save()
    clean_and_jsonify(fnames, -999, 2)