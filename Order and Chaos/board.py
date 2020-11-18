class Board:
    def __init__(self):
        self.board=[0]*36

    def get_b(self,x,y):
        return self.board[6*x+y]

    def setter_b(self,x,y,symbol):
        self.board[6*x+y]=symbol
    
    def get_row(self,x):
        return self.board[x*6:6*x+6]

        