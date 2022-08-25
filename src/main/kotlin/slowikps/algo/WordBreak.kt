package slowikps.algo

//https://leetcode.com/problems/word-break/
object WordBreak {

    fun wordBreak(s: String, wordDict: List<String>): Boolean =
        internal(s, wordDict.toSet(), 0, arrayOfNulls(s.length))

    private fun internal(s: String, wordDict: Set<String>, startIdx: Int, checked: Array<Boolean?>): Boolean {
        if (startIdx == s.length) return true
        else if (checked[startIdx] != null) return checked[startIdx]!!
        else {
            for (end in (startIdx + 1) .. s.length) {
                if (wordDict.contains(s.substring(startIdx, end)) && internal(s, wordDict, end, checked)) {
                    checked[startIdx] = true
                    return true
                }
            }
            checked[startIdx] = false
            return false
        }
    }
}