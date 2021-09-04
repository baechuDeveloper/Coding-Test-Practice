package Sort;

import java.io.*;
import java.util.Arrays;

public class compareABLE_and_Static {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		Most_view[] qq = new Most_view[N];

		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			qq[i] = new Most_view(arr[i]);
		}	

		Arrays.sort(arr);
		Arrays.sort(qq);

		for(int i=0; i<arr.length; i++) {
			System.out.print(qq[i].val);
		}System.out.println();

		for(int i:arr) 
			bw.write(i+" ");
		bw.flush();
	}//=================================================

	static class Most_view implements Comparable<Most_view>{
		int val=0;
		int count=0;
		Most_view(int a){
			val = a;
			count = 1;
		}
		@Override
		public int compareTo(Most_view o) {	// sort시 이것을 기준으로 하며 기본sort함수는 상대우세값이 작은 것부터 상대우세값이 큰 것으로 나열해준다. 
			if(this.val < o.val) 
				return -1;	// 상대에게 더 큰값을 부여
			else if(this.val > o.val) 
				return 1;	// 내게 더 상대우세값을 부여 
			return 0;	//아무런 변화가 없다. 
		}
	}//=================================================
}
