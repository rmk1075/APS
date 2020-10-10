import sys

if __name__ == "__main__":
    N = int(sys.stdin.readline())

    words = set()
    for _ in range(N):
        words.add(sys.stdin.readline().strip())

    for p in sorted(words, key=lambda l: (len(l), l)):
        print(p)
