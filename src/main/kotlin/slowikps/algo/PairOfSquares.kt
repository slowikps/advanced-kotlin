package slowikps.algo

import kotlin.math.pow

private fun Int.cube(): Int = this.toDouble().pow(3).toInt()

object PairOfSquares {

    fun bruteForce(n: Int): Set<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (k in 0 until n) {
                    for (l in 0 until n) {
                        if (i.cube() + j.cube() == k.cube() + l.cube()) {
                            result.add(listOf(i, j, k, l))
                        }
                    }
                }
            }
        }

        return result.toSet()
    }

    fun withDictionary(n: Int): Set<List<Int>> {
        val dict: Map<Int, List<Pair<Int, Int>>> =
            (0 until n)
                .flatMap { k -> (0 until n).map { Pair(k, it) } }
                .groupBy { (f, s) -> f.cube() + s.cube() }

        val result = mutableListOf<List<Int>>()

        for (i in 0..n) {
            for (j in 0..n) {
                dict[i.cube() + j.cube()]?.forEach { result.add(listOf(i, j, it.first, it.second)) }
            }
        }

        return result.toSet()
    }

    fun withDictionaryFaster(n: Int): Set<List<Int>> {
        val dict: Map<Int, List<Pair<Int, Int>>> =
            (0 until n)
                .flatMap { k -> (0 until n).map { Pair(k, it) } }
                .groupBy { (f, s) -> f.cube() + s.cube() }

        return dict.values.flatMap { sameCube ->
            sameCube.flatMap { out -> sameCube.map { inner -> out.toList() + inner.toList() } }
        }.toSet()
    }

}