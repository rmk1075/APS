class Node:
    def __init__(self, x: int, next: 'Node' = None, random: 'Node' = None):
        self.val = int(x)
        self.next = next
        self.random = random

class Solution:
    def copyRandomList(self, head: 'Node') -> 'Node':
        if not head:
            return None

        old_node_list, new_node_list = [], []
        current = head
        while current:
            old_node_list.append(current)
            new_node_list.append(Node(current.val))
            current = current.next

        N = len(old_node_list)
        for i in range(N):
            if i != N - 1:
                new_node_list[i].next = new_node_list[i + 1]
            
            node = old_node_list[i]
            if node.random:
                new_node_list[i].random = new_node_list[old_node_list.index(node.random)]
        return new_node_list[0]
