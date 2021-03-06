//Steven Huang
//sthuang
//CS 12B
//4/14/2015
//Searches an input file for key words
//Search.java

import java.io.*;
import java.util.*;

public class Search{
	public static void main(String[] args) throws IOException{
		
		String[] array;
		String line;
		
		if(args.length < 2){
			System.err.println("Usage: Search file targets");
			System.exit(1);
		}
		
		Scanner in = new Scanner(new File(args[0]));
		in.useDelimiter("\\Z");
		line = in.next();
		in.close();
		array = line.split("\\s+");
		
		int [] lineNumber = new int [array.length];
		
		for(int i = 0, j = 1; i<array.length; i++, j++){
			lineNumber[i] = j;
		}
		
		mergeSort(array, lineNumber, 0, array.length-1);
		
		int x = args.length;
		for(int i = 1; i<args.length; i++){
			int y = binarySearch(array, 0, array.length-1, args[i]);
			if(y == -1){
			System.out.println(args[i] + " not found");
			}else{
				System.out.println(array[y] + " found on line " + lineNumber[y]);
			}
		}
		
	}
	
	static void mergeSort(String[] word, int[] lineNumber, int p, int r){
		int q;
		if(p<r){
			q = (p+r)/2;
			mergeSort(word, lineNumber, p, q);
			mergeSort(word, lineNumber, q+1, r);
			merge(word, lineNumber, p, q, r);
		}
		
	}
	
	static void merge(String[] word, int[] lineNumber, int p, int q, int r){
		int n1 = q-p+1;
		int n2 = r-q;
		String[] L = new String[n1];
		String[] R = new String[n2];
		int [] x = new int[n1];
		int [] y = new int[n2];
		int i, j, k;
		
		for(i=0; i<n1; i++){
			L[i] = word[p+i];
			x[i] = lineNumber[p+i];
		}
		
		for(j=0; j<n2; j++){
			R[j] = word[q+j+1];
			y[j] = lineNumber[q+j+1];
		}
		
		i = 0; j = 0;
		
		for(k=p; k<=r; k++){
			if(i<n1 && j<n2){
				if(L[i].compareTo(R[j])<0){
					word[k] = L[i];
					lineNumber[k] = x[i];
					i++;
				}else{
					word[k] = R[j];
					lineNumber[k] = y[j];
					j++;
				}
			}else if(i<n1){
				word[k] = L[i];
				lineNumber[k] = x[i];
				i++;
			}else{
				word[k] = R[j];
				lineNumber[k] = y[j];
				j++;
			}
		}
		
		
	}
	
	public static int binarySearch(String[] word, int p, int r, String target){
		int q;
		if(p>r){
		return -1;
		}else{
			q = (p+r)/2;
			if(target.compareTo(word[q])==0){
				return q;
			}else if(target.compareTo(word[q])<0){
				return binarySearch(word, p, q-1, target);
			}else{
				return binarySearch(word, q+1, r, target);
			}
		}
	}
	
}