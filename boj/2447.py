def pattern(board, x, y, N):
    if N == 1:
        board[x][y] = '*'
        return
    
    for i in range(3):
        for j in range(3):
            if i == 1 and j == 1:
                continue
            else:
                time = int(N/3)
                pattern(board, x+(i*time), y+(j*time), time)

if __name__ == "__main__":
    N = int(input().strip())

    board = [[' ' for _ in range(N)] for _ in range(N)]
    pattern(board, 0, 0, N)

    for i in range(N):
        print(''.join(board[i]))
