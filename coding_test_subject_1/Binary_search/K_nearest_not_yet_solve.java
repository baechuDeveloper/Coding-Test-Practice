package Binary_search;

import java.io.*;

//그냥 정렬을 하면 메모리초과가 난다.
public class K_nearest_not_yet_solve {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		int[] a = new int[N*N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				a[N*i+j] = (i+1)*(j+1);
				System.out.print((i+1)*(j+1)+" ");
			}System.out.println();
		}
		System.out.println(a[k]);

	}

}
