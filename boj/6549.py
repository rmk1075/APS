import sys

while True:
    temp = list(map(int, sys.stdin.readline().split()))
    if temp[0] == 0:
        break
    else:
        n = temp[0]
        h = temp[1:]

        left, right = [0 for _ in range(n)], [0 for _ in range(n)]
        for i in range(n):
            val = i-1
            while True:
                if val == -1 or h[val] < h[i]:
                    break
                else:
                    val = left[val]

            left[i] = val

        for i in range(n-1, -1, -1):
            val = i+1
            while True:
                if val == n or h[val] < h[i]:
                    break
                else:
                    val = right[val]
            right[i] = val

        maxVal = 0
        for i in range(n):
            maxVal = max(maxVal, (right[i]-left[i]-1)*h[i])
        print(maxVal)

            # val = 0
            # for j in range(i + 1, n):
            #     if h[j] < h[i]:
            #         break
            #     elif h[j] == h[i]:
            #         val += right[j]
            #     val += 1
            # right[i] = val

        #     ans = max(ans, (right[i]-left[i])*h[i])
        # print(ans)
        # print(left, right)

        # left, right = [1 for _ in range(n)], [1 for _ in range(n)]
        # for i in range(1, n):
        #     if h[i] <= h[i-1]:
        #         left[i] += left[i-1]
        # for i in range(n-2, -1):
        #     if h[i] <= h[i+1]:
        #         right[i] += right[i-1]
        # ans = 0
        # for i in range(n):
        #     ans = max(ans, (left[i]+right[i]-1)*h[i])
        #
        # print(ans)