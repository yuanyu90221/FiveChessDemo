package yuanyu.demo.main;

import java.util.Scanner;

import yuanyu.demo.model.SelfList;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("enter test times n= ?");
		int n = Integer.parseInt(in.nextLine());
        for(int i=1;i <= n; i++)
		{
            System.out.println("The "+i +"th test:");
        	System.out.print("total student N = ?");
        	int N = Integer.parseInt(in.nextLine());
        	System.out.print("frequency freq =? ");
        	int freq = Integer.parseInt(in.nextLine());        	
        	System.out.println("result = "  + findLastNum(N, freq));        	
        }
        in.close();
	}
	
	public static int findLastNum(int number, int freq){
		SelfList sl = new SelfList(number);
		do{
			FindUseFul(sl,freq);
			System.out.println("[yuanyu] current number index " + sl.getCurrentNum().getValue());
		}while(sl.getLeftSize()> 1);
		return sl.getCurrentNum().getValue();
	}
	
	public static void FindUseFul(SelfList sl, int freq){
		int counter = 0;
		do{
			if(sl.getCurrentNum().isRemoved()){
				sl.iteration();
			}
			else{
			    if(counter==freq-1){
			    	sl.removeN(sl.getCurrentNum().getValue());
			    }
			    else{
			    	sl.iteration();
			    }
			    while(sl.getCurrentNum().isRemoved()){
			    	System.out.println("Next number : " + sl.getCurrentNum().getValue());
			    	sl.iteration();
			    }
				counter++;
			}
		}while(counter < freq);
	}
}
