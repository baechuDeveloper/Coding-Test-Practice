package Union_Find;
import java.util.*;
import java.io.*;

/*====================================================
 * {0}, {1}, {2}, ... , {n}이 각각 n+1개의 집합으로 이루고 있다.
 * 0은 합친다
 * 1은 같은 집합에 포함되어 있는지 확인
 ====================================================*/

// 1717번 - 집합의 표현
public class p1__Set_Expression {
	static StringBuilder sb = new StringBuilder();
	static int[] group;
	//==========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());	//0부터 n까지의 번호
		int m = Integer.parseInt(st.nextToken());	//연산의 개수
		group = new int[n+1];						//각 번호가 속한 그룹(집합)
		Arrays.fill(group, -1);
		//연산적용
		for(int z=0; z<m; z++) {
			st = new StringTokenizer(br.readLine());
			int cal = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			switch(cal) {
				case 0: merge(a, b);
						break;
				case 1: isSameGroup(a, b);
						break;
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}//==========================================================
	static int find(int a) {
		if(group[a]==-1) return a;	//이 조건문은 자신이 Root라면 그룹값이 -1이기에 자기자신을 준다는 말이다. find로 계속 재귀를 하다가 Root에 도달하면 Root에서는 group의 값이 -1이기에 자기 자신을 주게된다.
		return group[a] = find(group[a]);
	}//==========================================================
	static boolean merge(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) return false;
		group[b] = a;
		return true;
	}//==========================================================
	static void isSameGroup(int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b) 
			sb.append("YES\n");
		else 
			sb.append("NO\n");
	}//==========================================================
}
