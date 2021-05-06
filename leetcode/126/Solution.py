class Solution:
    def findLadders(self, beginWord: str, endWord: str, wordList: List[str]) -> List[List[str]]:
        if endWord not in wordList:
            return []

        result = []
        N, L, idx = len(wordList), len(beginWord), 0
        queue, visited = [(beginWord, [beginWord])], [-1 for _ in range(N)]
        while queue:
            size = len(queue)
            while 0 < size:
                currentWord, currentList = queue.pop(0)
                if currentWord == endWord:
                    result.append(currentList)
                else:
                    for i in range(N):
                        if visited[i] != -1 and visited[i] < idx:
                            continue
                        
                        if self.diffValid(L, currentWord, wordList[i]):
                            visited[i] = idx
                            queue.append((wordList[i], currentList + [wordList[i]]))
                size -= 1
            idx += 1
        return result

    def diffValid(self, L: int, currentWord: str, compareWord: str) -> bool:
        cnt = 0
        for i in range(L):
            if currentWord[i] != compareWord[i]:
                cnt += 1
                if cnt == 2:
                    return False
        if cnt == 1:
            return True
