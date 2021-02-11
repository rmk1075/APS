class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        slen, wlen = len(s), len(words)
        indexList = list()
        for i in range(slen):
            tempList = list()
            for j in range(wlen):
                if i + len(words[j]) <= slen and s[i:i + len(words[j])] == words[j]:
                    tempList.append(j)
            indexList.append(tempList)

        answer = list()
        for i in range(slen):
            if self.find(0, i, slen, wlen, indexList, words, [False] * wlen):
                answer.append(i)

        return answer

    def find(self, count: int, index: int, slen: int, wlen: int, indexList: List[List[int]], words: List[str], visited: List[bool]) -> bool:
        if count == wlen:
            return True

        if slen <= index:
            return False

        for i in indexList[index]:
            l = index + len(words[i])

            if not visited[i]:
                visited[i] = True
                if self.find(count + 1, l, slen, wlen, indexList, words, visited):
                    return True
                visited[i] = False
        
        return False