package Union_Find;
import java.util.*;
import java.io.*;

/*====================================================
 * {0}, {1}, {2}, ... , {n}�� ���� n+1���� �������� �̷�� �ִ�.
 * 0�� ��ģ��
 * 1�� ���� ���տ� ���ԵǾ� �ִ��� Ȯ��
 ====================================================*/

// 1717�� - ������ ǥ��
public class p1__Set_Expression {
	static StringBuilder sb = new StringBuilder();
	static int[] group;
	//==========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//0���� n������ ��ȣ
		int m = Integer.parseInt(st.nextToken());	//������ ����
		group = new int[n+1];						//�� ��ȣ�� ���� �׷�(����)
		Arrays.fill(group, -1);
		//��������
		for(int z=0; z<m; z++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(cal) {
				case 0: merge(a, b);
						break;
				case 1: isSameGroup(a, b);
						break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================
	static int find(int a) {
		if(group[a]==-1) return a;	//�� ���ǹ��� �ڽ��� Root��� �׷찪�� -1�̱⿡ �ڱ��ڽ��� �شٴ� ���̴�. find�� ��� ��͸� �ϴٰ� Root�� �����ϸ� Root������ group�� ���� -1�̱⿡ �ڱ� �ڽ��� �ְԵȴ�.
		return group[a] = find(group[a]);
	}//==========================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//==========================================================
	static void isSameGroup(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) 
			sb.append("YES\n");
		else 
			sb.append("NO\n");
	}//==========================================================
}
