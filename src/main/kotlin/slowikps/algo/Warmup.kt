package slowikps.algo

fun fibo(n: Int): Int =
    when (n) {
        0 -> 0
        1 -> 1
        2 -> 1
        else -> fibo(n - 1) + fibo(n - 2)
    }

