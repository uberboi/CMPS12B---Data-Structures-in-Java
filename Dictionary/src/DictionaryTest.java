public class DictionaryTest {
	
	public static void main(String[] args){
		Dictionary A = new Dictionary();
		//System.out.println(A.isEmpty());
		//System.out.println(A.size());
		
		A.insert("1","a");
		A.insert("2","b");
		A.insert("3","c");
		A.insert("4","d");
		A.insert("5","e");
		A.insert("6","f");
		A.insert("7","g");
		//A.insert("5","g");
		
		/*String v = A.lookup("3");
	    System.out.println("key=3 "+(v==null?"not found":("value="+v)));
	    
	    System.out.println(A.lookup("10"));
		
		System.out.println(A.isEmpty());
		System.out.println(A.size());
		System.out.println(A.lookup("5"));
		*/
		//System.out.println(A.lookup("7"));
		//A.delete("1");
		//A.insert("8", "h");
		//System.out.println(A);
		//System.out.println(A.size());
		//System.out.println(A);
		//A.makeEmpty();
		//System.out.println(A);
		//System.out.println(A.isEmpty());
		
		A.delete("4");
		A.delete("6");
		A.insert("10", "hi");
		A.insert("6", "f");
		System.out.println(A);
		A.insert("10", "bye");
	}
	

}
