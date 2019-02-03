import java.util.*

class TreeNode(var `val`: Int = 0, var left: TreeNode? = null,
               var right: TreeNode? = null)

fun preorderTraversal(root: TreeNode?): List<Int> {
    val list = LinkedList<Int>()
    fun addToList(list: MutableList<Int>, root: TreeNode?) {
        if (root == null) return
        list.add(root.`val`)
        addToList(list, root.left)
        addToList(list, root.right)
    }
    return list
}