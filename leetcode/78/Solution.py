class Solution(object):
    def subsets(self, nums):
        numsDict = dict()
        for num in nums:
            if num in numsDict:
                numsDict[num] += 1
            else:
                numsDict[num] = 1
        result, numsList = [[]], sorted(list(set(nums)))
        N = len(numsList)
        for i in range(N):
            num = numsList[i]
            result.append([num])
            numsDict[num] -= 1
            self.find(N, numsDict, numsList, i, [num], result)
            numsDict[num] += 1

        return result

    def find(self, N, numsDict, numsList, idx, temp, result):
        # TODO:
        print(numsDict)

        if idx == N:
            return

        for i in range(idx, N):
            if numsDict[numsList[i]] == 0:
                continue
            num = numsList[i]
            result.append(temp + [num])
            numsDict[num] -= 1
            self.find(N, numsDict, numsList, i, temp + [num], result)
            numsDict[num] += 1

# class Solution(object):
#     def subsets(self, nums):
#         nums.sort()
#         result = [[]]
#         N, past = len(nums), -99
#         for i in range(N):
#             if nums[i] == past:
#                 continue
#             result.append([nums[i]])
#             past = nums[i]
#             self.find(N, nums, i + 1, [nums[i]], result)

#         return result

#     def find(self, N, nums, idx, temp, result):
#         if idx == N:
#             return

#         past = -99
#         for i in range(idx, N):
#             if nums[i] == past:
#                 continue
#             result.append(temp + [nums[i]])
#             past = nums[i]
#             self.find(N, nums, i + 1, temp + [nums[i]], result)