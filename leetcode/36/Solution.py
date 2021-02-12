class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        # validate box 
        for i in range(3):
            for j in range(3):
                if not self.validBox(board, i * 3, j * 3):
                    return False
        
        # valid row and column
        for i in range(9):
            if not self.validRow(board, i) or not self.validCol(board, i):
                return False
        
        return True
    
    def validBox(self, board: List[List[str]], x: int, y: int) -> bool:
        nums = [False] * 10
        for i in range(3):
            for j in range(3):
                val = board[x + i][y + j]
                if val == '.':
                    continue
                else:
                    val = int(val)

                if nums[val]:
                    return False
                nums[val] = True

        return True
    
    def validRow(self, board: List[List[str]], r: int):
        nums = [False] * 10
        for c in range(9):
            val = board[r][c]
            if val == '.':
                    continue
            else:
                val = int(val)

            if nums[val]:
                return False
            nums[val] = True

        return True

    def validCol(self, board: List[List[str]], c: int):
        nums = [False] * 10
        for r in range(9):
            val = board[r][c]
            if val == '.':
                    continue
            else:
                val = int(val)

            if nums[val]:
                return False
            nums[val] = True

        return True