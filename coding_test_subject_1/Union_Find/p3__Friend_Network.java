package Union_Find;
import java.util.*;
import java.io.*;

// 4195�� - ģ�� ��Ʈ��ũ
public class p3__Friend_Network {

	static Map<String, Integer> map;
	static int[] group, groupcount;	//�׷��ȣ�� �ش� �׷��� �������ִ� ����� ��
	static int friendcount = 0;
	//======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());	//�׽�Ʈ���̽�	
		while(testcase-->0) {
			int F = Integer.parseInt(br.readLine());	//ģ�� ������ �� 100,000�� ���� �ʴ´�.	
			map = new HashMap<>();
			group = new int[2*100_001];	//2�踦 �ؾ��� ��� ģ���� �ִ�� ���밡�� ���ο� ģ���� ���ö����� ��ȣ�� �ο������״� �� �׷��� ����� �� �ִ�. 
			groupcount = new int[2*100_001]; //�ش� ��ȣ�� ���� �׷��� ������ �α����� �˷��ش�. ���ŵǸ� ��Ʈ�� �׷��� �α����� �����ϰ� ��������.
			Arrays.fill(group, -1);
			Arrays.fill(groupcount, 1);
			for(int i=0; i<F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				int a = map.computeIfAbsent(A, c->friendcount++);	//���ο� �̸����� ��ȣ�� �ο����ش�. �׸��� �� ��ȣ�� �����Ѵ�. 
				int b = map.computeIfAbsent(B, c->friendcount++);	//���� �ִ� �̸��̶� �� ��ȣ�� �������ش�.
				//System.out.println("��ȣ "+a+" "+b);
				sb.append(merge(a,b)+"\n");
			}
			friendcount = 0;
		}
		bw.write(sb.toString());
		bw.flush();
	}//======================================================
	static int find(int a) {
		if(group[a]==-1) return a;
		return group[a] = find(group[a]);	
	}//======================================================
	static int merge(int a, int b) {
		a = find(a);
		b = find(b);
		//System.out.println("���� "+groupcount[a]+" "+groupcount[b]);
		if(a==b) return groupcount[b];
		group[b] = a;
		groupcount[b] += groupcount[a];
		groupcount[a] = groupcount[b];
		//System.out.println("����� ���� "+groupcount[a]+" "+groupcount[b]);
		return groupcount[b];
	}//======================================================
}
