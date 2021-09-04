package Union_Find;
import java.util.*;
import java.io.*;

// 4195번 - 친구 네트워크
public class p3__Friend_Network {

	static Map<String, Integer> map;
	static int[] group, groupcount;	//그룹번호와 해당 그룹이 가지고있는 사람의 수
	static int friendcount = 0;
	//======================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());	//테스트케이스	
		while(testcase-->0) {
			int F = Integer.parseInt(br.readLine());	//친구 관계의 수 100,000을 넘지 않는다.	
			map = new HashMap<>();
			group = new int[2*100_001];	//2배를 해야지 모든 친구를 최대로 수용가능 새로운 친구가 나올때마다 번호를 부여해질테니 이 그룹을 사용할 수 있다. 
			groupcount = new int[2*100_001]; //해당 번호가 속한 그룹이 가지는 인구수를 알려준다. 갱신되면 루트의 그룹의 인구수를 갱신하고 같아진다.
			Arrays.fill(group, -1);
			Arrays.fill(groupcount, 1);
			for(int i=0; i<F; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();
				int a = map.computeIfAbsent(A, c->friendcount++);	//새로운 이름마다 번호를 부여해준다. 그리고 그 번호를 리턴한다. 
				int b = map.computeIfAbsent(B, c->friendcount++);	//원래 있는 이름이라도 그 번호를 리턴해준다.
				//System.out.println("번호 "+a+" "+b);
				sb.append(merge(a,b)+"\n");
			}
			friendcount = 0;
		}
		bw.write(sb.toString());
		bw.flush();
	}//======================================================
	static int find(int a) {
		if(group[a]==-1) return a;
		return group[a] = find(group[a]);	
	}//======================================================
	static int merge(int a, int b) {
		a = find(a);
		b = find(b);
		//System.out.println("개수 "+groupcount[a]+" "+groupcount[b]);
		if(a==b) return groupcount[b];
		group[b] = a;
		groupcount[b] += groupcount[a];
		groupcount[a] = groupcount[b];
		//System.out.println("변경된 개수 "+groupcount[a]+" "+groupcount[b]);
		return groupcount[b];
	}//======================================================
}
