def find(N, M, cards, picked, count):
    if count == 3:
        return sum(picked)
    
    val = 0
    for c in cards:
        if c not in picked and sum(picked) + c <= M:
            picked.append(c)
            temp = find(N, M, cards, picked, count+1)
            picked.pop()
            if val < temp:
                val = temp
    return val

if __name__ == "__main__":
    N, M = map(int, input().strip().split())
    cards = list(map(int, input().strip().split()))

    print(find(N, M, cards, [], 0))
    
