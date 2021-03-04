class Solution:
    def insert(self, intervals: List[List[int]], newInterval: List[int]) -> List[List[int]]:
        n, left, right = len(intervals), 0, len(intervals) - 1
        while left < n and intervals[left][1] < newInterval[0]:
            left += 1

        if left == n:
            intervals.append(newInterval)
            return intervals

        while -1 < right and newInterval[1] < intervals[right][0]:
            right -= 1

        if right == -1:
            intervals.insert(0, newInterval)
            return intervals
        
        newInterval = [min(newInterval[0], intervals[left][0]), max(newInterval[1], intervals[right][1])]

        l = intervals[:left] if left != 0 else []
        r = intervals[right + 1:] if right != n - 1 else []

        return l + [newInterval] + r