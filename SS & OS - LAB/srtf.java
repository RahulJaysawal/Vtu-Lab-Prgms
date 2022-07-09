import java.util.*;
import java.lang.Math;
public class srtf {
  static Queue<Integer> rdq = new LinkedList<>();
   static void RR(int a[],int b[],int n,int qt)
    {
    
      int total =0;
      int cb[] = new int[n];
      for(int i=0;i<n;i++)
      cb[i] = b[i];
      for(int i=0;i<n;i++)
      total+= b[i];
      int prev =-1;
      int flag[] = new int[n];
      int ct[] = new int[n];
      for(int i=0;i<n;i++)
      ct[i] = -1;
      System.out.println("-------Gantt chart---------");
      for(int time= 0;time<total;)
      {
          for(int arrt=0;arrt<n;arrt++)
          {
              if(a[arrt]<=time && flag[arrt]==0)
              {
                  rdq.add(arrt);
                  flag[arrt]=1;
              }
          }
          if(prev!=-1 && b[prev]>0)
          rdq.add(prev);
          else if(prev!=-1 && b[prev]==0)
          {
              ct[prev] = time;
          }
          if(!rdq.isEmpty())
          {
              time+= Math.min(qt, b[rdq.peek()]);
              b[rdq.peek()]-= Math.min(qt, b[rdq.peek()]);
              System.out.println("--p"+rdq.peek()+"--time");
          
          }
          prev = rdq.peek();
        
          rdq.remove();
          
      }
      int tat[] = new int[n];
      int wt[] = new int[n];
      double avgtat =0,avgwt = 0;
      for(int i=0;i<n;i++)
      {
          if(ct[i]==-1)
          {
              ct[i] = total;
          }
          tat[i] = ct[i]-a[i];
          wt[i] = tat[i]-cb[i];
          avgtat+= tat[i];
          avgwt+= wt[i];
      }
      avgtat/= n;
      avgwt/= n;
      System.out.println("process\tarrivaltime\tbursttime\tcomptime\ttat\twaitingtime\t");
      for(int i=0;i<n;i++)
      {
          System.out.println(i+"\t\t"+a[i]+"\t\t"+cb[i]+"\t\t"+ct[i]+"\t"+tat[i]+"\t"+wt[i]+"\t");
      }
      System.out.println("AvgTat"+avgtat);
      System.out.println("AvgWt"+avgwt);
    }
   static void SRT(int a[],int []b,int n)
    {
        RR(a, b, n, 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of processes");
        int n = sc.nextInt();
        System.out.println("Enter the Arrival Time of the procsses");
        int a[] = new int[n];
        for(int i=0;i<n;i++)
        {
            a[i] = sc.nextInt();
        }
        System.out.println("Enter the burst time of the procsess");
        int b[] = new int[n];
        int qt;
        for(int i=0;i<n;i++)
        {
            b[i] = sc.nextInt();
        }
        System.out.println("Pres  1 for SRT and 2 for RR");
        int choice = sc.nextInt();
        if(choice==1)
        {
            System.out.println("-------SRT--------");
            SRT(a,b,n);
        }
        else if(choice==2){
            System.out.println("Enter the quantum time");
            qt = sc.nextInt();
            System.out.println("-------RR----------");
            RR(a,b,n,qt);
        }
    }
    
}
