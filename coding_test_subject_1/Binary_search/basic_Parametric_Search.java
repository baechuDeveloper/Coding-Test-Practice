package Binary_search;

import java.io.*;
import java.util.*;

// 1654번-랜선자르기 
// 이분탐색을 응용한 문제.
// ㅆ:발 "N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다."
public class basic_Parametric_Search {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
	
		long[] line = new long[k];
		for(int i=0; i<k; i++) 
			line[i] = Long.parseLong(br.readLine());
		
		System.out.println( cut_line(n, line) );
	}
	
	public static long cut_line(int n, long[] line) {
		Arrays.sort(line);
		int k = line.length;
		// 맨 처음부터 볼때... 적어도 최대가 될 수 있는 선의 길이는 이 k개의 선들중 가장 작은것이 최대가 될수있는 자르는 선의 길이가 된다.
		long left = 1; //그래도 선의 길이는 0은 아닌것 같아서 1부터 시작. 사실 무관하지만 0은 최소가 될수없기에 제외해두도록한다. 
		long right = line[k-1]; //최소길이가 자르는 선에 대해서는 최대길이다.
		long mid = (left+right)/2;
		long line_count = 0;
		long answer = 0;
	
		while(left<=right) {
			
			mid = (left+right)/2;
			line_count = 0;
			for(int i=0; i<k; i++) 
				line_count += ( line[i]/mid ); //자른 선의 개수를 더해준다.

			if(line_count > n) {	// 예상보다 많은 선을 잘랐다 -> 자르는 선의 길이가 너무 짧아서 많은 선이 나왔다.
				left = mid+1;  //길이를 늘린다. 
				//ㅆ;발 아래 조건문은 만약 정확히 n만큼만 구하는거면 필요없지만 그 이상인 값도 되니 포함이 된다.
				if(mid > answer) { //이전에 구해 놓은 답보다 더 크다면 더 큰 값으로 고쳐준다.
					answer = mid;
				}
			}
			else if(line_count < n) { //적은 선이 나왔다 -> 자르는 선의 길이가 생각보다 길어서 적게 나왔다.
				right = mid-1; //길이를 줄인다. 
			}
			else if(line_count == n) {	//정확히 원하는 개수가 나왔으니 최대까지 노려본다.
				left = mid+1;
				if(mid > answer) { //이전에 구해 놓은 답보다 더 크다면 더 큰 값으로 고쳐준다.
					answer = mid;
				}
			}	
			
		}
		return answer;
	}

}
