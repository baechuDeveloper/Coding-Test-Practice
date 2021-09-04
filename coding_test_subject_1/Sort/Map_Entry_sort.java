package Sort;

import java.io.*;
import java.util.*;

// 통계학
public class Map_Entry_sort {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		Map<Integer, Integer> map = new HashMap<>();
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			sum += arr[i];
			if(map.containsKey(arr[i]) == false) 
				map.put(arr[i], 1);
			else {
				int temp = map.get(arr[i]);
				map.put(arr[i], temp+1);
			}
		}	
		Arrays.sort(arr);

		/** ======== 맵의 엔트리를  Sort하는 방법 ======== **/
		// 단 이렇게 하는건 map자체에게 sort를 하는게 아니다. 당연히도 map에게 sort라는건 존재하지 않는다. 따로 이를 관리하는 객체가 존재하게 만드는 것이다.  
		List< Map.Entry<Integer, Integer> > list_freq = new ArrayList<>(map.entrySet());
		
		//sort
		Collections.sort(list_freq, new Comparator< Map.Entry<Integer, Integer> >() {
			public int compare(Map.Entry<Integer, Integer> A, Map.Entry<Integer, Integer>B) {
				//return A.getValue().compareTo(B.getValue());	//오름차순
				return B.getValue().compareTo(A.getValue());	//내림차순
			}
		});
		
		//list_freq.forEach( i->System.out.println(i.getKey()+" "+i.getValue())); System.out.println();
		int best_freq = list_freq.get(0).getValue();	//가장 많은 빈도 수	
		List<Integer> temp = new ArrayList<>();
		
		for(Map.Entry<Integer, Integer> i : list_freq) {
			if(best_freq > i.getValue()) break;
			temp.add(i.getKey());
		}
		/** ================================================================== **/
		
		//산술평균
		System.out.printf("%.0f\n", (float)sum/(float)N);
		//중앙값
		System.out.println(arr[N/2]);
		//최빈값
		temp.sort(null);
		if(temp.size() == 1) System.out.println(temp.get(0));
		else System.out.println(temp.get(1));
		//범위
		System.out.println(arr[N-1] - arr[0]);
	}//=================================================


}