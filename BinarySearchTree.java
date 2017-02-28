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

	//if distance difference between two leaf nodes is more than 1, then tree is unbalanced
	public boolean checkIfBalanced()
	{
		return ((this.maxHeight(this.root)-this.minHeight(this.root))>1)? false :true;
	}

	public int maxHeight(Node root)
	{
		if(root==null)
		{
			return 0;
		}
		return 1+Math.max(this.maxHeight(root.left), this.maxHeight(root.right));
	}

	public int minHeight(Node root)
	{
		if(root==null)
		{
			return 0;
		}
		return 1+Math.min(this.minHeight(root.left), this.minHeight(root.right));
	}

	/*
		Given a sorted array in increasing order, construct a binary search tree with minimum height bst
	*/
		public Node constructMinHeightTree(int[] input, int start,int end)
		{
			if(start>end)
			{
				return null;
			}
			int mid=(start+end)/2;
			Node newNode=new Node(input[mid]);
			newNode.left=this.constructMinHeightTree(input, start, mid-1);
			newNode.right=this.constructMinHeightTree(input, mid+1 ,end);
			return newNode;
		} 

		/*public Node inOrderSuccessor(Node input)
		{
			//if its a leaf node, successor is keep going up until you are left child, parent is successor
			//if its internal node, keep going right down, until left is null, 
			if(input.left!=null || input.right!=null)
			{
				//its a internal node
				Node iterator=input.right;
				while(iterator!=null && iterator.left==null)
				{
					iterator=iterator.left;
				}
				return iterator;
			}
			else if(input.left==null && input.right==null)
			{
				Node iterator=input;
				while(iterator!=null && iterator.data<iterator.parent.data)
				{
					iterator=iterator.parent;
				}
				if(iterator==null)
				{
					return null;
				}
				return iterator.parent;
			}
			return null;
		}*/
		int c=0;
		public void printKthLargest(Node root, int k)
		{
			if(root==null)
			{
				return;
			}
			if(c>k)
			{
				return;
			}
			printKthLargest(root.right, k);
			c++;
			if(c==k)
			{
				
				return;
			}
			printKthLargest(root.left, k);
			return;

		}


		/*
			vertical traversal
		*/
			public void verticalTraversal(Node root, Integer hd, HashMap<Integer, ArrayList<Node>> map)
			{
				if(root==null)
				{
					return;
				}
				
				verticalTraversal(root.left,hd-1,map);
				ArrayList<Node> myList=map.get(hd);
				if(myList==null)
				{
						myList=new ArrayList<Node>();
						myList.add(root);
						map.put(hd,myList);
				}
				else
				{
						myList.add(root);
						map.put(hd,myList);
				}
				verticalTraversal(root.right,hd+1,map);

			}

	//testing
	public static void main(String[] args)
	{
		BinarySearchTree bst=new BinarySearchTree(25);
		bst.insert(24);
		bst.insert(26);
		bst.insert(14);
		bst.insert(17);
		//bst.printKthLargest(bst.root, 3);
		// System.out.println(bst.checkIfBalanced());
		// bst.delete(25);
		// bst.inOrderTraversel();
		// System.out.println();
		// bst.preOrderTraversel();
		// System.out.println();
		// bst.postOrderTraversel();
		// System.out.println("search result is: "+bst.search(26));
		// System.out.println("Tree being constructed using arrays ");
		// int[] input=new int[10];
		// for(int i=0;i<10;i++)
		// {
		// 	input[i]=i+1;
		// }
		// Node root=bst.constructMinHeightTree(input, 0,9);
		// bst.root=root;
		// bst.inOrderTraversel();
		// bst.preOrderTraversel();
		HashMap<Integer,ArrayList<Node>> result=new HashMap<Integer,ArrayList<Node>>();
		bst.verticalTraversal(bst.root, new Integer(0), result);
		List sortedKeys=new ArrayList(result.keySet());
		Collections.sort(sortedKeys);
		for (int j=0;j<sortedKeys.size();j++){
			ArrayList<Node> myList=result.get(sortedKeys.get(j));
			for(int i=0;i<myList.size();i++)
			{
				String value = String.valueOf(myList.get(i).data);  
            	System.out.println(((Integer)sortedKeys.get(j)).intValue() + " " + value);  
			}
            


} 
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