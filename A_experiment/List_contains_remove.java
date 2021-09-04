package A_experiment;

import java.util.*;

public class List_contains_remove {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(10);
		list.add(100);
		list.add(1000);
		list.add(7);
		
		
		// contain에 대하여
		if(list.contains(7))
			System.out.println("7 있음");
		else System.out.println("7 없음");
		
		if(list.contains(new Integer(7)))
			System.out.println("7 있음");
		else System.out.println("7 없음");
		//------------------------------------
	
		
		// remove에 대하여 
		try{
			System.out.println(list.remove(7));
			System.out.println("내용물을 지울려고 함");
		}
		catch(Exception o) {System.out.println("7번쨰를 지울려고 함");}
		
		try{
			System.out.println(list.remove(new Integer(7)));
			System.out.println("내용물을 지울려고 함");
		}
		catch(Exception o) {System.out.println("7번쨰를 지울려고 함");}
		//------------------------------------
		
		
		
		
		
	}

}
