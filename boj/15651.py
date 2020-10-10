def tracking(N, M, temp, answer):
    if len(temp) == M-1:
        for n in range(1, N+1):
            temp.append(n)
            print(' '.join(map(str, temp)))
            temp.pop()
        
        return
    
    for n in range(1, N+1):
        temp.append(n)
        tracking(N, M, temp, answer)
        temp.pop()

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    answer = list()

    temp = list()
    tracking(N, M, temp, answer)
    
