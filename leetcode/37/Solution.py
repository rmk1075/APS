class Solution:
    def solveSudoku(self, board: List[List[str]]) -> None:
        boxIndexList = [[0,0,0,1,1,1,2,2,2]] * 3 + [[3,3,3,4,4,4,5,5,5]] * 3 + [[6,6,6,7,7,7,8,8,8]] * 3
        boxList = [[] for _ in range(9)]

        count = 0
        for i in range(9):
            for j in range(9):
                if board[i][j] == '.':
                    count += 1
                    continue

                # box
                boxList[boxIndexList[i][j]].append(board[i][j])

        # if completed
        if count == 0:
            return

        self.dfs(count, 0, 0, board, boxIndexList, boxList)
    
    def dfs(self, count: int, x: int, y: int, board: List[List[str]], boxIndexList: List[List[int]], boxList: List[int]) -> bool:
        if count == 0:
            return True

        for i in range(x, 9):
            for j in range(9):
                if board[i][j] != '.':
                    continue
                
                boxIndex = boxIndexList[i][j]
                for num in range(1, 10):

                    if str(num) in boxList[boxIndex] or str(num) in board[i] or self.checkCol(j, str(num), board):
                        continue

                    board[i][j] = str(num)
                    boxList[boxIndex].append(str(num))

                    if self.dfs(count - 1, i, j, board, boxIndexList, boxList):
                        return True

                    board[i][j] = '.'
                    boxList[boxIndex].pop()
                
                if board[i][j] == '.':
                    return False

        return False

    def checkCol(self, j: int, num: str, board: List[List[str]]) -> bool:
        for i in range(9):
            if board[i][j] == num:
                return True

        return False