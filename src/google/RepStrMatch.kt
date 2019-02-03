package google

import kotlin.math.roundToInt

fun maxRepeatedSubstring(A: String, B: String): Int {
    val start = B.indexOf(A[0])
    var result = 0
    var amount = 0
    if (start == -1) return 0
    var i = start
    while (i < B.length) {
        if (B[i] == A[amount % A.length]) {
            amount++
        } else {
            result = Math.max(result, amount)
            amount = 0
            i = B.indexOf(A[0], i)
            if (i == -1) break
            continue
        }
        ++i
    }
    return Math.max(result, amount) / A.length
}

fun repeatedStringMatch(A: String, B: String): Int {
    if (A.contains(B)) return 1
    if (B.isEmpty()) return -1
    val starts = IntArray(A.length) { -1 }
    val char = B[0]
    var start = A.indexOf(char)
    if (start == -1) return -1
    starts[0] = start
    var k = 1
    while (start != -1 && k < starts.size && start + 1 < A.length) {
        start = A.indexOf(char, start + 1)
        starts[k] = start
        ++k
    }
    loop@ for (j in 0 until starts.size) {
        start = starts[j]
        if (start == -1) break
        for (i in 1 until B.length) {
            if (B[i] != A[(start + i) % A.length]) continue@loop
        }
        return Math.ceil((start + B.length) / A.length.toDouble()).roundToInt()
    }
    return -1
}

fun nextClosestTime(time: String): String {
    val nums = intArrayOf(
            time[0] - '0',
            time[1] - '0',
            time[3] - '0',
            time[4] - '0'
    )
    val resultArr = IntArray(4) { i -> nums[i] }
    val origin = nums[0] * 1000 + nums[1] * 100 + nums[2] * 10 + nums[3]
    println("Origin $origin")
    var result = origin + 2400
    for (i in 0..3) {
        for (j in 0..3) {
            for (k in 0..3) {
                for (l in 0..3) {
                    val n1 = nums[i]
                    if (n1 > 2) continue
                    val n2 = nums[j]
                    if (n1 == 2 && n2 > 4) continue
                    val n3 = nums[k]
                    if (n3 > 5) continue
                    val n4 = nums[l]
                    val tmpResult = n1 * 1000 + n2 * 100 + n3 * 10 + n4
                    if (origin != tmpResult) {
                        val tmpDelta = tmpResult - origin
                        val currentDelta = (result - origin).let { if (it < 0) it + 2400 else it }
                        if (tmpDelta > 0 && (tmpDelta < currentDelta) || tmpDelta < 0 && tmpDelta + 2400 < currentDelta) {
                            result = tmpResult
                            resultArr[0] = n1
                            resultArr[1] = n2
                            resultArr[2] = n3
                            resultArr[3] = n4
                        }
                    }
                }
            }
        }
    }
    return "${resultArr[0]}${resultArr[1]}:${resultArr[2]}${resultArr[3]}"
}

fun main(args: Array<String>) {
//    maxRepeatedSubstring("ba", "bbbaaabbabaaaa").let(::println)
//    repeatedStringMatch("abcd", "cdabcdab").let(::println)
//    repeatedStringMatch("aaac", "aac").let(::println)
//    repeatedStringMatch("a", "aa").let(::println)
//    repeatedStringMatch("abcabcabcabc", "abac").let(::println)
    println(nextClosestTime("19:34"))
    println(nextClosestTime("23:59"))
    println(nextClosestTime("23:22"))
    println(nextClosestTime("22:23"))
    println(nextClosestTime("11:11"))
    println(nextClosestTime("20:56"))
}