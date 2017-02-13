class StackFullException extends Exception{  
 StackFullException(String s){  
  super(s);  
 }  
}  

class StackEmptyException extends Exception{  
 StackEmptyException(String s){  
  super(s);  
 }  
}  


public class Stack
{
	//Methods in a stack
	/*
	isEmpty()
	push(Object object)
	pop()
	size()
	top()
	*/

	private static int t=-1;
	private int[] mainArray;

	public Stack(int n)
	{
		mainArray=new int[n];
	}

	public Stack()
	{
		this(1024);
	}

	public boolean isEmpty()
	{
		if(t==-1)
		{
			return true;
		}
		return false;
	}

	public int size()
	{
		return (t+1);
	}

	public Object top() throws StackEmptyException
	{
		if(isEmpty())
		{
		throw new StackEmptyException("Stack is empty");
		}
		else
		{
			return mainArray[t];
		}
	}

	public Object pop() throws StackEmptyException
	{
		if(isEmpty())
		{
		throw new StackEmptyException("Stack is empty");
		}
		else
		{
			mainArray[t]=0;
			return mainArray[t--];
		}
	}

	public Object push(Object obj) throws StackFullException
	{
		if(size()==mainArray.length)
		{
		throw new StackFullException("Stack is full");
		}
		else
		{
			mainArray[++t]=(int)obj;
			return mainArray[t];
		}
	}

	public void print()
	{
		for (int i=0;i<=t;i++)
		{
			System.out.println(mainArray[i]);
		}
	}

	public static void main(String args[])
	{
		try{
		Stack stack=new Stack();
		stack.push(3);
		stack.push(5);
		System.out.println(stack.top());
		stack.pop();
		stack.push(9);
		System.out.println(stack.size());
		System.out.println("Printing the Stack now ----------------");
		stack.print();
	}
	catch (StackFullException e)
	{
		System.out.println(e);
	}
	catch (StackEmptyException e)
	{
		System.out.println(e);
	}
	}

}