def tracking(N, M, temp, count):
    if count == M:
        print(' '.join(map(str, temp)))
        return
    
    for n in range(1, N+1):
        if n not in temp:
            temp.append(n)
            tracking(N, M, temp, count+1)
            temp.pop()

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    answer = list()

    temp = list()
    tracking(N, M, temp, 0)
    