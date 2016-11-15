import unittest, sys, io, os
from contextlib import redirect_stdout
from lab1 import *
from unittest.mock import patch



class testLab1(unittest.TestCase):

    def setUp(self):
        self.syms = ['@', '@', '!', '+' , '&']
        self.LOL = [[1,2,3], [2,9,4], [1,90,5]]
        self.LOL2 = [[5, 0, 1], [3, 8, 2], [1, 9, 3]]
        self.lol2ans = [[0, 1, 5], [1, 3, 9], [2, 3, 8]]


    def test_largest_num(self):
        
        with io.StringIO() as buf, redirect_stdout(buf):
            self.assertIsNone(largest_num([ 1, 2, 45, 2002, 3]))
            self.assertEqual('2002\n', buf.getvalue())

    def test_concat_name(self):
        names = ['King', 'Kong', 'Stephen', 'Hawking', 'Beyonce', 'Knowles', \
              'Henry', 'Cavill', 'Chuck', 'Norris', 'Bruce', 'Lee']
        ans = ['Beyonce Knowles', 'Bruce Lee', 'Chuck Norris', 'Henry Cavill', \
                'King Kong', 'Stephen Hawking']
        self.assertIsNotNone(concat_name(names))
        self.assertEqual(ans, concat_name(names))

    def test_reverse_name_alter_case(self):
        self.assertEqual('HaWkInG StEpHeN' ,reverse_name_alter_case('Stephen Hawking'))

    def test_organize_symbols(self):
        self.assertIsNone(organize_symbols(self.syms))
        self.assertEqual(self.syms, ['!', '@', '@', '&', '+'])

    def test_find_the_smiley(self):
        self.assertIsNotNone(find_the_smiley(":):):(:(:P:$:]", ":)"))
        self.assertEqual(2,find_the_smiley(":):):(:(:P:$:]", ":)"))

    def test_sum_main_diagonal(self):
        with io.StringIO() as buf, redirect_stdout(buf):
            self.assertIsNone(sum_main_diagonal(self.LOL))
            self.assertEqual('15\n', buf.getvalue())

    def test_organize_list_of_lists(self):
        self.assertIsNone(organize_list_of_lists(self.LOL2))
        self.assertEqual(self.LOL2, self.lol2ans)


test = unittest.defaultTestLoader.loadTestsFromTestCase(testLab1)
results = unittest.TextTestRunner().run(test)
sys.stdout = sys.__stdout__
sys.stdin = sys.__stdin__

print('Correctness score = ', str((results.testsRun - len(results.errors) - len(results.failures)) / results.testsRun * 100) + '%')