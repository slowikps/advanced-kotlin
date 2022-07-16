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
}