//Steven Huang
//sthuang
//CMPS 12B
//5/10/2015
//Simulation.java

import java.io.*;
import java.util.Scanner;

public class Simulation {
	
	public static Job getJob(Scanner in){
		String[] s = in.nextLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		return new Job(a, d);
	}
	
	public static void main(String[] args) throws IOException{
		Queue Backup = new Queue();
		
		if(args.length != 1){
			System.err.println("Usage: Simulation input_file");
			System.exit(1);
		}
		
		//Scanner and output files
		Scanner in = new Scanner(new File(args[0]));
		PrintWriter rpt = new PrintWriter(new FileWriter(args[0] + ".rpt"));
		PrintWriter trc = new PrintWriter(new FileWriter(args[0] + ".trc"));
		
		//Number of Jobs
		String str = in.nextLine();
		int m = Integer.parseInt(str);
		
		//Initializing Backup Queue
		while(in.hasNextLine()){
			Backup.enqueue(getJob(in));
		}
		
		//Initial output to file
		trc.println("Trace file: " + args[0] + ".trc");
		trc.println(m + " Jobs:");
		trc.println(Backup);
		trc.println();
		
		rpt.println("Report file: " + args[0] + ".rpt");
		rpt.println(m + " Jobs:");
		rpt.println(Backup);
		rpt.println();
		for(int i=0; i<59; i++){rpt.print("*");} rpt.println();
		
		for(int n = 1; n<m; n++){
			Queue Storage = new Queue();
			Queue [] Processor = new Queue[n];
			
			//Copying Backup Queue to Storage Queue
			for(int i=0; i<m; i++){
				Job temp = (Job)Backup.dequeue();
				temp.resetFinishTime();
				Backup.enqueue(temp);
				Storage.enqueue(temp);
			}
			
			//Initializing array of Queues for number of Processors
			for(int i=0; i<n; i++){
				Processor[i] = new Queue();
			}
			
			//Prints number of processors
			for(int i=0; i<29; i++){trc.print("*");} trc.println();
			trc.println(n + (n==1?" processor:":" processors:"));
			rpt.print(n + (n==1?" processor: ":" processors: "));
			for(int i=0; i<29; i++){trc.print("*");} trc.println();
			
			int time = 0;
			//Prints Initial Simulation
			trc.println("time=" + time);
			trc.println("0: " +  Storage);
			for(int i=1; i<=n; i++){
				trc.println(i + ": " + Processor[i-1]);
			}
			trc.println();
			
			while(!Finish(Processor, Storage, n)){
				boolean update = false;
				
				time ++;
				//if( checkArrival(Storage, Processor, time) ) update = true;
				if( checkFinish(Storage, Processor, time) ){
					update = true;
					updateProcessor(Storage, Processor, time);
				}
				if( checkArrival(Storage, Processor, time) ) update = true;
				
				//Updating Finish times
				for(int i=0; i<Processor.length; i++){
					if( !Processor[i].isEmpty() ){
						if( ((Job)Processor[i].peek()).getFinish() == Job.UNDEF ){
							((Job)Processor[i].peek()).computeFinishTime(time);
						}
					}
				}
				
				if(update){
					trc.println("time=" + time);
					trc.println("0: " + Storage);
					for(int i=1; i<=n; i++){
						trc.println(i + ": " + Processor[i-1]);
					}
					trc.println();
				}
			}
			int t = totalWait(Storage, m);
			int max = maxWait(Storage, m);
			double avg = (double)t/m;
			
			rpt.printf("totalWait=%d, maxWait=%d, averageWait=%.2f %n", t, max, avg);
		}
		
		in.close();
		trc.close();
		rpt.close();
	}
	
	//Checks for remaining job processes
	public static boolean Finish(Queue [] Processor, Queue Storage, int n){
		if( !Storage.isEmpty() ){
			if( ((Job)Storage.peek()).getFinish() == Job.UNDEF )
				return false;
		}
		
		for(int i=0; i<n; i++){
			if( Processor[i].isEmpty() ){
				continue;
			}else{
				return false;
			}
		}
		return true;
	}
	
	//Returns shortest an index to the Queue of shortest Length
	public static int minIndex(Queue [] Processor){
		int min = 0;
		for(int i=1; i<Processor.length; i++){
			if(Processor[i].length() < Processor[min].length()){
				min = i;
			}
		}
		return min;
	}
	
	//Checks for arrivals
	public static boolean checkArrival(Queue Storage, Queue [] Processor, int T){
		boolean b = false;
		if( !Storage.isEmpty() ){
			while(T == ((Job)Storage.peek()).getArrival() ){
				Job Temp = (Job)Storage.dequeue();
				int x = minIndex(Processor);
				Processor[x].enqueue(Temp);
				b = true;
				if(Storage.isEmpty()) break;
			}
			
		}
		return b;
	}
	
	//Checks finish times
	public static boolean checkFinish(Queue Storage, Queue [] Processor, int T){
		for(int i=0; i<Processor.length; i++){
			if( !Processor[i].isEmpty() ){
				if( ((Job)Processor[i].peek()).getFinish() == T ){
					return true;
				}
			}
		}
		return false;
	}
	
	//Updates Processor Queues
	public static void updateProcessor(Queue Storage, Queue [] Processor, int T){
		for(int i=0; i<Processor.length; i++){
			if( !Processor[i].isEmpty() ){
				if( T == ((Job)Processor[i].peek()).getFinish() ){
					Job Temp = (Job)Processor[i].dequeue();
					Storage.enqueue(Temp);
				}
			}
		}
	}
	
	//Calculates the total wait time
	public static int totalWait(Queue Storage, int m){
		int wait = 0;
		for(int i = 0; i<m; i++){
			Job temp = (Job)Storage.dequeue();
			wait += temp.getWaitTime();
			Storage.enqueue(temp);
		}
		return wait;
	}
	
	//Determines the max wait time
	public static int maxWait(Queue Storage, int m){
		int wait = 0;
		for(int i = 0; i<m; i++){
			Job temp = (Job)Storage.dequeue();
			if(wait < temp.getWaitTime()) wait = temp.getWaitTime();
			Storage.enqueue(temp);
		}
		return wait;
	}
	
}
