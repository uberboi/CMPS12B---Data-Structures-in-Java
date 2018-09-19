public class QueueTest {
	public static void main (String[] args){
		Queue A = new Queue();
		
		//System.out.println(A.isEmpty());
		//System.out.println(A.length());
		
		A.enqueue(1);
		A.enqueue(2);
		//System.out.println(A.dequeue());
		A.enqueue(3);
		A.enqueue(4);
		
		//System.out.println(A);
		
		Queue B = new Queue();
		//B = A;
		//System.out.println(B);
		
		//System.out.println(A.peek());
		
		A.dequeueAll();
		
		//System.out.println(A.isEmpty());
		//System.out.println(A.length());
		
		A.enqueue(6);
		A.enqueue(7);
		A.enqueue(8);
		A.enqueue(9);
		A.enqueue(10);
		
		//System.out.println(A);
		//System.out.println(B);
		
		A.dequeue();
		//System.out.println(A.isEmpty());
		//System.out.println(A.length());
		//System.out.println(A.peek());
		
		B.enqueue(new Job(1,4));
		B.enqueue(new Job(3,4));
		Job o = (Job)B.dequeue();
		
		B.enqueue(o);
		//System.out.println(B);
		//System.out.println(o.getFinish());
	
	}

}
