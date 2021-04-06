class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        result = []
        if s == '':
            return result

        if s[0] == '0':
            self.dfs(s[1:], 1, '0', result)
        else:
            for i in range(1, 4):
                if int(s[:i]) <= 255:
                    self.dfs(s[i:], 1, s[:i], result)
                    
        return result
    
    def dfs(self, s: str, cnt: int, temp: str, result: List[str]):
        if cnt == 4:
            if s == '':
                result.append(temp)
            return
        if s == '':
            return

        temp += '.'
        if s[0] == '0':
            self.dfs(s[1:], cnt + 1, temp + '0', result)
            return

        for i in range(1, 4):
            if int(s[:i]) <= 255:
                self.dfs(s[i:], cnt + 1, temp + s[:i], result)
            if i == len(s):
                break