package slowikps.algo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DictionaryTest {

    @Test
    fun `should properly calculate indices for params`() {
        Dictionary("baccabaaa", mapOf("a" to 3)).minNumberOfChars() shouldBe (6 to 8)
        Dictionary("baccabaaa", mapOf("a" to 3, "c" to 1)).minNumberOfChars() shouldBe (3 to 7)
        Dictionary("bacabaaa", mapOf("a" to 3, "c" to 1)).minNumberOfChars() shouldBe (1 to 5)
        Dictionary("bacabaaac", mapOf("a" to 3, "c" to 1)).minNumberOfChars() shouldBe (5 to 8)
        Dictionary("bacabaaac", mapOf("a" to 1, "c" to 1)).minNumberOfChars() shouldBe (1 to 2)
        Dictionary("baccabaaac", mapOf("a" to 2, "c" to 1)).minNumberOfChars() shouldBe (7 to 9)
        Dictionary("bacabaaac", mapOf("a" to 2, "c" to 2)).minNumberOfChars() shouldBe (2 to 8)
    }
}