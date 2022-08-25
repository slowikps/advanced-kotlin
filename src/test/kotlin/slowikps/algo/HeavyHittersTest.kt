package slowikps.algo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class HeavyHittersTest {

    @Test
    fun `heavy hitter basic test`() {
        HeavyHitters.heavyHitters(
            listOf(1, 2, 3, 3, 4, 5, 2, 1, 5, 6, 1, 1, 5).map { it.toString() }, 2
        ) shouldBe (mapOf(
            "1" to 4,
            "5" to 3
        ))
    }
}