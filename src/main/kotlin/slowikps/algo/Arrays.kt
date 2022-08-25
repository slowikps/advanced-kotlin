package slowikps.algo

fun main() {
    (1..5).forEachIndexed { index, i ->

    }


    println(
        indexOfSum(intArrayOf(1,2,3,4,5,6,7,8), 9)
    )
}

fun indexOfSum(nums: IntArray, target: Int): IntArray {
    val map: Map<Int, List<IndexedValue<Int>>> = nums.withIndex().groupBy { it.value }


    for((idx, num) in nums.withIndex()){
        if(map[target - num]?.any { it.index != idx } == true){
            return intArrayOf(idx, map[target - num]!!.first { it.index != idx }.index)
        }
    }
    return intArrayOf()
}

fun indexOfSumFold(nums: IntArray, target: Int): IntArray {
    nums.foldIndexed(mutableMapOf<Int, Int>()) { idx, acc, nxt ->
        val matching = acc[target - nxt]
        if(matching != null){
            return intArrayOf(matching, idx)
        }
        acc[nxt] = idx
        acc
    }
    return intArrayOf()
}