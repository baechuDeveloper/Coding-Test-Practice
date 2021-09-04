package ETC;
import java.util.*;
import java.io.*;

public class p1_divided_cal {
	private static ArrayList<ArrayList<Integer>> list;
	//=========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		setNum();
		/*for(int i=1; i<=9; i++) {
			System.out.print(i+"--> ");
			for(int go : list.get(i)) {
				System.out.print(go+" ");
			}System.out.println();
		}*/
		
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());	// 1 <= a <= 100
			int b = Integer.parseInt(st.nextToken());	// 1 <= b <= 1,000,000
			a %= 10;
			int count = list.get(a).size();
			b %= count;
			if(b==0) b=count-1;
			else b--;
			bw.write( list.get(a).get(b)+"\n" );
		}
		bw.flush();
		
	}//=========================================================
	private static void setNum() {
		list = new ArrayList<>();
		for(int i=0; i<=9; i++) 
			list.add(new ArrayList<>());
		
		list.get(0).add(10);
		list.get(1).add(1);
		
		for(int i=2; i<=9; i++) {
			int temp = i;
			list.get(i).add(temp);
			temp *= i;
			temp %= 10;
			while(temp != i) {
				list.get(i).add(temp);
				temp *= i;
				temp %= 10;
			}
		}
	}//=========================================================
}
