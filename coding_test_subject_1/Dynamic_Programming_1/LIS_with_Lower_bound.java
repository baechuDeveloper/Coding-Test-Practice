package Dynamic_Programming_1;

import java.io.*;
import java.util.*;
// 11053번 - 가장 긴 증가하는 부분 수열 LIS
// 12015번 - 가장 긴 증가하는 부분 수열 2 LIS
/**-------------------------------------------
10
100 200 4 8 6 7 8 9 4 10 		답은 4 6 7 8 9 10
  
7
10 20 30 1 2 3 4

7
10 20 30 1 2 3 40

4
1 1 1 1
 ---------------------------------------------**/
public class LIS_with_Lower_bound{

	//main 함수
	public static void main(String[] args) throws IOException{
		//LIS_1();	// O(n^2)방식		// dp방식 
		LIS_2();	// O(n*log n)방식	// Lower_Bound방식 (binary search응용)
	}//=========================================================
	/**-------------------------------------------------------------------------
	 * O(N^2) 알고리즘은 사실 다이나믹 프로그래밍을 공부한 사람은 어렵지 않게 생각할 수 있을만큼 쉽습니다. 
	 * 다음과 같은 점화식으로 N^2시간에 구할 수 있습니다.
	 * dp[i] = i 번째 원소를 마지막으로 하는 LIS의 길이
	 * dp[i] = 1 ~ (i-1)까지의 원소들에서, i번째 원소보다 값이 작은것들 중, 가장 큰 dp값 + 1
	 * 출처: https://seungkwan.tistory.com/8 [Seungkwan's Lab.]
	---------------------------------------------------------------------------**/
	public static void LIS_1() throws IOException{	//O(N^2)방식
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] dp = new int[N+1];	//  지금까지 연속된 횟수
		int[] arr = new int[N+1];
		int ans = -1;
		for(int i=1; i<=N; ++i) {
			arr[i] = Integer.parseInt(st.nextToken());
			int here = 0; // 이전까지 자신보다 낮은값이지만 연속된 증가를 갖는 갯수 .. 전체를 살펴봐서 가장 큰 here인 dp값을 찾는다.
			for(int j=1; j<i; ++j) {	//딱 (i-1)번째 까지
				if(arr[i] > arr[j])
					here = Math.max(here, dp[j]);
			}
			dp[i] = here + 1;
			ans = Math.max(ans, dp[i]);
		}
	
