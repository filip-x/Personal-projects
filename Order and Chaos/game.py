from board import Board
from service import Service
from texttable import Texttable
from validation import Validation
import exceptions as ex
from ai import Ai

class Game:

    def __init__(self):
        self.board = Board()
        self.service = Service(self.board)

    def dictionary(self):
        return {0:' ',-1:'o',1:'x'}

    def dictionary_inverted(self):
        return {' ':0,'o':-1,'x':1}

    def __str__(self):
        d = self.dictionary()
        t=Texttable()
        for i in range(6):
            r = self.board.get_row(i)
            for j in range(6):
                v = d[r[j]]
                r[j]=v
            t.add_row(r)
        return t.draw()

    #===================================== print the table and form the dict to implement  the X AND O and

    def input(self):
        d_i=self.dictionary_inverted()
        x= int(input("number for the row: "))
        y = int(input("number for the column: "))
        symbol=input("Chose between x and o to put: ")
        choice=int(input("Write 1 if you wnat to save or 0 if you don't: "))
        if choice == 1:
            self.service.save_file()
        elif choice != 0:
            print("Wrong number!")
            
        Validation().validation_input(x,y,symbol,self.board)
        self.service.move(x,y,d_i[symbol])# punem asa ca atunci cand se apeleaza in afisare sa preia 1 sau -1 sau 0 pentru a pune cum trebuie caracterele
    
 
def start():
    g = Game()
    try:
        choice2=(int(input("Write 1 to reload or 0 to have a new game:")))
        if choice2 == 1:
            g.service.read_file()
        elif choice2 !=0:
            print("Wrong number!")
    except Exception as e:
        print(e)
   
    print(g)
    ai =Ai(g.board, g.service)
    while True:
        try:
            g.input()
            if g.service.Order_won()== True:
                raise ex.HumanWon("Order has Won!!!")
                
            ai.random_move()
            if g.service.Order_won() == True:
                raise ex.HumanWon("Order has Won!!!")
               
            print(g)
            ai.free_moves()
        except ex.HumanWon as e:
            print(e)
            break
        except Exception as e:
            print(e)



start()    
