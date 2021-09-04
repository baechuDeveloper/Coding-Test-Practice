package Queue;

import java.util.*;
import java.io.*;

// 1966�� - ������ ť 
public class p1 {
	//�ڷᱸ��
	public static class node {
		int num;		// ������ ����
		int important;	// �߿䵵
		
		node(int a, int b){
			num = a;
			important = b;
		}
	}//-----------------------------------------------------------------
	//main �Լ�
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_N = Integer.parseInt(br.readLine());
		while(test_N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());		// �� ������ ���� 
			int want = Integer.parseInt(st.nextToken());	// ������� �μ� �Ǿ����� �ñ��� ������ȣ , 0������ ����
			st = new StringTokenizer(br.readLine());
			Queue<node> q = new LinkedList<>();
			int[] check = new int[10];	//�� �߿䵵�� ���� ���� ����
			int max_imp = 0;	// ���� ���� ū �߿䵵
			int count = 0; 		// �μⰡ ����Ǿ��°�. 
			
			for(int i=0; i<n; i++) {
				int imp_temp = Integer.parseInt(st.nextToken());
				q.add( new node(i, imp_temp) );
				check[imp_temp]++;
				if(max_imp<imp_temp) 
					max_imp = imp_temp;
			}
			
			//ť �۾� ����
			while(!q.isEmpty()) {
				node now = q.poll();
				
				if(now.important>=max_imp) {	//�߿䵵���� ���ų� ������ ť���� �������� �� �ִ�.
					count++;
					if(now.num == want) {
						bw.write(count+"\n");
						break;
					}
					check[now.important]--;
					if(check[max_imp]==0) {
						while(true) {
							max_imp--;
							if(max_imp<=0 || check[max_imp]!=0 )
								break;
						}
					}
				}
				else {	//�߿䵵���� ���ٸ� �ٽ� ť�� ����.
					q.add(now);
				}
			}//�ݺ��� ����
			
		}
		
		bw.flush();
	}//-----------------------------------------------------------------

}
