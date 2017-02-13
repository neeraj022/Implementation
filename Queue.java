//Methods are:
/*Size()
IsEmpty()
Enqueue(Object obj)
Dequeue() -- removes element from the front of the queue
Front() -- returns front element of queue
*/
class QueueEmptyException extends Exception
{
	 QueueEmptyException(String error)
	{
		super(error);
	}
}

class QueueFullException extends Exception
{
	 QueueFullException(String error)
	{
		super(error);
	}
}

public class Queue 
{
	private int f=-1,r=0;

	private int[] mainArray;

	private int n;

	public Queue(int n)
	{
		this.n=n;
		mainArray=new int[n];
	}
	public Queue()
	{
		this(10);
	}

	public boolean isEmpty()
	{
		if(f==r || f==-1)
		{
			return true;
		}
		return false;
	}

	public int size()
	{
		if(f==-1)
		{
			return 0;
		}
		if(r-f>0)
		{
			return r-f;
		}
		else
		{
			return n+r-f;
		}
	}

	public Object front() throws QueueEmptyException
	{
		if(this.isEmpty())
		{
			throw new QueueEmptyException("Queue is empty");
		}
		else
		{
			return mainArray[mod(r-1, n)];
		}
	}
	public Object dequeue() throws QueueEmptyException
	{
		if(this.isEmpty())
		{
			throw new QueueEmptyException("Queue is empty");
		}
		else
		{
			int temp=mainArray[f];
			mainArray[f]=0;
			f=mod(f+1,n);
			return temp;
		}
	}
	public void enqueue(Object obj) throws QueueFullException
	{
		if(this.size()==n-1)
		{
			throw new QueueFullException("Queue is full");
		}
		else
		{
			if(this.isEmpty())
			{
				f=mod(f+1, n);
			}
			mainArray[r]=(int) obj;
			r=mod(r+1, n);
		}
	}

	public void printParameters()
	{
		System.out.println("Array size is: "+this.size());
		System.out.println("front and rear is: "+this.f+" "+this.r);
		for(int i=0; i<n;i++)
		{
			System.out.print(mainArray[i]+" ");
		}
		System.out.println("");
	}
	private int mod(int x, int y)
	{
    int result = x % y;
    return result < 0? result + y : result;
	}

	public static void main(String args[])
	{
		try{
		Queue myqueue=new Queue(5);
		myqueue.enqueue(2);
		myqueue.enqueue(3);
		myqueue.enqueue(4);
		myqueue.enqueue(5);
		myqueue.dequeue();
		myqueue.printParameters();
		myqueue.enqueue(6);
		myqueue.printParameters();
		myqueue.dequeue();
		myqueue.enqueue(10);
		myqueue.printParameters();
		System.out.println(myqueue.front());
		//System.out.println(myqueue.front());
	}
	catch (QueueEmptyException e)
	{
		System.out.println(e);
	}
	catch (QueueFullException e)
	{
		System.out.println(e);
	}

	}
}