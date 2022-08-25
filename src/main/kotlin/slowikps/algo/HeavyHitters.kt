package slowikps.algo

import java.util.*

object HeavyHitters {

    fun heavyHitters(input: List<String>, top: Int): Map<String, Int> {
        val groupByValues: Map<String, Int> = input.groupBy { it }.mapValues { it.value.size }

        val mapValues: Map<String, Int> = input.fold(mutableMapOf()) { acc, next ->
            acc.compute(next) { _, v -> (v ?: 0) + 1 }
            acc
        }


        var result = PriorityQueue<Pair<String, Int>> { o1, o2 -> o1.second.compareTo(o2.second) }
        mapValues.forEach { (k, v) ->
            result.add(k to v)
            if (result.size > top) result.poll()

        }
        return result.toMap()
    }
}