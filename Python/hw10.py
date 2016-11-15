"""
Brendon Hudnell
Section Leader: Will Zielinski
4/27/16
ISTA 350 Hw10

Searches UA Phonebook for names found in names.txt, asks user for input, saves the resulting 
people to people.csv, and prints out the names not found
"""
import requests, csv, time
from bs4 import BeautifulSoup

class Person():
    def __init__(self, last, first, ptype, email, phone, unit, position, addr, bldg, rm):
        """
        initializes the instance fields

        last: last name string
        first: first name string
        ptype: person's type string (student, retired, professor, etc)
        email: email string
        phone: phone number string
        unit: department string
        position: degree string
        addr: address string
        bldg: building string
        rm: room string

        returns nothing
        """
        self.last = last
        self.first = first
        self.ptype = ptype
        self.email = email
        self.phone = phone
        self.unit = unit
        self.position = position
        self.addr = addr
        self.bldg = bldg
        self.rm = rm

    @classmethod
    def from_soup(cls, soup):
        """
        creates an instance of the Person class from a soup object

        soup: a BeautifulSoup object

        returns an instance of the Person class
        """
        name = soup.find('h3').get_text().strip()
        last = name.split(', ')[0]
        first = name.split(', ')[1]
        ptype = soup.find('span', class_="type").get_text().strip()
        email = soup.find('a', class_="mailto").get_text().strip()
        phone = soup.find('a', class_="phoneto").get_text().strip() if soup.find('a', class_="phoneto") else ''
        unit = soup.find('div', class_="degree").get_text().strip()
        pos = soup.find('div', class_="department").get_text().strip()
        pos = pos.split('\n')[0].strip()
        pos = pos.replace('\r', '')
        addr = soup.find_all('div')[6].get_text().strip()
        bldg = soup.find_all('div')[7].get_text().strip()
        if bldg != '':
            bldg = bldg.split(': ')[1]
        rm = soup.find_all('div')[8].get_text().strip()
        if rm != '':
            rm = rm.split(': ')[1]

        return cls(last, first, ptype, email, phone, unit, pos, addr, bldg, rm)

    def generator(self):
        """
        generates the instance variables as strings, one per line except: "last, first" on same line
        and "building rm room" on same line

        returns nothing
        """
        yield self.last + ', ' + self.first
        yield self.ptype
        yield self.email
        yield self.phone
        yield self.unit
        yield self.position
        yield self.addr
        yield self.bldg + ' rm ' + self.rm

    def __repr__(self):
        """
        defines how the Person class is represented as a string
        """
        return self.first + ' ' + self.last + ', ' + self.email + ', ' + self.position

    def __eq__(self, other):
        """
        defines the == operator for the Person class. compares emails
        """
        return self.email == other.email

    def __lt__(self, other):
        """
        defines the < operator for the Person class. compares last names, then first names, then emails
        """
        if self == other:
            return False
        if self.last < other.last:
            return True
        elif self.last == other.last:
            if self.first < other.first:
                return True
            elif self.first == other.first:
                if self.email < other.email:
                    return True
        return False

    def __hash__(self):
        """
        defines how the hash method works on the Person class. Hashs on emails
        """
        return hash(self.email)

class People:
    def __init__(self, people=None, fname=None):
        """
        initializes the people and missing instance variables from a file

        people: a list of Person objects
        fname: a string representation of a file name
        """
        self.people = people if people else []
        self.missing = []

        if fname:
            with open(fname, newline='') as fp:
                reader = csv.reader(fp, delimiter=',', quotechar='|')
                for row in reader:
                    r = requests.get('http://directory.arizona.edu/phonebook?lastname=' + row[0] \
                                     + '&firstname=' + row[1])
                    soup = BeautifulSoup(r.content, "html.parser")
                    peeps = soup.find_all('span','field-content')
                    if peeps:
                        peep_lst = [Person.from_soup(peep) for peep in peeps]
                        self.select_people(peep_lst, row)
                    else:
                        self.missing.append(row)

    def select_people(self, peep_lst, search_name):
        """
        asks user to choose people out of peep_lst to append to self.people based on the search_name

        peep_lst: a list of Person objects
        search_name: the name used to query the phonebook to generate the Person objects in peep_lst

        returns nothing
        """
        if len(peep_lst) > 1:
            while True:
                People.print_plist(peep_lst, search_name[0], search_name[1])
                UI = input("Enter numbers separated by spaces or return to select all: ")
                if UI == '':
                    self.people.extend(peep_lst)
                    break
                lst = sorted(UI.split())
                try:
                    for i in range(len(lst)):
                        lst[i] = int(lst[i])
                        if lst[i] == 0:
                            return
                        else:
                            if lst[i] in range(1,len(peep_lst)+1):
                                self.people.append(peep_lst[lst[i]-1])
                            else:
                                print ('Warning: Invalid number ' + str(lst[i]) + ' ignored.')
                    return
                except:
                    print ('Error: nonnumeric entry detected.  Please choose again.')
        else:
            self.people.extend(peep_lst)

    @staticmethod
    def print_plist(peep_lst, last, first):
        """
        prints out a numbered list people from peep_lst along with the search terms used

        peep_lst: a list of Person objects
        last: the last name used in the search
        first: the first name used in the search

        returns nothing
        """
        print ('\n')
        search = ' Search on ' + first + ' ' + last + ' returns: '
        print (search.center(80, '*'))
        print ('  0: Select nobody')
        for i in range(0, len(peep_lst)):
            string = '  ' + str(i+1) + ': ' + repr(peep_lst[i])
            print (string[:80])
        print ('*'*80)

    def write_people(self, fname):
        """
        saves a csv list of the Person objects in self.people to a filename

        fname: the name of the csv output file

        returns nothing
        """
        with open(fname, 'w', newline='') as fp:
            writer = csv.writer(fp, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            for person in self.people:
                writer.writerow(person.generator())

def main():
    """
    prints the time of the search, searches the UA phonebook for the people in 'names.txt', prompts
    the user for what names to select, saves the selected names in 'people.csv', and prints a list of
    the search terms not found in the phonebook
    """
    print ('\n')
    print ((' UA Phonebook Search, ' + time.strftime('%A, %m/%d/%Y, at %I:%M') + ' ').center(80, '*'))
    peeps = People(fname='names.txt')
    peeps.people = sorted(list(set(peeps.people)))
    peeps.write_people('people.csv')
    print ('\n')
    print ((' ' + str(len(peeps.missing)) + ' People Not Found ').center(80, '*'))
    for peep in sorted(peeps.missing, key=lambda name: name[1], reverse=True):
        print ('  ' + peep[1][0].upper() + peep[1][1:].lower() + ' ' + peep[0])
    print ()

if __name__ == "__main__":
    main()