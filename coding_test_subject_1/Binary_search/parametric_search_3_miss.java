package Binary_search;

import java.io.*;
import java.util.*;

// 2110번-공유기 설치
// 집을 지우는 다는 방향으로 잡았지만 이러면 항상 양 옆에 반드시 공유기는 설치 되야한다는 가정이 필요하다. 
// 다시보니깐 맨앞을 정하는건 문제없지만, 이렇게 풀면 맨 마지막 집도 반드시 포함되어야 하기때문에 안된다. 
public class parametric_search_3_miss {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 집 위치 
		int C = Integer.parseInt(st.nextToken());	// 설치할 공유기 개수
		int[] x = new int[N];
		for(int i=0; i<N; i++) 
			x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);
		//for(int i:x) System.out.print(i+" "); System.out.println();
		System.out.println( install_machine(C, x) );
		System.out.println(1_000_000_000);
	}//-------------------------------------------------------------------
	
	public static int install_machine(int C, int[] x) {
		//설치할 공유기가 C개 이므로, 다르게 보면 제거해야할 집의 개수가 x.length - C 개이다. 
		int answer = 0;
		int len = x.length;
		int want = len - C; //지우고 싶은 집의 개수
		int left = x[0];
		int right = x[len-1];
		int prev = x[0];
		int min = 1_000_000_000; //가장 인접한 공유기 길이.
		int delete_house = 0; //현재 지운 집의 개수
		int mid = (left+right)/2;	//이 길이 맞게 (보다 크게) 서로간의 간격을 갖추어야 한다. 
		// 우리가 결국 구하고자 하는 것은 가장 인접한 것에 대해서니... 가장 인접한 거리를 mid라고 두어 구해본다. 그러니 간격들은 적어도 mid보다 크거나 같아야 하며
		// 그렇지 못한 경우는 집이 제거 되는것이다. (공유기가 그 곳에 설치 안되는것이다.)
		
		while(left<=right) {
			prev = x[0];
			min = 1_000_000_000;
			delete_house = 0;
			mid = (left+right)/2;
			System.out.println("mid : "+mid);
			for(int i=1; i<len; i++) {
				if(x[i]-prev < mid) { //해당 i번의 집을 제거하여 mid만큼의 길이이상을 갖도록 해본다. 
					delete_house++;
				}
				else { //우리가 정해본 간격보다 크거나 같으므로 통과가 된다. 
					min = Math.min(min, x[i]-prev);
					//System.out.print(min+" ");
					prev = x[i];
				}
			}//System.out.println();
			//System.out.println("~~~count : "+delete_house);
			
			if(delete_house < want) {	// mid의 길이 기준을 낮추어야 겠다.
				left = mid+1;
				if(answer < min)	//가장 인접한 두 공유기 길이(min)중 가장 큰 값을 취한다.
					answer = min;
			}
			else if(delete_house > want){
				right = mid-1;
			}
			else {
				left = mid+1;
				if(answer < min)	//가장 인접한 두 공유기 길이(min)중 가장 큰 값을 취한다.
					answer = min;
			}
			
		}
		
		return answer;
	}//-------------------------------------------------------------------

}
