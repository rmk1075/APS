class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = []
        for i in range(len(s)):
            if s[i] == '(':
                stack.append(i)
            else:
                if len(stack) == 0:
                    stack.append(i)
                else:
                    if s[stack[-1]] == '(':
                        stack.pop()
                    else:
                        stack.append(i)

        if len(stack) == 0:
            return len(s)

        answer, left, right = 0, 0, len(s)
        while 0 < len(stack):
            left = stack.pop()
            answer = max(answer, right - left - 1)
            right = left
        answer = max(answer, right)

        return answer
