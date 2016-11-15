from hw9 import *
import unittest, json, filecmp, sys, io, hw9
from contextlib import redirect_stdout
from compare_files import compare_files
from compare_pandas import compare_frames
import matplotlib.pyplot as plt
from unittest.mock import patch

''' 
Files needed:
'compare_files.py', 'compare_pandas.py'
'wrcc_pcpn_panda.txt','wrcc_mint_panda.txt', 'wrcc_maxt_panda.txt'
'wrcc_pcpn_stats.txt','wrcc_mint_stats.txt', 'wrcc_maxt_stats.txt'
'wrcc_pcpn_smooth.txt','wrcc_mint_smooth.txt', 'wrcc_maxt_smooth.txt'
'wrcc_pcpn_correct.json','wrcc_mint_correct.json', 'wrcc_maxt_correct.json'
'wrcc_pcpn_smooth.json','wrcc_mint_smooth.json', 'wrcc_maxt_smooth.json'

This is what your output should look like:
'pcpn_all.png','mint_all.png','maxt_all.png','pcpn_jan.png'

Also provided:
'wrcc_pcpn.json', 'wrcc_maxt.json', 'wrcc_mint.json'
These are provided so that hw9.py can run correctly, in case
students failed to get hw8 to create them correctly.

No longer used:
'pcpn_all2.png','mint_all2.png','maxt_all2.png','pcpn_jan2.png'
'pcpn_all3.png','mint_all3.png','maxt_all3.png','pcpn_jan3.png'
'pcpn_all4.png','mint_all4.png','maxt_all4.png','pcpn_jan4.png'
'''

def load_panda(fname=None, txt=None):
    if fname:
        txt = open(fname).read()
    txt = txt.split('\n')
    return [line.split() for line in txt]

