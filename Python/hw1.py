"""
Brendon Hudnell
Section Leader: Will Zielinski
2/3/16
ISTA 350 Hw1

Contains ten functions used to demonstrate knowledge of strings and lists, 
as well as how to go back and forth between them.
"""

def sort_int_string(number_string):
    """
    Takes a string of integers, sorts them from smallest to largest, and returns the sorted string.

    number_string: a string of integers separated by spaces

    returns: a sorted string of integers
    """
    #splits the string into a list of numbers as strings
    strList = number_string.split() 

    #converts the numbers as strings into integers for sorting
    for i in range(len(strList)):
        strList[i] = int(strList[i])
    
    #sorts the list of numbers
    strList.sort()

    #converts the list of ints back into strings
    for i in range(len(strList)):
        strList[i] = str(strList[i])

    #returns the joined list of sorted numbers as strings
    return " ".join(strList)

def dash_reverse(input_string):
    """
    Takes one string, reverses the order of the characters separated by dashes, then returns the new string.

    input_string: a string of characters

    returns: the new modified string 
    """
    #Turn the string into a list, then reverse the list
    strList = list(input_string)
    strList.reverse()

    #Turn the reversed list back into a string with dashes between the characters
    return "-".join(strList)

def xslice_replace(start_string, start, end, step, replacement_string):
    """
    Mimics the functionality of extended slices for lists with string arguements instead.

    start_string: main string the function will act on
    start: int value where the string replacement will begin
    end: int value where the string replacement will end
    step: int value of the step size
    replacement_string: the string that will be sliced into the main string

    returns: the string start_string modified by the string replacement_string
    """
    #turns the main string and the replacement strings into lists
    start_list = list(start_string)
    rep_list = list(replacement_string)

    #replaces the elements of start_list defined by start, end, and step with the elements in the replacement list
    start_list[start:end:step] = rep_list

    #Turn the ammended start_list back into a string
    return "".join(start_list)

def element_ip_replace(input_list, key, replace=None):
    """
    A list function that mimics the replace method for strings.

    input_list: the list to be altered
    key: the object to be searched for
    replace: the replacement object that replaces the key object at every instance

    returns: nothing because the function replaces in place
    """
    for i in range(len(input_list)):
        if input_list[i] == key:
            input_list[i] = replace

def element_nl_replace(input_list, key, replace=None):
    """
    A list function that mimics the replace method for strings and returns a new list.

    input_list: the list to be altered
    key: the object to be searched for
    replace: the replacement object that replaces the key object at every instance

    returns: a new list with all instances of the key object replaced by the replace object
    """
    result = []
    for i in range(len(input_list)):
        if input_list[i] == key:
            result.append(replace)
        else: 
            result.append(input_list[i])
    return result

def lreplace(input_list, key, replace=[]):
    """
    A list function that mimics the replace method for strings.

    input_list: the list to be altered
    key: the sublist to be searched for
    replace: the replacement list that replaces the key list at every instance

    returns: nothing because the function replaces in place
    """
    result = []
    i = 0

    #normal case where the input list and key are non-empty lists
    if input_list != [] and key != []:
        while i < len(input_list):
            if input_list[i:i + len(key)] == key and len(key) > 0:
                result.extend(replace)
                i += len(key)
            else:
                result.append(input_list[i])
                i += 1

    #special case where the key is an empty list
    if key == []:
        if input_list == []:
            result.extend(replace)
        else:
            for i in range(len(input_list)):
                result.extend(replace)
                result.append(input_list[i])
            result.extend(replace)

    #clears the input list and replaces it with the result list
    input_list.clear()
    input_list.extend(result)

def list_lt(list1, list2):
    """
    A list function to determine if an element of the first list is less than the 
    corresponding element in the second list. 

    list1: a list of numbers
    list2: a list of numbers

    returns: None if the lists are different lengths, or a list of True or False values. 
    True if the element in list1 is smaller than that of list2, false if it is equal to or greater. 
    """
    result = []

    #checks that the lists are the same length
    if len(list1) != len(list2):
        return None

    # Checks if each element in list1 is smaller than that of list2
    for i in range(len(list1)):
        if list1[i] < list2[i]:
            result.append(True)
        else:
            result.append(False)
    return result

def sum_of_powers(bases, powers):
    """
    A list function that returns a list where the first element is the sum of each element
    in the bases list raised to the first element in the powers list, etc.

    bases: a list of numbers 
    powers: a list of numbers

    returns: a list of sums
    """
    result = []

    #if one of the arguements is an empty list, return an empty list
    if powers == [] or bases == []:
        return result

    #creates the list of sum of powers
    i = 0
    while i < len(powers):
        j = 0
        sum = 0
        while j < len(bases):
            sum += bases[j] ** powers[i]
            j += 1
        result.append(sum)
        i += 1

    return result

def trace(matrix):
    """
    A function that returns the sum of the diagonal enteries of a square matrix.

    matrix: a list of lists that form a non empty square matrix

    returns: an int sum of the diagonal enteries of the matrix
    """
    sum = 0
    for i in range(len(matrix)):
        sum += matrix[i][i]
    return sum

def str_by_twos(input_str):
    """
    A string function that returns a list of each pair of adjacent characters in order.

    input_str: a string

    returns: a list of pairs of characters
    """    
    result = []
    for i in range(len(input_str) - 1):
        result.append(input_str[i:i + 2])
    return result
    