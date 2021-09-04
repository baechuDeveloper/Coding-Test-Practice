package Math;

import java.util.*;
import java.io.*;

// 1037번 약수
// 해당 N이라는 값을 이루는 모든 약수가 주어진다는 문제. (1과 자신N을 제외한)
public class p2 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		int len = arr.length;
		if(len==1) 
			bw.write(arr[0]*arr[0]+"\n");
		else 
			bw.write(arr[0]*arr[len-1]+"\n");
		
		bw.flush();
		
	}

}
