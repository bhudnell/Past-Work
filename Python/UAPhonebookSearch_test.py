from hw10 import *
from unittest.mock import patch
from bs4 import BeautifulSoup
import unittest, os, sys, io, time, types
from contextlib import redirect_stdout
from compare_files import compare_files

#************ fix test_select_people
"""
Files needed:
'names.txt', 'compare_files.py', 'rich_thompson.html', 'csv_out_correct.csv'
'people_correct.csv', 'select_people_in.txt', 'test_name.txt'
"""

class TestPerson(unittest.TestCase):
    def setUp(self):
        self.p1 = Person("Thompson", "Richard A", "retired", "thompsri@email.arizona.edu", \
            "", "", "(retired) Associate Professor, Anthropology", "", "", "")
        self.p2 = Person("Thompson", "Richard B", "retired", "rbt@math.arizona.edu", \
            "", "", "(retired) Professor Emeritus", "", "", "")
        self.p3 = Person("Thompson", "Richard Micheal", "student", "richardt1@email.arizona.edu", \
            "", "", "Undergraduate - College of Soc & Behav Sci - Creative Writing", "", "", "")
        self.p4 = Person('Thompson', 'Richard Maxwell', 'appointed personnel', \
            'rmthomps@email.arizona.edu', '520-621-3566', \
            'School of Information', \
            'Lecturer, School of Information', 'PO Box 210077', 'Gould-Simpson (#77)', '917')
        self.p5 = Person('Smith', 'John', 'student', 'js@email.arizona.edu', '', \
            'Undergraduate - College of Science - Computer Science', '', '', '', '')

        soup = BeautifulSoup(open("rich_thompson.html").read())
        self.people = soup.find_all('span','field-content')

        self.genP1 = ["Thompson, Richard A", "retired", "thompsri@email.arizona.edu", \
            "", "", "(retired) Associate Professor, Anthropology", "", " rm "]
        self.genP2 = ["Thompson, Richard B", "retired", "rbt@math.arizona.edu", \
            "", "", "(retired) Professor Emeritus", "", " rm "]
        self.genP3 = ["Thompson, Richard Micheal", "student", "richardt1@email.arizona.edu", \
            "", "", "Undergraduate - College of Soc & Behav Sci - Creative Writing", "", " rm "]
        self.genP4 = ["Thompson, Richard Maxwell", "appointed personnel", 
            "rmthomps@email.arizona.edu", "520-621-3566", 
            'School of Information', \
            "Lecturer, School of Information", "PO Box 210077", "Gould-Simpson (#77) rm 917"]
        
    def test_init(self):
        self.assertEqual('Thompson', self.p4.last)
        self.assertEqual('Richard Maxwell', self.p4.first)
        self.assertEqual('appointed personnel', self.p4.ptype)
        self.assertEqual('rmthomps@email.arizona.edu', self.p4.email)
        self.assertEqual('520-621-3566', self.p4.phone)
        self.assertEqual('School of Information', self.p4.unit)
        self.assertEqual('Lecturer, School of Information', self.p4.position)
        self.assertEqual('PO Box 210077', self.p4.addr)
        self.assertEqual('Gould-Simpson (#77)', self.p4.bldg)
        self.assertEqual('917', self.p4.rm)
        self.assertEqual('Smith', self.p5.last)
        self.assertEqual('John', self.p5.first)
        self.assertEqual('student', self.p5.ptype)
        self.assertEqual('js@email.arizona.edu', self.p5.email)
        self.assertEqual('', self.p5.phone)
        self.assertEqual('Undergraduate - College of Science - Computer ' \
            'Science', self.p5.unit)
        self.assertEqual('', self.p5.position)
        self.assertEqual('', self.p5.addr)
        self.assertEqual('', self.p5.bldg)
        self.assertEqual('', self.p5.rm)


    def test_from_soup(self):

        peeps = [Person.from_soup(peep) for peep in self.people]

        self.assertEqual("Richard A", peeps[0].first)
        self.assertEqual("Thompson", peeps[0].last)
        self.assertEqual("retired", peeps[0].ptype)
        self.assertEqual("thompsri@email.arizona.edu", peeps[0].email)
        self.assertEqual("", peeps[0].phone)
        self.assertEqual("", peeps[0].unit)
        self.assertEqual("(retired) Associate Professor, Anthropology", peeps[0].position)
        self.assertEqual("", peeps[0].addr)
        self.assertEqual("", peeps[0].bldg)
        self.assertEqual("", peeps[0].rm)

        self.assertEqual("Richard B", peeps[1].first)
        self.assertEqual("Thompson", peeps[1].last)
        self.assertEqual("retired", peeps[1].ptype)
        self.assertEqual("rbt@math.arizona.edu", peeps[1].email)
        self.assertEqual("", peeps[1].phone)
        self.assertEqual("", peeps[1].unit)
        self.assertEqual("(retired) Professor Emeritus", peeps[1].position)
        self.assertEqual("", peeps[1].addr)
        self.assertEqual("", peeps[1].bldg)
        self.assertEqual("", peeps[1].rm)

        self.assertEqual("Richard Micheal", peeps[2].first)
        self.assertEqual("Thompson", peeps[2].last)
        self.assertEqual("student", peeps[2].ptype)
        self.assertEqual("richardt1@email.arizona.edu", peeps[2].email)
        self.assertEqual("", peeps[2].phone)
        self.assertEqual("", peeps[2].unit)
        self.assertEqual("Undergraduate - College of Soc & Behav Sci - " + \
            "Creative Writing", peeps[2].position)
        self.assertEqual("", peeps[2].addr)
        self.assertEqual("", peeps[2].bldg)
        self.assertEqual("", peeps[2].rm)

        self.assertEqual("Richard Maxwell", peeps[3].first)
        self.assertEqual("Thompson", peeps[3].last)
        self.assertEqual("appointed personnel", peeps[3].ptype)
        self.assertEqual("rmthomps@email.arizona.edu", peeps[3].email)
        self.assertEqual("520-621-3566", peeps[3].phone)
        self.assertEqual("School of Information", peeps[3].unit)
        self.assertEqual("Lecturer, School of Information", peeps[3].position)
        self.assertEqual("PO Box 210077", peeps[3].addr)
        self.assertEqual("Gould-Simpson (#77)", peeps[3].bldg)
        self.assertEqual("917", peeps[3].rm)

    def test_generator(self):
        self.assertEqual(self.genP1, list(self.p1.generator()))
        self.assertEqual(self.genP2, list(self.p2.generator()))
        self.assertEqual(self.genP3, list(self.p3.generator()))
        self.assertEqual(self.genP4, list(self.p4.generator()))
        self.assertTrue(isinstance(self.p1.generator(), types.GeneratorType))

    def test_repr(self):
        self.assertEqual("Richard Maxwell Thompson, rmthomps@email.arizona.edu," +\
            " Lecturer, School of Information",repr(self.p4))
        self.assertEqual("John Smith, js@email.arizona.edu, ", repr(self.p5))
        self.assertEqual("Richard A Thompson, thompsri@email.arizona.edu, " + \
            "(retired) Associate Professor, Anthropology", repr(self.p1))

    def test_eq(self):
        self.assertNotEqual(self.p1, self.p2)
        self.assertNotEqual(self.p2, self.p3)
        self.assertNotEqual(self.p3, self.p4)
        self.assertNotEqual(self.p4, self.p5)
        self.p5.email = 'rbt@math.arizona.edu'
        self.assertEqual(self.p2, self.p5)
        self.p4.email = "thompsri@email.arizona.edu"
        self.assertEqual(self.p1, self.p4)

    def test_lt(self):
        self.assertFalse(self.p1 < self.p1)
        self.assertTrue(self.p1 < self.p2)
        self.assertTrue(self.p1 < self.p3)
        self.assertTrue(self.p1 < self.p4)
        self.assertFalse(self.p1 < self.p5)
        self.assertFalse(self.p2 < self.p5)
        self.assertTrue(self.p2 < self.p4)
        self.assertTrue(self.p2 < self.p3)
        self.assertFalse(self.p2 < self.p2)
        self.assertFalse(self.p2 < self.p1)
        self.assertTrue(self.p3 > self.p1)
        self.assertTrue(self.p4 > self.p2)
        self.assertTrue(self.p5 < self.p4)

    def test_hash(self):
        peeps = [Person.from_soup(peep) for peep in self.people]
        self.assertEqual(hash(peeps[0]), hash("thompsri@email.arizona.edu"))
        self.assertEqual(hash(peeps[1]), hash("rbt@math.arizona.edu"))
        self.assertEqual(hash(peeps[2]), hash("richardt1@email.arizona.edu"))
        self.assertEqual(hash(peeps[3]), hash('rmthomps@email.arizona.edu'))

