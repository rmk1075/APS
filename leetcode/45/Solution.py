class Solution:
    def jump(self, nums: List[int]) -> int:
        if len(nums) == 1:
            return 0

        left, right, end = 0, 1, len(nums) - 1
        cnt, queue = 1, [0]
        while queue:
            size = len(queue)
            for _ in range(size):
                current = queue.pop(0)
                distance = current + nums[current] + 1
                if end < distance:
                    return cnt
                
                for i in range(right, distance):
                    queue.append(i)
                
                right = max(right, distance)
            
            cnt += 1

        return cnt