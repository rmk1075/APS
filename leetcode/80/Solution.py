class Solution(object):
    def removeDuplicates(self, nums):
        N = len(nums)
        start, left, right = 0, 0, 1
        while right < N:
            if nums[left] != nums[right]:
                if 2 < right - left:
                    nums[start] = nums[left]
                    start += 1
                    nums[start] = nums[left]
                    start += 1
                    left = right
                else:
                    for i in range(right - left):
                        nums[start] = nums[left]
                        start += 1
                    left = right
            right += 1
        if 2 < right - left:
            nums[start] = nums[left]
            start += 1
            nums[start] = nums[left]
            start += 1
        else:
            for i in range(right - left):
                nums[start] = nums[left]
                start += 1
        return start