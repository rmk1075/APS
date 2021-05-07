class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        N, L, turn = len(wordList), len(beginWord), 1
        queue, visited = [beginWord], [False for _ in range(N)]
        while queue:
            size = len(queue)
            while 0 < size:
                currentWord = queue.pop(0)
                for i in range(N):
                    if visited[i]:
                        continue

                    if self.diffValid(L, currentWord, wordList[i]):
                        if wordList[i] == endWord:
                            return turn + 1

                        queue.append(wordList[i])
                        visited[i] = True
                size -= 1
            turn += 1
        return 0
    
    def diffValid(self, L, current, compare) -> bool:
        cnt = 0
        for i in range(L):
            if current[i] != compare[i]:
                cnt += 1
                if cnt == 2:
                    return False
        return True