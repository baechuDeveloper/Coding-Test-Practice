package A_experiment;

import java.util.*;

public class List_contains_remove {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(10);
		list.add(100);
		list.add(1000);
		list.add(7);
		
		
		// contain�� ���Ͽ�
		if(list.contains(7))
			System.out.println("7 ����");
		else System.out.println("7 ����");
		
		if(list.contains(new Integer(7)))
			System.out.println("7 ����");
		else System.out.println("7 ����");
		//------------------------------------
	
		
		// remove�� ���Ͽ� 
		try{
			System.out.println(list.remove(7));
			System.out.println("���빰�� ������� ��");
		}
		catch(Exception o) {System.out.println("7������ ������� ��");}
		
		try{
			System.out.println(list.remove(new Integer(7)));
			System.out.println("���빰�� ������� ��");
		}
		catch(Exception o) {System.out.println("7������ ������� ��");}
		//------------------------------------
		
		
		
		
		
	}

}
