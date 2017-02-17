import java.util.* ;
public class BinarySearchTree
{
	Node root;
	public BinarySearchTree(int data)
	{
		root=new Node(data);
	}

	/*
	********************
	Search method
	********************
	*/
	public Node search(int data)
	{
		return searchHelper(this.root, data);
	}

	public Node searchHelper(Node root, int data)
	{
		if(root==null)
		{
			return null;
		}
		else
		{
			if(data==root.data)
			{
				return root;
			}
			else if(data>root.data)
			{
				return searchHelper(root.right, data);
			}
			else
			{
				return searchHelper(root.left, data);
			}
		}
	}

	public ArrayList<Node> searchWithParent(int data)
	{
		Node iterator=this.root;
		ArrayList<Node> result=new ArrayList<Node>();
		if(iterator==null)
		{
			return null;
		}
		else if(iterator.data==data)
		{
			result.add(null);
			result.add(iterator);
			return result;
		}
		else
		{
			Node parent=null;
			while(iterator!=null)
			{
			if(data>iterator.data)
			{
				parent=iterator;
				iterator=iterator.right;
			}
			else if(data<iterator.data)
			{
				parent=iterator;
				iterator=iterator.left;
			}
			else
			{
				result.add(parent);
				result.add(iterator);
				return result;
			}
			}
			return result;
		}
	}

	public int findMinimum(Node root)
	{
		Node parent=root;
		Node iterator=root.right;
		while(iterator.left!=null)
		{
			parent=iterator;
			iterator=iterator.left;
		}
		if(parent.right!=null && parent.right.data==iterator.data)
		{
			parent.right=null;
		}
		else
		{
			parent.left=null;
		}
		return iterator.data;
	}

	/*
	********************
	Insert
	********************
	*/
	public void insert(int data)
	{
		Node iterator=root;
		if(iterator==null)
		{
			Node newRoot=new Node(data);
			this.root=newRoot;
		}
		else
		{
			while((data>iterator.data && iterator.right!=null) || (data<iterator.data && iterator.left!=null))
			{
			if(data>iterator.data)
			{
				iterator=iterator.right;
			}
			else if(data<iterator.data)
			{
				iterator=iterator.left;
			}
			}
			Node newNode=new Node(data);
			if(data>iterator.data)
			{
				iterator.right=newNode;
			}
			else if(data<iterator.data)
			{
				iterator.left=newNode;
			}
		}
	}

	/*
	********************
	Delete
	********************
	*/
	public boolean delete(int data)
	{
		//search for the node
		ArrayList<Node> found=this.searchWithParent(data);
		if(found==null)
		{
			//node not found
			return false;
		}
		else if(found.get(1).left==null && found.get(1).right==null)
		{
			//its a leaf node
			Node parent=found.get(0);
			if(parent.left!=null && parent.left.data==data)
			{
				parent.left=null;
			}
			else
			{
				parent.right=null;
			}
			System.out.println("Inside delete");
			return true;
		}
		else if(found.get(1).left==null || found.get(1).right==null)
		{
			//node has 1 child
			Node parent=found.get(0);
			if(parent==null)
			{
				if(found.get(1).right!=null)
				{
					this.root=found.get(1).right;
				}
				else
				{
					this.root=found.get(1).left;
				}
				return true;
			}
			if(found.get(1).left!=null)
			{

			if(parent.left!=null && parent.left.data==data)
			{
				parent.left=found.get(1).left;
			}
			else
			{
				parent.right=found.get(1).left;
			}
			}
			else
			{
				if(parent.left!=null && parent.left.data==data)
			{
				parent.left=found.get(1).right;
			}
			else
			{
				parent.right=found.get(1).right;
			}
			}
			return true;
		}
		else
		{
			//found node has both left and right nodes, need to handle this
			int min=this.findMinimum(found.get(1));
			found.get(1).data=min;
			return true;
		}
		
	}
	/*
	********************
	Traversals
	********************
	*/

	public void inOrderTraversel()
	{
		inOrderTraversel(this.root);
	}
	public void inOrderTraversel(Node root)
	{
		if(root==null)
		{
			return;
		}
		inOrderTraversel(root.left);
		System.out.print(root.data+" ");
		inOrderTraversel(root.right);
	}

	public void preOrderTraversel()
	{
		preOrderTraversel(this.root);
	}
	public void preOrderTraversel(Node root)
	{
		if(root==null)
		{
			return;
		}
		System.out.print(root.data+" ");
		preOrderTraversel(root.left);
		preOrderTraversel(root.right);
	}

	public void postOrderTraversel()
	{
		postOrderTraversel(this.root);
	}
	public void postOrderTraversel(Node root)
	{
		if(root==null)
		{
			return;
		}
		postOrderTraversel(root.left);
		postOrderTraversel(root.right);
		System.out.print(root.data+" ");
	}

	//testing
	public static void main(String[] args)
	{
		BinarySearchTree bst=new BinarySearchTree(25);
		bst.insert(24);
		bst.insert(12);
		bst.insert(14);
		bst.delete(25);
		bst.inOrderTraversel();
		System.out.println();
		bst.preOrderTraversel();
		System.out.println();
		bst.postOrderTraversel();
		System.out.println("search result is: "+bst.search(26));
	}
}

class Node
{
	int data;
	Node left;
	Node right;

	public Node(int data)
	{
		this.data=data;
	}
}