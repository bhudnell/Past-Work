def compare_files(f1, f2, read_bytes=False):
    '''
    Python's file_cmp module has issues, particularly
    saying two files, one created on a mac and one on
    Windows, are different even though the content is 
    the same because of the line terminator issue.  Also,
    this prints out some useful info.
    '''
    if not read_bytes:
        f1 = open(f1)
        f2 = open(f2)
    else:
        f1 = open(f1, 'rb')
        f2 = open(f2, 'rb')
    l1 = f1.readline()
    line_no = 0
    while l1:
        line_no += 1
        # add line numbers
        l2 = f2.readline()
        if not l2:
            print('line number:', line_no)
            print('missing l2, no match for:', repr(l1))
            return False
        l1 = l1.rstrip() # avoid cross-platform eol character issues
        l2 = l2.rstrip()
        if l1 != l2:
            print('line number:', line_no)
            print('l1:', repr(l1))
            print('l2:', repr(l2))
            return False
        l1 = f1.readline()
    l2 = f2.readline()
    if l2:
        print('line number:', line_no + 1)
        print('extra l2:', repr(l2))
        return False
    return True
