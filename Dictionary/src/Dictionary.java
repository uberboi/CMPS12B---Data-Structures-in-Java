//Steven Huang
//sthuang
//CS 12B
//4/25/2015
//Dictionary.java

public class Dictionary implements DictionaryInterface{
	//private inner Node class
	private class Node{
		String key;
		String value;
		Node next;
		
		Node(String x, String y){
			key = x;
			value = y;
			next = null;
		}
	}
	
	//Fields for Dictionary class
	private Node head;
	private int numItems;
	
	//Dictionary()
	//Constructor for Dictionary class
	public Dictionary(){
		head = null;
		numItems = 0;
	}
	
	//private helper function
	//findKey()
	//returns reference to the Node containing its argument key
	private Node findKey(String key){
		Node N = head;
		for(int i=1; i<=numItems; i++){
			if(N.key.compareTo(key)==0){
				return N;
			}else{
				N = N.next;
			}
		}
		return null;
	}
	
	//ADT Operations
	
	//isEmpty()
	//pre: none
	//post: returns true if the Dictionary contains no pairs and returns false otherwise.
	public boolean isEmpty(){
		return(numItems == 0);
	}
	
	//size()
	//pre: none
	//post: returns the number of (key, value) pairs in the Dictionary.
	public int size(){
		return numItems;
	}
	
	//lookup()
	//pre: none
	//post: returns the associated value field if Dictionary contains a pair whose key field matches the argument key.
	//Otherwise returns a null reference if no such pair exists.
	public String lookup(String key){
		Node N = findKey(key);
		if(N==null){
			return null;
		}else{
			return N.value;
		}
		
	}
	
	//insert()
	//pre: lookup(key) == null
	//post: Adds the pair (key, value) to the Dictionary if pair does not already exist
	public void insert(String key, String value) throws KeyCollisionException{
		if(lookup(key)!=null){
			throw new KeyCollisionException("cannot insert duplicate keys");
		}
		if(numItems == 0){
			Node N = new Node(key, value);
			N.next = head;
			head = N;
		}else{
			Node N = head;
			for ( ; N.next != null; N = N.next);
				N.next = new Node(key, value);
		}
		numItems ++;
	}
	
	//delete()
	//pre: lookup(key) != null
	//post: Removes a pair from the Dictionary whose key field matches the argument key
	public void delete(String key){
		if(lookup(key) == null){
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		
		Node N = findKey(key);
		
		if(head == N){
			head = head.next;
			N.next = null;
		}else{
			Node P = head;
			for(; P.next != N; P = P.next);
			
			N = P.next;
			P.next = N.next;
			N.next = null;
		}
		numItems --;	
	}
	//makeEmpty()
	//pre: none
	//post: isEmpty()
	public void makeEmpty(){
		head = null;
		numItems = 0;
	}
	
	//toString()
	//pre: none
	//post: prints current state to stdout
	//Overrides Object's toString() method
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node N = head;
		
		for( ; N!=null; N=N.next){
			sb.append(N.key).append(" ").append(N.value).append("\n");
		}
		return new String(sb);
	}
}