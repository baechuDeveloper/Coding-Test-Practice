package Sort;

import java.util.*;
import java.io.*;

public class Reverse {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
	
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
			
		Arrays.sort(arr);
		//Arrays.sort(arr, 0, arr.lenth);	//동일 
	
		Integer[] arr2 = {1, 2, 3, 4, 5};	// 일반 Primitive타입처럼 클래스가 아닌 배열로는 '내림차순'이 안된다. 그래서 이렇게 int라면 Integer로 바꾸어라. 
		Arrays.sort(arr2);	//배열이면 Arrays는 가능하다. 물론 Primitive거나 compareTo가 있다면.
		Arrays.sort(arr2, Collections.reverseOrder());	//오름차순은 comapareTo가 있다면 
		String[] arr3 = {"bbb", "fff", "aaa"};
		Arrays.sort(arr3, Collections.reverseOrder());
		
		List<Integer> list = new ArrayList<>();	// list자료형은 이렇게 sort가 주어진다.
		list.add(1); list.add(5); list.add(3);
		list.sort(null); 	//기본 오름차순
		list.forEach( v->System.out.print(v+" "));	System.out.println();
		list.sort(Collections.reverseOrder());	// 이 또한 Collections.reverseOrder()를 인자로 주어서 내림차순이 가능하다. 
		list.forEach( v->System.out.print(v+" "));	System.out.println();
		
		//map, set은 정렬이 있을 필요가 없다. 정렬은 compareTo를 이용해서 진행을 하기에 이를 수정해주도록한다. 
		
		for(int i:arr) 
			bw.write(i+"\n");
		
		bw.flush();
	}

}
