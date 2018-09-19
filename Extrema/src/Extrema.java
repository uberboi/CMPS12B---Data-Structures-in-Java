//Steven Huang
//sthuang
//CMPS 12B
//4/8/2015

class Extrema {
   
   // maxArray()
   // returns the largest value in int array A
   static int maxArray(int[] A, int p, int r){
	   int q;
	   if (p == r){
		   return A[p];
	   }else{
		   q=(p+r)/2;
		   int L = maxArray(A,p,q);
		   int R = maxArray(A,q+1,r);
		   return max(L,R);
	   }
   }
   
   // minArray()
   // returns the smallest value in int array A
   static int minArray(int[] A, int p, int r){
	   int q;
	   if (p == r){
		   return A[p];
	   }else{
		   q=(p+r)/2;
		   int L = minArray(A,p,q);
		   int R = minArray(A,q+1,r);
		   return min(L,R);
	   }

   }
   
   static int max(int a, int b){
	   if(a>=b){
		   return a;
	   }else{
		   return b;
	   }
   }
   
   static int min(int a, int b){
	   if(a<=b){
		   return a;
	   }else{
		   return b;
	   }
   }
   
   // main()
   public static void main(String[] args){
	      int[] B = {-1, 2, 6, 3, 9, 2, -3, -2, 11, 5, 7};
	      System.out.println( "max = " + maxArray(B, 0, B.length-1) );  // output: max = 11
	      System.out.println( "min = " + minArray(B, 0, B.length-1) );  // output: min = -3
	   }
   
}