		System.out.println(ans);
	}//=========================================================
	
	// Lower bound라는 것을 알아야한다. 
	// 생각이 대단하다. 가장 중요한 건 이곳에서 주장하는'흐름'의 원리이다. 왼쪽부터 오른쪽으로 비교를 하면서 증가하는 부분 수열을 만들었고 10 20 30
	//  그 다음이 1이 들어온다면 10인 있는 곳에 1을 넣어서 1 20 30 을 만든다. lowerbound를 뜻하게 되는 값을 바꾸어가는데...
	// 기존 10 20 30 까지가 3으로 최대 길이 였고 1이 나와도 기존의 원래 3이므로 그 다음 오른쪽을 대비하며 1로 변해가도 같은 의미를 줄수있다. 
	// 이미 이 수열의 안에 맨 오른족으로 들어오지 못한다면 최대길이에서 더이상 증가를 하지 않으며 결국 오른쪽을 대비하는 방향으로 진행이 된다. 
	// 만약 10 20 30 1 2 3 4 이면 --- 10 20 30 이었다가 1 2 3 으로 변해왔을것이고 마지막 4가 나오면서 1 2 3 4 로 길이가 늘어난다.
	// 가장 긴 길이를 이렇게 챙겨가기위해 내부 진행이 매끄러워 진다.
	// 만약 10 20 30 1 2 3 40 이면 --- 10 20 30 40 으로 끝내도 되지만 1 2 3 40으로 끝내도 동일한 의미를 전달해준다. 
	// 즉 변하기 전 10 20 30 에서 늘어날 수 있었다면 1 2 3 4에서도 늘어날수 있다. 반면에 1 2 3에서 늘어날수있어도 10 20 30에서는 늘어날수없다.
	// 굉장히 반영을 잘하는 알고리즘이라고 생각이 된다.
	// 그리고 이 문제상 같은 값의 원소는 없게 된다.
	/**-------------------------------------------------------------------------
	 * Binary Search: 원하는 값 K를 찾는 과정
	 * Lower Bound: 원하는 값 K 이상인 값 이 처음 나오는 위치를 찾는 과정
	 * Upper Bound: 원하는 값 K 초과인 값 이 처음 나오는 위치를 찾는 과정
	 
	 * O(N log  N) 알고리즘을 보기전에, lower bound라는 것을 알아야 합니다. 
	 * 어떠한 정렬된 배열 arr에서 어떠한 값 val의 lower bound란 arr을 정렬된 상태로 유지하면서 val이 삽입될 수 있는 위치들 중 가장 인덱스가 작은 것입니다.
	 * 가령 [1, 3, 3, 6, 7] 이라는 배열에서 1의 lower bound는 1이고, 3의 lower bound는 2 이며, 5의 lower bound는 4입니다. 
	 * (이 글에서 배열의 인덱스는 1부터 시작한다는 것에 유의해주세요). 
	 * 또한 upper bound라는 개념도 있는데, 이것은 반대로 삽입될 수 있는 위치들 중 가장 인덱스가 큰 것입니다.
	 * lower bound는 이진 탐색을 통해 log N 시간에 구할 수 있으며, 
	 * C++의 경우 STL에 이미 구현이 되어 있기 때문에 직접 구현할 필요도 없습니다. 
	 * C++의 lower_bound 함수에 대해서는 아래의 링크를 참고하시기 바랍니다. 
	 * 출처: https://seungkwan.tistory.com/8 [Seungkwan's Lab.]
	 * https://sudeky.tistory.com/131
	----------------------------------------------------------------------------**/
	public static void LIS_2() throws IOException{	//O(N*logN)방식
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		List<Integer> dp = new ArrayList<>();	
		int[] arr = new int[N+1];

		for(int i=1; i<=N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		dp.add(arr[1]); System.out.println("맨처음 "+dp.get(0));
	
		int index;
		for (int i=2; i<=N; i++ ) {
			dp.forEach( a-> System.out.print(a+" ")); 
			index = Lower_bound_for_LIS(dp, arr[i]);	//dp에서 arr[i] 값보다 같거나 '큰' 하나의 값이 어느 index에 있는지 확인을 한다. 혹은 그
			System.out.println("--- "+index);
			if(dp.size()-1 == index && dp.get(index) < arr[i]) {	//맨 끝자리 인덱스가 나왔다는 것은 결국 최소 같은 값이 있다는 것이고, 이는 명백히 원래 값보다 오른쪽에 있어도 문제가 없다. 또한 큰값이면 당연히 오른쪽에 두면 된다. 
				dp.add(arr[i]);	 System.out.println("뒤에 추가");		//찾았다! 틀렸던 부분! 4에 1 1 1 1 이렇게 하면 답은 1인데 2로 나왔다. 조건에서  dp.get(index) <= arr[i] 으로 해서이다. < 으로 고쳐 두었다. 
			}
			else if(dp.get(index) > arr[i]) {
				dp.set(index, arr[i]);  System.out.println("해당위치 값 변경 ");
			}
			// 그리고 만약에 만약에 같은 값이면 위에 2조건을 무시하게된다.
			dp.forEach( a-> System.out.print(a+" "));System.out.println("\n");
		}
		
		System.out.println(dp.size());	
	}//=========================================================
	// LIS를 위해 개량한 Lower_bound 
	// 여기서 -1을 남겨두는 것으로 아무것도 들어있지 않은 list에서는 사용이 안되고, -1로 인해 위에 if조건도 더 추가 되는것도 있고, 이 의미가 약간 퇴색된다. 
	public static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size()-1;	//이 부분에 대해서는 -1을 하지않고 사용해도 배열범위를 넘어서지 않는다. mid에 의해서 넘어가지 못한다. //다만 여기서는 -1을 하고 위에도 수정을 해주었다.
		int mid;
		while(start < end) {
			mid = (start+end)/2;
			if(list.get(mid) < target) 
				start = mid + 1;
			else 
				end = mid;
		}
		return end;
	}//=========================================================
	//디폴트한 Lower_bound
	/**-------------------------------------------------------------------------
	 * 정렬된 데이터에서..
	 * Binary Search: 원하는 값 K를 찾는 과정
	 * Lower Bound: 원하는 값 K 이상인 값 이 처음 나오는 위치를 찾는 과정. 맞다	k를 포함할수 있다.
	 * Upper Bound: 원하는 값 K 초과인 값 이 처음 나오는 위치를 찾는 과정. 맞다	k를 포함하지는 않는다.
	 * 출처 : https://codemcd.github.io/algorithm/DataStructure-%EC%9D%B4%EB%B6%84-%ED%83%90%EC%83%89/
	 * 1 3 6 8 10 에서 target이 8이면 lower는 4번째임을 알려주고, upper면 초과한 값인 10의 위치 5번째를 알려준다.
	 ----------------------------------------------------------------------------**/
	public static int Lower_bound(int[] arr, int target) {
		int start = 0;
		int end = arr.length - 1;
		int mid;
		while(start < end) {
			mid = (start + end)/2;
			if(arr[mid] < target)		// upper bound라면 arr[mid] <= target 이면 된다.
				start = mid + 1;
			else
				end = mid;
		}
		return end;	//결국 end가 우리가 원하는 형태를 내보낸다.
	}//=========================================================
}
