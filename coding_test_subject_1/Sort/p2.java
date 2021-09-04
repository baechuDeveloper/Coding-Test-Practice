package Sort;

import java.util.*;
import java.io.*;

// 수 정렬하기 2	
// 기본 배열을 이용한 Arrays.sort를 해서 정렬을 했지만 시간 초과;;
public class p2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		for(int i:arr)
			bw.write(i+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}

}

