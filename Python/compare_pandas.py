'''
from pandas import DataFrame
import numpy as np
'''

def compare_frames(df1, df2, precision=0.011):
    if not ((df1.index == df2.index).all() and (df1.columns == df2.columns).all()) or \
       df1.values.shape != df2.values.shape:
        print('------DataFrame indices--------')
        print(df1.index)
        print(df2.index)
        print('------DataFrame columns--------')
        print(df1.columns)
        print(df2.columns)
        print('------DataFrame shape----------')
        print(df1.values.shape)
        print(df2.values.shape)
        print('-------------------------------')
        return False
    bools = abs(df1.values - df2.values) < precision
    if bools.all():
        return True
    indices = (bools.argmin() // bools.shape[1], bools.argmin() % bools.shape[1])
    print('*********DataFrame Contents differ*********')
    print(str(bools[indices[0], indices[1]]) + ':', indices[0], indices[1])
    print(df1[indices[0], indices[1]], df2[indices[0], indices[1]])
    print('*******************************************')
    return False

