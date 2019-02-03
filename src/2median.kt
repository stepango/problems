fun main(args: Array<String>) {
    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val size = nums1.size + nums2.size
        val arr = IntArray(size)
        var i = 0
        var j = 0
        while (i < nums1.size || j < nums2.size) {
            val iv = nums1.getOrNull(i) ?: Int.MAX_VALUE
            val jv = nums2.getOrNull(j) ?: Int.MAX_VALUE
            arr[i + j] = if (iv < jv) {
                if (i >= nums1.size) {
                    j++
                    jv
                } else {
                    i++
                    iv
                }
            } else {
                if (j >= nums2.size) {
                    i++
                    iv
                } else {
                    j++
                    jv
                }
            }
        }
        println(arr.contentToString())
        return when (size % 2) {
            0 -> (arr[size / 2] + arr[size / 2 - 1]) / 2.0
            else -> arr[size / 2].toDouble()
        }
    }

    println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3, 4)))
    println(findMedianSortedArrays(intArrayOf(1, 2), intArrayOf(3)))
}