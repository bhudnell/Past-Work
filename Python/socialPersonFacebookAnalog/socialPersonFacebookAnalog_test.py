from socialPersonFacebookAnalog import *
import unittest, sys, io
from unittest.mock import patch
from contextlib import redirect_stdout

class TestSocialPerson(unittest.TestCase):
    def setUp(self):
        with patch('builtins.input', 
              side_effect=['Rich', 'Thompson', '5/21', 'rm@g', 'Married']):
            self.sp1 = SocialPerson()
        self.sp2 = SocialPerson('sp2.txt')
        self.sp3 = SocialPerson('sp3.txt')
        self.sp4 = SocialPerson('sp4.txt')
    
    def test_init(self):
        self.assertEqual('Rich Thompson: 5/21, rm@g', repr(self.sp1.me))
        self.assertEqual('Married', self.sp1.status)
        self.assertEqual('Rich Thompson: 5/21, rm@g', repr(self.sp2.me))
        self.assertEqual('Married', self .sp2.status)
        self.assertEqual('Ryan Manner: 2/9, manner@j', repr(self.sp2.friends['manner@j']))
        self.assertEqual('Suse Blues: 11/54, sb@g', repr(self.sp2.friends['sb@g']))
        self.assertEqual('Rich Thompson: 5/21, rm@g', repr(self.sp2.me))
        self.assertEqual('Married', self .sp2.status)
        self.assertEqual('Ryan Manner: 2/9, manner@j', repr(self.sp2.friends['manner@j']))
        self.assertEqual('Suse Blues: 11/54, sb@g', repr(self.sp2.friends['sb@g']))
     
        # test the prompt
        sys.stdin = open('init_in.txt')
        with io.StringIO() as buf, redirect_stdout(buf):
            p = SocialPerson()
            prompts = "Enter person's first name: Enter person's last name: " + \
                      "Enter person's birthday: Enter person's e-mail: Enter my status: "
            self.assertEqual(prompts, buf.getvalue())
        sys.stdin = sys.__stdin__

    def test_friends_str(self):
        self.assertEqual("", self.sp1.friends_str())
        self.assertEqual('1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', self.sp2.friends_str())
        self.assertEqual('1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', self.sp3.friends_str())
        sp4_str = '1)  Ryan Manner: 2/9, manner@j\n' + \
                  '2)  Pete Warrant: 11/51, pw@g\n' + \
                  '3)  Suse Blues: 11/54, sb@g\n'
        self.assertEqual(sp4_str, self.sp4.friends_str())
                      
    def test_repr(self):
        self.assertEqual('---------- me ----------\nRich Thompson: 5/21, rm@g\n' +
          'My status is: Married\n\n------- friends --------\n', repr(self.sp1))
        self.assertEqual('---------- me ----------\nRich Thompson: 5/21, rm@g\n' +
          'My status is: Married\n\n------- friends --------\n' +
          '1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', repr(self.sp2))
        self.assertEqual('---------- me ----------\nRich Thompson: 5/21, rm@g\n' +
          'My status is: Married\n\n------- friends --------\n' +
          '1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', repr(self.sp3))
    
    @patch('builtins.input', side_effect=['John', 'Doe', '9/51', 'jd@g'])
    def test_add_friend(self, mock_input):
        self.sp2.add_friend()
        self.assertEqual('---------- me ----------\nRich Thompson: 5/21, rm@g\n' +
          'My status is: Married\n\n------- friends --------\n' +
          '1)  John Doe: 9/51, jd@g\n2)  Ryan Manner: 2/9, manner@j\n' +
          '3)  Suse Blues: 11/54, sb@g\n', repr(self.sp2))
        
    def test_get_key(self):
        ''' includes testing an input prompt '''
        # test invalid input
        with patch('builtins.input', side_effect=['-1']):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.assertEqual("", self.sp4.get_key()) # should generate invalid key warning
                correct_out = '------- friends --------\n' + \
                              '1)  Ryan Manner: 2/9, manner@j\n' + \
                              '2)  Pete Warrant: 11/51, pw@g\n' + \
                              '3)  Suse Blues: 11/54, sb@g\n' + \
                              '------------------------\n' + \
                              'Not a friend number: -1\n'
                self.assertEqual(correct_out, buf.getvalue())
        with patch('builtins.input', side_effect=['4']):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.assertEqual("", self.sp4.get_key()) # should generate invalid key warning
                correct_out = '------- friends --------\n' + \
                              '1)  Ryan Manner: 2/9, manner@j\n' + \
                              '2)  Pete Warrant: 11/51, pw@g\n' + \
                              '3)  Suse Blues: 11/54, sb@g\n' + \
                              '------------------------\n' + \
                              'Not a friend number: 4\n'
                self.assertEqual(correct_out, buf.getvalue())
                
        # test 0 to cancel and the input prompt
        sys.stdin = open('get_key_in.txt') # can't patch input w/o losing prompt
        with io.StringIO() as buf, redirect_stdout(buf):
            self.assertEqual("", self.sp4.get_key()) # should not generate warning
            correct_out = '------- friends --------\n' + \
                          '1)  Ryan Manner: 2/9, manner@j\n' + \
                          '2)  Pete Warrant: 11/51, pw@g\n' + \
                          '3)  Suse Blues: 11/54, sb@g\n' + \
                          '------------------------\n' + \
                          'Enter friend number or 0 to cancel: '
            self.assertEqual(correct_out, buf.getvalue())
        sys.stdin = sys.__stdin__
                
        # test valid inputs
        with patch('builtins.input', side_effect=['1']):
            self.assertEqual("manner@j", self.sp4.get_key())
        with patch('builtins.input', side_effect=['2']):
            self.assertEqual("pw@g", self.sp4.get_key())
        with patch('builtins.input', side_effect=['3']):
            self.assertEqual("sb@g", self.sp4.get_key())
        with patch('builtins.input', side_effect=['1']):
            self.assertEqual("", self.sp1.get_key())
            
    @patch('builtins.input', side_effect=['-1', '0', '1', '2', '1', '3', '1'])
    def test_unfriend(self, mock_input):
        self.sp2.unfriend()
        self.assertEqual('1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', self.sp2.friends_str())
        self.sp2.unfriend()
        self.assertEqual('1)  Ryan Manner: 2/9, manner@j\n2)  Suse Blues: 11/54, sb@g\n', self.sp2.friends_str())
        self.sp2.unfriend()
        self.assertEqual('1)  Suse Blues: 11/54, sb@g\n', self.sp2.friends_str())
        self.sp2.unfriend()
        self.assertEqual('1)  Suse Blues: 11/54, sb@g\n', self.sp2.friends_str())
        self.sp2.unfriend()
        self.assertEqual('', self.sp2.friends_str())
        self.sp4.unfriend()
        self.assertEqual('1)  Ryan Manner: 2/9, manner@j\n2)  Pete Warrant: 11/51, pw@g\n', self.sp4.friends_str())
        self.sp4.unfriend()
        self.assertEqual('1)  Pete Warrant: 11/51, pw@g\n', self.sp4.friends_str())

    def test_write_sp(self):
        self.sp1.write_sp('spout.txt')
        sp = SocialPerson('spout.txt')
        self.assertEqual(repr(self.sp1), repr(sp))
        self.sp2.write_sp('spout.txt')
        sp = SocialPerson('spout.txt')
        self.assertEqual(repr(self.sp2), repr(sp))
        self.sp4.write_sp('spout.txt')
        sp = SocialPerson('spout.txt')
        self.assertEqual(repr(self.sp4), repr(sp))
         
    def test_get_sp(self):
        with patch('builtins.input', side_effect=['0']):
            self.assertIsNone(SocialPerson.get_sp())
        with patch('builtins.input', side_effect=['1', 'Rich', 'Thompson', '5/21', 'rm@g', 'Married']):
            sp = SocialPerson.get_sp()
            self.assertEqual(repr(self.sp1), repr(sp))
        with patch('builtins.input', side_effect=['2', 'sp3.txt']):
            sp = SocialPerson.get_sp()
            self.assertEqual(repr(self.sp3), repr(sp))
        with patch('builtins.input', side_effect=['3']):
            self.assertIsNone(SocialPerson.get_sp())
        with patch('builtins.input', side_effect=['4']):
            self.assertIsNone(SocialPerson.get_sp())
        
        # test prompts
        sys.stdin = open('get_sp_in.txt') # can't patch input w/o losing prompt
        with io.StringIO() as buf, redirect_stdout(buf):
            SocialPerson.get_sp()
            correct_out = '---------- SocialPerson Options ----------\n' + \
                          '1) Create a new SocialPerson\n' + \
                          '2) Load a SocialPerson from file\n' + \
                          '3) Cancel\n' + \
                          'Enter option number: ' + \
                          "Enter person's first name: Enter person's last name: " + \
                          "Enter person's birthday: Enter person's e-mail: Enter my status: "
            self.assertEqual(correct_out, buf.getvalue())
        sys.stdin = open('get_sp_in2.txt') # can't patch input w/o losing prompt
        with io.StringIO() as buf, redirect_stdout(buf):
            SocialPerson.get_sp()
            correct_out = '---------- SocialPerson Options ----------\n' + \
                          '1) Create a new SocialPerson\n' + \
                          '2) Load a SocialPerson from file\n' + \
                          '3) Cancel\n' + \
                          'Enter option number: Enter filename: '
            self.assertEqual(correct_out, buf.getvalue())
        sys.stdin = sys.__stdin__
        
    def test_get_option(self):
        with patch('builtins.input', side_effect=['a', '0', '1']):
            self.assertEqual(1, SocialPerson.get_option())
        with patch('builtins.input', side_effect=['2']):
            self.assertEqual(2, SocialPerson.get_option())
        with patch('builtins.input', side_effect=['3']):
            self.assertEqual(3, SocialPerson.get_option())
        with patch('builtins.input', side_effect=['4']):
            self.assertEqual(4, SocialPerson.get_option())
        with patch('builtins.input', side_effect=['5']):
            self.assertEqual(5, SocialPerson.get_option())
        with patch('builtins.input', side_effect=['6', '1']):
            self.assertEqual(1, SocialPerson.get_option())
        
        # test prompts
        sys.stdin = open('get_option_in.txt') # can't patch input w/o losing prompt
        with io.StringIO() as buf, redirect_stdout(buf):
            SocialPerson.get_option()
            correct_out = '---------- SocialPerson Options ----------\n' + \
                          '1) Add a friend\n' + \
                          '2) Unfriend someone\n' + \
                          '3) Print to screen\n' + \
                          '4) Save\n' + \
                          '5) Exit\n' + \
                          'Enter option number: Invalid option: a, try again\n' + \
                          'Enter option number: Invalid option: 0, try again\n' + \
                          'Enter option number: Invalid option: 6, try again\n' + \
                          "Enter option number: "
            self.assertEqual(correct_out, buf.getvalue())
        sys.stdin = sys.__stdin__
    
    def test_main(self):
        save_out = sys.stdout # necessary only if current stdout might not be sys.__stdout__
        save_in = sys.stdin
        outfile = open('out.txt', 'w')
        infile = open('intest.txt', 'r')
        sys.stdout = outfile
        sys.stdin = infile
        main()
        sys.stdout = save_out
        sys.stdin = save_in
        outfile.close()
        infile.close()
        
        outfile = open('out.txt', 'r')
        stdout_actual = outfile.read()
        outfile.close()
        outfile = open('outtest.txt', 'r')
        stdout_expected = outfile.read()
        outfile.close()
        self.assertEqual(stdout_expected, stdout_actual)
        
        sp1 = SocialPerson('sp5.txt')
        sp2 = SocialPerson('sp5_expected.txt')
        self.assertEqual(repr(sp1), repr(sp2))

if __name__ == "__main__":
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestSocialPerson)
    results = unittest.TextTestRunner().run(test)
    tests_run = results.testsRun
    failures = len(results.failures)
    errors = len(results.errors)
    sys.stdout = sys.__stdout__
    print('Correctness score = ', str((tests_run - errors - failures) / tests_run * 100) + '%')
