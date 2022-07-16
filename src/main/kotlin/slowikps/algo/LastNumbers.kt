package slowikps.algo

import java.util.Deque
import java.util.LinkedList

class LastNumbers(private val numbers: Deque<Int>, private val capacity: Int) {
    private var zeroCount: Int = 0
    var sum = 0
        private set

    private var multiplication = 1

    constructor(capacity: Int) : this(numbers = LinkedList(), capacity = capacity)


    fun add(new: Int): LastNumbers {
        sum += new
        if (new != 0) multiplication *= new
        else zeroCount += 1

        numbers.add(new) //at the tail of this deque
        if (numbers.size > capacity) {
            val removed = numbers.pop() //removes and returns the first element of this deque
            sum -= removed
            if (removed != 0) multiplication /= removed
            else zeroCount -= 1

        }
        return this
    }

    fun multiplication(): Int = if (zeroCount == 0) multiplication else 0

}