class TestFns(unittest.TestCase):
    '''
    def test_get_panda(self):
        # fails on some machines, apparently due to the
        # way repr varies when abridging data
        self.assertEqual(open('wrcc_pcpn_panda.txt').read(), repr(get_panda('wrcc_pcpn_correct.json')))     
        self.assertEqual(open('wrcc_mint_panda.txt').read(), repr(get_panda('wrcc_mint_correct.json')))     
        self.assertEqual(open('wrcc_maxt_panda.txt').read(), repr(get_panda('wrcc_maxt_correct.json')))     
    '''

    def test_get_panda(self):
        self.assertEqual(load_panda('wrcc_pcpn_panda.txt'), load_panda(txt=repr(get_panda('wrcc_pcpn_correct.json'))))
        self.assertEqual(load_panda('wrcc_mint_panda.txt'), load_panda(txt=repr(get_panda('wrcc_mint_correct.json'))))
        self.assertEqual(load_panda('wrcc_maxt_panda.txt'), load_panda(txt=repr(get_panda('wrcc_maxt_correct.json'))))
        
    def test_get_stats(self):
        '''
        this test will fail if get_panda doesn't work
        '''
        self.assertEqual(open('wrcc_pcpn_stats.txt').read(), repr(get_stats(get_panda('wrcc_pcpn_correct.json'))))     
        self.assertEqual(open('wrcc_mint_stats.txt').read(), repr(get_stats(get_panda('wrcc_mint_correct.json'))))     
        self.assertEqual(open('wrcc_maxt_stats.txt').read(), repr(get_stats(get_panda('wrcc_maxt_correct.json'))))     
    
    def test_print_stats(self):
        '''
        this test will fail if either get_panda or get_stats doesn't work
        '''
        with io.StringIO() as buf, redirect_stdout(buf):
            print_stats('wrcc_pcpn_correct.json')
            self.assertEqual('----- Statistics for wrcc_pcpn_correct.json -----\n\n' + open('wrcc_pcpn_stats.txt').read() + '\n\n',
                buf.getvalue())
        with io.StringIO() as buf, redirect_stdout(buf):
            print_stats('wrcc_mint_correct.json')
            self.assertEqual('----- Statistics for wrcc_mint_correct.json -----\n\n' + open('wrcc_mint_stats.txt').read() + '\n\n',
                buf.getvalue())
        with io.StringIO() as buf, redirect_stdout(buf):
            print_stats('wrcc_maxt_correct.json')
            self.assertEqual('----- Statistics for wrcc_maxt_correct.json -----\n\n' + open('wrcc_maxt_stats.txt').read() + '\n\n',
                buf.getvalue())
    
    def test_smooth_data(self):
        '''
        this test will fail if get_panda doesn't work
        '''
        # add a test for the default value of precision
        self.assertTrue(compare_frames(get_panda('wrcc_pcpn_smooth.json'), smooth_data(get_panda('wrcc_pcpn_correct.json'), 2)))     
        self.assertTrue(compare_frames(get_panda('wrcc_mint_smooth.json'), smooth_data(get_panda('wrcc_mint_correct.json'), 2)))     
        self.assertTrue(compare_frames(get_panda('wrcc_maxt_smooth.json'), smooth_data(get_panda('wrcc_maxt_correct.json'), 2)))    
        '''
        self.assertEqual(open('wrcc_pcpn_smooth.txt').read(), repr(smooth_data(get_panda('wrcc_pcpn_correct.json'))))     
        self.assertEqual(open('wrcc_mint_smooth.txt').read(), repr(smooth_data(get_panda('wrcc_mint_correct.json'))))     
        self.assertEqual(open('wrcc_maxt_smooth.txt').read(), repr(smooth_data(get_panda('wrcc_maxt_correct.json'))))     
        '''
    """
    def test_make_plot(self):
        '''
        this test will fail if get_panda doesn't work
        '''
        # add a test for the default value of precision
        correct = get_panda('wrcc_pcpn_smooth.json')
        with patch('hw9.smooth_data', side_effect=[correct]):
            make_plot('wrcc_pcpn.json', precision=2)
            plt.savefig('plot.png')
            self.assertTrue(compare_files('pcpn_all.png', 'plot.png', True) or \
                            compare_files('pcpn_all2.png', 'plot.png', True) or \
                            compare_files('pcpn_all3.png', 'plot.png', True) or \
                            compare_files('pcpn_all4.png', 'plot.png', True)
                            )
        correct = get_panda('wrcc_mint_smooth.json')
        with patch('hw9.smooth_data', side_effect=[correct]):
            make_plot('wrcc_mint.json', precision=2)
            plt.savefig('plot.png')
            self.assertTrue(compare_files('mint_all.png', 'plot.png', True) or \
                            compare_files('mint_all2.png', 'plot.png', True) or \
                            compare_files('mint_all3.png', 'plot.png', True) or \
                            compare_files('mint_all4.png', 'plot.png', True)
                            )
        correct = get_panda('wrcc_maxt_smooth.json')
        with patch('hw9.smooth_data', side_effect=[correct]):
            make_plot('wrcc_maxt.json', precision=2)
            plt.savefig('plot.png')
            self.assertTrue(compare_files('maxt_all.png', 'plot.png', True) or \
                            compare_files('maxt_all2.png', 'plot.png', True) or \
                            compare_files('maxt_all3.png', 'plot.png', True) or \
                            compare_files('maxt_all4.png', 'plot.png', True)
                            )
        correct = get_panda('wrcc_pcpn_smooth.json')
        with patch('hw9.smooth_data', side_effect=[correct]):
            plt.figure()
            make_plot('wrcc_pcpn.json', 'Jan', 2)
            plt.savefig('plot.png')
            self.assertTrue(compare_files('pcpn_jan.png', 'plot.png', True) or \
                            compare_files('pcpn_jan2.png', 'plot.png', True) or \
                            compare_files('pcpn_jan3.png', 'plot.png', True) or \
                            compare_files('pcpn_jan4.png', 'plot.png', True)
                            )
    """   
def main():
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestFns)
    results = unittest.TextTestRunner().run(test)
    sys.stdout = sys.__stdout__
    print('Correctness score = ', str((results.testsRun - len(results.errors) - len(results.failures)) / results.testsRun * 80) + ' / 80')
    
if __name__ == "__main__":
    main()