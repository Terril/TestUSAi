import java.util.*

class ConnectivityProblem {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val matrix = arrayOf(
                intArrayOf(0, 0, 1, 1),
                intArrayOf(1, 1, 0, 0),
                intArrayOf(0, 0, 0, 0),
                intArrayOf(0, 1, 1, 0),
                intArrayOf(0, 1, 1, 1)
            )
            checkConnectivity(matrix)

            for (array in matrix) {
                for (value in array) {
                    if (value >= 1) {
                        print("${value - 1} ")
                    } else
                        print("$value ")
                }
                println()
            }
        }


        private fun checkConnectivity(matrix: Array<IntArray>) {
            var parent = 1
            val stack = Stack<IntArray>()

            for (i in matrix[0].indices) { // iterate the column
                for (j in matrix.indices) { // iterate the row
                    var x: Int
                    var y: Int

                    if (stack.isEmpty()) {
                        if (matrix[j][i] == 1) {
                            x = j
                            y = i
                            check4sNeighbours(x, matrix, y, stack, ++parent)
                        }
                    } else {
                        while (!stack.isEmpty()) {
                            val popChild = stack.pop()
                            matrix[popChild[0]][popChild[1]] = parent
                            x = popChild[0]
                            y = popChild[1]
                            check4sNeighbours(x, matrix, y, stack, parent)
                        }
                    }
                }
            }
        }

        /*
        * This method checks the neighbouring cells of a given cell(x,y)in 4 way :
        *  -up x+1,y
        *  -down x-1,y
        *  -right x,y+1
        * -left x,y-1
        *
        * If the cell are 1 then add them to the stack to label them later.
        * */
        private fun check4sNeighbours(
            x: Int,
            matrix: Array<IntArray>,
            y: Int,
            stack: Stack<IntArray>,
            parent: Int
        ) {
            if (x == -1) {
                return
            }
            val columnSize = matrix.size
            val rowSize = matrix[0].size

            // top
            if (x - 1 >= 0 && matrix[x - 1][y] == 1) {
                stack.push(intArrayOf(x - 1, y))
            }

            // down
            if (x + 1 < columnSize && matrix[x + 1][y] == 1) {
                stack.push(intArrayOf(x + 1, y))
            }

            // right
            if (y + 1 < rowSize && matrix[x][y + 1] == 1) {
                stack.push(intArrayOf(x, y + 1))
            }

            // left
            if (y - 1 >= 0 && matrix[x][y - 1] == 1) {

                stack.push(intArrayOf(x, y - 1))
            }

            matrix[x][y] = parent
        }

        /*
        * This method checks the neighbouring cells of a given cell(x,y) in 8 way :
        *  -top x+1,y
        *  -top left x+1,y-1
        *  -top right x+1,y+1
        *  -down x-1,y
        *  -down left x-1,y-1
        *  -down right x-1,y+1
        *  -right x,y+1
        *  -left x,y-1
        *
        * If the cell are 1 then add them to the stack to label them later.
        * */
        private fun check8sNeighbours(
            x: Int,
            matrix: Array<IntArray>,
            y: Int,
            stack: Stack<IntArray>
        ) {
            if (x == -1) {
                return
            }
            val columnSize = matrix.size
            val rowSize = matrix[0].size

            // right
            if (y + 1 < rowSize && matrix[x][y + 1] == 1) {
                stack.push(intArrayOf(x, y + 1))
            }

            // left
            if (y - 1 >= 0 && matrix[x][y - 1] == 1) {
                stack.push(intArrayOf(x, y - 1))
            }

            // top
            if (x - 1 >= 0 && matrix[x - 1][y] == 1) {
                stack.push(intArrayOf(x - 1, y))
            }
            // top left
            if (x - 1 >= 0 && y - 1 >= 0 && matrix[x - 1][y - 1] == 1) {
                stack.push(intArrayOf(x - 1, y - 1))
            }

            // top right
            if (x - 1 >= 0 && y + 1 < rowSize && matrix[x - 1][y + 1] == 1) {
                stack.push(intArrayOf(x - 1, y + 1))
            }

            // down
            if (x + 1 < columnSize && matrix[x + 1][y] == 1) {
                stack.push(intArrayOf(x + 1, y))
            }

            // down left
            if (x + 1 < columnSize && y - 1 >= 0 && matrix[x + 1][y - 1] == 1) {
                stack.push(intArrayOf(x + 1, y - 1))
            }

            // down right
            if (x + 1 < columnSize && y + 1 < rowSize && matrix[x + 1][y + 1] == 1) {
                stack.push(intArrayOf(x + 1, y + 1))
            }

        }
    }
}