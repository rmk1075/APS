import re

class Solution:
    integerObj = re.compile("[^0-9]")

    def isNumber(self, s: str) -> bool:
        eE = re.compile("[eE]")
        eEobj = re.findall(eE, s)
        if 1 < len(eEobj):
            return False
        elif len(eEobj) == 1:
            index = re.search(eE, s).start()
            return (self.isDecimal(s[:index]) or self.isInteger(s[:index])) and self.isInteger(s[index + 1:])

        return self.isDecimal(s) or self.isInteger(s)
    
    def isDecimal(self, s: str) -> bool:
        if 0 < len(s) and (s[0] == '+' or s[0] == '-'):
            s = s[1:]

        if '.' not in s or len(s) == 0:
            return False

        seq = re.split('[+-]', s)
        if len(seq) != 1:
            return False
        
        if 1 < s.count('.'):
            return False
        
        seq = s.split('.')
        if len(seq[0]) == 0:
            return self.isInteger(seq[1])
        else:
            if len(seq[1]) == 0:
                return self.isInteger(seq[0])
            else:
                return self.isInteger(seq[0]) and self.isInteger(seq[1])
    
    def isInteger(self, s: str) -> bool:
        if 0 < len(s) and (s[0] == '+' or s[0] == '-'):
            s = s[1:]
        
        if len(s) == 0:
            return False
        
        seq = re.split('[+-]', s)
        if len(seq) != 1:
            return False
        
        if self.integerObj.search(s):
            return False
        
        return True
