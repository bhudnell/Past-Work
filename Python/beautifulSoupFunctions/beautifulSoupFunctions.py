"""
Brendon Hudnell
Section Leader: Will Zielinski
4/6/16
ISTA 350 Hw7

Contains four functions used to demonstrate knowledge of the BeautifulSoup module and
the requests and gzip libraries.
"""

import requests, gzip, os
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

def main():
    """
    if the files in the files list are not saved to disk, it calls scrape_and_save()
    """
    files = ['wrcc_pcpn.html', 'wrcc_mint.html', 'wrcc_maxt.html']
    for file in files:
        if file not in os.listdir():
            print ('---- scraping and saving ----')
            scrape_and_save()
        