package slowikps.algo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class WarmupTest {

    @Test
    fun `should properly calculate fib for`() {
        fibo(15) shouldBe 610
    }
}