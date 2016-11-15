import unittest, sys, io
from lab12 import *


def check_solutions():
    in_file = open("lab12.py", 'r')

    contents = in_file.read()

    contents = contents[:contents.index("def main()")]

    # start = contents.index("def antennae(")
    funcs = ["antennae(", "pyramid(", "sum_digits(", "length(", "count_sup(", \
             "make_list(", "member(", "one(", "stars(", "parens("]

    func_count = []
    for i in range(10):
        start = contents.index('"""')
        contents = contents[start+3:]
        end = contents.index('"""')
        contents = contents[end+3:]

        func_count.append(contents.count(funcs[i]))
    in_file.close()
    
    return func_count

class TestLab12(unittest.TestCase):

    def setUp(self):
        self.results = check_solutions()

    def test_atennae(self):
        self.assertEqual(0, antennae(0))
        self.assertEqual(3, antennae(1))
        self.assertEqual(5, antennae(2))
        self.assertTrue(self.results[0] >= 1)
        

    def test_pyramid(self):
        self.assertEqual(0, pyramid(0))
        self.assertEqual(1, pyramid(1))
        self.assertEqual(5, pyramid(2))
        self.assertEqual(55, pyramid(5))
        self.assertEqual(204, pyramid(8))
        self.assertTrue(self.results[1] >= 1)

    def test_sum_digits(self):
        self.assertEqual(5, sum_digits(23))
        self.assertEqual(7, sum_digits(1111111))
        self.assertEqual(16, sum_digits(97))
        self.assertEqual(10, sum_digits(145))
        self.assertTrue(self.results[2] >= 1)

    def test_length(self):
        self.assertEqual(5, length("     "))
        self.assertEqual(3, length("hey"))
        self.assertEqual(0, length(""))
        self.assertEqual(0, length([]))
        self.assertEqual(1, length([32]))
        self.assertEqual(4, length([0, 11, 23, 23]))
        self.assertTrue(self.results[3] >= 1)

    def test_count_sup(self):
        self.assertEqual(1, count_sup("supabcd"))
        self.assertEqual(4, count_sup("supabcd SUp the anajdsup 99dhtsup"))
        self.assertEqual(1, count_sup("SuP") )
        self.assertEqual(0, count_sup("xxxxxxxxx"))
        self.assertTrue(self.results[4] >= 1)


    def test_make_list(self):
        self.assertEqual([], make_list(0))
        self.assertEqual([1, 2, 3], make_list(3))
        self.assertEqual([1, 2, 3, 4, 5, 6, 7, 8, 9], make_list(9))
        self.assertEqual([x for x in range(1, 21)], make_list(20))
        self.assertEqual([x for x in range(1, 300)], make_list(299))
        self.assertTrue(self.results[5] >= 1)


    def test_member(self):
        self.assertEqual([3, 4, 5],  member(3, [1, 2, 3, 4, 5]))
        self.assertEqual([5, 5, 3],  member(5, [3, 4, 5, 5, 3]))
        self.assertEqual(["a"],  member("a", ["a"]))
        self.assertFalse(member(3, []))
        self.assertTrue(self.results[6] >= 1)


    def test_one(self):
         self.assertEqual("111", one("one"))
         self.assertEqual("111 111", one("one one"))
         self.assertEqual("o ne", one("o ne"))
         self.assertEqual("111111111hi111hihi", one("oneONEoNEhioNehihi"))
         self.assertEqual("", one(""))
         self.assertTrue(self.results[7] >= 1)

    def test_stars(self):
        self.assertEqual("h*e*l*l*o", stars("hello"))
        self.assertEqual("h", stars("h"))
        self.assertEqual("", stars(""))
        self.assertEqual("h*e", stars("he"))
        self.assertTrue(self.results[8] >= 1)

    def test_parens(self):
        self.assertEqual("(a)", parens("xyz(a)123"))
        self.assertEqual("()", parens("()123"))
        self.assertEqual("(28282)", parens("(28282)"))
        self.assertTrue(self.results[9] >= 1)
  


test = unittest.defaultTestLoader.loadTestsFromTestCase(TestLab12)
results = unittest.TextTestRunner().run(test)
sys.stdout = sys.__stdout__
sys.stdin = sys.__stdin__

print('Correctness score = ', str((results.testsRun - len(results.errors) - len(results.failures)) / results.testsRun * 100) + '%')