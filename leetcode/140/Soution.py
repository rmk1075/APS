class Solution:
    def wordBreak(self, s: str, wordDict: List[str]) -> List[str]:
        N = len(s)
        wordDict = set(wordDict)
        arr = [[False for _ in range(N)] for _ in range(N)]
        for i in range(N):
            for j in range(i, N):
                arr[i][j] = s[i : j + 1] in wordDict
        result = []
        self.dfs(0, N, s, arr, '', result)
        return result
    
    def dfs(self, idx: int, N: int, s: str, arr: List[List[bool]], temp: str, result: List[str]):
        if idx == N:
            result.append(temp[:-1])
            return        
        for i in range(idx, N):
            if arr[idx][i]:
                self.dfs(i + 1, N, s, arr, temp + s[idx : i + 1] + ' ', result)