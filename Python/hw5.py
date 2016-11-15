"""
Brendon Hudnell
Section Leader: Will Zielinski
3/2/16
ISTA 350 Hw5

Contains the Binary class, which contains operator overload magic methods used to do 
basic binary integer arithmetic. 
"""
class Binary:
    def __init__(self, bin_string=""):
        """
        Takes a string of no more than 16 bits, converts it to a list, then pads the list to 16
        elements by repeating the leftmost digit.

        bin_string: a string of up to 16 bits. (Only numbers 0 and 1)
        """
        if len(bin_string) > 16:
            raise RuntimeError ("Binary: string longer than 16 bits")
        for char in bin_string:
            if char != '0' and char != '1':
                raise RuntimeError ("Binary: string contains values outside 0 and 1")

        self.num_list = list(bin_string)
        for i in range(len(self.num_list)):
            self.num_list[i] = int(self.num_list[i])
        self.pad()

    def pad(self):
        """
        Pads num_list by repeating the leftmost digit until it contains 16 elements.
        """
        pad_num = self.num_list[0] if len(self.num_list) > 0 else 0
        while len(self.num_list) < 16:
            self.num_list = [pad_num] + self.num_list

    def __repr__(self):
        """
        Shows how the Binary class instance is represented when printed.
        """
        string = ""
        for num in self.num_list:
            string += str(num)
        return string

    def __add__(self,other):
        """
        Overloads the + operator to add two binary numbers together.

        other: the Binary instance to be added to self

        returns: the sum of the two Binary instances added together
        """
        result = []
        carry = 0
        for i in range(15,-1,-1):
            bit_sum = self.num_list[i] + other.num_list[i] + carry
            result.insert(0, bit_sum%2)
            carry = int(bit_sum>1)
        if self.num_list[0] == other.num_list[0] != result[0]:
            raise RuntimeError ("Binary: overflow")
        string = ""
        for num in result:
            string += str(num)
        return Binary(string)

    def __neg__(self):
        """
        Returns a new Binary instance equal to -self
        """
        string = ""
        for i in range(len(self.num_list)):
            string += str((self.num_list[i] + 1)%2)
        return (Binary(string) + Binary("01"))

    def __sub__(self, other):
        """
        Overloads the - operator to subtract one binary number from another.

        other: the Binary instance to be subtracted from self

        returns: the difference of the two Binary instances
        """
        result = []
        carry = 0
        for i in range(15,-1,-1):
            bit_diff = (self.num_list[i] - other.num_list[i]) - carry
            result.insert(0, abs(bit_diff%2))
            carry = int(bit_diff<0)
        if self.num_list[0] != other.num_list[0] and self.num_list[0] != result[0]:
            raise RuntimeError ("Binary: overflow")
        string = ""
        for num in result:
            string += str(num)
        return Binary(string)

    def __int__(self):
        """
        Returns the decimal value of the Binary instance.
        """
        sum = 0
        bin_index = 0
        if self.num_list[0] == 1:
            test = -(self + Binary("01"))
            for i in range(15,-1,-1):
                sum += test.num_list[bin_index]*2**i
                bin_index += 1
            sum = -1 - sum
        else:
            test = self
            for i in range(15,-1,-1):
                sum += test.num_list[bin_index]*2**i
                bin_index += 1
        return sum

    def __eq__(self, other):
        """
        Overloads the == operator to determine if two Binary instances are equal.

        other: the Binary instance to be compared to self

        returns: True if self and other are equal, False otherwise
        """
        for i in range(len(self.num_list)):
            if self.num_list[i] != other.num_list[i]:
                return False
        return True

    def __lt__(self, other):
        """
        Overloads the < operator to determine if one Binary instance is less than the other.

        other: the Binary instance to be compared to self

        returns: True if self is less than other, False otherwise
        """
        if self.num_list[0] == 1 and other.num_list[0] == 1:
            for i in range(16):
                if self.num_list[i] > other.num_list[i]:
                    return False
                elif self.num_list[i] < other.num_list[i]:
                    return True
            return False

        elif self.num_list[0] == 1 and other.num_list[0] == 0:
            return True
            
        elif self.num_list[0] == 0 and other.num_list[0] == 1:
            return False

        else:
            for i in range(16):
                if self.num_list[i] < other.num_list[i]:
                    return True
            return False

    def __abs__(self):
        """
        Returns a new Binary instance that is the absolute value of self
        """
        if self.num_list[0] == 0:
            return Binary(repr(self))
        return Binary(repr(-self))