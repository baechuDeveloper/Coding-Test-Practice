package Sort;

import java.util.*;
import java.io.*;

// 수 정렬하기 2	
// Collections를 활용한 List 자료구조는 sort시 더 적합한 알고리즘 sorting을 구사한다. 시간충분!

public class p2_plus {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<N; i++) 
			list.add( Integer.parseInt(br.readLine()) );
		
		list.sort(null);
		
		for(int i:list)
			bw.write(i+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}

