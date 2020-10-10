N = int(input())
output = []

def hanoi(num, a, b, c, output):
    if(num == 1):
        output.append([a,c])
        return None

    hanoi(num-1, a, c, b, output)
    output.append([a,c])
    hanoi(num-1, b, a, c, output)

hanoi(N, 1, 2, 3, output)
print(len(output))
for a, b in output:
    print(a, b)
