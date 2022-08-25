package slowikps.algo

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class OutOfBoundaryPathsTest {

    @Test
    fun `should properly calculate for simple example`() {
        OutOfBoundaryPaths(2,2).findPaths(2,0,0) shouldBe 6
        OutOfBoundaryPaths(1,3).findPaths(3,0,1) shouldBe 12
    }
}