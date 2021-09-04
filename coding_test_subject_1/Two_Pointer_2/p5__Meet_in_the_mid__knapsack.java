package Two_Pointer_2;
import java.util.*;
import java.io.*;

// 1450번 - 냅색문제 
/**=================================================================================
 * 숫자의 크기가 너무 커서 일반적인 냅색풀이로는 풀 수 없다. 메모이제이션도 공간이 너무 많이 필요해지고 경우의 수가 2^N으로 진행할 수 가 없기에 N을 반으로 쪼개서 2^(N/2)으로 두 번을 진행하도록 해보자. 
 * meet in the mid 반으로 쪼개서 보자. 2^30과 2^15를 2번하는건 약 10억과 64000정도의 차이이다. 
 * 이분탐색과 다르다. 반으로 쪼갠것에 대해 투 포인터 형식의 선형 탐색을 진행한다. 즉, 모든 경우의 수를 나누어서 구해본다.
 * 
 * 1) n개의 물건을 절반씩 두 집합으로 나누어 입력받고, 각 집합에서 가능한 모든 무게의 조합을 s1과 s2에 저장한다.
 *    이때, 가방의 무게보다 무거운 무게의 조합은 버린다.
 *    두 집합 내 모든 물건들의 무게의 조합이 s1과 s2에 담기면 각각을 오름차순으로 정렬한다.
 *    
 * 2) s1은 앞에서부터, s2는 뒤에서부터 두 개의 포인터를 운용하여 두 집합의 합을 가방의 무게와 비교한다.
 *    s1[i] + s2[j] 의 값이 처음으로 가방의 무게보다 작거나 같아지는 지점을 찾아낸다.
 *    s1과 s2 모두 0번째 인덱스부터 저장되어 있으니, 위와 같이 찾았다면 j+1개의 조합으로 가방에 넣을 수 있다.
 *    이렇게 i를 1씩 증가시키면서 그에 상응하는 j를 감소시키며 찾아준다.
 ==================================================================================**/
// https://groti.tistory.com/17
// https://gre-eny.tistory.com/141
// 이 문제에 상관없이 여담으로 어떻게 보면 이분탐색은 투 포인터 알고리즘에서 파생된 케이스로 볼 수 있다. 투 포인터가 더 넓은 범위로. 물론 진짜 파생된 것이 아니라 구조상 유사성이다. 
// 이분탐색은 선형으로 각각 찾지 않고 중간값으로 이동하며 빠르게 찾아가고. 다르지만 구조상 비슷하고 넓은 범위인것 같았다. 엄연히 둘은 쓰임새에 완전히 구분이 된다. 

public class p5__Meet_in_the_mid__knapsack {
	//클래스 변수
	static int N, C, index;
	static int[] W;
	static ArrayList<Integer> lgroup, rgroup;	//왼쪽, 오른쪽 주머니의 경우의 값
	//========================================================

	public static void main(String[] args) throws IOException{
		//입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	//N개의 물건
		C = Integer.parseInt(st.nextToken());	//최대 C무게 만큼 넣을수있는 가방
		W = new int[N+1];
		st = new StringTokenizer(br.readLine());	
		for(int i=0; i<N; i++) 
			W[i] = Integer.parseInt(st.nextToken());

		lgroup= new ArrayList<>();
		rgroup = new ArrayList<>();

		lBruteForce(0, 0);
		rBruteForce(N/2, 0);
		Collections.sort(rgroup);	//rgroup을 기준으로 lgroup과의 합을 비교해서 가장 알맞은 양을 찾아간다. 

		int ans = 0;
		//이제 L그룹에서 각각의 값들이 R그룹에서 몇가지 경우가 되는지 하나씩 확인을 한다. 
		for(int i=0; i<lgroup.size(); i++) {
			index = -1;
			binarySearch(0, rgroup.size()-1, lgroup.get(i));
			ans += index+1;//index는 0이 나온것부터가 한개의 방법이다. 
		}

		System.out.println(ans);		
	}//========================================================
	//왼쪽 주머니 작업
	static void lBruteForce(int i, int sum) {	//i는 현재 넣을지 말지 고민해보는 아이템 번호를 말하며, sum은 i를 넣기전 현재의 주머니 상황을 알려준다.
		if(sum > C)	//주머니용량을 넘어버리면 해당 상황에서의 그 값은 진행을 할 수 없다. 
			return;
		if(i == N/2) {	//왼쪽 주머니의 마지막 작업을 끝내서 도착했음을 알리는 인덱스 값. 도달 할때까지 쌓아둔 총량을 기억해둔다. 
			lgroup.add(sum);
			return;
		}
		lBruteForce(i+1, sum+W[i]);	//현재 값을 넣어둔 채 다음 작업을 진행한다.
		lBruteForce(i+1, sum);		//현재 값을 넣지않은 채 다음 작업을 진행한다.
	}//========================================================
	//오른쪽 주머니 작업
	static void rBruteForce(int i, int sum) {
		if(sum > C)
			return;
		if(i == N) {	//오른쪽 주머니의 마지막 작업을 끝내 도착을 알리는 인덱스 값은 N이다.
			rgroup.add(sum);
			return;
		}
		rBruteForce(i+1, sum+W[i]);
		rBruteForce(i+1, sum);
	}//========================================================

	static void binarySearch(int start, int end, int val) {
	
		int left = start, right = end;
		int mid;
		
		while(left <= right) {
			mid = (left + right)/2;
			
			if(rgroup.get(mid) + val <= C) {
				index = mid;	//방법 수가 index인데 이렇게 바이너리서치로 작업하면 가장 if조건에 제일 근접하는 결과로 마지막에 도달하고 (mid값이 늘어나면 늘어났지 더 줄어들지 않는다. 이곳에 들어왔다는 의미를 살펴보면 안다. 이곳에 들어오면 left의 값은 증가하고 더이상의 mid는 줄어들수 없기때문이다. 따라서 이곳에 들어오면 들어올수록 더욱더 최적의 형태로 근접하게 되며 늘어나도 그만큼 방법의 수가 늘어난다는 뜻이기에 좋다.)				
				left = mid+1;
			} 
			else {
				right = mid-1;
			}
		}
	}//========================================================

}
