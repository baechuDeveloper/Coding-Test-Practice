package Sort;

import java.util.*;
import java.io.*;

public class p3 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		List<Location> list = new ArrayList<>();
		int x,y;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			list.add(new Location(x, y));
		}
		
		list.sort(null);
		for(Location i:list)
			bw.write(i.x+" "+i.y+"\n");
		
		bw.flush();
		bw.close();
		br.close();
	}
}
class Location implements Comparable<Location>{
	int x;
	int y;
	Location(int xx, int yy){
		x = xx;
		y = yy;
	}
	public int compareTo(Location o) {
		if(y < o.y) 
			return -1;
		else if(y > o.y) 
			return 1;
		else 
			if(x > o.x) 
				return 1;
			else 
				return -1;
	}	
}