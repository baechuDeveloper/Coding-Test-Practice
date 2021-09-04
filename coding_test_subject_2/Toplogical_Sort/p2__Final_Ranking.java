package Toplogical_Sort;
import java.io.*;
import java.util.*;

/**=================================================================
 * ����� �ٲ� ���� �ֿ��� ����� ������ ���� ���� �� �ڸ��� �״�� �����ϰ� �ȴ�. 
 * �׸��� �߿��� ������ �ִµ�, �������� ���Ѵ�� '�۳⿡ ���ؼ� ������� ������ �ٲ� ���� ��ǥ' �Ѵٰ� �Ѵ�.
 * ��, ���������� ��� ��쿡�� �⺻�� �־��� ������ ������� ��ġ ���迡��.. �� ������� ���谡 �޶��� �͸� ������ְڴٴ� ���̵ȴ�.
 * �׷��� ����� ���� �ʾҴٸ� ������ ������� ������ �����ؾ��Ѵٴ� ���̴�. �̰� ���� �߿��� ����Ʈ�� �ǰڴ�.
 * 
 * �׷��� ������� ��ġ�� �ٲ�� ������ ��, ���ʰ� �������� �� ����� ���踦 �����Ѱ� �ƴ϶�... ���� ���� ����� ���踦 ���� ��Ű�ڴٴ� ���� �ȴ�.
 * 
 * 1 2 3 4
 * 
 * 1 2
 * 3 4
 * 2 3
 * �ε�
 * 3 1 4 2 �� �Ǿ���ϴµ� �̷��� (2 4)�� (1 3)�� ���ԵǾ�� �Ѵ�. �׷��� �ȵȴ�.
 * ------------------------------------------------------------
 * 
 * '���� ����' 
 * 
 * ��������� ���� ���� ���� i������ j������ ���� �� ������ Ȯ�� �ϵ��� 2���� �迭�� ������ �ϰڴ�. 
 * ���� ���������� �ִ��� ���� n���� ���ľ� ������ n�� ���� ���� ���ڶ� �� ����� �� ������.
 * '��������Ʈ'�� ���� Ȯ���ϰ� ����� get������ O(1)�� �ƴϱ⿡ ������ ���� Ȯ���ϰ� ���ﶧ�� '�������'�� �� ������.
 * �ȿ� ���������� �ִ��� Ȯ�� �Ҷ��� ��������Ʈ�� �ϸ� ����, �ش� ���� �ִ��� �װ� ������ ã�µ��� 2���� �迭(�������)�� ������ ����.
 * 
 * ���� �������ķμ� ���������� 0�ΰ� ���� 1������ �����ָ� �ȴ�. �׷��� ���� 0�� �ǴµǷ� ������ �Ű��ָ� �Ǵµ� 
 * ���࿡ ť�� ���������� 0�ΰ� 2�� ���´ٸ� 2���� ��찡 �ݵ�� �����ϴ� ���̶� '?'��� ����� ���´�.
 * 
 * ������ �����ִ� ������ ������ �˷��ܰ� ���ÿ� ������� ��ġ�� �����ִ� ��Ȱ �̱⵵ �ϴ�. 
 * indegree�� �Բ��ؼ� ������ ť�� ������ indegree�� �پ��� ��Ȱ�� ���ְ�, �̸� ���� ������� ��ġ�� ���迡�� ������ �����ֵ��� ���ش�.
 * ������ ť�� �ְ� ���� �������� ������� ��ġ�� Ȯ�������� ������ �� �ִ�.
 =====================================================================*/
// 3665�� - ���� ����
public class p2__Final_Ranking {
	static int[][] from_to;
	//static ArrayList< ArrayList<Integer> > from_to;
	static int[] team, indegree;	//i���� ���� ������ �˷��ش�, indegree ��������
	//========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			int n = Integer.parseInt(br.readLine());	//���� ��	1���� n���� �� ��ȣ�� �ִ�.
			StringTokenizer st = new StringTokenizer(br.readLine());
			team = new int[n+1];	
			indegree = new int[n+1];
			from_to = new int[n+1][n+1];
			/*from_to = new ArrayList<>(); for(int i=0; i<=n; i++) from_to.add(new ArrayList<>());*/
			
