n = int(input())
triangle = []

for _ in range(n):
    triangle.append(list(map(int, input().split())))

for i in range(1, n):
    for j in range(i+1):
        if j == 0:
            triangle[i][j] += triangle[i-1][j]
        elif j == i:
            triangle[i][j] += triangle[i-1][j-1]
        else:
            if triangle[i-1][j-1] < triangle[i-1][j]:
                triangle[i][j] += triangle[i-1][j]
            else:
                triangle[i][j] += triangle[i-1][j-1]

max = 0
for i in triangle[n-1]:
    if max < i:
        max = i

print(max)