package Graph_short_path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 
5
3
1 3 1
3 4 2
4 5 3 

 */
// �ִ� ��θ� �������� ���� ��θ� ����غ��� �;���. �˰����� �÷��̵�� �غ��ڴ�.
// path��� �迭�� ���ؼ� path[i]�� i��° �������� ���� �� �� �ܰ� ������ �˷��ش�.
// 11780�� - �÷��̵� 2
public class Show_Path_floyd {

	//Ŭ��������
	static int N, M;		//���ð���, ��������
	static int[][] dist;	//����(����) ����. ��������Ʈ
	static int INF = 100_000_000;	//1�� 
	static int[][] path;	//��θ� ���� �߰�
	//==========================================
	//���� �ڷᱸ��
	static class Node {
		int index, dist;
		Node(int i, int d){
			index = i; dist = d;
		}
	}//==========================================
	//main�Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		dist = new int[N+1][N+1];
		path = new int[N+1][N+1];
		for(int i=0; i<=N; i++) {
			Arrays.fill(dist[i], INF);
			dist[i][i] = 0;
		}
		//���� �Է� 
		for(int z=0; z<M; z++) {	//������ M���� ���� ������ �������ش�. 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	//���� ����
			int b = Integer.parseInt(st.nextToken());	//���� ����
			int c = Integer.parseInt(st.nextToken());	//�ѹ� Ÿ�µ� �ʿ��� ���
			dist[a][b] = Math.min(dist[a][b], c);
		}

		//�÷��̵�-�ͼ� �˰���
		for(int k=1; k<=N; k++)	//k�� ����
			for(int i=1; i<=N; i++)	//����
				for(int j=1; j<=N; j++) { //����
					if(dist[i][j] > dist[i][k]+dist[k][j]) {
						dist[i][j] = dist[i][k]+dist[k][j];
						path[i][j] = k; //���Ŀ� ����, i���� J�� ���� �� ���̿� '�� �ϳ�'�� �ִ°� �ƴϴ�. 
						//�˰���� i�� j���� �ִܰ�ο��� �������� ������ ���� �Ҽ��ְ�, 
						//������ ������ ������ ��� �����Ǿ� ���� ������ ���� �ֽſ� �ش��ϴ� ������ �׻��̿� k�� �ִٴ� ����'�� ��'�̴�. 
						//�̸� �̿��ؼ� �ٽ� i�� k���� k�� j������ �߰� ���� ������ ������ �˾ƺ���. 
						//�ִܰ�η� �ϼ��Ǿ��⿡ �� �߰� ������ �ִܰ�ο� �´� �����θ� ����ִ°� ���������� �´�.
					}
				}
		//���
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(dist[i][j]==INF) sb.append(0+" ");
				else sb.append(dist[i][j]+" ");
			}sb.append("\n");
		}System.out.println();

		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				System.out.print(path[i][j]+" ");

			}System.out.println();
		}
		bw.write(sb.toString());
		bw.flush();
		br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(start==-1) break;
			if(dist[start][end]==INF) { 
				System.out.println("��ΰ� ���������ʳ׿�"); 
				continue;
			}
			
			System.out.print("v"+start+"->");
			View(start, end);
			System.out.println("v"+end);
		}
	}//==========================================

	static void View(int start, int end) {	//�߰��� ������ ��� ����� �Ѵ�. 
		if(path[start][end] != 0){
			View(start, path[start][end] );
			System.out.print("v"+ path[start][end] +"->");
			View(path[start][end] , end);
		}
	}
}
