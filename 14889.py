import sys

N = int(sys.stdin.readline())
S = [[i for i in list(map(int, sys.stdin.readline().strip().split()))] for _ in range(N)]
diff = 4000

def team(team1, x):
    if len(team1) == N/2:
        val1, val2 = 0, 0
        for i in range(N):
            for j in range(N):
                if i != j and i < j:
                    if i in team1 and j in team1:
                        val1 += S[i][j]+S[j][i]
                    elif i not in team1 and j not in team1:
                        val2 += S[i][j]+S[j][i]
        global diff
        if abs(val1-val2) < diff:
            diff = abs(val1-val2)
    else:
        for i in range(x+1, N):
            team1.append(i)
            team(team1, x+1)
            team1.remove(i)

team([0], 0)
print(diff)

# import sys

# N = int(sys.stdin.readline())
# S = [[i for i in list(map(int, sys.stdin.readline().strip().split()))] for _ in range(N)]
# diff = 4000

# def team(team1, team2):
#     if len(team1) == N/2:
#         global diff
#         val1, val2 = 0,0
#         for i in range(N):
#             for j in range(N):
#                 if i in team1 and j in team1:
#                     val1 += S[i][j]
#                 elif i in team2 and j in team2:
#                     val2 += S[i][j]
#         if abs(val1-val2) < diff:
#             diff = abs(val1-val2)
#     else:
#         for i in range(N):
#             if i not in team1:
#                 team1.add(i)
#                 team2.remove(i)
#                 team(team1, team2)
#                 team1.remove(i)
#                 team2.add(i)

# team(set([0]), set([i for i in range(1, N)]))
# print(diff)

## def team 1
# def team(members, val):
#     if len(members) == N/2:
#         members2 = set([i for i in range(N)]) - members

#         val2 = 0
#         for m1 in members2:
#             for m2 in members2:
#                 val2 += S[m1][m2]

#         global diff
#         if abs(val-val2) < diff:
#             diff = abs(val-val2)
#     else:
#         for i in range(N):
#             if i not in members:
#                 members.add(i)
#                 temp = 0
#                 for m in members:
#                     temp += S[i][m]+S[m][i]
#                 team(members, val+temp)
#                 members.remove(i)

## def team 1
# def team(members):
#     if len(members) == N/2:
#         members2 = [i for i in range(N)]

#         team1 = 0
#         for m1 in members:
#             members2.remove(m1)
#             for m2 in members:
#                 team1 += S[m1][m2]

#         team2 = 0
#         for m1 in members2:
#             for m2 in members2:
#                 team2 += S[m1][m2]

#         global diff
#         if abs(team1-team2) < diff:
#             diff = abs(team1-team2)
#     else:
#         for i in range(N):
#             if i not in members:
#                 members.append(i)
#                 team(members)
#                 members.remove(i)
