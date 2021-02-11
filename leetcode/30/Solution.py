from collections import Counter, defaultdict

class Solution:
    def findSubstring(self, s: str, words: List[str]) -> List[int]:
        answer, wordsMap = list(), Counter(words)
        totalLen, wlen = len(words) * len(words[0]), len(words[0])

        # 시작점 경우의 수 - wlen
        for i in range(wlen):
            start, count = i, 0
            countMap = defaultdict(int)

            # 각 시작점에서 s에 대한 반복문
            for j in range(i, len(s) - wlen + 1, wlen):
                word = s[j:j + wlen]

                if word in wordsMap:
                    countMap[word] += 1
                    count += 1

                    # 중복되는 word의 발생으로 countMap의 값이 wordsMap의 값보다 큰 경우
                    # 해당 word 제거를 위해서 start window 이돔 및 countMap에서 제거
                    while wordsMap[word] < countMap[word]:
                        countMap[s[start:start + wlen]] -= 1
                        count -= 1
                        start += wlen
                    
                    if count == len(words):
                        answer.append(start)
                else:
                    start, count = j + wlen, 0
                    countMap = defaultdict(int)
                
        return answer