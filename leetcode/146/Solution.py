class LRUCache:
    cache = None
    head = None
    tail = None
    capacity = 0
    size = 0

    class Node:
        prev = None
        next = None
        key = None
        value = None

        def __init__(self, prev, next, key, value):
            self.prev = prev
            self.next = next
            self.key = key
            self.value = value

    def __init__(self, capacity: int):
        self.cache = [None for _ in range(3001)]
        self.capacity = capacity

    def get(self, key: int) -> int:
        if self.cache[key] == None:
            return -1
        
        # handle linkedlist
        node = self.cache[key]
        if node == self.tail:
            return node.value
        
        if node == self.head:
            self.head = node.next
            self.head.prev = None
        else:
            prev, next = node.prev, node.next
            prev.next, next.prev = next, prev

        tail = self.tail
        tail.next, node.prev, node.next = node, tail, None
        self.tail = node

        return node.value

    def put(self, key: int, value: int) -> None:
        if self.head == None:
            newNode = self.Node(None, None, key, value)
            self.head = newNode
            self.tail = newNode
            self.cache[key] = newNode
            self.size += 1
            return

        if self.cache[key] != None:
            node = self.cache[key]
            if node == self.tail:
                node.value = value
                return

            if node == self.head:
                self.head = node.next
                self.head.prev = None
            else:
                prev, next = node.prev, node.next
                prev.next, next.prev = next, prev

            tail = self.tail
            tail.next, node.prev, node.next = node, tail, None
            self.tail = node

            node.value = value
            return
        
        if self.capacity == self.size:
            # remove head
            node = self.head
            self.cache[node.key] = None

            self.head = self.head.next
            self.size -= 1
            self.put(key, value)
            return
        
        newNode = self.Node(self.tail, None, key, value)
        self.cache[key] = newNode
        self.tail.next = newNode
        self.tail = newNode
        self.size += 1