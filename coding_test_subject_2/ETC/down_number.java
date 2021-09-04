package ETC;
import java.util.*;
import java.io.*;
// 1038번 - 감소하는 수 
public class down_number {
	
	private static int N;
	private static int MAX = 1_000_000;
	private static Queue<Long> q;
	private static long[] decending;
	//======================================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		q = new LinkedList<>();
		decending = new long[MAX+1];
		
		for(int i=1; i<=9; i++) {
			q.add((long)i);
			decending[i] = i ;
		}
		preCalculate();
		
		if(decending[N]==0 && N!=0)
			System.out.println(-1);
		else
			System.out.println(decending[N]);
		
		
	}//======================================================
	
	private static void preCalculate() {
		int idx = 9;
		while(idx<=N) {
			if(q.isEmpty()) {
				return; 
			}
			long descendNum = q.poll();
			
			int lastNum = (int)descendNum % 10;
			
			for(int i=0; i<lastNum; i++) {
				q.add(descendNum*10 + i);
				decending[++idx] = descendNum*10 + i;
			}
		}
	}//======================================================
}
