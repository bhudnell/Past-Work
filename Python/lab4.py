'''
IMPORTANT:

This lab is different from what you have been doing. As you can see,
there is some code that is already provided for your convenience. 

You are to complete all sections that are labeled with:

    #-------------------- TO DO ITEM ## --------------------#
    #   Instructions                                        #
    #-------------------------------------------------------#

You will be implementing two classes: FoodItem and SandwichShop.
'''

class FoodItem:
    '''
    This class creates a FoodItem object. A FoodItem has a name and a 
    price ($).
    '''
    
    def __init__(self, ingredient, cost):
        '''
        This method takes in a string (name) that represents an ingredient
        name and a float (price) that represents the price associated to the
        ingredient.                    
        '''
        self.name = ingredient
        self.price = float(cost)

    def __repr__(self):
        '''
        This method returns the string representation of a FoodItem.
        A FoodItem has a name and a price. The returned string is 
        formatted specifically for this module.

        Example of FoodItem representation:
            Red Bell Pepper            $0.50
            Pickles                    $0.25
        '''
        return self.name.ljust(27) +str("${:3.2f}".format(self.price))


class SandwichShop:
    '''
    This class creates a SandwichShop Object. A SandwichShop object serves to
    hold an inventory of FoodItems that is used to create a virtual sandwich. 
    The contents of the sandwich is determined by a user. This class contains 
    a total of 7 methods. You will be completing 5 of them.  
    '''

    def __init__(self, name):
        '''
        This method takes one parameter, name, that represents a person's name.

        This method has a total of 8 instance variables. The fist variable is 
        self.name which holds the string that was passed as a parameter. The 
        next 5 instance variables are lists. Each list represents a different 
        food category: meats, vegetables, breads, cheeses, and sauces. Each list
        will be populated with FoodItem objects. The 7th instance variable is
        self.selections which is a list that represents future FoodItems that
        a user will select to create their sandwich, Self.selections is to remain
        empty in the initializer. The 8th instance variable is self.storage which
        is a dictionary that maps an integer to a food category instance variable.
        This dictionary will be used to retrieve the food category instance 
        variables (lists) when a user is selecting FoodItems (this will be done 
        later on). 
        '''
        self.name = name

        self.meats = []
        self.sauces = []
        self.vegies = []
        self.breads = []
        self.cheeses = []
        self.selections = [] 

        in_file = open('choices.csv')

        for line in in_file:
            food_item = line.strip().split(',')
            category = food_item[0]
            name = food_item[1]
            price = food_item[2]

            item = FoodItem(name, float(price))

            if category == 'meat':
                self.meats.append(item)
            if category == 'sauce':
                self.sauces.append(item)
            if category == 'vegetable':
                self.vegies.append(item)
            if category == 'bread':
                self.breads.append(item)
            if category == 'cheese':
                self.cheeses.append(item)

        in_file.close()

        self.storage = {1: self.breads, 2: self.cheeses, 3: self.meats, \
                        4: self.vegies, 5: self.sauces}
        
    def __repr__(self):
        '''
        This method returns a string representation of the final SandwichShop 
        selected FoodItems. 
        '''
        
        result = "\n"
        if len(self.selections) > 3:
            result += "Wooow look at the size of that thing!\n"
        result += "That is one good looking virtual sandwich " + self.name + "!\n\n"
        result += "The items you selected:\n\n"
        
        total_cost = 0

        for item in self.selections:
            result += repr(item.name) + "\n"
            total_cost += item.price

        result += "\n Your grand total: $" + str(round(total_cost,2))

        return result

    @staticmethod
    def print_items_in_list(lst, remove = False):
        '''
        This static method takes two parameters a list and a boolean. The list
        will represent one of the food instance variable lists. 

        The method will print all the items in the list and will list them
        numerically. A user will be asked to select a food item by selecting a
        number that corresponds to the food item listed. If the user inputs an
        invalid number or the number that corresponds to "Abandon Ship!" the 
        function will return False. False indicates that the user did not wish
        to select a FoodItem listed. If the user inputs a valid number than 
        A FoodItem will be returned. If the current purpose is to remove a 
        FoodItem then an index is returned. 

        Your Optional Task:
            Analyze the given code, ask question if you have any.
        '''
        print()
        valid_opt_lst = []
        food_items = []
        for i in range(len(lst)):
            print((" " + str(i+1) + ")").rjust(4) + " " + repr(lst[i]))
            valid_opt_lst.append(i+1)
            food_items.append(lst[i])
        print((" " + str(len(food_items) +1) + ")").rjust(4) + " Abandon Ship!")
        print()
        opt_key = int(input("Select a number: "))
        if opt_key in valid_opt_lst:
            if remove:
                return opt_key-1
            else:
                return food_items[opt_key-1]
        else:
            return False

    def remove_item(self):
        '''
        This method will delete a FoodItem from the self.selections list.
        The print_items_in_list static method will be called to display the current
        (if any) items as well as ask the user to select the item the wish to 
        delete. 
        Your Optional Task:
            Analyze the given code, ask question if you have any.
        '''
        if len(self.selections) != 0:
            item_key = SandwichShop.print_items_in_list(self.selections, True)
            if item_key:
                del self.selections[item_key] #Removing FoodItem 
            else:
                print("No item removed!")
        else:
            print("No items to remove!")

    def add_item(self, code):
        '''
        This method takes one integer parameter, code, that represents a key 
        for the self.storage instance variable. 

        The overall purpose of this method is to append to the self.selections 
        list instance variable. 
        '''
        if code != 6:
            item_key = SandwichShop.print_items_in_list(self.storage[code])
            if item_key:
                self.selections.append(item_key)
            else:
                print ("Nothing selected!")            
        else:
            print("No items added!")

    @staticmethod 
    def select_main_option():
        '''
        This static method displays a menu with 3 options. A user will be asked
        to select a number that corresponds to a desired option. If the number
        selected is valid then the method will return it, otherwise the user
        is notified and is prompted to try again. This is the main menu for the
        sandwich shop. A user will have the option to add and remove items and
        decide when their order is complete. 
        '''
        print()
        print(" Construct Sandwich ".center(50, '-'))
        print("1) Add Item")
        print("2) Remove Item")
        print("3) All Done!")

        while True:
            option_key = int(input("Select a number: "))
            if option_key in [1,2,3]:
                return option_key
            else:
                print ("Not an option, try again!")
          
    @staticmethod
    def select_sandwich_options():
        '''
        This static method displays a menu with 6 options. A user will be asked
        to select a number that corresponds to a desired option. If the number
        selected is valid then the method will return it, otherwise the user
        is notified and is prompted to try again. The menu for this method
        shows 5 food categories: Bread, Cheese, Meat, Vegetable, and Sauce. 
        The 6th option, "Abort!", is provided just in case the user does not
        wish to select an option.
        '''

        print()
        print(" Select an Item ".center(50, '-'))
        print("1) Bread")
        print("2) Cheese")
        print("3) Meat")
        print("4) Vegetable")
        print("5) Sauce")
        print("6) Abort!")
        
        while True:
            option_key = int(input("Select a number: "))
            if option_key in [1,2,3,4,5,6]:
                return option_key
            else:
                print ("Not an option, try again!")


def main():
    '''
    The main method will use the SandwichShop class to simulates a restaurant 
    that makes Virtual sandwiches YUM! Main will make use of the methods
    in the class.
    '''
    print("Welcome to The One and Only Virtual Sandwich Shop!")
    print()

    customer = SandwichShop(input("Please enter your name: "))
   
    print()

    while True:
        main_selection = SandwichShop.select_main_option()

        if main_selection == 1:
            sandwich_selection = SandwichShop.select_sandwich_options()
            customer.add_item(sandwich_selection)
        elif main_selection == 2:
            customer.remove_item()
        elif main_selection == 3:
            print (repr(customer))
            break

    print()
    print("Enjoy!")


if __name__ == '__main__':
    main()