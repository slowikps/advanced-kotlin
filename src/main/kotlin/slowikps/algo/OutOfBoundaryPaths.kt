package slowikps.algo

class OutOfBoundaryPaths(private val m: Int, private val n: Int) {
    private val modulo = 1000000007

    //There is an m x n grid with a ball.
    fun findPaths(maxMove: Int, startRow: Int, startColumn: Int): Int =
        findPathsWithMem(maxMove, startRow, startColumn)


    //Time complexity : O(4^n), n refers to the number of moves allowed.
    private fun findPathsBrute(maxMove: Int, startRow: Int, startColumn: Int): Int =
        if (isOutOfBoundary(startRow, startColumn)) 1
        else if (maxMove == 0) 0
        else findPathsBrute(maxMove - 1, startRow + 1, startColumn) +
                findPathsBrute(maxMove - 1, startRow - 1, startColumn) +
                findPathsBrute(maxMove - 1, startRow, startColumn + 1) +
                findPathsBrute(maxMove - 1, startRow, startColumn - 1)


    //Time complexity : O(mnN)O(mnN). We need to fill the memomemo array once with dimensions m \times n \times Nm×n×N. Here, mm, nn refer to the number of rows and columns of the given grid respectively. NN refers to the total number of allowed moves.
    private fun findPathsWithMem(maxMove: Int, startRow: Int, startColumn: Int): Int {
        val mem = mutableMapOf<Triple<Int, Int, Int>, Long>()

        fun internal(maxMove: Int, startRow: Int, startColumn: Int): Long =
            if (isOutOfBoundary(startRow, startColumn)) 1
            else if (maxMove == 0) 0
            else mem.getOrPut(Triple(startRow, startColumn, maxMove)) {
                (internal(maxMove - 1, startRow + 1, startColumn) +
                        internal(maxMove - 1, startRow - 1, startColumn) +
                        internal(maxMove - 1, startRow, startColumn + 1) +
                        internal(maxMove - 1, startRow, startColumn - 1)
                        ) % modulo
            }


        return internal(maxMove, startRow, startColumn).toInt()
    }


    private fun isOutOfBoundary(row: Int, col: Int) =
        (row < 0 || row >= m) || (col < 0 || col >= n)
}