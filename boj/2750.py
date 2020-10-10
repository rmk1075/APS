if __name__ == "__main__":
    N = int(input().strip())
    numbers = [int(input().strip()) for _ in range(N)]
    numbers.sort()

    for n in numbers:
        print(n)