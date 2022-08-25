package slowikps.algo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class WarmupTest {

    @Test
    fun `should properly calculate fib for`() {
        fibo(15) shouldBe 610
    }

    @Test
    fun `romanToInteger test`() {
        romanToInteger("III") shouldBe 3
        romanToInteger("LVIII") shouldBe 58
        romanToInteger("MCMXCIV") shouldBe 1994
    }

    @Test
    fun `charOccurrence simple test`() {
        charOccurrence("PawelPawlPawllll") shouldBe mapOf(
            'p' to 3,
            'a' to 3,
            'w' to 3,
            'e' to 1,
            'l' to 6
        )
    }
}