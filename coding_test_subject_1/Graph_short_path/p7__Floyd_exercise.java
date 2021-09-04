package Graph_short_path;
import java.util.*;
import java.io.*;
// �÷��̵� �ͼ��� ������ �� �ִ�.
// ���� dist[i][i]�� �Ÿ��� ������� �ʰ� 0���� �ʱ�ȭ�صξ��µ� 
// 1956�� - �
public class p7__Floyd_exercise {

	static int V, E;		//���� ����, ���� ����   
	static int[][] dist;	//[i][j] i�������� j������ ���� �ִܰŸ� ��. (�پ����� �ʴ���, �ٸ� �������� ������ ���ؼ� ������� �ִܰŸ�)
	static int INF = 100_000_000;
	//==========================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());	//���� ����. 2 <= V <= 400
		E = Integer.parseInt(st.nextToken());	//���� ����. 0 <= E <= V(V-1)
		dist = new int[V+1][V+1];
		for(int i=0; i<=V; i++) {
			Arrays.fill(dist[i], INF);
			//dist[i][i] = 0;
		}

		//���� �Է�
		for(int z=0; z<E; z++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		//�÷��̵�-�ͼ� �˰���
		for(int k=1; k<=V; k++)	//k�� ������ �����Ͽ�
			for(int i=1; i<=V; i++)	//�����ؼ�
				for(int j=1; j<=V; j++) {//�����Ҷ�
					//�ִܰŸ��� ������Ʈ ���ش�.
					dist[i][j] = Math.min(dist[i][j], dist[i][k]+dist[k][j]);
				}
		
		//�÷��̵��� �۵���Ĵ��п� [i][i]�� ���ؼ� �˾ƺ���. 
		//�ϳ��� ������ ���İ��� ������ ������� [i][i]�� ����Ŭ�� �Ǿ�����. [i][i]�� ���� �ּ��ΰ��� ���� ���� ����Ŭ�̴�.
		int minVal = INF;
		for(int i=1; i<=V; i++) 
			minVal = Math.min(minVal, dist[i][i]);
		if(minVal==INF) minVal = -1;
		System.out.println(minVal);
	}//==========================================



}
