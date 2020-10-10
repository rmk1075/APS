import sys

N = int(sys.stdin.readline())
board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
dp = [[[0 for _ in range(3)] for _ in range(N)] for _ in range(N)]

for i in range(1, N):
    if board[0][i] == 1:
        break
    dp[0][i][0] = 1

for i in range(1, N):
    for j in range(2, N):
        if board[i][j] == 1:
            continue

        dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2]
        dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2]
        if board[i][j-1] != 1 and board[i-1][j] != 1:
            dp[i][j][2] += dp[i-1][j-1][0]+dp[i-1][j-1][1]+dp[i-1][j-1][2]
print(sum(dp[N-1][N-1]))
# print(dp)


# import sys
#
# N = int(sys.stdin.readline())
# board = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]
# ans = 0
#
#
# def check(tail):
#     return board[tail[0]+1][tail[1]] != 1 and board[tail[0]][tail[1]+1] != 1 and board[tail[0]+1][tail[1]+1] != 1
#
#
# def move(tail, status):
#     if tail == [N-1, N-1]:
#         global ans
#         ans += 1
#         return
#
#     if status == 0:
#         # horizontal
#         if tail[1]+1 < N and board[tail[0]][tail[1]+1] != 1:
#             move([tail[0], tail[1]+1], 0)
#
#         if tail[0]+1 < N and tail[1]+1 < N and check(tail):
#             move([tail[0]+1, tail[1]+1], 2)
#     elif status == 1:
#         # vertical
#         if tail[0] + 1 < N and board[tail[0]+1][tail[1]] != 1:
#             move([tail[0]+1, tail[1]], 1)
#
#         if tail[0] + 1 < N and tail[1] + 1 < N and check(tail):
#             move([tail[0]+1, tail[1] + 1], 2)
#     elif status == 2:
#         # diagonal
#         if tail[1] + 1 < N and board[tail[0]][tail[1] + 1] != 1:
#             move([tail[0], tail[1] + 1], 0)
#
#         if tail[0] + 1 < N and board[tail[0]+1][tail[1]] != 1:
#             move([tail[0]+1, tail[1]], 1)
#
#         if tail[0] + 1 < N and tail[1] + 1 < N and check(tail):
#             move([tail[0]+1, tail[1] + 1], 2)
#     return
#
#
# move([0, 1], 0)
# print(ans)