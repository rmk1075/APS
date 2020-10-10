import sys

if __name__ == "__main__":
    N = int(sys.stdin.readline())
    numbers = dict()

    sum_val = 0
    most = 0
    for _ in range(N):
        num = int(sys.stdin.readline())

        sum_val += num

        if num in numbers:
            numbers[num] += 1
        else:
            numbers[num] = 1
        
        if most < numbers[num]:
            most = numbers[num]

    keys = sorted(numbers.keys())

    mid_val = 0
    most_val = 0
    count = 0
    check = 0
    for k in keys:
        if mid_val == 0:
            count += numbers[k]
            if N/2 <= count:
                mid_val = k
        
        if check < 2:
            if most == numbers[k]:
                most_val = k
                check += 1
            
    print(int(round(sum_val/N, 0)))
    print((mid_val))
    print(most_val)
    print(keys[len(keys)-1] - keys[0])
