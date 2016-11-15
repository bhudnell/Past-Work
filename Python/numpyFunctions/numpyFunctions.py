"""
Brendon Hudnell
Section Leader: Will Zielinski
3/30/16
ISTA 350 Hw6

Contains ten functions used to demonstrate knowledge of the numpy module and arrays.
"""

import numpy as np 

def reverse(array):
    """
    takes an array and returns a new array with the elements reversed.

    array: an array

    returns a reverse array
    """
    result = np.empty(len(array))
    index = len(array)-1
    for i in range(len(result)):
        result[i] = array[index]
        index -= 1
    return result

def odd_even_mask(array):
    """
    takes an array and returns a new array of 1's and 0's. 1 if the element in the
    original array is odd and 0 if the element is even.

    array: an array of numbers

    returns an array of 1's and 0's
    """
    result = np.empty(len(array))
    for i in range(len(result)):
        if array[i]%2 == 0:
            result[i] = 0
        else:
            result[i] = 1
    return result

def cycle(array, n):
    """
    takes an array and returns a new array with the elements shifted
    n places to the right, with wraparound to element 0.

    array: an array
    n: number of places to shift each element

    returns an array
    """
    result = np.empty(len(array))
    for i in range(len(result)):
        result[(i+n+len(result))%len(result)] = array[i]
    return result

def double(array):
    """
    takes an array and returns a new array with each element doubled

    array: an array of numbers

    returns an array
    """
    result = np.empty(len(array))
    for i in range(len(result)):
        result[i] = array[i]*2
    return result

def double_ip(array):
    """
    takes an array and doubles each even element in place.

    array: an array of numbers

    returns nothing
    """
    for i in range(len(array)):
        array[i] = array[i]*2

def square_evens(array):
    """
    takes an array and squares each even element in place.

    array: an array of numbers

    returns nothing
    """
    for i in range(len(array)):
        if array[i]%2 == 0:
            array[i] = array[i]**2

def binary_search(key, array):
    """
    Takes an array and performs a binary search for a key and returns the 
    index of the element that matches the key

    key: the term to be searched for
    array: an array

    returns the index of the element that matches the key, or -1 if key wasnt found
    """
    low = 0
    high = len(array)-1
    while low <= high:
        mp = (low+high)//2
        if array[mp] == key:
            return mp
        if array[mp] > key:
            high = mp - 1
        else:
            low = mp +1
    return -1

def insert(array, index, value, overwrite):
    """
    Inserts value into the array at index. Overwrites if overwrite is true.

    array: an array
    index: the position to insert the value
    value: the term to be inserted into the array
    overwrite: a boolean. If true, overwrite the current value at index. 
               if false, shift values at index and higher one to the right, eliminating the last item

    returns nothing
    """
    if index >= len(array):
        raise IndexError
    if overwrite == False:
        for i in range(len(array)-1, index, -1):
            array[i] = array[i-1]
    array[index] = value

def swap(array, index1, index2):
    """
    swaps the values at index1 and index2

    array: an array
    index1: the first index
    index2: the second index

    returns nothing
    """
    temp = array[index1]
    array[index1] = array[index2]
    array[index2] = temp

def add_arrays(array1, array2):
    """
    takes two arrays and adds them together

    array1: an array of numbers
    array2: an array of numbers

    returns an array of the added values
    """
    small = array1
    big = array2
    if len(array1) > len(array2):
        small = array2
        big = array1
    result = np.empty(len(big))
    for i in range(len(small)):
        result[i] = array1[i] + array2[i]
    for i in range(len(small), len(big)):
        result[i] = big[i]
    return result