class TestPeople(unittest.TestCase):

    # Need to have a working from_soup() from Person class to pass
    # test cases in the People class. 
    def setUp(self):
        soup = BeautifulSoup(open("rich_thompson.html").read())
        richards = soup.find_all('span','field-content')
        self.peeps = [Person.from_soup(rich) for rich in richards]

        self.maxDiff = None
        with patch('builtins.input', side_effect=['0']):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.people_test = People(fname="test_name.txt")
    
    def test_init(self):
        people1 = People()
        self.assertEqual([], people1.people)
        self.assertEqual([], people1.missing)
        with patch('builtins.input', side_effect=['4']):
            people2 = People(fname="test_name.txt")
            self.assertEqual([self.peeps[3]], people2.people)
            self.assertEqual([['Link', 'Zelda']], people2.missing)
        with patch('builtins.input', side_effect=['1']):
            people2 = People(fname="test_name.txt")
            self.assertEqual([self.peeps[0]], people2.people)
            self.assertEqual([['Link', 'Zelda']], people2.missing)
        with patch('builtins.input', side_effect=['0']):
            people3 = People(fname="test_name.txt")
            self.assertEqual([], people3.people )
            self.assertEqual([['Link', 'Zelda']], people3.missing)

        # test input prompt: invalid and multiple Person selection input
        sys.stdin = open('select_people_in.txt') 
        with io.StringIO() as buf, redirect_stdout(buf): 
            people4 = People(fname="test_name.txt")
            correct_out =  "\n\n" \
            "********************* Search on Richard Thompson returns: **********************\n" \
            "  0: Select nobody\n" \
            "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
            "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
            "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
            "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
            "********************************************************************************\n" \
            "Enter numbers separated by spaces or return to select all: Error: nonnumeric entry " \
            "detected.  Please choose again.\n" \
            "\n\n" \
            "********************* Search on Richard Thompson returns: **********************\n" \
            "  0: Select nobody\n" \
            "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
            "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
            "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
            "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
            "********************************************************************************\n" \
            "Enter numbers separated by spaces or return to select all: "
            self.assertEqual(correct_out, buf.getvalue())
            self.assertEqual([self.peeps[0], self.peeps[3]], people4.people)
            self.assertEqual([['Link', 'Zelda']], people4.missing)
        sys.stdin = sys.__stdin__

    def test_select_people(self):
        with patch('builtins.input', side_effect=['1']):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.people_test.select_people(self.peeps, ['Thompson', 'Richard'])
                correct_out =  "\n\n" \
                "********************* Search on Richard Thompson returns: **********************\n" \
                "  0: Select nobody\n" \
                "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
                "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
                "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
                "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
                "********************************************************************************\n" 
                self.assertEqual(correct_out, buf.getvalue())

        with patch('builtins.input', side_effect=['SUP', "100001010"]):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.people_test.select_people(self.peeps, ['Thompson', 'Richard'])
                correct_out =  "\n\n" \
                "********************* Search on Richard Thompson returns: **********************\n" \
                "  0: Select nobody\n" \
                "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
                "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
                "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
                "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
                "********************************************************************************\n" \
                "Error: nonnumeric entry detected.  Please choose again.\n" \
                "\n\n" \
                "********************* Search on Richard Thompson returns: **********************\n" \
                "  0: Select nobody\n" \
                "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
                "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
                "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
                "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
                "********************************************************************************\n" \
                "Warning: Invalid number 100001010 ignored.\n"
                self.assertEqual(correct_out, buf.getvalue())

    def test_print_plist(self):
        with io.StringIO() as buf, redirect_stdout(buf):
            People.print_plist(self.peeps, 'Thompson', 'Richard')
            correct_out =  "\n\n" \
            "********************* Search on Richard Thompson returns: **********************\n" \
            "  0: Select nobody\n" \
            "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" \
            "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" \
            "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" \
            "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" \
            "********************************************************************************\n" 
            self.assertEqual(correct_out, buf.getvalue())

    def test_write_people(self):
        with patch('builtins.input', side_effect=['1 2 3 4']):
            with io.StringIO() as buf, redirect_stdout(buf):
                self.people_test2 = People(fname="test_name.txt")
        files = os.listdir()
        if("csv_out.csv" in files):
            os.remove("csv_out.csv")
        self.people_test2.write_people("csv_out.csv")
        self.assertTrue(compare_files("csv_out_correct.csv", "csv_out.csv"))        
    
    def test_main(self):

        files = os.listdir()
        if "people.csv" in files:
            os.remove("people.csv")

        with patch('builtins.input', side_effect=['1 2 8 11 14', '3 4']):
            with io.StringIO() as buf, redirect_stdout(buf):
                with patch('time.strftime', side_effect=['Friday, 11/27/2015, at 03:17', 'Friday, 11/27/2015, at 03:17']):
                    main()
                    cur_time =  (' UA Phonebook Search, ' + time.strftime('%A, %m/%d/%Y, at %I:%M') + ' ').center(80, '*')
                    main_out = "\n\n" + \
                        cur_time + \
                        "\n\n\n************************* Search on Ann Hart returns: **************************\n" + \
                        "  0: Select nobody\n" + \
                        "  1: Brianna Mae Barnhart, briannabarnhart@email.arizona.edu, Undergraduate - Co\n" + \
                        "  2: Dianne M Bret Harte, dianne@email.arizona.edu, (retired) Program Coordinato\n" + \
                        "  3: Ann W Hart, president@email.arizona.edu, President of the University\n" + \
                        "  4: Kailey Breanne Hart, kbhart@email.arizona.edu, Instructional Specialist\n" + \
                        "  5: Johnna Reymann Hartenstine, jrhartenstine@email.arizona.edu, Undergraduate \n" + \
                        "  6: Anne Margaret Hartingh, ahartingh@email.arizona.edu, \n"+ \
                        "  7: Elizabeth Anne Hartley, eahartley@email.arizona.edu, Undergraduate - Colleg\n" + \
                        "  8: Dakota Hartliep Hartliep, dhartliep@email.arizona.edu, \n" + \
                        "  9: Nicole Ann Hartman, nicoleannhartman@email.arizona.edu, Undergraduate - Col\n" + \
                        "  10: Loring Taylor Ann Hartmann, loringhartmann@email.arizona.edu, \n" + \
                        "  11: Shannon L Hartsuck, hartsucs@email.arizona.edu, Assistant Director, Consul\n" + \
                        "  12: Karli Ann Hartwig, karlihartwig@email.arizona.edu, \n" + \
                        "  13: Sarah Anne Lenhart, slenhart@email.arizona.edu, Undergraduate - College of\n" + \
                        "  14: Hanna Christine Lockhart, hlockhart@email.arizona.edu, \n" + \
                        "********************************************************************************\n" + \
                        "\n\n" + \
                        "*********************** Search on Rich Thompson returns: ***********************\n" + \
                        "  0: Select nobody\n" + \
                        "  1: Richard A Thompson, thompsri@email.arizona.edu, (retired) Associate Profess\n" + \
                        "  2: Richard B Thompson, rbt@math.arizona.edu, (retired) Professor Emeritus\n" + \
                        "  3: Richard Micheal Thompson, richardt1@email.arizona.edu, Undergraduate - Coll\n" + \
                        "  4: Richard Maxwell Thompson, rmthomps@email.arizona.edu, Lecturer, School of I\n" + \
                        "********************************************************************************\n" + \
                        "\n\n" + \
                        "****************************** 4 People Not Found ******************************\n" + \
                        "  From grimm Monroe\n" + \
                        "  Marilyn Monroe\n" + \
                        "  Joe Montana\n" + \
                        "  George Washington\n\n" 
                    self.assertEqual(main_out, buf.getvalue())

        self.assertTrue(compare_files("people_correct.csv", "people.csv")) 
    

if __name__ == '__main__':
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestPerson)
    results = unittest.TextTestRunner().run(test)
    tests_run1 = results.testsRun
    failures1 = len(results.failures)
    errors1 = len(results.errors)
    test = unittest.defaultTestLoader.loadTestsFromTestCase(TestPeople)
    results = unittest.TextTestRunner().run(test)
    tests_run2 = results.testsRun
    failures2 = len(results.failures)
    errors2 = len(results.errors)
    print('Assignment correctness score = ', str((tests_run1 - errors1 - failures1) / tests_run1 * 100) + '%')
    print('Extra credit course grade points (1 max) = ', str((tests_run2 - errors2 - failures2) / tests_run2) + ' grade points')
    
