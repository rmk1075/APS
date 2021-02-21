class Solution:
    def jump(self, nums: List[int]) -> int:
        end = len(nums) - 1
        if end == 0:
            return 0

        cnt, left, right = 0, 0, 0
        for i in range(end):
            if right < i + nums[i]:
                right = i + nums[i]
                if end <= right:
                    return cnt + 1
            
            if i == left:
                cnt += 1
                left = right

        return cnt

# 168ms / 16.1MB
# class Solution:
#     def jump(self, nums: List[int]) -> int:
#         if len(nums) == 1:
#             return 0

#         left, right, end = 0, 1, len(nums) - 1
#         cnt, queue = 1, [0]
#         while queue:
#             size = len(queue)
#             for _ in range(size):
#                 current = queue.pop(0)
#                 distance = current + nums[current] + 1
#                 if end < distance:
#                     return cnt
                
#                 if distance <= right:
#                     continue
                
#                 for i in range(right, distance):
#                     queue.append(i)
                
#                 right = distance
            
#             cnt += 1

#         return cnt

# 176ms / 17.7MB
# class Solution:
#     def jump(self, nums: List[int]) -> int:
#         if len(nums) == 1:
#             return 0

#         right, end = 1, len(nums) - 1
#         queue = [(0, 1)]
#         while queue:
#             current, cnt = queue.pop(0)
#             distance = current + nums[current] + 1
#             if end < distance:
#                 return cnt
            
#             if distance <= right:
#                 continue
            
#             for i in range(right, distance):
#                 queue.append((i, cnt + 1))
            
#             right = distance

#         return cnt

# 176ms / 16.3MB
# class Solution:
#     def jump(self, nums: List[int]) -> int:
#         if len(nums) == 1:
#             return 0

#         left, right, end = 0, 1, len(nums) - 1
#         cnt, queue = 1, [0]
#         while queue:
#             size = len(queue)
#             for _ in range(size):
#                 current = queue.pop(0)
#                 distance = current + nums[current] + 1
#                 if end < distance:
#                     return cnt
                
#                 for i in range(right, distance):
#                     queue.append(i)
                
#                 right = max(right, distance)
            
#             cnt += 1

#         return cnt