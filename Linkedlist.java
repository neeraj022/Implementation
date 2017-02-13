import java.util.*;
public class Linkedlist
{
	public static void main(String args[])
	{
		Node head=new Node(35);
		head.append(24);
		head.append(26);
		head.append(24);
		head.append(50);
		head.append(45);
		//head.printLinkedList(head);
		//head.deleteNode(head, 26);
		head.append(50);
		head.printLinkedList(head);
		head.deleteDuplicateWoBuffer(head);
		head.printLinkedList(head);

		Node add1=new Node(3);
		add1.append(1);
		add1.append(5);
		add1.append(1);
		Node add2=new Node(5);
		add2.append(9);
		add2.append(2);
		add1.addTwoNumbers(add1,add2);
	}
}

class Node
{
	int data;
	// this is pointer to next node
	Node next;
	
	public Node(int data)
	{
		this.data=data;
	}
	public Node()
	{

	}

	//method to set data
	public void setData(int data)
	{
		this.data=data;
	}

	//method to append a node in the end
	public void append(int data)
	{
		Node end=new Node(data);
		//now find the end node
		Node currentNode=this;
		if(this.next==null)
		{
			//current node is the last node, add node to it
			this.next=end;
		}
		else
		{
			//find the last node in the linkedlist
			while(currentNode.next!=null)
			{
				currentNode=currentNode.next;
			}
			//now current node will be tail node
			currentNode.next=end;
		}

	}

	public Node deleteNode(Node head, int data)
	{
		if(head.data==data)
		{
			//we've found the node, delete it and change the reference
			head=head.next;
			return head;
		}
		else
		{
			Node node=head;
			boolean elementFound=false;
			while(node.next!=null)
			{
				if(node.data==data)
				{
					elementFound=true;
					break;
				}
				node=node.next;
			}
			if(elementFound)
			{
				//delete this node, copy the contents of next node into this node 
				//and change the link to next to next node
				Node nextNode=node.next;
				Node nextNodeLink=nextNode.next;
				node.data=nextNode.data;
				node.next=nextNodeLink;
			}
			return head;
		}
	}

	public void deleteDuplicate(Node head)
	{
		Node previous=null;
		Node iterator=head;
		Hashtable table=new Hashtable();
		while(iterator!=null)
		{
			if(table.containsKey(iterator.data))
			{
				previous.next=iterator.next;
			}
			else
			{
				table.put(iterator.data, true);
				previous=iterator;
			}
			iterator=iterator.next;
		}
	}

	public void deleteDuplicateWoBuffer(Node head)
	{
		Node iterator1=head;
		Node iterator2=head;
		Node previous=head;
		while(iterator1!=null)
		{
			boolean isDuplicate=false;
			while(iterator2!=iterator1)
			{
				if(iterator2.data==iterator1.data)
				{
					isDuplicate=true;
				}
				iterator2=iterator2.next;
			}
			if(isDuplicate)
			{
				previous.next=iterator1.next;
			}
			else
			{
				previous=iterator1;
			}
			iterator2=head;
			iterator1=iterator1.next;
		}
	}

	public void printLinkedList(Node head)
	{
		Node node=head;
		while(node.next!=null)
		{
		System.out.print(node.data+" ");
		node=node.next;
		}
		System.out.print(node.data);
		System.out.println("");
	}

	/*
	This is custom method used to sum two numbers represented by linked lists
	example:
	input: (3->1->5)+(5->9->2)
	output: (8->0->8) where head is units digit
	*/
	public void addTwoNumbers(Node head1, Node head2)
	{
		Node result=new Node();
		Node resultIterator=result;
		int quotient=0;
		while(head1!=null || head2!=null)
		{
			int temp=(head1==null? 0 : head1.data)+(head2==null? 0 : head2.data)+quotient;
			resultIterator.append(temp%10);
			quotient=temp/10;
			if(head1!=null)
			{
				head1=head1.next;
			}
			if(head2!=null)
			{
				head2=head2.next;
			}
		}
		result=result.next;
		result.printLinkedList(result);
	}
}