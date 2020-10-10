if __name__ == "__main__":
    N = int(input().strip())

    val = 0
    for i in range(N):
        if i + sum([int(n) for n in str(i)]) == N:
            val = i
            break
    print(val)