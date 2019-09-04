package com.kartik.org;

/**
 * Reverse every k nodes in a linked list. So if the input is A->B->C->D->E->F->G->H and k is 3, 
 * then the output should be C->B->A->F->E->D->H->G

Both recursive and non-recursive solutions exist to reverse every k nodes with O(n) time complexity. 
Although the recursive solution takes more space, we will use it here since it is simpler. If there 
are n nodes in the linked list, we reverse the first k nodes and then recursively process the remaining
 n � k nodes. Let the linked list be A->B->C->D->E->F->G->H and k = 3. The diagram below illustrates the technique

@link http://www.interviewdruid.com/category/linked-lists/
 * @author kmandal
 *
 */

public class LinkListReverseEveryKnodes {

	 static Node head;
	    
		static class Node {
			int data;
			Node next;
			Node() {
			};
			Node(int d) {
				data = d;
				next = null;
			}
		}

		public static void push(int data) {
			Node newNode = new Node();
			newNode.data = data;
			newNode.next = head;
			head = newNode;
		}

		public static void printLinkedList() {
			System.out.println("Printing LinkedList (head --> last) ");
			Node current = head;
			while (current != null) {
				System.out.println(current.data + "");
				current = current.next;
			}
			System.out.println();
		}
				
		/* 
		head: first node of the linked list
		k: how many nodes should be reversed
		Return value: first node of the new linked list after reversing every k nodes
		*/
		public static Node kReverseList(Node head, int k) {
		    int i = 0;
		 
		    if (head == null || k == 0)
		        return head;
		 
		    Node curNode = head;
		    Node prevNode = null;
		 
		    while (curNode != null && i < k) {
		        /*Store the next node in a temporary variable*/
		    	Node tempNode = curNode.next;
		 
		        /*Reverse the link */
		        curNode.next = prevNode;
		 
		        /*Update the previous node to the current node */
		        prevNode = curNode;
		 
		        /*Proceed to the next node in the original linked list*/
		        curNode = tempNode;
		 
		        ++i;
		    }
		 
		    /*
		    We have reversed k nodes. So curNode refers to the (k+1)th node.
		    and head now refers to the kth node.
		    Now recursively process the remaining nodes from curNode onwards 
		    and assign the result to head.next.
		    */
		    if (curNode != null)
		        head.next = kReverseList(curNode, k);
		 
		    /*prevNode will refer to first node in linked list after reversal*/
		    return prevNode;
		}
		
		
		
		public static void main (String[] args) {
			for ( int i = 130 ; i >= 10 ; i=i-10)
				push(i);
				//Node n= head;
			System.out.println("Generated Linked List: ");
			printLinkedList();
			Node n = head;
			head =null;
			head=kReverseList(n, 3);
			System.out.println("Generated Linked List: ");
			printLinkedList();
		}

}
