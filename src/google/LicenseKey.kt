package google

fun licenseKeyFormatting(S: String, K: Int): String {
    if (S.length == 1 && S[0] != '-') return S.toUpperCase()
    val startSize = (S.length - S.count { it == '-' }) % K
    val buffer = StringBuffer()
    var lastIndexOfDash = 0
    for (i in 0 until S.length) {
        if (S[i] == '-') {
            continue
        } else if ((startSize != 0 && buffer.length == startSize) ||  buffer.length - lastIndexOfDash == K) {
            buffer.append('-').append(S[i].toUpperCase())
            lastIndexOfDash = buffer.length - 1
        } else {
            buffer.append(S[i].toUpperCase())
        }
    }
    return buffer.toString()
}

fun main(args: Array<String>) {
    println(licenseKeyFormatting("5F3Z-2e-9-w", 4))
    println(licenseKeyFormatting("5F3Z-2e-9-w", 3))
}

