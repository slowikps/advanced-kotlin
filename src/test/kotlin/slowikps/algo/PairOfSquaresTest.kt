package slowikps.algo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PairOfSquaresTest {

    @Test
    fun `faster solution should return the same result as brute force`() {
        PairOfSquares.bruteForce(10) shouldBe  PairOfSquares.withDictionary(10)
    }

    @Test
    fun `two dictionary solutions should yield the same result`() {
        PairOfSquares.withDictionary(10) shouldBe  PairOfSquares.withDictionaryFaster(10)
    }
}