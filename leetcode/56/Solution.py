class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals = sorted(intervals, key = lambda k : (k[0], k[1]))
        result = []
        start, end = intervals[0][0], intervals[0][1]
        for interval in intervals:
            if end < interval[0]:
                result.append([start, end])
                start, end = interval[0], interval[1]
            else:
                end = max(end, interval[1])
        result.append([start, end])

        return result