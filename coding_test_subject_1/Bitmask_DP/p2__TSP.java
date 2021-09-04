package Bitmask_DP;

import java.util.*;
import java.io.*;

// 2098�� ���ǿ� ��ȸ - Traveling Salesman Problem(TSP)
// ��� ���ľ��ϴ� ��Ȳ
// https://dragon-h.tistory.com/29
public class p2__TSP {

	static int N; 		//���� ����
	static int visit=0; //�ִ� 16���� ���ð� ����. ��Ʈ����ũ�� �湮 ǥ��. �湮��
	static int[][] cost;//i���� j�� ���� ���. [i][j]
	static int[][] dp;	//dp[node][visit]�� ���� node��ȣ�� �ְ� visit�� �湮�ϰ� ���� ��, 0�� ���� ���� �ּ��� �Ÿ� 
	static int INF = 16 * 1_000_000 + 1;	//dp�� �������ִ� �ִ� ũ�� 
	static int start_city, start_visit;
	//-------------------------------------------------------------------
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][N];
		dp = new int[N][ (1<<N)-1 ];	//[���� ����][ visit�� ] ���⼭ [visit]�� ��ü �迭�� ���� ����ϴ� ���� �ƴϸ� ���� visit�湮���� ���ϰ� ������ �����ִ� �����̴�. �̸� ��Ʈ�� ǥ���ϹǷ� �������� ���� �ʿ��ϴ�.  
		for(int i=0; i<N; i++)
			Arrays.fill(dp[i], INF);
		
		for(int z=0; z<N; z++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int x=0; x<N; x++) 
				cost[z][x] = Integer.parseInt(st.nextToken());
		}
		start_city = 0;	//�̰��� N-1��ȣ���� � ���ڷ� �ٲٵ� ����� ���� ���̴� ����. N=4�̸� 0,1,2,3�� �԰����ϴٴ� ���̴�. ��� � N�� ���̵� 0�� �׻� �����ϹǷ� ������ �����ϱ� ���ؼ� 0���� �ٿ��� �����ϸ� �ȴ�. �ƴϸ� N-1�� �����ص� �ȴ�.
		start_visit = (1<<start_city);
		int result = tsp(start_city, start_visit);	//��� ���ľ� �Ѵ�. �ڱ� �ڽŸ� ���� �湮�ߴٴ� ǥ�÷� ���� ����ϴ� ���ÿ� �ش��ϴ� visit�� �����Ѵ�. 0�������� �����ߴٸ� 2^0�� 1�� visit�� �����ϸ� �ȴ�.
								//�� ���ư� ���࿡ 1�������� �����ߴٸ� 2^1�� �ش��ϴ� ��Ʈ �� 2�μ� (1, 2)�� �����ϸ� �ǰ�.
								//2�� ������ �����ߴٸ� 2^2�� �ش��ϴ� ��Ʈ �� 4�μ� (2, 4)�� �����ϸ� �ȴ�. 
								//�׷��� �ּҿ� �ش��ϴ� ��δ� ��� ���� �����ϴ� ���·� �ϳ��� ������ �����ϱ� ������. �ᱹ ��� ������ �ϵ� ���̴�. ���� ���̴�.
								//�� ��Ȳ�� ��� ���� �ѹ��� ������ �ϴ� ���ε�, �ᱹ ��� ������ �����ϵ� �� ���� �ݵ�� ���� ���̱⿡ ����� ���ƾ� �Ѵ�. 
								//���÷� �θ� ���� 1->2->3->4->1�� ���� ������� 3���� �����ص� 3->4->1->2->3���� �Ȱ��� ����� ���� �� �ִ�. 
								// 3->4 , 4->1 , 1->2 , 2->3 �̷��� 4���� ���·� �ɰ����� ������ ���ո� �޶��� �� �ɰ����°� ���⿡ ��� ���� ���ƾ��Ѵ�.
		//�� �Ѹ��� ���� �����Ѱ��� �� �ŵξ�� �ٽ� ȣ�� �Ѱ��� ���� ����� ������ �Ƕ�̵� ����
		bw.write(result+"");
		bw.flush();
	}//-------------------------------------------------------------------
	
	static int tsp(int node, int visit) {	//node�� ���� �湮�� ���� , visit�� ������ �湮�� tsp�� ��ͷ� ȣ�� �Ҽ��� �湮�� ���ð� ���������̴�. 
		//���� ��� ������ �湮 �� ���
		if(visit == (1<<N)-1) {	//�湮���� ��� ���ð� �������� 1111 1111 1111 1111 �̷��� 
			if(cost[node][start_city] == 0) return INF;	//�׷��� ��ó�� ���� 0������ ���� ���ΰ� ����.
			else return cost[node][start_city];			//������ ���� 0������ ���� ���ΰ� �ִ�.
		}
		
		//���� �湮�� ���� �̹� �湮�ؼ� ����� ���� ���
		if(dp[node][visit] != INF) 
			return dp[node][visit];	//�̹� �湮�ؼ� ����� ���� ����� �������ش�.
		
		//���������� �湮�� �Ǿ��ٸ� ���� �湮���� ��󺻴�. 
		for(int i=0; i<N; i++) {
			if(cost[node][i] == 0 || (visit & (1<<i)) != 0) // �湮�� ���ΰ� ���ų�, �̹� �湮�� �� �� 
				continue; //�Ѿ��.
			
			int next = visit | (1<<i);	//�ش� ��ȣ�� ���ø� �湮 �ߴٰ� ��Ʈ����ũ�� �߰����ش�. 
			
			dp[node][visit] = Math.min(dp[node][visit], cost[node][i] + tsp(i, next));	//�ش� node���� N���� ���÷� ���� ����� ���� ���� ���� ������ �� �����´�. 
		} //�� ó���� ȣ���� (0,1)�� �ᱹ ��� return�� ��ġ�� �� ��� ����� ���� �� ���� �ּҸ� �������� �� ���̴�.
		
		//�ּ��� ����� ���� �ڽ��� �湮 ����� ȣ�����ذ��� �����ش�.
		return dp[node][visit];
	}//-------------------------------------------------------------------

}