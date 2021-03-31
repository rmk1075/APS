class Solution(object):
    def merge(self, nums1, m, nums2, n):
        if m == 0:
            for i in range(n):
                nums1[i] = nums2[i]

        if n == 0:
            return

        nums = nums1[:m]
        i, j, current = 0, 0, 0
        while i < m and j < n:
            if nums[i] < nums2[j]:
                nums1[current] = nums[i]
                i += 1
            else:
                nums1[current] = nums2[j]
                j += 1
            current += 1
        
        while i < m:
            nums1[current] = nums[i]
            current += 1
            i += 1
        while j < n:
            nums1[current] = nums2[j]
            current += 1
            j += 1