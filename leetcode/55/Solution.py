class Solution:
    def canJump(self, nums: List[int]) -> bool:
        n, left, right = len(nums) - 1, 0, nums[0]
        while left < right:
            distance = right
            for index in range(left, right + 1):
                distance = max(distance, index + nums[index])
                if n <= distance:
                    return True

            left = right
            right = distance

        return n <= right