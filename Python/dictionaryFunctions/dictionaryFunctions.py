"""
Brendon Hudnell
Section Leader: Will Zielinski
2/10/16
ISTA 350 Hw2

Contains 8 functions designed to show knowledge of mapping types, dictionaries, and file manipulation.
"""
def ltranslate(lst1, dict1):
    """
    Takes a list and a dictionary. Replaces each occurance of the dictionary keys in the list with
    the corresponding values in place.

    lst1: a list to be translated
    dict1: a dictionary holding the key and its replacement value

    returns: nothing. Changes the list in place.
    """
    for i in range(len(lst1)):
        if lst1[i] in dict1:
            lst1[i] = dict1[lst1[i]]

def word_count(filename, dict1=None):
    """
    Reads a file and returns a wordcount dictionary.

    filename: the file to be read
    dict1: the wordcount dictionary. Default to an empty dictionary.

    returns: a dictionary containing the word count for the file read
    """
    if dict1 == None:
        dict1 = {}

    fp = open(filename)
    for word in fp.read().split(): 
        if word.lower() in dict1:
            dict1[word.lower()] += 1
        else:
            dict1[word.lower()] = 1

    return dict1

def average_wc(wc_dict):
    """
    Takes a wordcount dictionary and returns the average wordcount.

    wc_dict: a dictionary containing a wordcount

    returns: the average wordcount of the dictionary.
    """
    if wc_dict == {}:
        return 0
    return sum(wc_dict.values())/len(wc_dict)

def max_wc(wc_dict):
    """
    Takes a wordcount dictionary and returns the maximum wordcount.

    wc_dict: a dictionary containing a wordcount

    returns: the maximum wordcount
    """
    max_val = 0

    for key in wc_dict:
        if wc_dict[key] > max_val:
            max_val = wc_dict[key]

    return max_val

def dreverse(for_dict):
    """
    Takes a dictionary and returns a dictionary where the key:value pairs are reversed.

    for_dict: a forward dictionary

    returns: a reverse dictionary
    """
    rev_dict = {}
    
    for key in for_dict:
        if for_dict[key] not in rev_dict:
            rev_dict[for_dict[key]] = [key]
        else:
            rev_dict[for_dict[key]].append(key)

    return rev_dict

def bird_weights(filename):
    """
    Takes a file containing bird names and their weights and returns a dictionary
    with bird names as keys and their weights as values.

    filename: the file to be read

    returns: a dictionary containing bird names and their weights
    """
    bird_dict = {}

    fp = open(filename)
    for line in fp:
        name = line.split(':')[0]
        name = name.upper()[0]+name.lower()[1:]
        weights = line.split(':')[1].split()

        for i in range(len(weights)):
            weights[i] = float(weights[i])

        if name in bird_dict:
            bird_dict[name].extend(weights)
        else:
            bird_dict[name] = weights

    return bird_dict

def median(num_list):
    """
    Takes a list of numbers and returns its median value.

    num_list: a list of numbers

    returns: the medium value of the list
    """
    if len(num_list) == 0:
        return None
    sort_list = sorted(num_list)
    if len(sort_list)%2 == 1:
        return sort_list[len(sort_list)//2]
    else:
        return (sort_list[len(sort_list)//2] + sort_list[len(sort_list)//2-1])/2

def median_bird_weights(bw_dict):
    """
    Takes a bird weight dictionary and returns a new dictionary mapping bird names to their median weight.

    bw_dict: a dictionary of bird weights

    returns: a dictionary of median bird weights
    """
    median_dict = {}

    for bird in bw_dict:
        median_weight = median(bw_dict[bird])
        median_dict[bird] = median_weight

    return median_dict