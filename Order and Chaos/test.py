from board import Board as B
from service import Service as s
from ai import Ai as AI
def test():
    newB = B()
    newS = s(newB)
    newAi = AI(newB, newS)
    newS.move(0, 0, 1)
    assert newB.get_b(0, 0) == 1
    count = 0
    for i in range(6):
        for j in range(6):
            if newB.get_b(i, j) == 1:
                count += 1
    assert count == 1
    newS.move(0, 1, 1)
    newS.move(0, 2, 1)
    newS.move(0, 3, 1)
    assert newS.Order_won() == False
    newS.move(0, 4, 1)
    assert newS.Order_won() == True
    assert newAi.free_moves() == 6 * 6 - 5
    