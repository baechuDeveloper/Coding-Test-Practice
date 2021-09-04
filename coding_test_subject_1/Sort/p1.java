package Sort;

import java.util.*;
import java.io.*;

// 나이순 정렬
public class p1 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int age = 0;
		String str ="";
		My_sort_class[] sorts = new My_sort_class[N];
	
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			age = Integer.parseInt(st.nextToken());
			str = st.nextToken();
			sorts[i] = new My_sort_class(i, age, str);
		}
		Arrays.sort(sorts);
		
		for(int i=0; i<N; i++) 
			bw.write(sorts[i].age+" "+sorts[i].name+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}

class My_sort_class implements Comparable<My_sort_class>{
	int seq;
	int age;
	String name;
	My_sort_class(int s, int a, String str){
		seq = s;
		age = a;
		name = str;
	}
	@Override
	public int compareTo(My_sort_class o) {
		if(age > o.age) 	 return 1;
		else if(age < o.age) return -1;
		else 	// 같을 때
			if(seq<o.seq) 	 return -1;
			else 			 return 1;
	}
}
