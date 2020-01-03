import sys

exp = sys.stdin.readline().strip().split('-')
exp_ = list()
for e in exp:
    exp_.append(sum(list(map(int,e.split('+')))))
for i in range(1, len(exp_)):
    exp_[i] = exp_[i-1]-exp_[i]
print(exp_[len(exp_)-1])