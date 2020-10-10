import sys
from collections import deque

cards = deque(i for i in range(1, int(sys.stdin.readline())+1))
while 1 != len(cards):
    cards.popleft()
    cards.rotate(-1)
print(cards[0])