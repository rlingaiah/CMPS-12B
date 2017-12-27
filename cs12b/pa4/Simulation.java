/*rlingaia@ucsc.edu
Simulation.java*/

import java.util.Scanner;
import java.io.*;

public class Simulation{

 public static Job getJob(Scanner in){
      String[] s = in.nextLine().split(" ");
      int a = Integer.parseInt(s[0]);
      int d = Integer.parseInt(s[1]);
      return new Job(a,d);
   }
     public static void procJob(Queue[] que, int a){
      if(que.length-1==1){
         if(!que[0].isEmpty()){
            Job A = ((Job)que[0].peek());
            if(A.getArrival()==a){
               que[1].enqueue(que[0].dequeue());
            }
         }
      }else{
         if(!que[0].isEmpty()){
            Job A = ((Job)que[0].peek());
            if(A.getArrival()==a){
               int[] lengths = new int[que.length-1];
               for(int i = 0; i<lengths.length;i++){
                  lengths[i] = que[i+1].length();
               }
               int index = 0;
               int min = lengths[0];
               for(int i = 1; i<lengths.length; i++){
                  if(lengths[i]<min){
                     min = lengths[i];
                     index = i;
                  }
               }
               que[index+1].enqueue(que[0].dequeue());
            }
         }
      }
   } 

   public static void sort(int[] arr){
      for(int k = arr.length-1; k>0;k--){
         for(int j = 1; j<=k; j++){
            if(arr[j]<arr[j-1]){
               int temp = arr[j];
               arr[j] = arr[j-1];
               arr[j-1] = temp;
            }
         }
      }
   }

  

   public static int nextT(Queue[] proc, int a){
      int[] t= new int[proc.length];
      if(a==0 && !proc[0].isEmpty()){
         a = ((Job)proc[0].peek()).getArrival();
         return a;
      }else if(!proc[0].isEmpty()){
         Job B = ((Job)proc[0].peek());
         if(B.getFinish()==-1){
            t[0] = B.getArrival();
         }
      }
      for(int i = 1; i<proc.length; i++){
         if(!proc[i].isEmpty()){
            Job A = ((Job)proc[i].peek());
            t[i] = A.getFinish();
         }
      }
   
      sort(t);

      for(int i = 0; i<t.length; i++){
         if(t[i]==0) continue;
         if(t[i]!=0){
            a = t[i];
            break;
         }
      }
      return a;
   }


    public static void main(String[] args) throws IOException{
    // check command line 
    if(args.length != 1){
        System.err.println("Usage: Simulation file");
        System.exit(1);
    }

    //open in and out files 
    Scanner in = new Scanner(new File(args[0]));
    PrintWriter report = new PrintWriter(new FileWriter(args[0]+".rpt"));
    PrintWriter trace = new PrintWriter(new FileWriter(args[0]+".trc"));
    String s = in.nextLine();
    int m = Integer.parseInt(s);
    int totalT, maxT;
    double avg;
    Queue storage = new Queue();
    while(in.hasNext()){
        storage.enqueue((Job)getJob(in));
    }

    trace.println("Trace file: " + args[0] + ".trc");
    trace.println(m + " Jobs:");
    trace.println(storage.toString());
    trace.println();

    report.println("Report file: " + args[0] + ".rpt");
    report.println(m + " Jobs:");
    report.println(storage.toString());
    report.println("*********************************************");
    for(int n = 1; n<m; n++){
         trace.println("*****************************");
         trace.println(n==1? n+" processor:": n+" processors:");

        Queue[] que = new Queue[n+1];
        for(int i = 0; i<=n; i++){
            que[i] = new Queue();
        }
          
        for(int i = 0; i<m; i++){
            Job A = (Job)storage.dequeue();
            A.resetFinishTime();
            que[0].enqueue(A);
            storage.enqueue(A);
        }
    int t = 0;

    while(que[0].isEmpty() || ((Job)que[0].peek()).getFinish()==-1 
        || que[0].length()!=m){

        if(t==0){
            trace.println("time=0");
                for(int i = 0; i<=n; i++){
                    trace.println(i+": " + que[i].toString());
                }
        }   
           
        t = nextT(que,t);
        
        for(int i = 1; i<=n; i++){
            if(!que[i].isEmpty() && ((Job)que[i].peek()).getFinish()==t){
                que[0].enqueue(que[i].dequeue());
                if(que[i].length()>0){
                    if(((Job)que[i].peek()).getFinish()==-1){
                        Job top = ((Job)que[i].peek());
                            top.computeFinishTime(t);
                    }
                }
            }
        }
            procJob(que, t);
            for(int i = 1; i<que.length; i++){
               if(!que[i].isEmpty()){
                  Job top = ((Job)que[i].peek());
                    top.computeFinishTime(t);
               }
            }            trace.println("time=" +t);
            for(int j = 0; j<=n; j++){
               trace.println(j+": " + que[j].toString());
            }
            trace.println();
         }
         int total=0;
         int[] times = new int[que[0].length()];
         for(int i = 0; i<times.length; i++){
            Job temp1 = ((Job)que[0].peek());
            times[i] = temp1.getWaitTime();
    
            que[0].enqueue(que[0].dequeue());
         }
         sort(times);
         maxT= times[times.length-1];
         avg= (double)total/(double)times.length;
         
 
         report.print(n==1? n+" processor: " : n+" processors: ");
         report.println(", averageWait="+String.format("%.2f", avg));
    } 

    trace.close();
    report.close();
    in.close();  
    
    }   
}