def up_vowels(sentence):
    """
    This function takes one parameter, sentence, which represents a string.

    Change all vowels in the sentence to uppercase, otherwise lowercase.

    Return the new string.
    """
    vowels = ['a', 'e', 'i', 'o', 'u']
    result = ""
    for i in range(len(sentence)):
        if sentence[i].lower() in vowels:
            result += sentence[i].upper()
        else:
            result += sentence[i].lower()
    return result

def star_point(char_lst):
    """
    This function takes one parameter, char_lst, which represents a list of 
    strings containing single characters. 
        Example:
            char_lst = ['a', 'b', '^', 'z', 'D', '*', 'U', '*']

    You will return a new list (you can not alter the original list) that 
    contains all the '*' and '^' string characters moved to the  end of 
    the list. Print the original list to see if you altered it after you are 
    done iterating through it.

    All '^' characters must come before all the '*' characters.

        Example after running the function:

            char_lst = ['a', 'b', '^', 'z', 'D', '*', 'U', '*']

            new_lst = ['a', 'b', 'z', 'D', 'U', '^', '*', '*']

    """
    carrot = []
    star = []
    other = []
    for i in range(len(char_lst)):
        if char_lst[i] == '^':
            carrot.append(char_lst[i])
        elif char_lst[i] == '*':
            star.append(char_lst[i])
        else:
            other.append(char_lst[i])
    return other + carrot + star

def remove_replace(emoji_lst):
    """
    This function takes one parameter, emoji_lst, which represents a list of strings
    containing emoticon faces. 
        Example: 
            emoji_lst = [":P", ":)", ":|", ":(", "8)", "8(" ,";)"] 
    
    You will remove any sad face emoticon, any faces with a frown "(", and
    append the removed sad face to a new list.

    Print the list that contains the sad faces. 
    Print the original list.

    returns None
    """
    sad = []
    i = 0
    for face in emoji_lst:
        if '(' in face:
            sad.append(emoji_lst.pop(i))
        i += 1

    print (sad)
    print (emoji_lst)

def is_dentity(matrix_lst):
    """
    This function takes one parameter, matrix_lst, which represents a list of 
    lists. 

    You will return true if the matrix_list is an identity matrix. Recall that 
    an identity matrix has ones along the main diagonal and zeros everywhere
    else. 

        Example:
            matrix_lst = [[1, 0, 0],
                          [0, 1, 0], 
                          [0, 0, 1]]

            Would result to True.
    """
    for r in range(len(matrix_lst)):
        for c in range(len(matrix_lst[r])):
            if r == c and matrix_lst[r][c] != 1:
                return False
            if r != c and matrix_lst[r][c] != 0:
                return False
    return True

def sum_diagonal(matrix_lst):
    """
    This function takes one parameter, matrix_lst, which represents a list of 
    lists. 

    You will return the sum of the main diagonal.

        Example:
            matrix_lst = [[5, 4, 0],
                          [1, 5, 6], 
                          [8, 2, 4]]

            would return 14
    """
    sum = 0
    for r in range(len(matrix_lst)):
        for c in range(len(matrix_lst)):
            if r == c:
                sum += matrix_lst[r][c]
    return sum

def num_or_char(elements):
    """
    This function takes one parameter, elements, which represents a list containing
    strings of characters or numbers.
        Example: elements = ['a', 'b', '1', '90', 'd', 'z', '1000']

    Create a literal dictionary and map the boolean value True to an empty list 
    and boolean False to an empty list. (Yes this dictionary only has two mappings)

    Iterate through the elements list. If the current element is a character append
    it to the list that is mapped to the boolean True. If the character is a
    digit append it to the list that is mapped to the boolean False.

    Return the dictionary. 
    """
    return_dict = {True:[], False:[]}
    for ele in elements:
        if ele.isdigit():
            return_dict[False].append(ele)
        if ele.isalpha():
            return_dict[True].append(ele)
    return return_dict

def count_vals(elements):
    """
    This function takes one parameter, elements, which represents a list containing
    strings of either characters or numbers.
        Example: elements = ['a', 'b', '1', '90', 'd', 'z', '1000']

    You will need to use the function num_or_char(). Recall that is returns a
    dictionary that maps the boolean values True or False to a list of strings.
    Assign your function call to a variable which will hold the dictionary returned
    by the num_or_char() function.

    Print out the length of the list that is mapped to True. Do the same for False

    Your function should print the info following the format below:
        True: 4
        False: 3

    This function returns None.
    """
    print ("True: " + str(len((num_or_char(elements)[True]))))
    print ("False: " + str(len((num_or_char(elements)[False]))))

def int_or_str(lst):
    '''
    This function takes in a a list that can contain any type
    (int, str, float, bool, list, dict). 

    We only care about the string and int values. 

    You will translate any integers from the range 0 to 10 inclusive to there English name


        example: 1 -> 'one' #Note: lower case 

    You will also translate any English named integers ('one', 'two', 'three' ...) 
    to there integer corresponding values, 'zero' to 'ten' inclusive. Case does not 
    matter the function should recognize the whole word.

        example: 'ONe' -> 1

    You will need to check the values of any inner lists and the key:value mappings 
    in dictionaries and do translations as well.

        Example:

            lst = ['oNe', 3.3, 5, True, [1, 'hi', False], {1: 'hi'}, False, "HAM", {"onE": [1,2,3]}, ':)']

        Will result in 

            [1, 3.3, five, True, ['one', 'hi', False], {'one': 'hi'}, False, "HAM", {1: ['one', 'two', 'three']}, ':)']


    This function returns None, you must alter the list in place. 
    '''

        
def damn_daniel(file):
    ''' 
    Daniel has many different pairs of shoes (most notably his white vans), a
    few pairs  of jeans, and lots of shirts.  Read in the file, daniels_outfits.csv,
    (this is represented by filename which is the string of the file you will read in)
    which contains 3 columns in the following order: shoes, jeans, shirts, where each 
    row is an outfit that Daniel has worn this year and create a dictionary that maps 
    each shoe to a list of lists, where the first list
    contains all of the shirts he  has worn with that shoe, and the second list
    contains all of the jeans he was worn with that shoe.

    Return the dictionary. Be sure to avoid any duplicate items in the list of lists!
    '''
    result = {}
    fp = open(file)
    for line in fp:
        list = line.strip().split(',')
        shoe = list[0]
        pants = list[1]
        shirt = list[2]
        if shoe not in result.keys():
            result[shoe]=[[],[]]
            result[shoe][0] = [shirt]
            result[shoe][1] = [pants]
        else:
            if shirt not in result[shoe][0]:
                result[shoe][0].extend([shirt])
            if pants not in result[shoe][1]:
                result[shoe][1].extend([pants])

    return result

def main():
    '''
    Test your functions in main!
    '''
    print (damn_daniel('daniels_outfits.csv'))

if __name__ == '__main__':
    main()
