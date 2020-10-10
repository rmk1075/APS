from collections import deque

def tracking(queue, N):
    if len(queue) == N:
        return 1

    answer = 0
    for n in range(N):
        if n in queue:
            continue
        
        check = True
        for i in range(len(queue)):
            if len(queue)-i == abs(n-queue[i]):
                check = False
                break
        
        if check:
            queue.append(n)
            answer += tracking(queue, N)
            queue.pop()
    
    return answer

if __name__ == '__main__':
    N = int(input().strip())
    answer = 0
    for n in range(N):
        answer += tracking([n], N)

    print(answer)
