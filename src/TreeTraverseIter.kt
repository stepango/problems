import java.util.*

fun preorderTraversalI(root: TreeNode?): List<Int> {
    val list = LinkedList<Int>()
    root ?: return list
    fun addToList(list: MutableList<Int>, root: TreeNode?) {
        val s = Stack<TreeNode>()
        s.push(root)
        while (s.isNotEmpty()) {
            val curr = s.pop().apply { list.add(this.`val`) }
            curr?.right?.let(s::push)
            curr?.left?.let(s::push)
        }
    }
    addToList(list, root)
    return list
}

fun inorderTraversal(root: TreeNode?): List<Int> {
    val list = LinkedList<Int>()
    root ?: return list
    fun addToList(list: MutableList<Int>, root: TreeNode?) {
        root ?: return
        addToList(list, root.left)
        list.add(root.`val`)
        addToList(list, root.right)
    }
    addToList(list, root)
    return list
}

fun inorderTraversalI(root: TreeNode?): List<Int> {
    val list = LinkedList<Int>()
    root ?: return list
    fun addToList(list: MutableList<Int>, root: TreeNode?) {
        val s = Stack<TreeNode>()
        s.push(root)
        while (s.isNotEmpty()) {
            val curr = s.peek()
            while (curr.left != null) {
                s.push(curr.left)
            }
            list.add(curr.`val`)
            curr?.right?.let(s::push)
            curr?.left?.let(s::push)
        }
    }
    addToList(list, root)
    return list
}

fun levelOrder(root: TreeNode?): List<List<Int>> {
    val list = LinkedList<List<Int>>()
    root ?: return list
    fun addToList(list: MutableList<List<Int>>, root: TreeNode?) {
        val s: Queue<TreeNode> = LinkedList<TreeNode>()
        s.add(root)
        while (s.isNotEmpty()) {
            val level = LinkedList<Int>()
            for (i in 0 until s.size) {
                val node = s.poll()
                level.add(node.`val`)
                node.left?.let(s::offer)
                node.right?.let(s::offer)
            }
            list.add(level)
        }
    }
    addToList(list, root)
    return list
}

fun maxDepth(root: TreeNode?): Int {
    root ?: return 0
    fun maxDepth(depth: Int, node: TreeNode?): Int {
        node ?: return depth - 1
        return Math.max(maxDepth(depth + 1, node.left), maxDepth(depth + 1, node.right))
    }
    return maxDepth(1, root)
}

fun isSymmetric(root: TreeNode?): Boolean {
    root ?: return true
    fun isSymmetric(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) return true
        return left?.`val` == right?.`val`
                && isSymmetric(left?.left, right?.right)
                && isSymmetric(left?.right, right?.left)
    }
    return isSymmetric(root.left, root.right)
}

fun hasPathSum(root: TreeNode?, sum: Int): Boolean {
    root ?: return false
    fun hasPathSum(node: TreeNode?, currentSum: Int): Boolean {
        node ?: return false
        return when {
            node.left == null && node.right == null -> currentSum + node.`val` == sum
            else -> hasPathSum(node.left, currentSum + node.`val`) || hasPathSum(node.right, currentSum + node.`val`)
        }
    }
    return hasPathSum(root, 0)
}

fun sortedSquares(A: IntArray): IntArray {
    for (i in 0 until A.size) {
        val v = A[i]
        A[i] = v * v
    }
    A.sort()
    return A
}

/*
A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
OR
A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

Return the length of a maximum size turbulent subarray of A.
 */

fun maxTurbulenceSize(A: IntArray): Int {
    if (A.size <= 1) return A.size
    var result = 1
    var anchor = 0
    for (k in 1 until A.size - 1) {
        val sign = Integer.compare(A[k - 1], A[k])
        val tmpSign = Integer.compare(A[k], A[k + 1])
        if (tmpSign * sign != -1) {
            result = Math.max(result, k - anchor + 1)
            anchor = k
        }
    }
    return Math.max(result, A.size - anchor)
}

fun main(args: Array<String>) {
//    preorderTraversalI(TreeNode(1, TreeNode(2, right = TreeNode(3)))).forEach { println(it) }
//    inorderTraversal(TreeNode(1, TreeNode(2, TreeNode(3), TreeNode(4)))).forEach { println(it) }
//    levelOrder(TreeNode(1, TreeNode(2, TreeNode(3), TreeNode(4)))).forEach { println(it.joinToString()) }
//    maxDepth(TreeNode(1, TreeNode(2, TreeNode(3), TreeNode(4)))).let(::println)
//    hasPathSum(TreeNode(1, TreeNode(2)), 1).let(::println)
//    sortedSquares(intArrayOf(-2, -1, 0, 1, 2)).forEach { println(it) }
    maxTurbulenceSize(intArrayOf(9, 4, 2, 10, 7, 8, 8, 1, 9)).let(::print) //5
    println(" 5")
    maxTurbulenceSize(intArrayOf(4, 8, 12, 16)).let(::print) //2
    println(" 2")
    maxTurbulenceSize(intArrayOf(100)).let(::print)//1
    println(" 1")
    maxTurbulenceSize(intArrayOf(2, 0, 2, 4, 2, 5, 0, 1, 2, 3)).let(::print)//6
    println(" 6")
    //                            > < > > < > < > <
    maxTurbulenceSize(intArrayOf(0, 1, 1, 0, 1, 0, 1, 1, 0, 0)).let(::print)//5
    println(" 5")
    //                            < = > < > < > < =
    maxTurbulenceSize(intArrayOf(8, 8, 9, 10, 6, 8, 2, 4, 2, 2, 10, 6, 6, 10, 10, 2, 3, 5, 1, 2, 10, 4, 2, 0, 9, 4, 9, 3, 0, 6, 3, 2, 3, 10, 10, 6, 4, 6, 4, 4, 2, 5, 1, 4, 1, 1, 9, 8, 9, 5, 3, 5, 5, 4, 5, 5, 6, 5, 3, 3, 7, 2, 0, 10, 9, 7, 7, 3, 5, 1, 0, 9, 6, 3, 1, 3, 4, 4, 3, 6, 3, 2, 1, 4, 10, 2, 3, 4, 4, 3, 6, 7, 6, 2, 1, 7, 0, 6, 8, 10)).let(::print)
    println(" 7")
    //7
    maxTurbulenceSize(intArrayOf(0, 8, 45, 88, 48, 68, 28, 55, 17, 24)).let(::print)
    //                            <  <   <   >   <   >   <   >   <
    println(" 8")
    //8
}


