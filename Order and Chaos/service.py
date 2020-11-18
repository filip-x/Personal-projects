from board import Board
class Service:

    def __init__(self,board):
        self.board = board

    def move(self,x,y,symbol):
        self.board.setter_b(x,y,symbol)
        
    def Order_won(self):

        for i in range(6):
            for j in range(6):
                if self.board.get_b(i, j)!=0:
                    if j+4 < 6 and j+4 >=0 and self.board.get_b(i, j+1) == self.board.get_b(i, j) and self.board.get_b(i, j+2) == self.board.get_b(i, j) and self.board.get_b(i, j+3) == self.board.get_b(i, j) and self.board.get_b(i, j+4) == self.board.get_b(i, j):
                        return True
                    elif i+4 < 6 and i+4 >= 0 and self.board.get_b(i+1, j) == self.board.get_b(i, j) and self.board.get_b(i+2, j) == self.board.get_b(i, j) and self.board.get_b(i+3, j) == self.board.get_b(i, j) and self.board.get_b(i+4, j) == self.board.get_b(i, j):
                        return True
                    elif i+4 < 6 and i+4 >= 0 and j+4<6 and j+4 >=0 and self.board.get_b(i+1, j+1) == self.board.get_b(i, j) and self.board.get_b(i+2, j+2) == self.board.get_b(i, j) and self.board.get_b(i+3, j+3) == self.board.get_b(i, j) and self.board.get_b(i+4, j+4) == self.board.get_b(i, j):
                        return True
                    elif i-4 < 6 and i-4 >= 0 and j+4 < 6 and j+4 >= 0 and self.board.get_b(i-1, j+1) == self.board.get_b(i, j) and self.board.get_b(i-2, j+2) == self.board.get_b(i, j) and self.board.get_b(i-3, j+3) == self.board.get_b(i, j) and self.board.get_b(i-4, j+4) == self.board.get_b(i, j):
                        return True
        return False

    def save_file(self):
        list_file=""
        f = open("file.txt","w")
        for i in range(6):
            for j in range(6):
                list_file=list_file + str(self.board.get_b(i,j))+" "
        f.write(list_file)        
        f.close()

    def read_file(self):
        list_matrix=[]
        try: 
            f=open("file.txt","r")
            matrix=f.read()
            list_matrix=matrix.split()
            for i in range(len(list_matrix)):
                list_matrix[i]=int(list_matrix[i])
            self.board.board = list_matrix

            f.close()
        except Exception as e:
            print(e)