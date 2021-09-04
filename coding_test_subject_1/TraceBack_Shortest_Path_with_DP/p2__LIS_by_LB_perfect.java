package TraceBack_Shortest_Path_with_DP;
import java.util.*;
import java.io.*;

// 14003번 - 가장 긴 증가하는 부분 수열 5
// LIS를 구하고 정확한 배열도 구해보자
public class p2__LIS_by_LB_perfect {
	//클래스변수
	static int N;
	static int[] arr;		// 주어진 수열 값
	static int[] seqindex;	// 해당 수열 번호에 해당하는 LIS의 부여된 순서		... 이렇게 하니 Node 자료구조로 이어가서 작업을 거치지 않고도 훌륭하게 같은 역활을 할 수 있었다. 순서에만 맞다면 이거 자체가 자료구조처럼 활용이 된다. 
	//=================================================================
	
	//main함수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		seqindex = new int[N];
		ArrayList<Integer> dp = new ArrayList<>();
		Deque<Integer> stack = new ArrayDeque<>();	//스택은 무조건 Deque로 

		// LowerBound를 이용해서 LIS를 구해보며 순서가 올바른 배열을 구해봄 (배열은 여러개가 정답일수있지만 정확한 순서로 이어지는 배열이어야 한다)
		for(int i=0; i<N; i++){
			int count = dp.size();
			int where = Lower_bound_for_LIS(dp, arr[i]);
			seqindex[i] = where;
			
			if(where == count)   //길이와 같은 크기는 리스트에서 벗어난 범위라 새로운 값이 들어가도 된다.
				dp.add(arr[i]);
			else 
				dp.set(where, arr[i]);
		}
		
		// 여태 LIS와 달리 정확한 순서를 가진 배열을 뽑아본다.
		StringBuilder sb = new StringBuilder();
		int lastIndex = dp.size()-1;
		
		for(int i=N-1; i>=0; i--) {
			if(lastIndex == seqindex[i]) {
				stack.push(arr[i]); 	//deque에서 addFirst()할시 push와 같은 효과가 나온다. 
				lastIndex--;
				if(lastIndex < 0) break;
			}
		}
		//stack을 활용해서 역순서로 값을 뽑아낸다.
		while(!stack.isEmpty())		
			sb.append(stack.pop()+" "); //deque에서 poll()과 같은 의미가 된다.
		
		bw.write(dp.size()+"\n");
		bw.write(sb.toString());
		bw.flush();
	}//=================================================================
	//Lower_Bound 메소드
	/** 바로 자기가 대체할 위치를 알려준다. 만약 '10'하나를 넣으면 리스트에 번호는 0만 있지만 여기서 '1'을 리턴해주면 list '1'에 넣어주면되고,
	 * '0'이면 list에 있던 0을 대체해주면 된다.
	 * LB가 들어간 값은 리스트에서 자신과 같거나 한 단계 큰 값이 어디인지 알려준다.
	 * 한 단계 큰값을 알려준다면 그 값보다 내가 더 작기때문에 교체해주어도 리스트상 LIS에는 문제가 없고 더 좋은 LIS로 나오는 경우도 만들 수 있다.**/
	static int Lower_bound_for_LIS(List<Integer> list, int target) {
		int start = 0;
		int end = list.size();  //end가 N이라서 배열의 범위를 넘어서도 mid에서 맨처음 반으로 두니 괜찮다.
		int mid;
		while(start < end) {
			mid = (start+end)/2;
			if(list.get(mid) < target)
				start = mid + 1;
			else
				end = mid;
		}
		return end;
	}//=================================================================

}
