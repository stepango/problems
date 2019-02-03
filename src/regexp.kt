fun isMatch(s: String, p: String): Boolean {
    val dp = Array(s.length) { BooleanArray(p.length) }
    for (i in s.indices.reversed()) {
        for (j in p.indices.reversed()) {
            val firstMatch = i < s.length && s[i] == p[j] || p[j] == '.'
            if (j + 1 < p.length && p[j + 1] == '*') {
                dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j]
            } else {
                dp[i][j] = firstMatch && if (i >= s.length - 1 || j >= p.length - 1) true else dp[i + 1][j + 1]
            }
        }
    }
    dp.forEach {
        print(it.contentToString())
        println()
    }
    return dp[0][0]
}


fun main(args: Array<String>) {
    println(isMatch("aabbaa", "a*a"))
    println(isMatch("aabbaa", "a*"))
}