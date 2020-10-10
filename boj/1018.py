def find(board, x, y):
    color = ['W', 'B']

    ans = 64
    for c in color:
        temp = c
        val = 0
        for i in range(x, x+8):
            for j in range(y, y+8):
                if board[i][j] == temp:
                    val += 1
                    temp = color[abs(color.index(temp)-1)]
                else:
                    temp = board[i][j]
            temp = color[abs(color.index(temp)-1)]
        if val < ans:
            ans = val

    return ans

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    board = list()

    for i in range(N):
        board.append(list(map(str, [e for e in input().strip()])))
    
    val = M*N
    for i in range(0, N-7):
        for j in range(M-7):
            temp = find(board, i, j)
            if temp < val:
                val = temp
    print(val)
