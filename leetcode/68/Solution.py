class Solution:
    def fullJustify(self, words: List[str], maxWidth: int) -> List[str]:
        result = []
        left, right, length = 0, 0, 0
        while right < len(words):
            if maxWidth < length + len(words[right]) + right - left:
                result.append(self.text(words, maxWidth, length, left, right))

                left = right
                length = len(words[left])
            else:
                length += len(words[right])

            right += 1
        
        temp = words[left]
        for i in range(left + 1, right):
            temp += ' ' + words[i]
        temp += ' ' * (maxWidth - len(temp))
        result.append(temp)
        
        return result

    def text(self, words: List[str], maxWidth: int, length: int, left: int, right: int) -> str:
        if left == right - 1:
            return words[left] + (' ' * (maxWidth - length))
        
        count = right - left - 1
        q, r = (maxWidth - length) // count, (maxWidth - length) % count

        cnt = 0
        result = words[left]
        for i in range(left + 1, right):
            if cnt < r:
                result += ' ' * (q + 1) + words[i]
                cnt += 1
            else:
                result += ' ' * q + words[i]
        
        return result