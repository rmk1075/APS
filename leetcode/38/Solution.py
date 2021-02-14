class Solution:
    def countAndSay(self, n: int) -> str:
        if n == 1:
            return "1"
        return self.count(self.countAndSay(n - 1))
        
    def count(self, seq: str) -> str:
        result = str()
        num, cnt = seq[0], 0
        for s in seq:
            if s == num:
                cnt += 1
            else:
                result += str(cnt) + num
                num = s
                cnt = 1
        
        result += str(cnt) + num
        return result