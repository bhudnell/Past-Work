from stringAndListFunctions import *
import unittest


class TestFns(unittest.TestCase):
        
    def test_sort_int_string(self):
        self.assertEqual("-17 4 42", sort_int_string("42 4 -17"))
        self.assertEqual("-3 -1", sort_int_string("-1 -3"))
        self.assertEqual("-17 4 42", sort_int_string("\t42   4 -17  \n"))
        self.assertEqual("3 3 3 4 4 4 5 5 5", sort_int_string("5 5 5 4 4 4 3 3 3"))
        self.assertEqual("", sort_int_string(""))
        self.assertEqual("", sort_int_string("\t"))
        self.assertEqual("1", sort_int_string("1"))

    def test_dash_reverse(self):
        self.assertEqual("--1-7- -4- -4-2", dash_reverse("24 4 71-"))
        self.assertEqual("", dash_reverse(""))
        self.assertEqual("\n- -\t", dash_reverse("\t \n"))
        self.assertNotEqual("\t", dash_reverse(""))
        self.assertEqual("I- -h-e-a-r-t- -m-y- -d-o-g-!", dash_reverse("!god ym traeh I"))
        
    def test_xslice_replace(self):
        s = "I heart Rocket!"
        t = "Ixhyazt Rocket!"
        s2 = "I hear"
        t2 = "Ixhyaz"
        s3 = "I he"
        t3 = "AI he"
        t4 = "Ixhy"
        self.assertRaises(ValueError, xslice_replace, s, 1, 5, 2, 'xyz')
        self.assertEqual(t, xslice_replace(s, 1, 6, 2, 'xyz'))
        self.assertEqual(t, xslice_replace(s, 1, 7, 2, 'xyz'))
        self.assertEqual('Ixhyazt1R2c3e4!', xslice_replace(s, 1, 25, 2, 'xyz1234'))
        self.assertRaises(ValueError, xslice_replace, s, 1, 8, 2, 'xyz')
        self.assertEqual(t2, xslice_replace(s2, 1, 6, 2, 'xyz'))
        self.assertRaises(ValueError, xslice_replace, s3, 1, 6, 2, 'xyz')
        self.assertRaises(ValueError, xslice_replace, s3, -1, -6, -2, 'xyz')
        self.assertRaises(ValueError, xslice_replace, s3, 6, 1, 2, 'xyz')
        self.assertEqual('I hexy', xslice_replace(s3, 6, 1, 1, 'xy'))
        self.assertEqual('Iyhx', xslice_replace(s3, -1, -6, -2, 'xy'))
        self.assertEqual(t3, xslice_replace(s3, 0, 0, 1, 'A'))
        self.assertEqual(t4, xslice_replace(s3, 1, 6, 2, 'xy'))

    def test_element_ip_replace(self):
        m = [True, 0, -17, "wtf?", -17, True, True]
        element_ip_replace(m, True)
        n = [None, 0, -17, "wtf?", -17, None, None]
        self.assertEqual(n, m )
        element_ip_replace(m, True)
        self.assertEqual(n, m)
        self.assertIsNone(element_ip_replace(m, True))
        p = []
        element_ip_replace(p, 500)
        self.assertEqual([], p)
        element_ip_replace(p, 500, 222)
        self.assertEqual([], p)
        element_ip_replace(m, None, 55)
        n = [55, 0, -17, "wtf?", -17, 55, 55]
        self.assertEqual(n, m)
        element_ip_replace(m, "wtf?", 53)
        n = [55, 0, -17, 53, -17, 55, 55]
        self.assertEqual(n, m)
    
    def test_element_nl_replace(self):
        l = [True, 0, -17, "wtf?", -17, True, True]
        m = element_nl_replace(l, True)
        n = [None, 0, -17, "wtf?", -17, None, None]
        self.assertEqual(n, m)
        m = element_nl_replace(m, True)
        self.assertEqual(n, m)
        p = element_nl_replace([], 500)
        self.assertEqual([], p)
        p = element_nl_replace(p, 500, 222)
        self.assertEqual([], p)
        m = element_nl_replace(m, None, 55)
        n = [55, 0, -17, "wtf?", -17, 55, 55]
        self.assertEqual(n, m)
        m = element_nl_replace(m, "wtf?", 53)
        n = [55, 0, -17, 53, -17, 55, 55]
        self.assertEqual(n, m)
        
    def test_lreplace(self):
        m = ['a b', ' d ', 12, 'a b', ' d ', 'a b', ' d ', 12]
        lreplace(m, ['a b', ' d ', 12], [11])
        self.assertEqual(m, [11, 'a b', ' d ', 11])
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [88, 777, 777, 66], [5])
        self.assertEqual([777, 777, 777, 88, 777, 777], m)
        lreplace(m, [88, 777, 777], [5])
        self.assertEqual([777, 777, 777, 5], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777, 777], [5])
        self.assertEqual([5, 777, 88, 5], m)
        m = [1, 2, 3, 3]
        lreplace(m, [1, 2, 3], [1, 1, 2])
        self.assertEqual([1, 1, 2, 3], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777, 777], [8, 8, 8, 8])
        self.assertEqual([8, 8, 8, 8, 777, 88, 8, 8, 8, 8], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777, 777], [777])
        self.assertEqual([777, 777, 88, 777], m)
        m = [777, 777, 777, 88, 777, 777]
        m.insert(0, 777)
        lreplace(m, [777, 777], [777])
        self.assertEqual([777, 777, 88, 777], m)
        m = []
        lreplace(m, [], [777])
        self.assertEqual([777], m)
        m = []
        lreplace(m, [], [777, 88])
        self.assertEqual([777, 88], m)
        m = []
        lreplace(m, [])
        self.assertEqual([], m)
        m = []
        lreplace(m, [58])
        self.assertEqual([], m)
        m = []
        lreplace(m, [58], [59])
        self.assertEqual([], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [], [])
        self.assertEqual([777, 777, 777, 88, 777, 777], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777], [])
        self.assertEqual([88], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777, 777], [])
        self.assertEqual([777, 88], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [], [58])
        self.assertEqual([58, 777, 58, 777, 58, 777, 58, 88, 58, 777, 58, 777, 58], m)
        m = [777, 777, 777]
        lreplace(m, [], [58, 59])
        self.assertEqual([58, 59, 777, 58, 59, 777, 58, 59, 777, 58, 59], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, [777, 777])
        self.assertEqual([777, 88], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, m)
        self.assertEqual([], m)
        m = [777, 777, 777, 88, 777, 777]
        lreplace(m, m, [15])
        self.assertEqual([15], m)
        m = [777, 777, 777, 88, 777, 777]
        self.assertIsNone(lreplace(m, [777, 777]))

    def test_list_lt(self):
        self.assertEqual([], list_lt([], []))
        self.assertEqual([False], list_lt([54], [54]))
        self.assertEqual([True], list_lt([54], [55]))
        self.assertEqual([False], list_lt([55], [54]))
        self.assertEqual([False, True], list_lt([54, 45], [45, 54]))
        self.assertEqual([True, False], list_lt(["Abc", "abc"], ['a', 'A']))
        self.assertEqual([True, True], list_lt(["Abc", 3], ['a', 5]))
        self.assertIsNone(list_lt([1, 4, 5], [1, 2]))
        
    def test_sum_of_powers(self):
        self.assertEqual([], sum_of_powers([], []))
        self.assertEqual([6, 14], sum_of_powers([1, 2, 3], [1, 2]))
        self.assertEqual([1], sum_of_powers([1], [100]))
        self.assertEqual([.5 + 1/3, 5], sum_of_powers([4, 9], [-.5, .5]))
        
    def test_trace(self):
        m = [[15, 777, -12],
             [ 5,   4,  13],
             [11,   7,  -1]]
        self.assertEqual(18, trace(m))
        m = [[15.7, 777, -12],
             [   5, 4.8,  13],
             [  11,   7,  -1]]
        self.assertEqual(19.5, trace(m))
        m = [[-15.7, 777, -12],
             [ 5,   -4.8,  13],
             [11,      7,   1]]
        self.assertEqual(-19.5, trace(m))
        m = [[1, 1, 1, 1],
             [1, 1, 1, 1],
             [1, 1, 1, 1],
             [1, 1, 1, 1]]
        self.assertEqual(4, trace(m))
        m = [[13]]
        self.assertEqual(13, trace(m))
        
    def test_str_by_twos(self):
        self.assertEqual(['ab', 'bc', 'cd'], str_by_twos('abcd'))
        self.assertEqual([], str_by_twos(''))
        self.assertEqual([], str_by_twos('a'))
        self.assertEqual(['ab'], str_by_twos('ab'))
        
if __name__ == "__main__":
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestFns)
    results = unittest.TextTestRunner().run(test)
    print('Correctness score = ', str((results.testsRun - len(results.errors) - len(results.failures)) / results.testsRun * 100) + '%')
