import sys

height = [int(sys.stdin.readline()) for _ in range(9)]

def find(sum, members):
    if 7 < len(members):
        return
        
    if len(members) == 7 and sum == 100:
        for m in sorted(members):
            print(m)
        sys.exit(0)
    
    for i in range(len(height)):
        if height[i] not in members and sum+height[i] <= 100:
            members.append(height[i])
            find(sum+height[i], members)
            members.pop()

find(0, [])