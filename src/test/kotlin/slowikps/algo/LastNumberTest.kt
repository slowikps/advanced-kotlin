package slowikps.algo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class LastNumberTest {

    @Test
    fun `should properly calculate last number`() {
        val lastNumbers = LastNumbers(4).add(1)

        lastNumbers.sum shouldBe 1
        lastNumbers.multiplication() shouldBe 1

        //adding 5 - 1,5
        lastNumbers.add(5)
        lastNumbers.sum shouldBe 6
        lastNumbers.multiplication() shouldBe 5

        //adding 0 - 1,5,0
        lastNumbers.add(0)
        lastNumbers.sum shouldBe 6
        lastNumbers.multiplication() shouldBe 0

        //adding 0 - 1,5,0,0
        lastNumbers.add(0)
        lastNumbers.sum shouldBe 6
        lastNumbers.multiplication() shouldBe 0

        //adding 7 - 5,0,0, 7
        lastNumbers.add(7)
        lastNumbers.sum shouldBe 12
        lastNumbers.multiplication() shouldBe 0

        //adding 3 - 0,0, 7, 3
        lastNumbers.add(3)
        lastNumbers.sum shouldBe 10
        lastNumbers.multiplication() shouldBe 0

        //adding 2 - 0, 7, 3, 2
        lastNumbers.add(2)
        lastNumbers.sum shouldBe 12
        lastNumbers.multiplication() shouldBe 0

        //adding 2 - 7, 3, 2, 8
        lastNumbers.add(8)
        lastNumbers.sum shouldBe 20
        lastNumbers.multiplication() shouldBe 42 * 8
    }

}