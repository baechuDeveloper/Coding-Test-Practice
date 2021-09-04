package Two_Pointer_alg;


// ���� - ȸ�� �ʹ� - 2531��
// https://www.acmicpc.net/problem/2531

/**=================================================================
 * �� �մ��� �׻� �������� �Դ´�. 
 * ��� ��Ģ
 * 1 .���� ȸ�� �ʹ��� �մ��� ������� �ʹ���  ������, ���� �ʹ丸ŭ �Ĵ븦 ���������, 
 * ��Ʈ�� ������ �� ��ġ���� k���� ���ø� �����ؼ� ���� ��� ���ε� ���� �������� �����Ѵ�. 
 * 
 * 2. �� �������� �ʹ��� ���� �ϳ��� ���� ������ �����ϰ�, 1�� ��翡 ������ ��� �� ������ ������ 
 * ������ �ʹ� �ϳ��� �߰��� ����� �����Ѵ�. ���� �� ��ȣ�� ������ �ʹ��� ���� ��Ʈ ���� ���� ���, 
 * �丮�簡 ���� ����� �մԿ��� �����Ѵ�.  
 * ----------------------------------------------------------
 * ������ ����ؼ� �߰��� �Դ°� �����ؼ� ���� �ʹ��� ������ �ø��� �ִ�.
 ====================================================================**/

import java.util.*;
import java.io.*;

public class p6__Sliding_Window_Sushi {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		// ������ 2 �� N �� 30,000, 2 �� d �� 3,000, 2 �� k �� 3,000 (k �� N), 1 �� c �� d �̴�.
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// ȸ�� �ʹ� ��Ʈ�� ���� ������ ��
		/*int d =*/ Integer.parseInt(st.nextToken());	// �ʹ��� ������, �ٵ� �ִ� �������� ������ ��������� ����. �ִ� ��ȣ�� �˷��ذ�.
		int k = Integer.parseInt(st.nextToken());	// �����ؼ� ���� ������ ��
		int c = Integer.parseInt(st.nextToken());	// ���� ��ȣ
		
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());

		Set<Integer> set = new HashSet<>();
		Map<Integer, Integer> map = new HashMap<>();
		
		map.put(c, 0);
		for(int i=0; i<k; i++) {
			set.add(arr[i]);
			if(map.containsKey(arr[i])) {
				int temp = map.get(arr[i]);
				map.put(arr[i], temp+1);
			}
			else 
				map.put(arr[i], 1);
		}
		
		int L = 0;				// 0��°���� ���� ����
		int R = k; 				// k��° ���� �߰� ���� 
		int count = set.size();	// ���� ���� ����
		if(map.get(c)==0) count++;
		int temp_count = 0;
		int temp = 0;
		boolean circle = false;	//R�� �ѹ��� ���Ҵ°�.

		while(true) {
			//System.out.println((L+1)+" "+R);
			//map.forEach( (a,b) -> {System.out.println("key:"+a+" cont:"+b);});
			
			//���� ����
			if(map.get(arr[L]) == 1) 
				set.remove(arr[L]);
			temp = map.get(arr[L]);
			map.put(arr[L], temp-1);
			++L;
		
			//������ �߰�
			set.add(arr[R]);
			if(map.containsKey(arr[R])) {
				temp = map.get(arr[R]);
				map.put(arr[R], temp+1);
			}
			else 
				map.put(arr[R], 1);
			++R;
			
			//���� üũ
			temp_count = set.size();
			
			if(map.get(c)==0) 
				temp_count++;
			
			if(count<temp_count)
				count = temp_count;
			//System.out.println("temp:"+temp_count+"\n");
		
			//����üũ
			if(R==N) {
				R=0;
				circle=true;
			}
			//����
			if(circle==true && R==k) 
				break;
		}
		
		bw.write(count+"");
		bw.flush();
		bw.close();
		br.close();
	}

}