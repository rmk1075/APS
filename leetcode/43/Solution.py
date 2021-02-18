class Solution:
    def multiply(self, num1: str, num2: str) -> str:

        if num1 == '0' or num2 == '0':
            return '0'

        i = 0
        nums = list()
        for a in range(len(num2) - 1, -1, -1):
            value = ""
            quo = 0
            for b in range(len(num1) - 1, -1, -1):
                temp = str(int(num1[b]) * int(num2[a]) + quo)
                value = temp[len(temp) - 1] + value
                quo = 0 if len(temp) == 1 else int(temp[0:len(temp) - 1]) 
            
            if quo != 0:
                value = str(quo) + value
            
            nums.append(value + (i * '0'))
            i += 1

        result = str()
        quo = 0
        for index in range(0, len(nums[len(nums) - 1])):
            temp = 0
            for num in nums:
                if len(num) <= index:
                    continue
                temp += int(num[len(num) - 1 - index])
            
            temp = str(temp + quo)
            result = temp[len(temp) - 1] + result
            quo = 0 if len(temp) == 1 else int(temp[0:len(temp) - 1])
        
        if quo != 0:
            result = str(quo) + result

        return result