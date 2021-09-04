package Graph_short_path;
import java.util.*;
import java.io.*;
/****=============================================================================
 * ���� ���� �˰�����. �Ǵٸ� �ִ� ��� �˰������̴�.
 * ���ͽ�Ʈ�� �˰������ ���������� �������� ���� �ָ� �ٸ� ��� ���������� �ִ� ��θ� ���ϴµ�, ���ͽ�Ʈ�� �˰����򺸴ٴ� �ð��� ���� �ɷ��� O(EV)�� �ð��� �ɸ�����
 * �� �˰������� ���� cost�� ������ ���� ����� �� �� �ֽ��ϴ�!!!
 * �ƴ� ����ü �Ÿ��� ������� ���� �Ҹ���, �� �׷� �� �� ������� �׳� �ִܰŸ��� ���� ������ �������� ���ٰ� ģ�ٸ�, ���� �װ� ������ �Ǵ��� �������� ���ع����ٴ� ����.
 * �׳��� ���� �Ǵ� ���ø� ���ڸ� �Ÿ� ��� �̵��ð��̶� �����ϰ�, Ÿ�Ӹӽ��� Ÿ�� ���ŷ� ���ٰ� �����ϴ� �̴ϴ�. �̋��� �ð��� ���� �����Ƿ� �ҿ�ð��� ������ �� ���Դϴ�.
 --------------------------------------------------
 * ������� ���ͽ�Ʈ��δ� ���� ������ ����� �� �ݿ��մϴ�. 
 * ���� ���� ���� �˰������� 2�� for���� ���� ö���ϰ� ������ ��� ��츦 �� üũ�ϱ�� �մϴ�.
 * �ϴ�, �ִ� ��ζ�� ���� ���� ������ �� �� ���� ���� ���� ������ ���ѷ����� ���ٴ� �����Ͽ� ������ �ִ� ����� ���� ������ ���ƺ��� V-1�� �Դϴ�. (ū for��)
 
 * �켱 ���̶�� ǥ���� ���̴ϴ�. ���̶� dist�迭�� ����ִ� ���¸� ���ϸ� INF�̶�� ���� �ִٸ� ���� �����־ �ش� �������δ� ���� �ִܰ�θ� ���� �����ϴµ� �̿��Ҽ� �����ϴ�.
 * �츮�� �� ���� �վ�鼭 �ִܰ���ΰ��� ���� �ִܰ�ΰ� Ȯ���Ǿ��(�� �۴ٸ� ����, �ƴϸ� �״��) ���� ������ ��δ� ������ ��η� ������ �ɰ��Դϴ�.
 * ���� ���� ������ �ִٸ� �ִܰ�η� �����ص� �����ص� ���� �ȳ��ϴ�. �׷��� ���ѷ����� ���ٸ� ���� ���϶����� �Ǵٸ� �ִܰ���� ��츦 ���غ��� �ִ� �̴ϴ�. 
 * �׷��� ���� ���̴� �� �ִ� V-1���� ���ؼ� �Ҽ��ִ� ��� ���� �վ �� �ֽ��ϴ�. �� �ȿ� ���� ���տ��ٸ� �ش� ������ ��� �ؼ��� ���� �� �� ���� ���Դϴ�. �� �����ִ� ������ �������Դϴ�.
 * V-1�̶�°� �ִܰ���� ������ V-1���̹Ƿ� �־ǿ� ��� V-1���� �õ��� �ؾ��մϴ�.
 * 
 * �� ��츦 ���� 1 -> 5 -> 4 -> 3 -> 2 ��� �������� ��θ� �����ٸ� 1���� ������ 5���� 4���� ���̰�, 2���� ������ 3���� ���̰�, 3���� ������ 2���� ���̸鼭 
 * ��츦 �� ã�ư����ϴ�. �������� �ִܰ�θ� �����ϰ� ���� ���� ���� ���ؼ� �� ������ ������ ���� �ִܰ�ΰ� ������ �ɼ��ְ� ���ο� ��ΰ� ����ϴ�.
 * �������� ��ζ�� V-1���� ������ ���ؼ� ��ü ������ �ش��ϴ� ��츦 �˾ƺ����ֽ��ϴ�. 
 * k=1�϶� ���� ���� ������ �����ϰ�, k=2�϶� k�� 1�϶� ���� ������ ���� ���ο� ���� �ִܰ�θ� �����մϴ�. �� k=2�� k�� 1�϶����� �� �ֽ������� �������ֽ��ϴ�(������ ����). 
 * k=3�϶� k=2�϶� ���� ������ ������ ���ؼ� �� ���ο� ���� �ִܰ�θ� �����մϴ�. �̷��� ����ؼ� 
 * k=V-1�϶� k=V-2�϶� ���� ������ ������ ���ؼ� �� �ٸ� �ִܰ�θ� �����մϴ�. �׷��� ������ V-1���� �����ϸ� ���������ε� ��� �������� �ִܰ�θ� ���ϰ� �˴ϴ�.
 * ���� ���⼭�� ���ѷ����� �߻��Ҽ������� V���� �غ��� �̶� ���࿡ ������ �Ǿ��ٸ� �ִ� V-1�����ؼ� ���̻��� ������ �ִ°��� ���ѷ����̱� ������ ������ �Ǿ��ٰ� ������ �� �ֽ��ϴ�.
 --------------------------------------------------
 * dist[0]=0���� �����ؼ� dist[1]�� 0���� ���� ���� ���� �� �ִ� ��Ȳ�̶�� �����ϱ� ���ѷ����̴�. �׸����� �����Ҷ� ��ó������ ���ƿ��� ��ΰ� ������, ���� �� �۾����� ��� �ɱ� �ϴ� ������ �亯�̴�. 
 * ���� �׷��ԵǸ� k=1���� ������ ������������ ���Ŀ� �ּ� 1���� �ִܰ�� ������ ����Ʈ���� �ȴ�. �׷� ���������� ���ѷ����� ������ �ɰ��̴�.
 
 * https://m.blog.naver.com/kks227/220796963742
 =================================================================================*/

