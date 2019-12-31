import sys

X = int(sys.stdin.readline())
steps = dict()

def step(x):
    if x < 2:
        return 0
    if x in steps:
        return steps[x]
    else:
        # compare divide by 2 with divide by 3 (each value get the counts from addition 1.)
        steps[x] = 1+min(step(x//3)+x%3, step(x//2)+x%2)
        return steps[x]

print(step(X))

# import sys

# X = int(sys.stdin.readline())

# if X == 1:
#     print(0)
#     sys.exit(0)

# steps = dict()
# steps[1] = 0

# for count in range(1, X):
#     if count in steps:
#         if count+1 not in steps:
#             steps[count+1] = steps[count]+1
#         else:
#             steps[count+1] = min(steps[count+1], steps[count]+1)

#         if 2*count not in steps:
#             steps[2*count] = steps[count]+1
#         else:
#             steps[2*count] = min(steps[2*count], steps[count]+1)
        
#         if 3*count not in steps:
#             steps[3*count] = steps[count]+1
#         else:
#             steps[3*count] = min(steps[3*count], steps[count]+1)

# print(steps[X])
