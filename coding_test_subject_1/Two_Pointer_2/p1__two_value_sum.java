package Two_Pointer_2;
import java.util.*;
import java.io.*;
// 3273번 - 두 수의 합
// 투포인터를 이용하여 작성해보자. 
// 투포인터에서 left와 right의 기준은 문제마다 다르다. 그러니 같은 위치에서 시작해도되고, 양 끝에서 시작해도 되고 문제에 따라 내가 알아서 하자. (이분탐색은 양끝이긴 하지)
// 이 2개의 포인터가 어떤 움직임으로 진행될지 생각하는게 중요한거 같다. 보통은 같이 시작해서 범위의 값을 구하거나 했는데(정렬 없이도),
// 여기서는 정렬을 통해 각 끝단에서 시작을 해보도록 한다.(이분탐색 비슷하지만 mid값을 기준이 아니라는 점, 투포인터와 이분탐색은 투포인터가 좀 더 넓은 범위처럼 느껴진다. 물론 포함되는 관계자체는 아니긴하지만.)

// 다른 문제에서는 양끝단을 이용할때 정렬이 필요 없는것도 있을거다. 그러니 양끝단을 위해서는 반드시 정렬해야하는게 아닌, 문제를 위해서 정렬을 해야한다가 맞는 말이다.
public class p1__two_value_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int X = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for(int i=0; i<N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		if(N==1) System.out.println(0);
		
		Arrays.sort(arr);//정렬을 해준다.
		
		//투 포인터 시작
		int left=0, right=N-1, sum=0, count=0;
		
		while(left<right) {	//같은 때는 인정해주지 않는다고 문제에 나옴
			sum = arr[left]+arr[right];
			
			if(sum<X) { //값이 작다면 값을 상승. 
				left++;
			}
			else {	//값이 크거나 같으면 값을 줄인다. 
				if(sum==X) {	//정확한 값만 포함.
					count++;
				}
				right--;
			}
		}
		System.out.println(count);
	}

}
