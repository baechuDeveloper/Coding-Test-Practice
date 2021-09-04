package Queue;

import java.util.*;
import java.io.*;

// 1966번 - 프린터 큐 
public class p1 {
	//자료구조
	public static class node {
		int num;		// 문서의 번로
		int important;	// 중요도
		
		node(int a, int b){
			num = a;
			important = b;
		}
	}//-----------------------------------------------------------------
	//main 함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int test_N = Integer.parseInt(br.readLine());
		while(test_N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());		// 총 문서의 개수 
			int want = Integer.parseInt(st.nextToken());	// 몇번쨰에 인쇄 되었는지 궁금한 문서번호 , 0번부터 시작
			st = new StringTokenizer(br.readLine());
			Queue<node> q = new LinkedList<>();
			int[] check = new int[10];	//각 중요도에 따른 문서 개수
			int max_imp = 0;	// 현재 가장 큰 중요도
			int count = 0; 		// 인쇄가 몇번되었는가. 
			
			for(int i=0; i<n; i++) {
				int imp_temp = Integer.parseInt(st.nextToken());
				q.add( new node(i, imp_temp) );
				check[imp_temp]++;
				if(max_imp<imp_temp) 
					max_imp = imp_temp;
			}
			
			//큐 작업 실행
			while(!q.isEmpty()) {
				node now = q.poll();
				
				if(now.important>=max_imp) {	//중요도보다 높거나 같으면 큐에서 빠져나올 수 있다.
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
				else {	//중요도보다 낮다면 다시 큐에 들어간다.
					q.add(now);
				}
			}//반복문 종료
			
		}
		
		bw.flush();
	}//-----------------------------------------------------------------

}
