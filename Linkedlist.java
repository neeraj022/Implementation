public class Linkedlist
{
	public static void main(String args[])
	{
		Node head=new Node(35);
		head.append(24);
		head.append(26);
		head.append(50);
		head.append(45);
		head.printLinkedList(head);
		head.deleteNode(head, 26);
		head.printLinkedList(head);
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
}