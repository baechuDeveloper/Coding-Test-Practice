package ETC;

import java.io.*;
import java.util.*;

// 1017�� - �Ҽ��� 
public class prime_pair {

	static int N;
	static boolean[] prime;
	static int[] arr;
	static ArrayList<ArrayList<Integer>> list;
	static Set<Integer> set;
	//=========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int num = 2001;
		prime = new boolean[num+1];    //false �ΰ��� ��� �Ҽ� 
		prime[0] = prime[1] = true; //true�� �Ȱ��� prime�Ҽ��� '�� �� ���� ��'���̴�.
	
		//2 ���� ���ڸ� Ű������ ������� ����
		for(int i=2; i*i<=num; i++) {	//i�� 1�� �����ϸ鼭 ���� ��ġ�� ����� �����ִ� ��Ȱ
			for(int j=i*i; j<=num; j+=i) { //j�� �ش� i�� �������� �����ŭ �ö󰣴�. (������ ����� ���� ���´� ���������� �Ҽ��� �ƴ��� �����ְ� �Ǿ�����ī���)
				prime[j] = true;        //2�� ������ 2�� ��� true
			}
		}
		// prime���� false�� �͵��� �Ҽ��� �ȴ�. 
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		list = new ArrayList<>();
		for(int i=0; i<=N; i++) 
			list.add(new ArrayList<>());
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i==j) continue;
				
			}
		}

	}//=========================================================

}
