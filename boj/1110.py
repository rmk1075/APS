input = int(input())
temp = input
n = 0
while n == 0 or input != temp:
    n = n+1
    temp = ((temp%10)*10) + ((int(temp/10) + temp%10)%10)
print(n)
