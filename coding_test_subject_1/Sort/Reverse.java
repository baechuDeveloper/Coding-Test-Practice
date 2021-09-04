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
		//Arrays.sort(arr, 0, arr.lenth);	//���� 
	
		Integer[] arr2 = {1, 2, 3, 4, 5};	// �Ϲ� PrimitiveŸ��ó�� Ŭ������ �ƴ� �迭�δ� '��������'�� �ȵȴ�. �׷��� �̷��� int��� Integer�� �ٲپ��. 
		Arrays.sort(arr2);	//�迭�̸� Arrays�� �����ϴ�. ���� Primitive�ų� compareTo�� �ִٸ�.
		Arrays.sort(arr2, Collections.reverseOrder());	//���������� comapareTo�� �ִٸ� 
		String[] arr3 = {"bbb", "fff", "aaa"};
		Arrays.sort(arr3, Collections.reverseOrder());
		
		List<Integer> list = new ArrayList<>();	// list�ڷ����� �̷��� sort�� �־�����.
		list.add(1); list.add(5); list.add(3);
		list.sort(null); 	//�⺻ ��������
		list.forEach( v->System.out.print(v+" "));	System.out.println();
		list.sort(Collections.reverseOrder());	// �� ���� Collections.reverseOrder()�� ���ڷ� �־ ���������� �����ϴ�. 
		list.forEach( v->System.out.print(v+" "));	System.out.println();
		
		//map, set�� ������ ���� �ʿ䰡 ����. ������ compareTo�� �̿��ؼ� ������ �ϱ⿡ �̸� �������ֵ����Ѵ�. 
		
		for(int i:arr) 
			bw.write(i+"\n");
		
		bw.flush();
	}

}
