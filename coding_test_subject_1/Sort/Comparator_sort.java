package Sort;

import java.util.*;

public class Comparator_sort {

	/**
	 * Collections.sort 메소드는 두 번째 인자로 Comparator 인터페이스를 받을 수 있도록 해놓았습니다.
	 * Comparator 인터페이스의 compare 메소드를 오버라이드 하면 됩니다.
	 * compare 메소드는 Comparable의 compareTo 메소드와 동일한 규칙으로 작성하면됩니다.
	  
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
				return a.get(0) - b.get(0); //맨 앞자리가 더 작은 순서대로 리스트를 정렬해본다.
			}
		});
		
		
		
		
		for(ArrayList<Integer> l: list) {
			for(int i:l) {
				System.out.print(i+" ");
			}System.out.println();
		}
	}

}
