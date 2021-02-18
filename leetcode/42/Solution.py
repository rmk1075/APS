class Solution:
    def trap(self, height: List[int]) -> int:
        answer, left, right = 0, 0, 0
        while right < len(height):
            if height[left] <= height[right]:
                tempHeight = height[left]
                for i in range(left, right):
                    answer += tempHeight - height[i]
                left = right
            
            right += 1

        right, left = len(height) - 1, len(height) - 1
        while -1 < left:
            if height[right] < height[left]:
                tempHeight = height[right]
                for i in range(right, left, -1):
                    answer += tempHeight - height[i]
                right = left
            
            left -= 1
        
        return answer
