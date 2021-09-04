package Two_Pointer_2;
import java.util.*;
import java.io.*;

// 2470번 - 두 용액
//문제 특성상 정렬을 하고 두 수의 합을 진행해보는 방향이 떠오른다.양끝단 사용

// 생각을 해보면 만약 left가 가리킨게 음수에서 도중에 양수의 값을 가리킨다면? 상관없다. -900 1 2 10 400 이렇게 있다면 답은 산성 1과 산성 2이다.  
// -18 -2 -1 1 5 10 20  
// 모든 용액에 대해 진행을 해보긴 해야한다.그걸 효율적으로 비교 할수있다. 
public class p2__two_liquid {
	
	//==========================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		//투 포인터 알고리즘 시작
		int left=0, right=N-1, sum=0, min=2_000_000_000;	//min은 현재까지 0에 가장 가까운 값, 절대값으로 두자.
		int[] index = new int[2]; index[0]=0; index[1]=N-1;
		
		while(left<right) {	//두 개의 합이니 같을 때는 반복이 끝난다.
			sum = arr[left]+arr[right];
			int abs_sum = Math.abs(sum);

			//더 0에 가까운 값이 있다면 갱신
			if(min > abs_sum) {	
				 min = abs_sum;
				 index[0] = left;
				 index[1] = right;
			}
			
			if(sum<0) {	//음의 값이 더 커보이니 left를 올려서 값을 키운다. 
				left++;
			}
			else {	//양의 값이 더 커보이니 right를 내려서 값을 내린다.
				if(sum==0) { //만약 0이 하나라도 나오면 그것만 보여줘도 된다.
					break;
				}
				right--;
			}
		
		}//투 포인터 알고리즘 종료
		
		System.out.println(arr[index[0]]+" "+arr[index[1]]);
		
	}//==========================================================

}
