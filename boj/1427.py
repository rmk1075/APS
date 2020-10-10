import sys

if __name__ == "__main__":
    N = [int(char) for char in sys.stdin.readline().strip()]
    N.sort(reverse=True)
    print(''.join(list(map(str, N))))
