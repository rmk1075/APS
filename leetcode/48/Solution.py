class Solution:
    def rotate(self, matrix: List[List[int]]) -> None:
        n = len(matrix)
        for i in range(int(n / 2)):
            length = n - (2 * i)
            if length < 1:
                break

            top = matrix[i][i + 1: i + length]
            bottom = list(reversed(matrix[i + length - 1][i: i + length - 1]))
            right, left = [], []
            for j in range(length - 1):
                right.append(matrix[i + 1 + j][i + length - 1])
                left.append(matrix[length - 2 - j + i][i])

            for j in range(length - 1):
                # top
                matrix[i][i + j + 1] = left[j]

                # right
                matrix[i + j + 1][i + length - 1] = top[j]

                # bottom
                matrix[i + length - 1][i + length - 2 - j] = right[j]

                # left
                matrix[i + length - 2 - j][i] = bottom[j]