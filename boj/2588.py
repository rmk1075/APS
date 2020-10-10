a = int(input())
b = int(input())

print(a * (b%10))
print(int((a * ((b%100) - (b%10)))/10))
print(int((a * (b - (b%100)))/100))
print(a * b)
