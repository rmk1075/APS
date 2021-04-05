class Solution:
    def reverseBetween(self, head: ListNode, left: int, right: int) -> ListNode:
        leftPoint, rightPoint = head, head
        dump = ListNode(next=head)
        current = dump
        cnt = 1
        while current and current.next:
            if cnt == left:
                leftPoint = current
            if cnt == right:
                rightPoint = current.next
            cnt += 1
            current = current.next
        past, current = leftPoint.next, leftPoint.next.next
        while past != rightPoint:
            temp = current.next
            current.next = past
            past, current = current, temp
        leftPoint.next.next = current
        leftPoint.next = rightPoint
        return dump.next