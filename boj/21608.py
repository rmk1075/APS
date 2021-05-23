dx, dy = [-1, 0, 1, 0], [0, -1, 0, 1]

def count(N, x, y, board, friend_ids):
    score, empty = 0, 0
    for d in range(4):
        tx, ty = x + dx[d], y + dy[d]
        if tx < 0 or ty < 0 or tx == N or ty == N:
            continue
        if board[tx][ty] == 0:
            empty += 1
        elif board[tx][ty] in friend_ids:
            score += 1
    return score, empty

def find(N, board, friend_ids):
    x, y, score, empty = N + 1, N + 1, 0, 0
    for i in range(N - 1, -1, -1):
        for j in range(N - 1, -1, -1):
            if board[i][j]:
                continue
            t_score, t_empty = count(N, i, j, board, friend_ids)
            if (score < t_score) or (score == t_score and empty <= t_empty):
                x, y, score, empty = i, j, t_score, t_empty
    return x, y

def allocate(N, board, student_id, friend_ids):
    x, y = find(N, board, friend_ids)
    board[x][y] = student_id

N = int(input())
board = [[0 for _ in range(N)] for _ in range(N)]
student_infos = []
for i in range(N ** 2):
    student_infos.append(list(map(int, input().split(' '))))

for student_info in student_infos:
    allocate(N, board, student_info[0], student_info[1:])

student_infos.sort()
result = 0
for i in range(N):
    for j in range(N):
        student_info = student_infos[board[i][j] - 1]
        score, empty = count(N, i, j, board, student_info[1:])
        if score == 1:
            result += 1
        elif score == 2:
            result += 10
        elif score == 3:
            result += 100
        elif score == 4:
            result += 1000

print(result)