class Solution(object):
    def subsets(self, nums):
        nums.sort()
        result = [[]]
        N, past = len(nums), -99
        for i in range(N):
            if nums[i] == past:
                continue
            result.append([nums[i]])
            past = nums[i]
            self.find(N, nums, i + 1, [nums[i]], result)

        return result

    def find(self, N, nums, idx, temp, result):
        if idx == N:
            return

        past = -99
        for i in range(idx, N):
            if nums[i] == past:
                continue
            result.append(temp + [nums[i]])
            past = nums[i]
            self.find(N, nums, i + 1, temp + [nums[i]], result)