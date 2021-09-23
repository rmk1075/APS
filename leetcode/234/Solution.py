# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def isPalindrome(self, head: Optional[ListNode]) -> bool:
        rev = None
        front = mid = head
        while front and front.next:
            front = front.next.next
            rev, rev.next, mid = mid, rev, mid.next
        if front:
            mid = mid.next
        while rev and rev.val == mid.val:
            rev = rev.next
            mid = mid.next
        return rev == None