package slowikps.algo

import java.util.*

object Lists {

    @JvmStatic
    fun main(args: Array<String>) {
        val linedList = LinkedList<String>()
        linedList.push("Mid")
        linedList.add("END")
        linedList.push("Start")
        println(linedList)

        println(
            linedList.poll()
        )

        println(
            linedList.pollLast()
        )

        val tmp = arrayOfNulls<Boolean>(50)

        tmp[10] = true
        tmp[11] = true


//        println(
//            (0 until 100).withIndex().groupBy { it.index / 4 }.mapValues { it.value.map { it.value } }
//        )
//
//
//        println(
//            (0 until 10).chunked(3) /* [[0, 1, 2], [3, 4, 5], [6, 7, 8], [9]] */
//        )

        //Initialisation
//        val dp = Array(2) { Array(3) { arrayOfNulls<Long>(3 + 1) } }

//        println(dp.contentToString())
    }

}