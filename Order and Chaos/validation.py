import exceptions as ex 

class Validation:

    def validation_input(self,x,y,symbol,board):
        if x not in [0,1,2,3,4,5] or y not in [0,1,2,3,4,5]:
            raise ex.Inputincorrect("Move not on the board ")
        
        if symbol not in ['x','o']:
            raise ex.Symbolincorrect("Chose again a good symbol!!")

        if board.get_b(x,y) != 0:
            raise ex.SquareTaken("Square is already taken!")

    def validation_won(self,symbol):
        pass
