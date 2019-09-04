package com.kartik.org;


public class LinkedListPalindromeCheck{
	 
	private static Node head;
 
	private static class Node {
		private int value;
		private Node next;
 
		Node(int value) {
			this.value = value;
 
		}
	}
 
	public void addToTheLast(Node node) {
 
		if (head == null) {
			head = node;
		} else {
			Node temp = head;
			while (temp.next != null)
				temp = temp.next;
 
			temp.next = node;
		}
	}
 
 
	public void printList() {
		Node temp = head;
		while (temp != null) {
			System.out.format("%d ", temp.value);
			temp = temp.next;
		}
		System.out.println();
	}
 
	// This function will find middle element in linkedlist
	public static Node findMiddleNode(Node head)
	{
		// step 1
		Node slowPointer, fastPointer; 
		slowPointer = fastPointer = head; 
 
		while(fastPointer !=null) { 
			fastPointer = fastPointer.next; 
			if(fastPointer != null && fastPointer.next != null) { 
				slowPointer = slowPointer.next; 
				fastPointer = fastPointer.next; 
			} 
		} 
 
		return slowPointer; 
	}
 
	// Function to check if linked list is palindrome or not
	public static boolean checkPalindrome (Node head)
	{
		// Find middle node using slow and fast pointer
		Node middleNode=findMiddleNode(head);
		// we got head of second part
		Node secondHead=middleNode.next;
		// It is end of first part of linked list
		//below line use for head node middle null 
		middleNode.next=null;
		// get reversed linked list for second part
		Node reverseSecondHead=reverseLinkedList(secondHead);
 
		while(head!=null && reverseSecondHead!=null)
		{
			if(head.value==reverseSecondHead.value)
			{
				head=head.next;
				reverseSecondHead=reverseSecondHead.next;
				continue;
			}
			else
			{
				return false;
			}
		}
 
		return true;
 
 
	}
 
	public static Node reverseLinkedList(Node node) 
	{ 
		if (node == null || node.next == null) {
			return node;
		}

		Node remaining = reverseLinkedList(node.next);
		node.next.next = node;
		node.next = null;
		return remaining; 
	} 
 
	private static Node left;
	// Initial parameters to this function are &head and head 
   static boolean isPalindromeUtil(Node right)  
    { 
        left = head; 
        /* stop recursion when right becomes NULL */
        if (right == null) 
            return true; 
        /* If sub-list is not palindrome then no need to 
           check for current left and right, return false */
        boolean isp = isPalindromeUtil(right.next); 
        if (isp == false) 
            return false; 
  
        /* Check values at current left and right */
        boolean isp1 = (right.value == left.value); 
  
        /* Move left to next node */
        left = left.next; 
  
        return isp1; 
    } 
  
    // A wrapper over isPalindromeUtil() 
    static boolean isPalindrome(Node head)  
    { 
        boolean result = isPalindromeUtil(head); 
        return result; 
    } 
 
	public static void main(String[] args) {
		LinkedListPalindromeCheck list = new LinkedListPalindromeCheck();
		// Creating a linked list
		Node head=new Node(1);
		list.addToTheLast(head);
		list.addToTheLast(new Node(2));
		list.addToTheLast(new Node(1));
		list.addToTheLast(new Node(2));
		list.addToTheLast(new Node(1));
 
		list.printList();
 
		System.out.println("Linked list palidrome: "+checkPalindrome(head));
		
		System.out.println();
		System.out.println("Recursive wise palindrome check "+isPalindrome(head));
	}
}
