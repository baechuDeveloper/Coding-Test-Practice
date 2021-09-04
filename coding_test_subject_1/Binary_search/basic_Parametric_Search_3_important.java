package Binary_search;

import java.io.*;
import java.util.*;
// 2110번-공유기 설치
// 한가지 느껴야 할것은 인접한 공유기의 거리가 최대가 되도록 하려면 정렬을 한 상태의 위치에서 맨 처음과 맨 마지막을 포함시키는게 가장 이상적이고 맞는 것이다.
// 왜냐하면 처음과 마지막이 가장 긴 거리이며 그 안에 여러 집들이 있는 것이고, 항상 양 옆은 아닐수 있어도 "모든 정답에는 양옆을 포함시켜도 가장 인접한 공유기의 최대거리에 영양을 주는곳은 맨처음 이나 맨마지막중 하나라서 
// 맨앞이 포함되었을때 맨마지막이 포함이 되도 안되도 결과는 맨앞에 대해서만 나온다.
// 쉽게 말해서 맨마지막을 포함하는게 아닐수 있어도. 맨앞부터 시작하는건 100% 맞는 말이다. 
public class basic_Parametric_Search_3_important {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 집 위치 
		int C = Integer.parseInt(st.nextToken());	// 설치할 공유기 개수
		int[] x = new int[N];
		for(int i=0; i<N; i++) 
			x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);

		System.out.println( install_machine(C, x) );
	}//-----------------------------------------------------------

	public static int install_machine(int C, int[] x) {

		// 간격으로 진행을 한다. left, right, mid 모두 간격에 대해 표현을 한다. 
		int len = x.length;
		int left = 1;					//최소
		int right = x[len-1] - x[0];	//최대	//left, right로 점점 그 간격의 범위에 대한 폭이 좁아져간다.
		int answer = 0;

		while(left<=right) {
			int mid = (left+right)/2; 	//mid보다 크거나 같아야 그 공유기는 살아있게 된다. 
			int router_count = 1; //우선 한개 등록이 된다.
			int cur_x = x[0];
			for(int i=1; i<len; i++) {
				if(mid <= x[i] - cur_x) {
					System.out.println(cur_x+" -> "+x[i]);
					router_count++;
					cur_x = x[i];
				}
			}
			/*System.out.println("mid : "+mid);
			System.out.println("count : "+router_count);
			System.out.println("------------------------");
			*/
			if(router_count < C) {
				right = mid-1;		//새로 만들어질 mid의 크기를 줄여서 더 많은 공우기 사이가 포함되도록 한다. 
			}
			else if(router_count >= C) {
				//if(router_count==C)	//왜 정답에서 말한 정확한 라우터 개수가 나올때'만' 정답인게 아닌가? 그 이유는 반드시 정확한 수가 아니라 그 이상을 가져도 그것을 정답으로 포함시켜주는 경우가 있기때문이다. 
				if(mid > answer)	
					answer = mid;
				left = mid+1;
			}
		}
/**
* 위에 준하는 아주 좋은 예시가 있다. 
 * 
5 4
1
7
8
9
10
-------------
1 -> 7
mid : 5
count : 2
------------------------
1 -> 7
7 -> 9
mid : 2
count : 3
------------------------
1 -> 7
7 -> 8
8 -> 9
9 -> 10
mid : 1
count : 5
------------------------
* 
* 만약 정확한 수 만을 정답을 가진다면 위에 결과는 0이 나온다. 라우터의 개수가 4개인 상황이 전혀 나오지 않았기 때문이다.
* 다만 그 이상의 개수인 5개의 라우터를 갖는 경우는 있다. 자세히 보면 같은 간격 1인 경우가 정말 많이 나오기에 5개이지만 4개라는 경우에 집어 넣어도 
* 중복되는 길이만 하나 없어도 최단길이 1은 항상 유효하다. 쉽게 말해 5개에는 4개인 상황이 이미 포함되는 상황이 된다. 
* 
* "이것을 최적해"라고 한다. 파라매트릭 서치에서는 이런 최적해를 기본적으로 두고 진행을 하게 된다.
* 그러니 파라매트릭을 이용할때 이하 이상이라는 말이 없어도 최적해로 문제 정답에 접근하는 것이 우선 기본이 된다고 보면 될것이다. 
* <= 혹은 >= 으로 최적해를 담는 방향을 생각해서 구현해주고 ==인 경우만 생각해도 되는문제에서도 
* 그 이상의 개수, 이하의 개수를 가지고 정확한 개수에 대입이 되는지를 여겨보며 잘 진행하면 될것이다. 그리고 대부분이 기본적으로 유효하고 유효하게 작동되니 기본 구조로 생각해두면 좋겠다.
* 
* **/
		return answer;
		//return right; //파라매트릭 서치의 특징이다. left와 right는 mid로 인해 값이 변경이되며 left혹은 right는 모든 서치에 맨마지막은 서로 같은 결과 값에 도달하게된다.
		// 거기서 마지막 C보다 크냐 작냐 같냐를 비교하고 만약 작다면 right가 낮아지고 while반복문을 벗어나며 right에는 정답이 담겨있다. 
		

	}//-----------------------------------------------------------
}
