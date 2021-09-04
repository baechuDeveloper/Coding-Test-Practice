package Union_Find;
import java.util.*;
import java.io.*;

// 1976�� - ���� ����
public class p2__Lets_Travel {
	static int[][] road;
	static int[] group;
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	//������ ��	200����
		int M = Integer.parseInt(br.readLine());	//�����ȹ�� ���� ���õ��� ��	1000����
		road = new int[N+1][N+1];
		group = new int[N+1];
		Arrays.fill(group, -1);
		//����� ���������� �Է�
		//���ù�ȣ�� 1����, i�����ÿ� j�����ð� ����Ǿ������� 1, �ƴϸ� 0���� �־�����.
		//�� ������ ���� ���� ����� ���ó����� '�׷�'�� ������ִ�. ���� �׷쿡 �ִٸ� ���� �������ְ�, ���� �׷��� �ƴϸ� ���� �� ����.
		//�̰������� merge�۾��� ���� ���ó����� �׷��� �����ϰ� ���ش�.
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
				if(road[i][j] == 1) {
					merge(i, j);	//i�� j�� ���ø� ���� �׷����� �д�.
				}
			}
		}
		
		//���� ��ȹ
		//���� �������� ���õ��� ���� ���� �׷����� üũ�ϸ� �ȴ�. 
		int whereGroup = -1;		//���� ����ǰ��ִ� �׷��� �������� Ȯ���ϸ� �̰Ͱ� �ٸ� �׷��̶�� ������ ���̴�. �ʱ⿡ -1�� �ƹ��͵� ���������ְ�, �������� �� ���� �����Ѵ�.
		boolean isConnect = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=M; i++) {
			int city = Integer.parseInt(st.nextToken());
			int nowGroup = find(city);
			if(whereGroup==-1) 
				whereGroup = nowGroup;
			else if(whereGroup!=nowGroup) {
				isConnect = false;
				break;	
			}
		}
		
		System.out.println( (isConnect? "YES" : "NO") );
	}//========================================================
	static int find(int a) {
		if(group[a]==-1)
			return a;
		return group[a] = find(group[a]);
	}//========================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//========================================================
}
