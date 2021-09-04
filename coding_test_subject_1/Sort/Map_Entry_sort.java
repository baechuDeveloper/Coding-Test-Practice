package Sort;

import java.io.*;
import java.util.*;

// �����
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

		/** ======== ���� ��Ʈ����  Sort�ϴ� ��� ======== **/
		// �� �̷��� �ϴ°� map��ü���� sort�� �ϴ°� �ƴϴ�. �翬���� map���� sort��°� �������� �ʴ´�. ���� �̸� �����ϴ� ��ü�� �����ϰ� ����� ���̴�.  
		List< Map.Entry<Integer, Integer> > list_freq = new ArrayList<>(map.entrySet());
		
		//sort
		Collections.sort(list_freq, new Comparator< Map.Entry<Integer, Integer> >() {
			public int compare(Map.Entry<Integer, Integer> A, Map.Entry<Integer, Integer>B) {
				//return A.getValue().compareTo(B.getValue());	//��������
				return B.getValue().compareTo(A.getValue());	//��������
			}
		});
		
		//list_freq.forEach( i->System.out.println(i.getKey()+" "+i.getValue())); System.out.println();
		int best_freq = list_freq.get(0).getValue();	//���� ���� �� ��	
		List<Integer> temp = new ArrayList<>();
		
		for(Map.Entry<Integer, Integer> i : list_freq) {
			if(best_freq > i.getValue()) break;
			temp.add(i.getKey());
		}
		/** ================================================================== **/
		
		//������
		System.out.printf("%.0f\n", (float)sum/(float)N);
		//�߾Ӱ�
		System.out.println(arr[N/2]);
		//�ֺ�
		temp.sort(null);
		if(temp.size() == 1) System.out.println(temp.get(0));
		else System.out.println(temp.get(1));
		//����
		System.out.println(arr[N-1] - arr[0]);
	}//=================================================


}