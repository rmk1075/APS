class Solution:
    def search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left < right:
            mid = int((left + right) / 2)
            if nums[mid] == target:
                return mid
            
            if nums[left] < nums[right]:
                if nums[mid] < target:
                    left = mid + 1
                else:
                    right = mid
            else:
                if nums[mid] < target:
                    if nums[right] < target:
                        if nums[right] < nums[mid]:
                            left = mid + 1
                        else:
                            right = mid
                    else:
                        left = mid + 1
                else:
                    if nums[right] < target:
                        right = mid
                    else:
                        if nums[right] < nums[mid]:
                            left = mid + 1
                        else:
                            right = mid
        
        if left == right and nums[int((left + right) / 2)] == target:
            return int((left + right) / 2)

        return -1