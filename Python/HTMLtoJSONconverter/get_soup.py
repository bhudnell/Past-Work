def get_soup(url=None, fname=None, gzipped=False):
    if fname:
        return BeautifulSoup(open(fname))
    r=requests.get(url)
    if gzipped:
        content = zlib.decompress(r.content, 16+zlib.MAX_WBITS) 
        # content = gzip.decompress(r.content) 
        return BeautifulSoup(content)
    return BeautifulSoup(r.content)  
