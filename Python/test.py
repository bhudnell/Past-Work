def is_palindrome(str):
    """
    Recursive function to test if a string is a palindrome. Spaces, capitalization,
    and punctuation will not affect whether or not a string is a palindrome.

    str: a string

    returns: True if the string is a palindrome, False otherwise
    """
    import string

    #removes punctuation and whitespace from string
    temp=''
    for char in str:
        if char not in string.punctuation and char not in string.whitespace: 
            temp += char

    #changes all characters to lowercase so capitalization won't mess up palindrome
    test = temp.lower()

    if len(test) <= 1:
        return True
    if test[0] == test[-1]:
        return is_palindrome(test[1:len(test)-1])
    return False