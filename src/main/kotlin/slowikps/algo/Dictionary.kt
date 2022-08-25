package slowikps.algo

class Dictionary(
    private val sentence: String,
    private val dictionary: Map<String, Int>
) {

    /**
     * Improvements:
     *  - there is no need to store letter that are not in the dictionary
     */
    fun minNumberOfChars(): Pair<Int, Int>? {
        var result: Pair<Int, Int>? = null
        var tmpDic = mutableMapOf<String, Int>()
        var startIdx = 0

        sentence.forEachIndexed { idx, c ->
            tmpDic.compute(c.toString()) { _, i: Int? ->
                (i ?: 0) + 1
            }

            if (containsAll(tmpDic, dictionary)) {
                startIdx = shorten(sentence, startIdx, tmpDic, dictionary)
                if (result == null
                    || (result!!.second - result!!.first > idx - startIdx)
                ) {
                    result = startIdx to idx
                }
            }
        }
        return result
    }

    private fun containsAll(source: MutableMap<String, Int>, target: Map<String, Int>): Boolean {
        return target.all { (letter: String, count: Int) ->
            (source[letter] ?: 0) >= count
        }
    }

    private fun shorten(
        sentence: String,
        startIdx: Int,
        subSentence: MutableMap<String, Int>,
        dictionary: Map<String, Int>
    ): Int {
        var newIdx = startIdx
        while (true) {
            val letter = sentence[newIdx].toString()
            if (subSentence[letter]!! > (dictionary[letter] ?: 0)) {
                newIdx += 1
                subSentence[letter] = subSentence[letter]!! - 1
                //subSentence.merge(letter, -1) { a, b -> a + b }
            } else {
                break
            }
        }

        return newIdx
    }
}