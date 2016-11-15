import sys
import time

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GIVEN type_simulation <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<#
def type_simulation(string,speed):
    """ 
    This function takes two parameters a string and an integer.

    This function slows down the text (string) printed to the console
    which is controlled by the rate provided by the speed parameter.
    """    
    for char in string:
        sys.stdout.write(char)
        sys.stdout.flush()
        time.sleep(speed) #set speed to 0 for faster printing text for debugging.
    print()
    input()

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> NOT GIVEN <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<#
class Character:
    """
    You will implement 8 methods in this class.
    """

    def __init__(self, name):
        """
        This method takes one parameter, name, which represents a characters
        name. 

        Each character holds 4 pieces of data (instance variables). The first is 
        their name, the second is their health status (name this self.life) which
        starts off at 100 (max health status), the third is attack force (self.force) 
        which starts at 1, and the final one is their current level (self.level). 
        You must check if the character name is "Ganon" or "Zelda", if so they 
        will start off at level 100 (indicates an in-game character) otherwise 
        players start at level 1. 

        Summary:
            You will have four instance variables. One is a string the others are
            ints. (self.name, self.life, self.force, self.level)
        """
        self.name = name
        self.life = 100
        self.force = 1
        self.level = 100 if self.name == "Ganon" or self.name == "Zelda" else 1

    def __repr__(self):
        """
        This method takes no parameters. 

        You will return a visual representation of the player's status.
        Reproduce the chart below:

                                           # <- Note: there is a new line before the chart       
            -------- Link's Stats -------- #Note: Link should not be hard coded 
            Level: 1            
            Health: 100%
            Damage force: 1
                                           # <- Note: there is a newline after the chart
        """
        return ("\n-------- " + self.name + "'s Stats --------\n" + \
               "Level: " + str(self.level) + "\n" +\
               "Health: " + str(self.life) + "%\n" + \
               "Damage force: " + str(self.force) + "\n")


    def level_up(self):
        """
        This method takes no parameters. Increase player's level by 1 if and 
        only if the current character name is not "Zelda" or "Ganon". 

        returns None
        """
        if self.name != "Zelda" and self.name != "Ganon":
            self.level += 1

    def force_up(self):
        """
        This method takes no parameters. Increase the attack force by one.

        returns None
        """
        self.force += 1

    def force_down(self):
        """
        This method takes no parameters. Decrease the attack force by one.

        returns None
        """
        self.force -= 1


    def life_up(self):
        """
        This method takes no parameters. Increase the player's health (self.life)
        by 10 if their current health is less then 100. The player's health can
        not exceed 100 adjust the health value appropriately if this occurs.

        returns None
        """
        self.life += 10
        if self.life > 100:
            self.life = 100

    def life_down(self):
        """
        This method takes no parameters. Decrease the player's health (self.life)
        by 5 if their current health is greater than 0. The player's health can
        not be negative if this happens adjust the health value appropriately by
        setting it to 0. 

        returns None
        """
        self.life -= 5
        if self.life < 0:
            self.life = 0

    def __add__(self, other):
        """
        This method takes one parameter, other, which is a Character instance.

        You will be overloading the addition operator by returning strings
        based on which two character instances are being added. 

        If the current character instance is not "Ganon" and the other Character
        instance name is "Zelda" return:
            "Princess Zelda joins forces with Link!!!\n"  
            #Note: Link should not be hard coded

        If the current character instance is "Ganon" and the other Character
        instance name is "Zelda" return:
            "Ganondorf is holding Princess Zelda captive save her!\n" 

        Else return 
            "Link and Random chat for one second.\n" 
            #Note Link and Random are example names
        """
        if self.name != "Ganon" and other.name == "Zelda":
            return ("Princess Zelda joins forces with " + self.name + "!!!\n")
        if self.name == "Ganon" and other.name == "Zelda":
            return ("Ganondorf is holding Princess Zelda captive! save her!\n")
        return (self.name + " and " + self.other + " chat for one second.\n")

    #>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GIVEN load_storyline <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<# 
    @staticmethod
    def load_storyline(name):
        """
        This method takes one parameter, name, which is a string representation 
        of a file name. 

        This method returns a dictionary that maps an integer to a string. 
        The string represents a portion of the game story line. There is a total
        of 4 keys in the dictionary labeled 0, 1, 2, and 3. 

        The method will loop through each line in the file and look for ":",
        this indicates a new section in the story line. (Take a look at 
        "ZeldaStoryLine.txt" to see the ":" in the file)

        Once a ":" is found the portioned text will be mapped to the appropriate
        key chronologically. 
        """
        story_lines = {}
        count = 0 # used as the key will be incremented in the for loop
        input_file = open("ZeldaStoryLine.txt")
        first_line = input_file.readline() # must read one line to ovoid writing
                                           # and empty string to the dictionary
        result = ""
        for line in input_file:
            line = line.rstrip() #only strip the newline on the right
            if line[-1] != ":": #slice the last char in the string 
                result += line + "\n"
            else: # the last character of line is ":" this means we have 
                  # a completed story line portion for a particular level
                result = result.replace("Link",name) # Replace generic hero name
                                                     # player's name
                story_lines[count] = result # map to dictionary at incremented key.
                result = ""
                count+=1 #increase key after a section has been mapped
                         # moving from 0 to 3
        input_file.close()
        return story_lines  # returning the dictionary.

