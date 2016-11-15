def antennae(ants):
    """
    You see a bunch of ants marching in a single file line. You notice that all 
    even positioned ants (2, 4 ...) have two antennae and all odd positioned 
    ants (1, 3...) have three antennae.

    Recursively compute and return the total number of antennae from the total
    number of ants. 

        Example Outputs:

            antennae(0) yields 0
            antennae(1) yields 3
            antennae(2) yields 5  
    """
    if ants == 0:
        return 0
    if ants%2 == 0:
        return 2 + antennae(ants-1)
    if ants%2 == 1:
        return 3 + antennae(ants-1)

def pyramid(levels):
    """
    You are given a pyramid made of blocks. The first level (top) has one block,
    the second level has 4 blocks, third level has 9 blocks, fourth level has 16 
    ... and so on. 

    Write a recursive function that will count and return the total number
    blocks a pyramid has based on the number of levels it has.

        Example Outputs:
        
            pyramid(0) yields 0
            pyramid(1) yields 1
            pyramid(2) yields 5
            pyramid(8) yields 204
    """
    if levels == 0:
        return 0;
    return levels**2 + pyramid(levels-1)

def sum_digits(n):
    """
    You are given a non-negative integer n, write a recursive functions that 
    returns the sum of its digits.

        Example Outputs:
        
            sum_digits(23) yields 5
            sum_digits(111111) yields 6
            sum_digits(97) yields 16
            sum_digits(145) yields 10
    """
    if n == 0:
        return 0
    return n%10 + sum_digits(n//10)

def length(element):
    """
    This function takes one parameter, element. It can either be a string or
    a list.

    Recursively compute and return the length of element.

        Example Outputs:
        
            length("     ") yields 5
            length("hey") yields 3
            length("") yields 0
            length([]) yields 0
            length([34]) yields 1
            length([0, 11, 23, 23]) yields 4    
    """
    if not element:
        return 0
    return 1 + length(element[1:])

def count_sup(string):
    """
    You are given a String that contains the may or may not contain the word 
    "sup" in it. 

    Your task is to recursively compute the number of times the word "sup"
    appears in the given string. Note that "sup" does not have to be lower
    cased to be counted.

        Example Outputs:
        
            count_sup("supabcd") yields 1
            count_sup("supabcd SUp the anajdsup 99dhtsup") yields 4
            count_sup("SuP") yields 1
            count_sup("xxxxx") yields 0
    """
    if not string:
        return 0
    if string[0:3].lower() == 'sup':
        return 1 + count_sup(string[3:])
    return count_sup(string[1:])
    
def make_list(n):
    """
    This function takes a single non-negative integer as an argument, n.

    Recursively produce and return a list of all the integers between 1 and
    the argument.

        Example Outputs:
        
            make_list(0) yields []
            make_list(3) yields [1, 2, 3]
            make_list(9) yields [1, 2, 3, 4, 5, 6, 7, 8, 9]

    """
    if n < 1:
        return []
    if n == 1:
        return [1]
    return make_list(n-1) + [n]

def member(item, lst):
    """
    This function takes two arguments item and lst. Note lst is a list that
    can contain elements of any type and item can be any type as well. 
    Type as in int, str, tuple ... etc.

    Your task is to recursively determine if item is in lst.

    The function returns False if item is not in lst, otherwise
    return a sublist starting with the item.

        Example Outputs:
        
            member(3, []) yields False
            member(3, [1, 2, 3, 4, 5]) yields False [3, 4, 5]
            member(5, [3, 4, 5, 5, 3]) yields False [5, 5, 3]
    """
    if not lst:
        return False
    if lst[0] == item:
        return lst
    return member(item, lst[1:])

def one(string):
    """
    You are given a string.

    Recursively create and return a string in which all appearances of "one" 
    have been replaced by "111" , note that the letter cases in "one" do not
    matter.

        Example Output:
            one("one") yields "111"
    """
    if not string:
        return ''
    if string[0:3].lower() == 'one':
        return '111' + one(string[3:])
    return string[0] + one(string[1:])

def stars(string):
    """
    You are given a string.

    Recursively create and return a string in which a star "*" is inserted
    in between each character is the string.

        Example Output:

            stars("hello") yields "h*e*l*l*o"

    """
    if len(string) <= 1:
        return string
    return string[0] + '*' + stars(string[1:])

def parens(string):
    """
    You are given a string that contains a single pair of parenthesis.
    
    Recursively compute a new string only of the parenthesis and their 
    contents.

        Example Outputs:
        
            parens("xyz(a)123") yields "(a)"
            parens("()123") yields "()"
            parens("(28282)") yields "(28282)"
    """
    if string[0] == '(' and string[-1] == ')':
        return string
    if string[0] == '(' and string[-1] != ')':
        return parens(string[:-1])
    if string[0] != '(' and string[-1] == ')':
        return parens(string[1:])
    return parens(string[1:-1])

def main():
    """
    Test your code.
    """

    print(antennae(5))
if __name__ == '__main__':
     main() 
