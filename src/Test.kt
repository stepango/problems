fun main(args: Array<String>) {

    data class Node(var prev: Node? = null, var next: Node? = null, var value: Int, val key: Int)

    class LRUCache(val capacity: Int) {

        val map = HashMap<Int, Node>()
        var first: Node? = null
        var last: Node? = null

        fun get(key: Int): Int {
            return when {
                map.isEmpty() -> -1
                !map.containsKey(key) -> -1
                else -> {
                    val node = map[key]!!
                    toFirst(node)
                    return node.value
                }
            }
        }

        private fun toFirst(node: Node) {
            if (node.key == last?.key) {
                last = last?.prev
                last?.next = null
                node.prev = null
                node.next = first
                first?.prev = node
                first = node
            } else if (node.key != first?.key) {
                val p = node.prev
                val n = node.next
                node.next = first
                node.next?.prev = node
                first = node
                p?.next = n
                n?.prev = p
            }
        }

        fun put(key: Int, value: Int) {
            val v = map[key]
            if (v != null) {
                v.value = value
                toFirst(v)
                return
            }
            if (capacity == 1) {
                map.clear()
            } else if (map.size == capacity) {
                val remove = map.remove(last?.key)
                last = remove?.prev
                last?.next = null
            }
            val node = Node(next = first, value = value, key = key)
            map[key] = node
            when (map.size) {
                1 -> {
                    first = node
                    return
                }
                2 -> {
                    last = first
                    first = node
                    last?.prev = first
                    first?.next = last
                }
                else -> {
                    first?.prev = node
                    node.next = first
                    first = node
                }
            }
        }
    }

    fun printNode(node: Node?) {
        print("${node?.key} -> ")
        node?.next?.let { printNode(it) }
    }

    val cache = LRUCache(3 /* capacity */)

    cache.put(1, 1)
    printNode(cache.first); println()
    cache.put(2, 2)
    printNode(cache.first); println()
    cache.put(3, 3)    // evicts key 2
    printNode(cache.first); println()
    cache.put(4, 4)    // evicts key 1
    printNode(cache.first); println()
    println(cache.get(4))       // returns -1 (not found)
    printNode(cache.first); println()
    println(cache.get(3))       // returns 3
    printNode(cache.first); println()
    println(cache.get(2))
    printNode(cache.first); println()
    println(cache.get(1))       // returns 4
    printNode(cache.first); println()
    cache.put(5, 5)
    printNode(cache.first); println()
    println(cache.get(1))       // returns -1 (not found)
    printNode(cache.first); println()
    println(cache.get(2))       // returns 3
    printNode(cache.first); println()
    println(cache.get(3))
    printNode(cache.first); println()
    println(cache.get(4))
    printNode(cache.first); println()
    println(cache.get(5))
    printNode(cache.first); println()

}