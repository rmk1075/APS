class Solution(object):
    def search(self, nums, target):
        if nums[-1] < target < nums[0]:
            return False
        
        left, right = 0, len(nums)
        while left < right:
            mid = int((left + right) / 2)

            if nums[mid] == target:
                return True
            
            if nums[left] == nums[mid]:
                left = mid + 1
                continue

            isMidLeft = nums[left] <= nums[mid]
            isTargetLeft = nums[left] <= target
            if isMidLeft ^ isTargetLeft:
                if isMidLeft:
                    left = mid + 1
                else:
                    right = mid
            else:
                if nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid
        
        return False
