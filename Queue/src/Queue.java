//Steven Huang
//sthuang
//CMPS 12B
//5/9/2015
//Queue.java

public class Queue implements QueueInterface{
	
	//Private Node class
	private class Node{
		Object item;
		Node next;
		
		Node(Object x){
			item = x;
			next = null;
		}
	}
	
	//Fields for Queue class
	private Node head;
	private int numItems;
	
	//Queue()
	//Constructor for Queue class
	public Queue(){
		head = null;
		numItems=0;
	}
	
	// isEmpty()
	// pre: none
	// post: returns true if this Queue is empty, false otherwise
	public boolean isEmpty(){
		return(numItems == 0);
	}
	
	// length()
	// pre: none
	// post: returns the length of this Queue.
	public int length(){
		return(numItems);
	}
	
	//enqueue()
	//adds newItem to back of this Queue
	//pre: none
	//post: !isEmpty()
	public void enqueue(Object newItem){
		if(numItems == 0){
			Node N = new Node(newItem);
			N.next = head;
			head = N;
		}else{
			Node N = head;
			for( ; N.next != null; N = N.next);
				N.next = new Node(newItem);
		}
		numItems ++;
	}
	
	//dequeue()
	//deletes and returns item from front of this Queue
	//pre: !isEmpty()
	//post: This Queue will have one fewer element
	public Object dequeue() throws QueueEmptyException{
		if(numItems==0){
			throw new QueueEmptyException("cannot dequeue() empty queue");
		}
		
		Object returnValue = head.item;
		
		Node N = head;
		head = head.next;
		N.next = null;
		numItems--;
		
		return returnValue;
	}
	
	//peek()
	//pre: !isEmpty()
    //post: returns item at front of Queue
	public Object peek() throws QueueEmptyException{
		if(numItems==0){
			throw new QueueEmptyException("cannot peek() empty queue");
		}
		
		return head.item;
	}
	
	//dequeueAll()
	//sets this Queue to the empty state
	//pre: !isEmpty()
	//post: is Empty()
	public void dequeueAll() throws QueueEmptyException{
		if(numItems==0){
			throw new QueueEmptyException("cannot dequeueAll() empty queue");
		}
		head = null;
		numItems=0;
		
	}
	
	//toString()
	//overrides Object's toString() method
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Node N = head;
		
		for( ; N!=null; N=N.next){
			sb.append(N.item).append(" ");
		}
		return new String(sb);
	}
}

