"""
Brendon Hudnell
Section Leader: Will Zielinski
2/10/16
ISTA 350 Hw3

Contains the Person class and 8 functions related to it. Meant to review and extend knowledge of 
python classes as well as interacting with databases through python's SQL functionality, SQLite3.
"""
import os.path
import sqlite3
import sys

class Person:
    """
    Used to create a Person and read/write them into a file.
    """
    def __init__(self, first="", last="", bday="", email=""):
        """
        Initialize the instance of the Person class.

        first: a string of the person's first name
        last: a string of the person's last name
        bday: a string of the person's birthday in the form of MM/DD
        email: a string of the person's email address
        """
        self.first = first if first else input('Enter person\'s first name: ')
        self.last = last if last else input('Enter person\'s last name: ')
        self.bday = bday if bday else input('Enter person\'s birthday: ')
        self.email = email if email else input('Enter person\'s e-mail: ')

    def __repr__(self):
        """
        Shows how the class is represented when printed
        """
        return self.first + ' ' + self.last + ': ' + self.bday + ', ' + self.email

    @classmethod
    def read_person(cls, file):
        """
        Reads a the data necessary from a text file to return a Person instance.

        file: a file object

        returns: a Person instance
        """
        first = file.readline().strip()
        if not first:
            return False
        last = file.readline().strip()
        bday = file.readline().strip()
        email = file.readline().strip()
        return cls(first, last, bday, email)
    
    def write_person(self, file):
        """
        Writes the instance variables from a Person instance to a file

        file: a file object
        """
        file.write(self.first + "\n")
        file.write(self.last + "\n")
        file.write(self.bday + "\n")
        file.write(self.email + "\n")

def open_persons_db():
    """
    Opens a database of persons. If the database doesnt exist, it creates a
    'friends' and a 'colleagues' table. 

    returns: the database
    """
    #determines if the database exists
    exists = False
    if os.path.exists('persons.db'):
        exists = True

    #connects to the database
    persons_db = sqlite3.connect('persons.db')
    persons_db.row_factory = sqlite3.Row

    #if the database doesnt already exist, creates the 'friends' and 'colleagues' tables
    if not exists:
        persons_db.execute('CREATE TABLE friends (first TEXT, last TEXT, bday TEXT, email TEXT PRIMARY KEY)')
        persons_db.execute('CREATE TABLE colleagues (first TEXT, last TEXT, bday TEXT, email TEXT PRIMARY KEY)')

    persons_db.commit()

    return persons_db

def add_person(person_db, person, friend=True, colleague=False):
    """
    Adds a Person instance to the persons database in the 'friends' table if 'friend' is True
    and to the 'colleagues' table if 'colleague' is True

    person_db: a database of Persons instances
    person: a Person instance
    friend: a boolean, True if person is a friend, False otherwise
    colleague: a boolean, True if person is a colleague, False otherwise

    returns: a boolean, True if person was added, False otherwise
    """
    #raises an error and returns false if the person is neither a friend nor colleague
    if not friend and not colleague:
        sys.stderr.write('Warning: ' + person.email + ' not added - must be friend or colleague\n')
        return False

    #if friend or colleague, adds person to the correct table in the person database
    if friend:
        person_db.execute('INSERT INTO friends (first, last, bday, email) VALUES (?, ?, ?, ?);', \
                         (person.first, person.last, person.bday, person.email))
    if colleague:
        person_db.execute('INSERT INTO colleagues (first, last, bday, email) VALUES (?, ?, ?, ?);', \
                         (person.first, person.last, person.bday, person.email))
        
    person_db.commit()
    return True

def delete_person(person_db, person):
    """
    Deletes a person instance from the person database.

    person_db: a database of persons
    person: a Person instance
    """
    person_db.execute('DELETE FROM friends WHERE email = ?;', (person.email,))
    person_db.execute('DELETE FROM colleagues WHERE email = ?;', (person.email,))
    person_db.commit()

def to_Person_list(cursor):
    """
    Takes data from the persons database and puts it into a list of Person instances

    cursor: a cursor object
    """
    person_list = []
    rows = cursor.fetchall()
    for row in rows:
        person_list.append(Person(row[0], row[1], row[2], row[3]))
    return person_list

def get_friends(person_db):
    """
    Takes a person database and returns a list of Person objects representing
    all the friends in the database

    person_db: a database of persons
    """
    cursor = person_db.execute('SELECT * FROM friends;')
    return to_Person_list(cursor)

def get_colleagues(person_db):
    """
    Takes a person database and returns a list of Person objects representing
    all the colleagues in the database

    person_db: a database of persons
    """
    cursor = person_db.execute('SELECT * FROM colleagues;')
    return to_Person_list(cursor)

def get_all(person_db):
    """
    Takes a person database and returns a list of Person objects representing
    all the friends and colleagues in the database without duplicates

    person_db: a database of persons
    """
    result = []

    friend_cursor = person_db.execute('SELECT * FROM friends;')
    friends = to_Person_list(friend_cursor)

    colleague_cursor = person_db.execute('SELECT * FROM colleagues;')
    colleagues = to_Person_list(colleague_cursor)

    result.extend(friends)
    for colleague in colleagues:
        if colleague not in result:
            result.append(colleague)

    return result

def get_and(person_db):
    """
    Takes a person database and returns a list of Person objects representing
    all the people who are both friends and colleagues in the database

    person_db: a database of persons
    """
    result = []

    friend_cursor = person_db.execute('SELECT * FROM friends;')
    friends = to_Person_list(friend_cursor)

    colleague_cursor = person_db.execute('SELECT * FROM colleagues;')
    colleagues = to_Person_list(colleague_cursor)

    for colleague in colleagues:
        if colleague in friends:
            result.append(colleague)

    return result