// 11657�� - Ÿ�Ӹӽ�
public class p4__Bellman_Ford_alg {
	
	//Ŭ���� ����
	static int N, M;
	static long[] dist;
	static ArrayList< ArrayList<Node> > list;
	static int INF = 60_000_000;
	//==================================================
	//���� �ڷᱸ��
	static class Node{
		int index, dist;	// �ش� ������ ��ȣ, �� ������ �����ϴ� �������� ���� �Ÿ� 
		Node(int i, int d){
			index = i; dist = d;
		}
	}//=================================================
	//main�Լ�
	public static void main(String[] args) throws IOException {
		//���� ����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		list = new ArrayList< ArrayList<Node> >();
		for(int i=0; i<=N; i++) 
			list.add( new ArrayList<>() );
		
		//���� �Է�
		for(int z=0; z<M; z++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//���۵���
			int b = Integer.parseInt(st.nextToken());	//��������
			int c = Integer.parseInt(st.nextToken());	//����Ÿ�� �̵��ϴµ� �ɸ��ð�
			list.get(a).add( new Node(b, c) );
		}
			
		//���� ���� �˰����� ���� (���ѷ����� ������ -1�� ó��)
		boolean minusCycle = false;
		dist[1] = 0; //�� ó������ 1���� ���� �տ��ִ�. ���������� �հ� �����Ѵ�.
		for(int k=1; k<=N; k++) {		// ū ���� V��(�ѹ��� ������ ���� ��� 1���� �ִܰ�δ� �ϼ��ϴٰ� �����ϰ� �־��� ��� N-1, � ������ ���� �����־ ������ �ȵ����� N-1���� ���鼭 ������ �ϰԵǸ� ������ ��� ����� ���� �տ��ִ�. �̷��� �ص� ������ ���� �������� ������ �������� �ʴ� ����̴�.). 
		// ��ü �������� ������ �ִ� ������ ������ N-1���̱� ������ �ִ� Ƚ����ŭ �õ��غ��� ��. �ٸ�, (N-1) + 1���� ����. �������� ���� ����Ŭ ���� ���� Ȯ�ο�
			
			for(int i=1; i<=N; i++) {	// ���� ����. �ۿ� 'k�� �ִ� for�� N-1'���� ������ ���� �� ������ N�� ������ ���Ŀ��� �ִܰ�� ����
				for(Node nextNode: list.get(i)) {	//����. (���� i�� for������ ���ļ� E�� )�ش� ������ ��������Ʈ�� �����ͼ� ������ �������� Ȯ���Ѵ�.
					int next = nextNode.index;
					int d = nextNode.dist;
					if(dist[i]!=INF && dist[next] > dist[i] + d) {	//���������� INF��� ���� �����ִ�, ���� �������ִ� ���� ������ �Ÿ����� �� ���� ���� ���� �� �ִٸ� �����Ѵ�.
						dist[next] = dist[i] + d;
						if(k==N) minusCycle = true;	//k==N�϶� �̰����� ���Դٸ� �� ������ �ȴٰ�? �̷� �Ҹ��� �Ǳ⿡ ���ѷ����� ���·� �����ֱ��� �� �� �ִ�.
					}
				}
		}}
		
		//�� ��� 
		if(minusCycle) bw.write("-1");
		else 
			for(int i=2; i<=N; i++) 	// 2�����ú��� �������ش�.  
				bw.write( (dist[i]==INF ? "-1" : dist[i]+"") +"\n");
		bw.flush();
	}//==================================================

}