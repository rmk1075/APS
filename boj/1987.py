import sys

answer = 1
board, dx, dy = [], [-1, 0, 1, 0], [0, -1, 0, 1]
R, C = map(int, sys.stdin.readline().strip().split())
for _ in range(R):
    board.append(sys.stdin.readline().strip())

queue = set([(0, 0, board[0][0])])
while queue:
    x, y, visited = queue.pop()
    for d in range(4):
        cx, cy = x + dx[d], y + dy[d]
        if cx < 0 or cy < 0 or R <= cx or C <= cy or board[cx][cy] in visited:
            continue

        queue.add((cx, cy, visited + board[cx][cy]))
        answer = max(answer, len(visited) + 1)

print(answer)