			//�۳� �������� ���� �־��ش�. 
			for(int i=1; i<=n; i++) {	//i�� ������ ���Ѵ�.
				int t = Integer.parseInt(st.nextToken());	//�� ��ȣ
				team[i] = t;	// i���� ���� �������� �˷��ش�.
			}
			
			//�۳� ������ ���� �� ������ ������ְ� ���������� ������ش�. �� ������ �����ִ� ������ �˷��ְ� ���������� ���ؼ� ������� ť�� ���� ��������� ������������ �˷��ش�.
			for(int i=1; i<=n; i++) {
				for(int j=i+1; j<=n; j++) {	//���� ���� i�� ���� ���� j�θ� �����ִ� �����̴�.
					//from_to.get(team[i]).add(team[j]);	
					from_to[ team[i] ][ team[j] ] = 1;  //i���� ������ ������ ���� ���鿡 ���� ���� ��(����)�� �ִٰ� �˷��ش�.
					indegree[ team[j] ]++;				//�ش� �������� ������ ���� ����� �޴� ��(������ ����)�� indegree�� �����ش�.
				}
			}
			
			//����� ����� �ٲ� ���� �ִٸ� �� ������ ������ �ٲپ��ش�. 
			int m = Integer.parseInt(br.readLine());	//����� �ٲ� ���� ���� ���� 
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int t1 = Integer.parseInt(st.nextToken());	//���� ��ȣ�� �޴´�.
				int t2 = Integer.parseInt(st.nextToken());
				if( from_to[ t1 ][ t2 ] == 1 ) {
					from_to[ t1 ][ t2 ] = 0;
					from_to[ t2 ][ t1 ] = 1;
					indegree[t1]++;
					indegree[t2]--;
				}
				else {
					from_to[ t1 ][ t2 ] = 1;
					from_to[ t2 ][ t1 ] = 0;
					indegree[t1]--;
					indegree[t2]++;
				}
				
				/*if(from_to.get(t1).contains(t2)) {
					from_to.get(t1).remove(new Integer(t2));	//�̷��� �ؾ� index�� ���ϴ� remove�� �ȵǰ� ������ �����°� �ȴ�.
					from_to.get(t2).add(t1);
					indegree[t1]++;
					indegree[t2]--;
				}
				else {
					from_to.get(t2).remove(new Integer(t1));
					from_to.get(t1).add(t2);
					indegree[t1]--;
					indegree[t2]++;
				}*/
			}
			
			/**�������� ����**/
			Queue<Integer> q = new LinkedList<>();
			for(int i=1; i<=n; i++) {
				if(indegree[i]==0) 
					q.add(i);
			}
			StringBuilder sb = new StringBuilder();
			boolean isfinish = true;
			
			// ���� ������ŭ ������ �����־�� �Ѵ�. 
			for(int cnt=0; cnt<n; cnt++) {	
				if(q.size()==0) {
					bw.write("IMPOSSIBLE\n"); //����Ŭ�� �����ؼ� ���������� ������ �ʴ� ��� - �ϰ����� ���� �����ͷ� ������ �����ټ� ����.
					isfinish = false;
					break;
				}
				else if(q.size()>=2) {
					bw.write("?\n");	//������ ������ ���� �����Ŀ� ���� ������ �޶����� Ȯ���� ������ ������ �� ���� ���.
					isfinish = false;
					break;
				}
				int nowteam = q.poll();
				sb.append(nowteam+" ");	//���� ������ �´� ���� ��������.
				
				//�� ���� �������鼭 ���� ���� ���� �� �� �ִ� ������ ���� �ش� ���� ���������� �ٿ��ش�.
				for(int next=1; next<=n; next++) {
					if(from_to[ nowteam ][ next ] == 1) {
						indegree[next]--;
						if(indegree[next] == 0)
							q.add(next);
					}
				}
				
				/*for(int next : from_to.get(nowteam)) {
					indegree[next]--;
					if(indegree[next]==0) 
						q.add(next);	
				}*/	
			}
			if(isfinish) {
				sb.append("\n");
				bw.write(sb.toString());
			}
		}
		
		bw.flush();
	}//========================================================
}
