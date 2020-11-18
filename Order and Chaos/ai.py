from board import Board
import random
import exceptions as ex 
class Ai:
    def __init__(self,board,service):
        self.board=board
        self.service=service
    
    def free_moves(self):
        moves=[]
        for i in range(6):
            for j in range(6):
                if self.board.get_b(i,j) == 0:
                    moves.append([i,j])
        print(len(moves))
        return moves

    def random_move(self):
        # we will need 2 random 1 for the move and 1 for the symbol
        if self.win_move() == True:
            return
        free_list=self.free_moves()
        len_free=len(free_list)
        if len_free !=0:
            rand1=random.randint(0,len_free-1)
            # we got the move we must separate the x and the y
            pair = free_list[rand1]
            x = pair[0]
            y = pair[1]
            #======
            simbol=[1,-1]
            rand2= random.randint(0,1)
            symbol= simbol[rand2]
            self.board.setter_b(x,y,symbol)
            free_list = self.free_moves()
            if len(free_list)==0:
                raise ex.ComputerWon("Chaos has Won!!!")
    
    def win_move(self):
        for i in range(6):
            for j in range(6):
                if self.board.get_b(i, j) == 0:
                    self.board.setter_b(i, j, 1)
                    if self.service.Order_won() == True:
                        self.board.setter_b(i, j, -1)
                        return True
                    self.board.setter_b(i, j, -1)
                    if self.service.Order_won() == True:
                        self.board.setter_b(i, j, 1)
                        return True
                    self.board.setter_b(i, j, 0)
        return False
            



    
