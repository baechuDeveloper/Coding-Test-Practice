package Two_Pointer_alg;

// 백준 - N번째 큰 수 _ 우선순위 큐 활용 
// https://www.acmicpc.net/problem/2075

/**============================================================
 * PriorityQueue<Integer> minHeap = new PriorityQueue<>();
   System.out.println("최소 힙");	// 오름차순 , 작은 값부터 큰 값으로

   PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
   System.out.println("최대 힙");	// 내림차순 , 큰 값부터 작은 값으로
 * =======================================================
  
 * 문제는 최소힙으로 처음 N개까지 우선순위큐에 집어두고 그 이상부터는 넣고 pop하고를 반복하며
 * N개를 유지하며 현재에서 가장 작은 값을 빼두는 것으로 리니어하게 구할수 있다. 
 ==============================================================**/
import java.util.*;
import java.io.*;

public class p2_with_PriorityQueue {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			if(i==0) {
				for(int j=0; j<N; j++) 
					pq.add(Integer.parseInt(st.nextToken()));
			}
			else {
				for(int j=0; j<N; j++) {
					pq.add(Integer.parseInt(st.nextToken()));
					pq.remove();
				}
			}
		}

		bw.write(pq.peek()+"");
		bw.flush();
		
		br.close();
		bw.close();

	}

}
