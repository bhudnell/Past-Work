"""
Brendon Hudnell
Section Leader: Will Zielinski
2/24/16
ISTA 350 Hw4

Contains the SocialPerson class, a conceptually similar analog to facebook.
Meant to review and extend knowledge of python classes as well as practice
storing a program's state in a text file between runs.
"""
from classAndDatabaseFunctions import Person


class SocialPerson:
    """
    Initialize an instance of the SocialPerson class.

    filename: the name of the file where the information for the SocialPerson class is stored.
    """
    def __init__(self, filename=None):
        self.friends = {}
        if filename is None:
            self.me = Person() 
            self.status = input("Enter my status: ")
        else:
            fp = list(open(filename))
            self.me = Person(fp[0].strip(), fp[1].strip(), fp[2].strip(), fp[3].strip())
            self.status = fp[4].strip()
            i = 5
            while i < len(fp):
                self.friends[fp[i+3].strip()] = Person(fp[i].strip(), fp[i+1].strip(), fp[i+2].strip(),
                                                       fp[i+3].strip())
                i += 4

    def friends_str(self):
        """
        Takes the dictionary of friends and returns a string containing that information.

        Returns a string listing all friends and their information.
        """
        string = ""
        if self.friends == {}:
            return string
        i = 1
        for key in sorted(self.friends.keys()):
            string += str(i) + ")  " + self.friends[key].first + " " + self.friends[key].last + ": " + \
                      self.friends[key].bday + ", " + self.friends[key].email + "\n"
            i += 1
        return string

    def __repr__(self):
        """
        Shows how an instance of the class is represented when printed.
        """
        return "---------- me ----------\n" + \
               self.me.first + " " + self.me.last + ": " + self.me.bday + ", " + self.me.email +"\n" + \
               "My status is: " + self.status + "\n" + \
               "\n------- friends --------" + "\n" + \
               self.friends_str()

    def add_friend(self):
        """
        Adds a friend to the friends dictionary using the Person class to take inputs.
        """
        friend = Person()
        self.friends[friend.email] = friend 

    def get_key(self):
        """
        Prints a list of friends and returns the key corresponding to a specific friend.

        Returns an integer value of the key chosen, or an empty string if the key is not valid
        """
        print ("------- friends --------")
        print (self.friends_str(), end="")
        print ("------------------------")
        UI = int(input("Enter friend number or 0 to cancel: "))
        keys = sorted(self.friends.keys())
        if UI > 0 and UI <= len(keys):
            return self.friends[keys[UI-1]].email
        elif UI == 0:
            return ""
        print ("Not a friend number: " + str(UI))
        return ""

    def unfriend(self):
        """
        Uses the key returned from the get_key method and uses it to remove a friend from the friends dictionary
        """
        key = self.get_key()
        if key:
            self.friends.pop(key)

    def write_sp(self, filename):
        """
        Writes an instance of the SocialPerson class to a text file.

        filename: the name of the file to where the SocialPerson will be written.
        """
        fp = open(filename, 'w')
        self.me.write_person(fp)
        fp.write(self.status + "\n")
        for key in self.friends:
            self.friends[key].write_person(fp)
        fp.close()

    @staticmethod
    def get_sp():
        """
        A static method that prompts the user to create a new SocialPerson or to read one from a file.

        Returns an instance of the SocialPerson class.
        """
        print ("---------- SocialPerson Options ----------")
        print ("1) Create a new SocialPerson")
        print ("2) Load a SocialPerson from file")
        print ("3) Cancel")
        UI = input("Enter option number: ")

        if int(UI) == 1:
            return SocialPerson()
        elif int(UI) == 2:
            filename = input("Enter filename: ")
            return SocialPerson(filename)

    @staticmethod
    def get_option():
        """
        A static method that prompts the user to add a friend, unfriend someone, print the SocialPerson's
        info, save the SocialPerson to a text file, or to exit the program.

        Returns and integer value of the user's choice.
        """
        print ("---------- SocialPerson Options ----------")
        print ("1) Add a friend")
        print ("2) Unfriend someone")
        print ("3) Print to screen")
        print ("4) Save")
        print ("5) Exit")

        while True:
            UI = input("Enter option number: ")
            if UI not in ["1", "2", "3", "4", "5"]:
                print ("Invalid option: " + UI + ", try again")
            else:
                return int(UI)

def main():
    """
    Gets an instance of the SocialPerson class and prompts the user to interact with the program to modify
    the instance of the SocialPerson chosen.
    """
    sp = SocialPerson.get_sp()

    while True:
        sp_option = SocialPerson.get_option()
        if sp_option == 1:
            sp.add_friend()
        elif sp_option == 2:
            sp.unfriend()
        elif sp_option == 3:
            print (sp)
        elif sp_option == 4:
            save_file = input("Enter save filename: ")
            sp.write_sp(save_file)
        else:
            break