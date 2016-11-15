import random

class Wizard:

    def __init__(self, name, age):
        '''
        A wizard has four instance variables: name, age, house, and year.  Name and age 
        must be given when declaring a new instance of a wizard. Year is equal to the wizard's
        age minus 10, and house will be determined by the sort method (defined below)
        '''
        self.name = name
        self.age = age
        self.year = self.age - 10
        self.house = self.rand_house()
        
    def rand_house(self):
        '''
        In the rand_house method, given the list of houses, randomly choose a house using the
        random module and return the randomly selected house.
        '''
        houses = ['Gryffindor', 'Hufflepuff', 'Ravenclaw', 'Slytherin']
        return random.choice(houses)

    def the_wand_chooses_the_wizard(self):
        '''
        In the_wand_chooses_the_wizard, given the lists of wand lengths, wood_types, and 
        core types, randomly choose one item from each list and create a string of the form:
        "<number of inches> inches, <type of wood>, with a <type of core> core". Return this string."
        '''
        lengths = [10, 9.5, 12, 8, 11, 12.75, 9.75, 10.25, 18] 
        wood_types = ['cherry', 'yew', 'maple', 'vine', 'oak', 'mahogany', 'cedar']
        core = ['dragon heartstring', 'phoenix feather', 'unicorn hair'] 
        
        return (str(random.choice(lengths)) + " inches, " + random.choice(wood_types) + \
               ", with a " + random.choice(core) + " core")

class Hogwarts:

    def __init__(self):
        '''
        A Hogwarts object contains two instance variables, both of which are automatically
        set up by the constructor (this means they are not declared in the function 
        definition).  roster is a dictionary that maps the name of each house
        to an empty list and house_points is a dictionary that maps the name of each house to 
        the integer 0

        Recall your list of houses are:
            'Gryffindor', 'Hufflepuff', 'Ravenclaw', and 'Slytherin'
        '''
        self.roster = {'Gryffindor':[], 'Hufflepuff':[], 'Ravenclaw':[], 'Slytherin':[]}
        self.house_points = {'Gryffindor':0, 'Hufflepuff':0, 'Ravenclaw':0, 'Slytherin':0}

    def enroll_wizard(self, wizard):
        '''
        This method takes in a Wizard object as its only argument. Append the the wizard's name 
        to the roster instance variable base on the the house the wizard belongs to.
        '''
        self.roster[wizard.house].append(wizard.name)

    def add_house_points(self, house, amount):
        '''
        This method takes in a string that represents a house name and an integer that represents
        an amount. Increase the points assigned to the house by the given amount.
        '''
        self.house_points[house] += amount

    def subtract_house_points(self, house, amount):
        '''
        This method takes in a string that represents a house name and an integer that represents
        an amount. Decrease the points assigned to the house by the given amount.
        '''
        self.house_points[house] -= amount

    def house_cup_winner(self):
        '''
        This method takes no parameters.

        Your task is to iterate through the roster dictionary and find the house with the largest
        number of points.

        You are guaranteed that only one house will have a max number of points. 

        Return a string that contains the following format and words:

            "The winner is: <house name> with a grand total of <points>."  
        '''
        winner = 'Gryffindor'
        for house in self.house_points.keys():
            if self.house_points[house] > self.house_points[winner]:
                winner = house 
        return ("The winner is: " + winner + " with a grand total of " + str(self.house_points[winner]) + " points.")

def main():
    '''
    Test your classes in main!
    '''
    Harry = Wizard("Harry", 11)
    Percy = Wizard("Percy", 16)
    school = Hogwarts()
    school.enroll_wizard(Harry)
    school.enroll_wizard(Percy)
    print (school.roster)
    school.add_house_points(Harry.house, 30)
    school.add_house_points(Percy.house, 40)
    print (school.house_cup_winner())


if __name__ == '__main__':
    main()