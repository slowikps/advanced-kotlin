package slowikps.algo

import java.util.*

fun fibo(n: Int): Int =
    when (n) {
        0 -> 0
        1 -> 1
        2 -> 1
        else -> fibo(n - 1) + fibo(n - 2)
    }

/**
 * Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
 */
fun romanToInteger(s: String): Int {
    fun symbolToNumber(symbol: Char): Int = when (symbol) {
        'I' -> 1
        'V' -> 5
        'X' -> 10
        'L' -> 50
        'C' -> 100
        'D' -> 500
        'M' -> 1000
        else -> throw java.lang.IllegalArgumentException("Unexpected argument: $symbol")
    }

    fun inner(sum: Int, prev: Int, rest: String): Int =
        if (rest.isBlank()) sum + prev
        else {
            val next = symbolToNumber(rest.first())
            if (next > prev) inner(sum, next - prev, rest.drop(1))
            else inner(sum + prev, next, rest.drop(1))
        }

    return inner(0, symbolToNumber(s.first()), s.drop(1))
}

fun charOccurrence(input: String): Map<Char, Int> =
    input.lowercase(Locale.getDefault()).groupBy { it }.mapValues { it.value.size }