#>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> GIVEN main <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<#
def main():
    """
    Main simulates a very short in text game that is based on the
    Legend of Zelda. You can interact with the "game".
    You do not have to implement anything in main however you 
    can look through the code and review how to call methods
    from a class and how to create class objects.
    """

    zelda = Character("Zelda") # Creating Character instances. 
    ganon = Character("Ganon")
    game_name = input("Enter your game name: ") 
    link = Character(game_name) # This Character is you. 

    print("\n\n")
    type_simulation("\t\tThe Legend Of Zelda: Unfinished Journey", 0.02)

    game_story = Character.load_storyline(link.name) #accessing instance variables with link.name
    story_index = 0 # This variable will be used to index the dictionary returned
                    # by the load_storyline() static method in the Character Class  

    type_simulation(game_story[story_index], 0.02)
    type_simulation("Pssst ... You ... Yeah you looking at the screen!!", 0.02)
    type_simulation("Are you " + link.name + ", master of all ready to embark\n" +
                     "on this treacherous quest to save Princess Zelda?", 0.02)
    quest = input("(Y/N) ")
    print()
    if quest.lower() == "y":
        story_index += 1 # Increased the index for the dictionary. 
        type_simulation("..... " + link.name+ " Wake up!!!", 0.04)
        type_simulation("Are you ready to start your journey?", 0.09)
        quest = input("(Y/N) ")
        if quest.lower() == "y":
            print(link)
            type_simulation(game_story[story_index], 0.07)
            print(ganon + zelda) # Using the __add__ magic method
            type_simulation("You enter the boxing ring to battle!", 0.01)
            type_simulation("Ganon Hits you!", 0.01)
            link.life_down() # calling a method from the Character class
            type_simulation("You hit back full force!", 0.01)
            type_simulation("Knock Out! Ganon is down but uses his magic and escapes with Zelda.", 0.08)
            ganon.life_down()
            ganon.life_down()
            link.life_up()
            link.force_up()
            link.level_up()
            print(link)
            print(ganon)
            type_simulation("Are you ready to continue your quest?", 0.09)
            quest = input("(Y/N) ")
            print()
            if quest.lower() == "y":
                story_index +=1
                type_simulation(game_story[story_index], 0.07)
                print(link + zelda) 
                type_simulation("You enter the boxing ring to battle!", 0.01)
                type_simulation("Ganon Hits you!", 0.01)
                type_simulation("Ganon Hits you Again!", 0.01)
                for i in range(6):
                    link.life_down()
                link.force_down()
                type_simulation("You hit back!", 0.01)
                type_simulation("You hit back Again!", 0.01)
                type_simulation("Zelda hands you a chair. You hit back full force!", 0.01)
                for i in range(20):
                    link.force_up()
                    zelda.force_up()
                type_simulation("Knock Out! Ganon is down but uses his magic and escapes with Zelda again.", 0.08)
                ganon.life_down()
                ganon.life_down()
                link.life_up()
                link.level_up()
                print(link)
                print(zelda)
                print(ganon)
                type_simulation("Are you ready to continue your quest?", 0.09)
                quest = input("(Y/N) ")
                print()
                if quest.lower() == "y":
                    story_index +=1
                    type_simulation(game_story[story_index], 0.07)
                else:
                    type_simulation("Not everyone is cut out to be a hero.", 0.09 )
            else:
                type_simulation("Not everyone is cut out to be a hero.", 0.09 ) 
        else:
            type_simulation("Not everyone is cut out to be a hero.", 0.09 )  
    else:
        type_simulation("Not everyone is cut out to be a hero.", 0.09 )

    print()    
    type_simulation("The End", 0.09)

if __name__ == '__main__':
    main()