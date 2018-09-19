//Steven Huang
//sthuang
//CMPS 12M
//4/13/2015

import java.io.*;
import java.util.Scanner;

class FileReverse {
	public static String stringReverse(String s, int n){
		String x = "";
		if(n>0){
		x = (s.substring(n-1));
		x += stringReverse(s.substring(0,n-1), n-1);
		}
		return x;
	}
	
	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter out = null;
		String[] token = null;
		String line = null;
		
		if(args.length<2){
			System.err.println("Usage: FileReverse infile outfile");
			System.exit(1);
		}
		
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));
		
		while(in.hasNextLine()){
			line =  in.nextLine().trim() + " ";
			token = line.split("\\s");
			int n = token.length;
			for (int i=0; i<n; i++){
				int length = token[i].length();
				out.println(stringReverse(token[i], length));
			}
		}
		
		in.close();
		out.close();
	}

}
