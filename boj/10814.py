N = int(input())

members = []
for i in range(N):
    member = list(map(str, input().split()))
    members.append(member)

for i in sorted(members, key=lambda x: int(x[0])):
    print(i[0], i[1])
