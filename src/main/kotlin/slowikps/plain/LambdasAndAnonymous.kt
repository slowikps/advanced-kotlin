package slowikps.plain

import java.math.BigDecimal
import java.math.MathContext
import java.util.function.BiFunction
import java.util.function.Consumer
import java.util.stream.Stream
import kotlin.reflect.KFunction1

fun main() {
    //Using object:
    printString(
        object: SingleMethodInterface {
            override fun someString(x: String, y: Int): String = "string:$x, int:$y"
        }
    )

    //SAM - Java
    val samJavaInterface = Consumer<String> {
        println("samJavaInterface just consumed: $it")
    }
    val biFunctionJavaInterface = BiFunction<String, Int, String> { str, int ->
        println("biFunctionJavaInterface just consumed [str: $str, int: $int]")
        "$str, $int"
    }
    samJavaInterface.accept("StringParam")

    //SAM - Kotlin doesn't work!
    //Doesn't work!!!
    // val smi = SingleMethodInterface {}

    //Reference to function
    val anonymousOne: () -> String = { -> "lambda"}
    val anonymousTwo: (Int, String) -> String = { a: Int, b: String -> "lambda"}

    //From current scope:
    val existing: KFunction1<@ParameterName(name = "input") SingleMethodInterface, Unit> = ::printString

    val str = "ABC"
    val toBigDecimal: () -> BigDecimal = str::toBigDecimal
    val toBigDecimalWitParam: (MathContext) -> BigDecimal = str::toBigDecimal




    // Stream.of("A", "B")
}

fun printString(input: SingleMethodInterface) {
    println(input.someString("printStringFun", 1))
}

interface SingleMethodInterface {
    fun someString(x: String, y: Int): String
}