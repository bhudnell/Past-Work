from dictionaryFunctions import *
import unittest

class TestFns(unittest.TestCase):
        
    def test_ltranslate(self):
        d = {0:False, 1:-1, 'big':'little'}
        l = [0, False, 1, -1, 'big', 'little']
        m = [False, False, -1, -1, 'little', 'little']
        ltranslate(l, d)
        self.assertEqual(m, l)
        ltranslate(l, {})
        self.assertEqual(m, l)
        d = {False:77, -1:-7, 'little':'big'}
        m = [77, 77, -7, -7, 'big', 'big']
        ltranslate(l, d)
        self.assertEqual(m, l)
        d = {'cat':'shark', 'dog':'cat'}
        l = ['cat', 'dog', 'cat', 'dog']
        m = ['shark', 'cat', 'shark', 'cat']
        ltranslate(l, d)
        self.assertEqual(m, l)
        d = {'cat':'dog', 'dog':'shark'}
        l = ['cat', 'dog', 'cat', 'dog']
        m = ['dog', 'shark', 'dog', 'shark']
        ltranslate(l, d)
        self.assertEqual(m, l)
        
    def test_word_count(self):
         self.assertEqual({}, word_count('wc1.txt'))
         self.assertEqual({}, word_count('wc5.txt'))
         self.assertEqual({'a':1}, word_count('wc2.txt'))
         self.assertEqual({'a':1}, word_count('wc3.txt'))
         wc = word_count('wc4.txt')
         self.assertEqual({'a':5, 'cat':2, 'dog':5}, wc)
         wc = word_count('wc1.txt')
         wc = word_count('wc5.txt', wc)
         self.assertEqual({}, wc)
         wc = word_count('wc2.txt', wc)
         self.assertEqual({'a':1}, wc)
         wc = word_count('wc3.txt', wc)
         self.assertEqual({'a':2}, wc)
         wc = word_count('wc4.txt', wc)
         self.assertEqual({'a':7, 'cat':2, 'dog':5}, wc)
         wc = word_count('wc4.txt', wc)
         self.assertEqual({'a':12, 'cat':4, 'dog':10}, wc)
    
    def test_average_wc(self):
         self.assertEqual(0, average_wc(word_count('wc1.txt')))
         self.assertEqual(0, average_wc(word_count('wc5.txt')))
         self.assertEqual(1, average_wc(word_count('wc2.txt')))
         self.assertEqual(1, average_wc(word_count('wc3.txt')))
         wc = word_count('wc4.txt')
         self.assertEqual(4, average_wc(wc))
         wc = word_count('wc2.txt')
         wc = word_count('wc3.txt', wc)
         self.assertEqual(2, average_wc(wc))
         wc = word_count('wc4.txt', wc)
         self.assertEqual(14 / 3, average_wc(wc))
         wc = word_count('wc4.txt', wc)
         self.assertEqual(26 / 3, average_wc(wc))
         
    def test_max_wc(self):
         self.assertEqual(0, max_wc(word_count('wc1.txt')))
         self.assertEqual(0, max_wc(word_count('wc5.txt')))
         self.assertEqual(1, max_wc(word_count('wc2.txt')))
         self.assertEqual(1, max_wc(word_count('wc3.txt')))
         wc = word_count('wc4.txt')
         self.assertEqual(5, max_wc(wc))
         wc = word_count('wc2.txt')
         wc = word_count('wc3.txt', wc)
         self.assertEqual(2, max_wc(wc))
         wc = word_count('wc4.txt', wc)
         self.assertEqual(7, max_wc(wc))
         wc = word_count('wc4.txt', wc)
         self.assertEqual(12, max_wc(wc))
    
    def test_dreverse(self):
        self.assertEqual({}, dreverse({}))
        self.assertEqual({'dog':['cat']}, dreverse({'cat':'dog'}))
        d = {'dog':'cat', 7:33, 19:33, 'eel':'cat', 'fish':33}
        # Turn the lists into sets since order may differ
        dr = {key: set(value) for (key, value) in dreverse(d).items()}
        e = {'cat':{'dog', 'eel'}, 33:{'fish', 7, 19}}
        self.assertEqual(e, dr)
        
    def test_bird_weights(self):
        self.assertEqual({'Bluebird':[78.3]}, bird_weights('bc1.txt'))
        bc = {'Bluebird':[78.3, 89.3, 77.0, 69.9, 91.9],
              'Tanager':[111.9, 107.65, 108.0, 110.0]}
        self.assertEqual(bc, bird_weights('bc2.txt'))
          
    def test_median(self):
        self.assertIsNone(median([]))
        self.assertEqual(57, median([57]))
        self.assertEqual(-2, median([-1, -3]))
        l = [-1, -3, -2]
        self.assertEqual(-2, median(l))
        self.assertEqual([-1, -3, -2], l)
        l = [-1, -3, -2.5, -1.5]
        self.assertEqual(-2, median(l))
        self.assertEqual([-1, -3, -2.5, -1.5], l)
        l = [57, 63.7, 78.8, 57, 56.6]
        self.assertEqual(57, median(l))
        self.assertEqual([57, 63.7, 78.8, 57, 56.6], l)
        l = [111.9, 107.65, 108.0, 110.0]
        self.assertEqual(109, median(l))       
        
    def test_median_bird_weights(self):
        self.assertEqual({'Bluebird':78.3}, median_bird_weights(bird_weights('bc1.txt')))
        bc = {'Bluebird':[78.3, 89.3, 77.0, 69.9, 91.9],
              'Tanager':[111.9, 107.65, 108.0, 110.0]}
        self.assertEqual({'Bluebird':78.3, 'Tanager':109}, median_bird_weights(bc))
        bc = {'Bluebird':[78.3, 89.3, 77.0],
              'Tanager':[111.9, 110.0]}
        self.assertEqual({'Bluebird':78.3, 'Tanager':110.95}, median_bird_weights(bc))
        self.assertEqual([78.3, 89.3, 77.0], bc['Bluebird'])
        self.assertEqual([111.9, 110.0], bc['Tanager'])

def main():
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestFns)
    results = unittest.TextTestRunner().run(test)
    print('Correctness score = ', str((results.testsRun - len(results.errors) - len(results.failures)) / results.testsRun * 100) + '%')

if __name__ == "__main__":
    main()
