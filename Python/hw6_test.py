import unittest, numpy as np
from hw6 import *

def make_flist(arr):
    return [round(x, 5) for x in arr]
        
class TestFns(unittest.TestCase):
    def setUp(self):
        self.a1 = np.arange(1, 2.1, .2)
        self.a2 = np.linspace(1, 2, num=6)
        self.a3 = np.empty(5, str)
        self.a4 = np.zeros(3)
        self.a5 = np.array(["", "", "", "", ""])
        self.a6 = np.array([1, 1.2, 1.4, 1.6, 1.8, 2])
        self.a6d = np.array([2, 2.4, 2.8, 3.2, 3.6, 4])
        self.a6c0 = np.array([1, 1.2, 1.4, 1.6, 1.8, 2])
        self.a6c1 = np.array([2, 1, 1.2, 1.4, 1.6, 1.8])
        self.a6c4 = np.array([1.4, 1.6, 1.8, 2, 1, 1.2])
        self.a6r = np.array([2, 1.8, 1.6, 1.4, 1.2, 1])
        self.a7 = np.array([1])
        self.a7oem = np.array([1])
        self.a8 = np.arange(1, 10)
        self.a8a6 = np.array([2, 3.2, 4.4, 5.6, 6.8, 8, 7, 8, 9])
        self.a8i1 = np.array([1, 2, -5, 4, 5, 6, 7, 8, 9])
        self.a8i2 = np.array([1, 2, -5, 3, 4, 5, 6, 7, 8])
        self.a8sw08 = np.array([9, 2, 3, 4, 5, 6, 7, 8, 1])
        self.a8sw23 = np.array([9, 2, 4, 3, 5, 6, 7, 8, 1])
        self.a8se = np.array([1, 4, 3, 16, 5, 36, 7, 64, 9])
        self.a8oem = np.array([1, 0, 1, 0, 1, 0, 1, 0, 1])
        self.a9 = np.array([2])
        self.a9oem = np.array([0])
    
    @staticmethod
    def make_flist(arr):
        return [round(x, 5) for x in arr]
    
    def test_demo(self):
        self.assertEqual([1, 1.2, 1.4, 1.6, 1.8, 2], TestFns.make_flist(self.a1))
        self.assertFalse(all(self.a1 == self.a6))
        self.assertTrue(TestFns.make_flist(self.a1) == TestFns.make_flist(self.a6))
        self.assertTrue(all(self.a2 == self.a6))
        self.assertFalse(all(self.a1 == self.a2)) #!!! round-off issues
        self.assertTrue(TestFns.make_flist(self.a1) == TestFns.make_flist(self.a2))
        self.assertFalse(all(self.a1 == self.a7))
        
    def test_reverse(self):
        a6copy = self.a6.copy()
        self.assertTrue(all(self.a6 == reverse(self.a6r)))
        self.assertTrue(all(a6copy == self.a6))
        self.assertTrue(all(self.a4 == reverse(self.a4)))
        self.assertTrue(all(self.a7 == reverse(self.a7)))

    def test_odd_even_mask(self):
        self.assertTrue(all(self.a7oem == self.a7))
        a8copy = self.a8.copy()
        self.assertTrue(all(self.a8oem == odd_even_mask(self.a8)))
        self.assertTrue(all(a8copy == self.a8))
        self.assertTrue(all(self.a9oem == odd_even_mask(self.a9)))

    def test_cycle(self):
        a6copy = self.a6.copy()
        self.assertTrue(all(self.a6c0 == cycle(self.a6, 0)))
        self.assertTrue(all(self.a6c0 == cycle(self.a6, 30)))
        self.assertTrue(all(a6copy == self.a6))
        self.assertTrue(all(self.a6c1 == cycle(self.a6, 1)))
        self.assertTrue(all(self.a6c1 == cycle(self.a6, 13)))
        self.assertTrue(all(self.a6c4 == cycle(self.a6, 4)))
        self.assertTrue(all(self.a6c4 == cycle(self.a6, 28)))
        
    def test_double(self):
        a6copy = self.a6.copy()
        self.assertTrue(all(self.a6d == double(self.a6)))
        self.assertTrue(all(a6copy == self.a6))
        
    def test_double_ip(self):
        self.assertIsNone(double_ip(self.a6))
        self.assertTrue(all(self.a6d == self.a6))
        
    def test_square_evens(self):
        self.assertIsNone(square_evens(self.a8))
        self.assertTrue(all(self.a8se == self.a8))
        self.a9copy = self.a9.copy()
        self.assertTrue(all(self.a9copy == self.a9))
        
    def test_binary_search(self):
        a8copy = self.a8.copy()
        self.assertEqual(-1, binary_search(45, self.a8))
        self.assertEqual(0, binary_search(1, self.a8))
        self.assertEqual(4, binary_search(5, self.a8))
        self.assertEqual(5, binary_search(6, self.a8))
        self.assertEqual(8, binary_search(9, self.a8))
        self.assertTrue(all(a8copy == self.a8))
        
    def test_insert(self):
        self.assertRaises(IndexError, insert, self.a8, 33, -5, False)
        self.assertRaises(IndexError, insert, self.a8, 33, -5, True)
        self.assertIsNone(insert(self.a8, 2, -5, True))
        self.assertTrue(all(self.a8i1 == self.a8))
        self.assertIsNone(insert(self.a8, 3, 3, False))
        self.assertTrue(all(self.a8i2 == self.a8))
        
    def test_swap(self):
        self.assertIsNone(swap(self.a8, 0, 8))
        self.assertTrue(all(self.a8sw08 == self.a8))
        self.assertIsNone(swap(self.a8, 3, 2))
        self.assertTrue(all(self.a8sw23 == self.a8))
        
    def test_add_arrays(self):
        a8copy = self.a8.copy()
        self.assertTrue(all(self.a8a6 == add_arrays(self.a8, self.a6)))
        self.assertTrue(all(self.a8a6 == add_arrays(self.a6, self.a8)))
        self.assertTrue(all(a8copy == self.a8))
        
def main():
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestFns)
    results = unittest.TextTestRunner().run(test)
    tests_run = results.testsRun
    failures = len(results.failures)
    errors = len(results.errors)
    score = (tests_run - errors - failures) / tests_run * 100
        
    print('Correctness score = ', str(score) + '%')
        
if __name__ == "__main__":
    main()