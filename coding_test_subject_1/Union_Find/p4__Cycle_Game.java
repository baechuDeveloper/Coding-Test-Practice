package Union_Find;
import java.util.*;
import java.io.*;

// 20040�� - ����Ŭ ����
public class p4__Cycle_Game {
	static int[] group;
	//======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//���� ����  	��ȣ�� 0���ͽ���
		int m = Integer.parseInt(st.nextToken());	//����� ������ ����
		group = new int[n];
		Arrays.fill(group, -1);
		for(int i=1; i<=m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if( merge(a, b)==false ) {	//���� ���� ���տ� �����ִٴ� ���̵ȴ�. �� ���� �ߺ��� ������ �����ʾұ⿡ ���� a,b������ �״�� �޾��־��ٸ� ����Ŭ�� ������̴�.
				System.out.println(i);
				return;
			}
		}
		System.out.println(0);
	}//======================================================
	static int find(int a) {
		if(group[a]==-1) return a;
		return group[a] = find(group[a]);	
	}//======================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//======================================================
}
