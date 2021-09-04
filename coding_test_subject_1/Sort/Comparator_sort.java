package Sort;

import java.util.*;

public class Comparator_sort {

	/**
	 * Collections.sort �޼ҵ�� �� ��° ���ڷ� Comparator �������̽��� ���� �� �ֵ��� �س��ҽ��ϴ�.
	 * Comparator �������̽��� compare �޼ҵ带 �������̵� �ϸ� �˴ϴ�.
	 * compare �޼ҵ�� Comparable�� compareTo �޼ҵ�� ������ ��Ģ���� �ۼ��ϸ�˴ϴ�.
	  
	 * https://includestdio.tistory.com/35
	 * */
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<>();
		
		list.add(new ArrayList<>());
		list.get(0).add(1);
		list.get(0).add(7);	
		
		list.add(new ArrayList<>());
		list.get(1).add(3);
		list.get(1).add(6);
		list.get(1).add(8);
		
		list.add(new ArrayList<>());
		list.get(2).add(2);
		list.get(2).add(4);
		list.get(2).add(5);
		
		
		Collections.sort(list, new Comparator< ArrayList<Integer> >() {
			
			public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
				return a.get(0) - b.get(0); //�� ���ڸ��� �� ���� ������� ����Ʈ�� �����غ���.
			}
		});
		
		
		
		
		for(ArrayList<Integer> l: list) {
			for(int i:l) {
				System.out.print(i+" ");
			}System.out.println();
		}
	}

}
