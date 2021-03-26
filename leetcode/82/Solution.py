# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

class Solution(object):
    def deleteDuplicates(self, head):
        point = ListNode(-101, head)
        last, current = point, head
        while current.next:
            cnt = 1
            while current.next and current.val == current.next.val:
                current = current.next
                cnt += 1
            
            if 1 < cnt:
                current = current.next
                continue

            last.next = current
            last = current
            current = current.next
        last.next = current

        return point.next 