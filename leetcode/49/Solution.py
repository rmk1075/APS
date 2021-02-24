class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        anagramMap = dict()
        for s in strs:
            key = str(sorted(s))
            if key in anagramMap:
                anagramMap[key].append(s)
            else:
                anagramMap[key] = [s]

        result = list()
        for key in anagramMap:
            result.append(anagramMap[key])

        return result
