
public class ObjectBasedStackQueue 
{
	public static void main(String args[])
	{
		Stack stack=new Stack();
		stack.push(65);
		stack.push(45);
		stack.push(100);
		stack.push(1);
		stack.push(27);
		stack.sortStack();
		System.out.println(" Stack size is: "+stack.size());
		stack.printStack();
		// System.out.println(stack.size());
		// try{stack.pop();
		// }
		// catch (StackEmptyException e)
		// {
		// 	System.out.println(e);
		// }
		// System.out.println(stack.size());


		Queue queue=new Queue();
		queue.enqueue(12);
		queue.enqueue(20);
		System.out.println(queue.size());
		try{queue.dequeue();
		}
		catch (QueueEmptyException e)
		{
			System.out.println(e);
		}
		System.out.println(queue.size());
	}
}

class Node
{
	Node next;
	Object data;

	public Node(Object data)
	{
		this.data=data;
	}
	public Node()
	{
	}
}

class Stack
{
	private Node top;
	public void push(Object obj)
	{
		if(top!=null)
		{
			Node newTop=new Node(obj);
			newTop.next=top;
			top=newTop;
		}
		else
		{
			top=new Node(obj);
		}
	}

	public Object pop() throws StackEmptyException
	{
		if(top!=null)
		{
			Object item=top.data;
			top=top.next;
			return item;
		}
		else
		{
			throw new StackEmptyException("Stack is Empty");
		}
	}

	public Object top()
	{
		return top.data;
	}

	public boolean isEmpty()
	{
		return (top==null);
	}

	public int size()
	{
		int counter=0;
		Node iterator=top;
		while (iterator!=null)
		{
			counter++;
			iterator=iterator.next;
		}
		return counter;
	}

	/*
	Sort a stack in ascending order
	int specific methods
	*/
	public void sortStack()
	{
		try
		{
		Stack toBeSorted=this;
		Stack buffer=new Stack();
		while(!toBeSorted.isEmpty())
		{
			int temp=(int)toBeSorted.pop();
			while(!buffer.isEmpty() && (int)buffer.top()>temp)
			{
				toBeSorted.push(buffer.pop());
			}
			buffer.push(temp);
		}
		toBeSorted.top=buffer.top;
	}
	catch (StackEmptyException e)
	{
		System.out.println(e);
	}
	}
//int specific methods
	public void printStack()
	{
		try{
		Stack stack=this;
		Stack buffer=new Stack();
		while(!stack.isEmpty())
		{
			int temp=(int)stack.pop();
			System.out.print(temp+" ");
			buffer.push(temp);
		}
		while(!buffer.isEmpty())
		{
			stack.push(buffer.pop());
		}
	}
	catch (StackEmptyException e)
	{
		System.out.println(e);
	}
		System.out.println("");
	}
	
}

class Queue
{
	private Node front;
	private Node rear;

	public void enqueue(Object obj)
	{
		if(rear!=null)
		{
			Node newRear=new Node(obj);
			rear.next=newRear;
			rear=newRear;
		}
		else
		{
			rear=new Node(obj);
			front=rear;
		}
	}

	public Object dequeue() throws QueueEmptyException
	{	
		if(front!=null)
		{
			Object item=front.data;
			front=front.next;
			return item;
		}
		else
		{
			throw new QueueEmptyException("Queue is Empty");
		}
	}

	public boolean isEmpty()
	{
		return (front==null);
	}

	public Object top()
	{
		return front;
	}

	public int size()
	{
		int counter=0;
		Node iterator=front;
		while(iterator!=null)
		{
			counter++;
			iterator=iterator.next;
		}
		return counter;
	}
}

class StackEmptyException extends Exception
{
	public StackEmptyException(String r)
	{
		super(r);
	}
}

class QueueEmptyException extends Exception
{
	public QueueEmptyException(String r)
	{
		super(r);
	}
}
