package Toplogical_Sort;
import java.io.*;
import java.util.*;
/*===========================================================================
 * ������ �߰��� ��ųƮ�� ����
 * ���� Ǫ�°� ���� ������ �ִٸ� �� �������� �ݵ�� Ǯ��� �Ѵ�.
 * �ϴ� ��� ���� ��ȣ�� ���� ������� �����ֱ����� ������ ������ ������ִ� ������ 32000^2�̹Ƿ� 9���� �Ѵ´�. ���� ���� �ȵǴ� ��ġ�� �����̵Ǿ 
 * �� ����� �ȵǴ� 1���� ������� �۾��� �ҷ����ϴµ� Ȥ�� �� �� �۾����� �ؾ߸� �ϴ� �۾� �����ִ��� ���� ���̴�. 
  

 * ���� �߿������� ���� ������ Ǯ����ϴ� Ÿ�̹��� ��, �� Ÿ�ֿ̹��� ���� ���ƾ��� ������� �־�� �Ѵٴ� ���̴�. 
  
 * 5 2
 * 1 4
 * 5 2 
 * ���� 1 3 4 5 2 �̴�. 
 * �� ������ Ǫ�� Ÿ�̹��̶� �� ������ ���� ����� �ϴ� �����̴�. ù ��° ������ ������ Ǫ�� Ÿ�ֿ̹� ���� ��������� indegree�� 0�ΰ��� 1�� �����̴�
 * �� ��° ������ ������ Ǫ�� Ÿ�ֿ̹� ���� ���� �������� indegree�� 0�� ���� 3�� �����̴�.
 * �� ��° ������ ������ Ǫ�� Ÿ�ֿ̹� ���� ���� �������� indegree�� 0�� ���� 4�� �����̴�. 4�� ������ 1�������� Ǯ�� �� indegree���� 0���� �Ǿ���.
 * �� ��° ������ ������ Ǫ�� Ÿ�ֿ̹� ���� ���� �������� indegree�� 0�� ���� 5�� �����̴�. 
 * �ټ���° ������ ������ Ǫ�� Ÿ�ֿ̹� ���� ���� �������� indegree�� 0�� ���� 2�� �����̴�. 5�� ������ Ǯ�鼭 2�� ������ indegree�� 0�� �Ǿ��� �ٽ� ť�� ���ͼ� ���� ������� ���õǾ���.
 ============================================================================*/

// 1766�� - ������
public class p4__StudyGuide {
	/*Ŭ���� ����*/
	static int N, M; 
	static int[] indegree;
	static ArrayList< ArrayList<Integer> > list;
	static boolean[] finish;	//�ش� ������ Ǯ��´ٸ� true�� �ش�, �ƴϸ� false
	static boolean[] include;	//�ش� ���� ��ȣ�� ť�� ���� ���ԵǾ��ִ���, ������ Ǯ���ų� Ȥ�� ���� ��Ǯ� ť���� ���������� false���� �ش�. 
	static StringBuilder sb = new StringBuilder();
	//=========================================================
	/*main�Լ�*/
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//������ ��	1<=N<=32,000
		M = Integer.parseInt(st.nextToken());	//���� Ǫ�� ���� ���� ������ ���� ���� ����	1<=M<=100,000
		indegree = new int[N+1];
		list = new ArrayList<>();
		for(int i=0; i<=N; i++)
			list.add(new ArrayList<>());
		
		//���� Ǯ����� ���� üũ
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	//A�� ������ B�� �������� ���� Ǫ�� ���� ����.
			int B = Integer.parseInt(st.nextToken());
			list.get(A).add(B);	//A������ Ǯ������ B������ Ǯ�� ��������.
			indegree[B]++;		//B������ ���� �ռ��� Ǯ����� ������ �ִ�.
		}
		
		//�̹� �������Ŀ� �ʿ��� �߰� ������� �����Ҵ�
		finish = new boolean[N+1];
		include = new boolean[N+1];
		Arrays.fill(include, true);
		
		//�켱����ť�� �ξ ���� Ǯ��� �� ������ ���� ���� ������ �������� �������ش�. ���� ���߿� indegree�� 0�ΰ� ������ �� ������ ���� ������ �°� �־��ش�.
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for(int i=1; i<=N; i++)
			pq.add(i);	//�켱 ��� ������ �־ ������ �Ѵ�. ���� ��ȣ�� �� ������ ���̵���.
		
		/*---�������� ����---*/
		for(int cnt=0; cnt<N; ) {
			if(pq.isEmpty()) break; //�������� �̷� ���� ���� �ʴ´ٰ� �ߴ�. �� ����Ŭ�� �����ϴ� ��츦 ������ �ʰڴٰ� �ߴ�.
			
			//���� �� ������ Ǯ��� now�� �����´�.
			int now = pq.poll();	
			
			//ť���� now�� ���̻� ����.
			include[now] = false;	
			
			//���翡 ���� ���� Ǯ��� �� ������ ������ ���� ������ �Ѿ��.
			if(indegree[now]!=0)	
				continue;	

			//�� Ǯ��� �� ������ ���ٸ� �ʸ� Ǯ���ٰ� üũ.
			finish[now] = true; 
			sb.append(now+" ");
			
			//������ �������� ������ �������� üũ���ش�.
			for(int easynext : list.get(now)) {	
				if(finish[easynext]) 		//�̹� Ǭ ������� �Ѿ��. ���� ����Ŭ�� ������ �ʾƼ� ���⸦ ������ ���� ���� ���̴�.
					continue; 	
				indegree[easynext]--;		//�ռ� ������ Ǯ�����ٰ� �˷��ش�.
				if(indegree[easynext]==0 && !include[easynext]) { 	//���� �� �������� ������ ���̻� �տ� Ǯ�� �������µ� ť�� �ȵ� �ִٸ� �־��ش�.
					pq.add(easynext);	
					include[easynext] = true;
				}
			}
			
			//���� ������ Ǯ� Ǭ ������ �÷��ش�.
			cnt++;		
		}/*---�������� ����---*/
		
		bw.write(sb.toString());
		bw.flush();
	
		/** --------------------------------------------------
		//�̰� �� ���� Ÿ�ֶ̹� ���� ������� Ǯ����Ѵٴ� ������ ���� �������ش�.
		// �ٵ� ���� �ٲٸ� �����ϰڳ� 
		//���� Ǯ����� ���� üũ
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());	//A�� ������ B�� �������� ���� Ǫ�� ���� ����.
			int B = Integer.parseInt(st.nextToken());
			list.get(B).add(A);	//�ݴ�� B�� �ϱ����ؼ� �ʿ��Ѱ� ���������� �����ؼ� �������� �־��ش�.		
		}
		for(int i=1; i<=N; i++)
			list.get(i).sort(null);
		for(int cnt=0; cnt<N; ) {
			if(visit[now]) continue;
			preStudy(now);
			cnt++;
		}-----------------------------------------------------**/
	
	}//=========================================================
	
	/**static void preStudy(int prenow) {
		visit[prenow] = true;
		for(int prenext : list.get(prenow)) {
			if(visit[prenext]) continue;
			preStudy(prenext);
		}
		sb.append(prenow+" ");
	}//=========================================================
	**/
}



