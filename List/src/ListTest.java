//Steven Huang
//sthuang
//5/17/2015
//CMPS 12B
//ListTest.java

class ListTest{
	public static void main(String[] args){
		List<String> A = new List<String>();
		List<Double> B = new List<Double>();
		
		A.add(1, "a");
		A.add(2, "b");
		A.add(3, "c");
		A.add(4, "d");
		
		System.out.println("A: " + A);
		
		A.remove(3);
		
		System.out.println(A.size());
		
		System.out.println(A.get(3));
		
		A.removeAll();
		System.out.println(A.isEmpty());
		
		B.add(1, 1.1);
		B.add(2, 2.2);
		B.add(3, 3.3);
		B.add(4, 4.4);
		
		System.out.println("B: " + B);
		
	}
}