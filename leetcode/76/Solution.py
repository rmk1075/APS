class Solution(object):
    def minWindow(self, s, t):
        t_list = list(t)
        t_set_list = list(set(t))
        N = len(t_set_list)
        t_cnt = [0 for _ in range(N)]
        for i in range(N):
            t_cnt[i] = t_list.count(t_set_list[i])
        
        result = s + '1'
        left, right, cnt = 0, 0, 0
        while right < len(s):
            if s[right] in t_set_list:
                index = t_set_list.index(s[right])
                t_cnt[index] -= 1
                if t_cnt[index] == 0:
                    cnt += 1
            
            if cnt == N:
                while s[left] not in t_set_list or t_cnt[t_set_list.index(s[left])] < 0:
                    if s[left] in t_set_list:
                        t_cnt[t_set_list.index(s[left])] += 1
                    left += 1

                if right - left + 1 < len(result):
                    result = s[left : right + 1]

                while left < right and cnt == N:
                    if s[left] in t_set_list:
                        index = t_set_list.index(s[left])
                        t_cnt[index] += 1
                        if t_cnt[index] == 1:
                            cnt -= 1
                    left += 1
            right += 1

        if cnt == N and right - left + 1 < len(result):
            result = s[left : right + 1]

        return result if len(result) <= len(s) else ''
