class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        N = len(gas)
        diff = [gas[i] - cost[i] for i in range(N)]
        if sum(diff) < 0:
            return -1
        
        start, val = 0, 0
        for i in range(N):
            val += diff[i]
            if val < 0:
                start, val = i + 1, 0
        return start