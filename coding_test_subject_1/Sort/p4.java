package Sort;
import java.util.*;
import java.io.*;

// 18870¹ø - ÁÂÇ¥¾ÐÃà 
public class p4 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<>();
		int[] arr = new int[N];
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		List<Integer> list = new ArrayList<>();
		for(int i:set) 
			list.add(i);
		list.sort(null);
		Map<Integer, Integer> map = new HashMap<>();
		
		int seq = 0;
		for(int i:list) {
			if(!map.containsKey(i)) {
				map.put(i, seq++);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) 
			sb.append(map.get(arr[i])+" ");
		
		bw.write(sb.toString());
		bw.flush();
	}

}
