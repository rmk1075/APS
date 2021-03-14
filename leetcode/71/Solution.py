class Solution:
    def simplifyPath(self, path: str) -> str:
        result = []
        for p in path.split('/'):
            if p == '..':
                if result:
                    result.pop()
            elif p and p != '.':
                result.append(p)

        return '/' + '/'.join(result)


# class Solution:
#     def simplifyPath(self, path: str) -> str:
#         pathList = list()
#         fileName = str()
#         i, slashCnt = 1, 0
#         while i < len(path):
#             while i < len(path) and path[i] == '/':
#                 i += 1
#                 slashCnt += 1
#             if 0 < slashCnt:
#                 slashCnt = 0
#                 if len(fileName) != 0:
#                     if fileName == '.':
#                         pass
#                     elif fileName == '..':
#                         if  len(pathList) != 0:
#                             pathList.pop()
#                     else:
#                         pathList.append(fileName)
#                 fileName = str()
#                 continue
#             fileName += path[i]
#             i += 1

#         if fileName == '.' or len(fileName) == 0:
#             pass
#         elif fileName == '..':
#             if  len(pathList) != 0:
#                 pathList.pop()
#         else:
#             pathList.append(fileName)
#         result = '/'
#         for p in pathList:
#             result += p + '/'

#         return result if len(result) == 1 else result[:-1]