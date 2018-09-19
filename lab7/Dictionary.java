//Steven Huang
//sthuang
//CMPS 12B
//5/27/2015
//Dictionary.java

public class Dictionary implements DictionaryInterface{
	
//Private types and functions------------------------------
	
	private class Node{
		String key;
		String value;
		Node left;
		Node right;
		
		Node(String k, String v){
			key = k;
			value = v;
			left = right = null;
		}
	}
	
	//Fields for Dictionary class
	private Node root;
	int numPairs;
	
	//findKey()
	//Returns the Node containing key k in the subtree rooted at R, 
	//or returns null if no such Node exists
	private Node findKey(Node R, String key){
		if(R == null || key.compareTo(R.key) == 0 ){
			return R;
		}
		if( key.compareTo(R.key) < 0 ){
			return findKey(R.left, key);
		}else{
			return findKey(R.right, key);
		}
	}
	
	//findParent()
	//returns the parent of N in the subtree rooted at R, or returns null
	//if N is equal to R. (pre: R != null)
	private Node findParent(Node N, Node R){
		Node P = null;
		if( N!=R ){
			P = R;
			while( P.left != N && P.right != N ){
				if( N.key.compareTo(P.key)<0 ){
					P = P.left;
				}else{
					P = P.right;
				}
			}
		}
		return P;
	}
	
	//findLeftmost()
	//returns the leftmost Node in the subtree rooted at R, or null if R is null.
	private Node findLeftmost(Node R){
		Node L = R;
		if( L != null )	for( ; L.left != null; L = L.left );
		return L;
	}
	
	//printInOrder()
	//prints the (key, value) pairs belonging to the subtree rooted at R in order
	//of increasing keys to file pointed to by out.
	private String printInOrder( Node R, StringBuffer sb ){
		if( R!=null ){
			printInOrder(R.left, sb);
			sb.append(R.key).append(" ").append(R.value).append("\n");
			//System.out.println(R.key + " " + R.value);
			printInOrder(R.right, sb);
		}
		return new String(sb);
	}
	
//public functions------------------------------
	
	//Dictionary()
	//constructor for the Dictionary type
	public Dictionary(){
		root = null;
		numPairs = 0;
	}
	
	// isEmpty()
    // returns true if this Dictionary is empty, false otherwise
    // pre: none
	public boolean isEmpty(){
		return numPairs == 0;
	}
	
	//size()
	//returns the number of entries in this Dictionary
	//pre: none
	public int size(){
		return numPairs;
	}
	
	//lookup()
	//returns value associated key, or null reference if no such key exists
	//pre: none
	public String lookup(String key){
		Node N = findKey(root, key);
		return( N==null?null:N.value);
	}
	
	//insert()
	//inserts new (key,value) pair into this Dictionary
	//pre: lookup(key)==null
	public void insert(String key, String value) throws KeyCollisionException{
		if( lookup(key) != null ){
			throw new KeyCollisionException("cannot insert duplicate key: " + key);
		}
		Node N = new Node(key, value);
		Node B = null;
		Node A = root;
		while( A != null ){
			B = A;
			if( key.compareTo(B.key) < 0 ){
				A = A.left;
			}else{
				A = A.right;
			}
		}
		if( B == null ){
			root = N;
		}else if( key.compareTo(B.key) < 0 ){
			B.left = N;
		}else{
			B.right = N;
		}
		numPairs++;
	}
	
	//delete()
	//deletes pair with the given key
	//pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException{
		if(lookup(key) == null){
			throw new KeyNotFoundException("cannot delete non-existent key: " + key);
		}
		Node N = findKey(root, key);
		Node P, S;
		if(N.left == null && N.right == null){
			if(N == root){
				root = null;
			}else{
				P = findParent(N, root);
				if(P.right == N){
					P.right = null;
				}else{
					P.left = null;
				}
			}
		}else if(N.right == null){
			if(N == root){
				root = N.left;
			}else{
				P = findParent(N, root);
				if(P.right == N){
					P.right = N.left;
				}else{
					P.left = N.left;
				}
			}
		}else if(N.left == null){
			if(N == root){
				root = N.right;
			}else{
				P = findParent(N, root);
				if(P.right == N ){
					P.right = N.right;
				}else{
					P.left = N.right;
				}
			}
		}else{
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P=findParent(S, N);
			if(P.right == S){
				P.right = S.right;
			}else{
				P.left = S.right;
			}
		}
		numPairs--;
	}
	
	//makeEmpty()
	//pre: none
	public void makeEmpty(){
		root = null;
		numPairs = 0;
	}
	
	//toString()
	//returns a String representation of this Dictionary
	//overrides Object's toString() method
	//pre: none
	public String toString(){
		StringBuffer sb = new StringBuffer();
		printInOrder( root, sb);
		return new String(sb);
	
	}
	
}
