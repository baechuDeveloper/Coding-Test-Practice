package Toplogical_Sort;
import java.io.*;
import java.util.*;

// 2252�� - �� �����
public class p1__Line_Setting {
	/*Ŭ��������*/
	static int N, M;								//�л���, Ű�� ���� Ƚ��
	static ArrayList< ArrayList<Integer> > edge;	//������ ������ ��� �迭����Ʈ
	static int[] indegree;							//�ڽſ��� ������ �ٸ� ������ �����.
	static StringBuilder sb = new StringBuilder();
	//================================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	
		M = Integer.parseInt(st.nextToken());	
		//�л���ȣ�� 1���� �����Ѵ�.
		indegree = new int[N+1]; 
		
		edge = new ArrayList<>();
		for(int i=0; i<=N; i++) 
			edge.add(new ArrayList<>());
		
		//�ܹ��� ���� ����
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			edge.get(L).add(R);	//���� ������ �ִ°Ÿ鼭��, ���ʿ��� ���������� ���� �����̶�� �����ϰ� �̷��� ��Ī���ְ� �־��־���. 
			indegree[R]++;	//�ڽ����� ������ ������ �ִٸ� ī��Ʈ�� �÷��ش�. 
		}
		
		toplogicalSort();
		
		bw.write(sb.toString());
		bw.flush();
	}//================================================================
	/*���� ����*/
	static void toplogicalSort() {
		Queue<Integer> q = new LinkedList<>();
		
		//�ʱ�: ���� ������ ������ �ʴ� ������ ť�� ����
		for(int i=1; i<=N; i++) {
			if(indegree[i] == 0)
				q.add(i);
		}
		
		//int count=0; //������ Ƚ��, Ȥ�� �湮�� Ƚ��
		
		//������ �� ��ŭ �ݺ����� �� ť�� ��� ������ ���ŵǾ�� �Ѵ�.
		while(!q.isEmpty()) {	//N����ŭ�� ������ ���� �װ��� �Ǿ����� �ȵǾ����� �˼������Ƿ� ���������� for������ N�� �������Ѵ�. �ٸ� ���� N���� �ʰ����� �ʴ´�.
			//�ش� ���� �湮
			int now = q.poll();	
			sb.append(now+" "); //���� ���	
			
			//������ �� �� �ִ� �������� �ڽ��� ���������� �˷��ָ鼭 ������ ť�� ���ü��ִ��� Ȯ���Ѵ�.  
			for(int next : edge.get(now)) {
				indegree[next]--;	//now�� ������ ������ �Ǹ鼭 �ش�next�������� ���� ������ �پ��� indegree�� �ٿ��ش�.
				//���� �ش� next������ indegree�� 0�� �Ǿ��ٸ� 
				if(indegree[next]==0) {
					q.add(next);
				}
			}
			//count++; // ���� ������ Ƚ���� N��ŭ �Ǿ��ٸ� ����� ���������� �Ϸ�Ǿ���, �׷��� �ʾҴٸ� ����Ŭ�� �����ؼ� �����ʾҴ�. 
					   //count�� ����� �ο��� �ʰ����� ���Ѵ�. ��� �� ���������� �ݵ�� ���������� �Ǵ� ������ �־��⿡ �������� �ʾҴ�.
					   //'count��� ǥ�� ��� for���� i�� ��ü�ؼ� ����ϴ°͵� ����̴�.' 
		}
	}//================================================================

}
