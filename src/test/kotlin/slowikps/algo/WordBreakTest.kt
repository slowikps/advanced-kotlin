package slowikps.algo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class WordBreakTest {


    @Test
    fun `should correctly process provided examples`() {
        WordBreak.wordBreak("leetcode", listOf("leet","code")) shouldBe true
        WordBreak.wordBreak("applepenapple", listOf("apple","pen")) shouldBe true
        WordBreak.wordBreak("catsandog", listOf("cats","dog","sand","and","cat")) shouldBe false
    }

}