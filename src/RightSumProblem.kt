class RightSumProblem {
    companion object {
        val n = 4
        val m = 4
        val requiredSum = 16
        var ans = ""

        @JvmStatic
        fun main(args: Array<String>) {
            val x = 0
            val y = 0
            val path = ""
            val sum = 0;
            val arr = arrayOf(arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4), arrayOf(1, 2, 3, 4))
            ans = pathFinder(arr, x, y, sum, path)
            if (ans.isNotBlank())
                print(ans)
            else
                print("Not Possible")

        }

        private fun pathFinder(arr: Array<Array<Int>>, x: Int, y: Int, sum: Int, path: String): String {
            if (x == n - 1 && y == m - 1) {
                return if (sum + arr[x][y] == requiredSum) {
                    path
                } else {
                    ""
                }
            }
            if (sum > requiredSum) {
                return ""
            }
            if (x < n - 1) {
                ans = pathFinder(arr, x + 1, y, sum + arr[x][y], path + "R")
                if (ans.isNotEmpty()) {
                    return ans
                }
            }

            if (y < m - 1) {
                ans = pathFinder(arr, x, y + 1, sum + arr[x][y], path + "D")
                if (ans.isNotEmpty()) {
                    return ans
                }
            }

            return ""
        }
    }
}