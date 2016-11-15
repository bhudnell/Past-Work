def largest_num(nums):
    '''
    This function takes in a list of integers. Your task is to print
    the largest number in the list.

        Example:
            nums = [ 1, 2, 45, 2002, 3]
            
            2002 should be printed
    '''
    print (max(nums))

def concat_name(names_list):
    '''
    This function takes a list of strings that represent either a first or
    last name. 
        Example: ['King', 'Kong', 'Stephen', 'Hawking', ...]

    Your task is to CREATE and RETURN a new SORTED list that contains
    the first and last names concatenated with a space. You 
    can assume that the names_list will follow the pattern of 
    first name, last name, first name, last name etc. 
        Example of returned list:
            ['King Kong', 'Stephen Hawking', ...]
    '''
    result = []
    i = 0
    while i < len(names_list):
        sublist = names_list[i:i+2]
        result.append(" ".join(sublist))
        i += 2
    return sorted(result)

def reverse_name_alter_case(name_str):
    '''
    This function takes in a string that represents a name. The string will
    only consist of a first and last name.

    Your task is to reverse name_str to last name then first and alternate
    the letter cases. 

    This function should return a string.

        Example: 
            name_str = 'Stephen Hawking'

            Function returns: 'HaWkInG StEpHeN'

    Note: the that every word must start with an upper case letter and then
    alternate between upper and lower case.

        Hints: split, list, and join may be useful in this function.

    '''
    first_list = list(name_str.split(" ")[0])
    last_list = list(name_str.split(" ")[1])
    
    for i in range(len(first_list)):
        if i % 2 == 0:
            first_list[i] = first_list[i].upper()
        else:
            first_list[i] = first_list[i].lower()
            
    for i in range(len(last_list)):
        if i % 2 == 0:
            last_list[i] = last_list[i].upper()
        else:
            last_list[i] = last_list[i].lower()
    
    last_name = ''.join(str(char) for char in last_list)
    first_name = ''.join(str(char) for char in first_list)
    
    return last_name + " " + first_name

def organize_symbols(sym_lst):
    ''' 
    This function takes in a list that contains single string elements. Each
    element in the string is a symbol.

        Example: sym_lst = ['@', '@', '!', '+' , '&']

    You will need to re-arrange the mixed up list based on the given order.

        order = "!@#$%^&*()_+"  #This is given bellow

        Example:

            sym_lst = ['@', '@', '!', '+' , '&']

            After function execution: 

                sym_lst = ['!', '@', '@', '&', '+']


    This function shouldn't return anything, alter the list in place. 


    Hint: Will be useful to use clear() and extend() 
    '''

    order = "!@#$%^&*()_+" # this the symbol order you must follow this order when 
                           # organizing
    result = []
    for sym in list(order):
        for ele in sym_lst:
            if ele == sym:
                result.append(ele)
    
    sym_lst.clear()
    sym_lst.extend(result)

def find_the_smiley(stringy, key ):
    '''
    This function take in two strings. This first parameter, stringy contains a 
    random sequence of text emoji smilies. The second parameter, key, will consist
    of a singe emoji smiley which will serve as your key.

    Given a string with smilies count how many times the key smiley appears in 
    the string

    Example:

        stringy = ":):):(:(:P:$:]"
        key =  ":)"

        function returns: 2
    '''
    count = 0
    for i in range(len(stringy)):
        if stringy[i:i + len(key)] == key:
            count += 1
            
    return count

def sum_main_diagonal(LOL):
    '''
    This function takes in a lists of lists as its sol argument. 

    Your task is to PRINT the sum of the main diagonal.

    Example LOL = [[5, 0, 1],    
                   [1, 9, 3],
                   [3, 8, 2]]

        Function prints: 16
    '''
    sum = 0
    for i in range(len(LOL)):
        sum += LOL[i][i]
    
    print (sum)

def organize_list_of_lists(LOL):
    '''
    This function takes in a lists of lists as its sol argument. 

    Organize the list of lists. Order the inner list in ascending order,
    then re-arrange the list of list based on the first of the element in 
    ascending.

    Return None, use clear and extend if you want but do you have to?  

    Example LOL = [[5, 0, 1],    
                   [1, 9, 3],
                   [3, 8, 2]]

            would result in 

            LOL = [[0, 1, 5],
                   [1, 3, 9],    
                   [2, 3, 8]]
    '''
    for i in range(len(LOL)):
        LOL[i].sort()
    
    for i in range(1, len(LOL)):
            j = i
            while j > 0 and LOL[j][0] < LOL[j-1][0]:
                LOL[j], LOL[j-1] = LOL[j-1], LOL[j]
                j -= 1
    
    return None
    
def main():
    '''
    test your functions in main
    '''
    nums = [ 1, 2, 45, 2002, 3]


    names = ['King', 'Kong', 'Stephen', 'Hawking', 'Beyonce', 'Knowles', \
              'Henry', 'Cavill', 'Chuck', 'Norris', 'Bruce', 'Lee']

    syms = ['@', '@', '!', '+' , '&']

    sm= ":):):(:(:P:$:]"
    key=  ":)"

    LOL = [[1,2,3], [2,9,4], [1,90,5]]
    
    
    
    


if __name__ == '__main__':
    main()