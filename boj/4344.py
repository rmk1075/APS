for c in range(int(input())):
    N = list(map(int, input().split()))
    average = sum(N[1:])/N[0]
    n = 0
    for i in N[1:]:
        if(average < i):
            n += 1
    print(str('%0.3f' % round(n/N[0]*100, 3))+'%')
