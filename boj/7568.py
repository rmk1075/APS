if __name__ == "__main__":
    N = int(input().strip())
    persons = list()

    for _ in range(N):
        persons.append(tuple(map(int, input().strip().split())))
    
    bigger = [0 for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if i != j and persons[i][0] < persons[j][0] and persons[i][1] < persons[j][1]:
                bigger[i] += 1
 
    for b in bigger:
        print(b+1, end=' ')
