package Two_Pointer_2;
import java.io.*;
// 1644번 - 소수의 연속합
// 에라토스테네스의 체로 소수를 구하고, 투 포인터 알고리즘을 이용해서 연속합을 구한다.
public class p4__some_prime_sum {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1]; //소수가 들어간 배열
		int len = 0; //소수 개수
		/**에라토스테네스의 체**/
		boolean[] Pri = new boolean[N+1];    //false 인것은 모두 소수 
		Pri[0] = Pri[1] = true; //true가 된것은 prime소수가 '될 수 없는 자'들이다.
	
		//2 부터 숫자를 키워가며 배수들을 제외
		for(int i=2; i*i<=N; i++) {	//i는 1씩 증가하면서 새로 배치될 배수를 정해주는 역활
			for(int j=i*i; j<=N; j+=i) { //j는 해당 i의 제곱부터 배수만큼 올라간다. (제곱에 배수를 더한 형태는 수학적으로 소수가 아님을 보여주게 되었나라카드라)
				Pri[j] = true;        //2를 제외한 2의 배수 true
			}
		}
		
		for(int i=0; i<=N; i++) {
			if(false == Pri[i]) {
				arr[len++] = i; // 소수인 값
			}
		}

		//이제부터 arr의 최대 범위를 len-1까지로 작업한다. 길이를 len으로 활용하자.  
		/** 투 포인터 알고리즘 시작 **/
		int left=-1, right=-1, sum=0, pos_count=0;
		
		while(true) {	//p3클래스에서 말했듯 사실 left<=right가 없이도 이 연속합에서는 괜찮다.
			
			if(sum > N) {	//합이 더 크다면 left를 올려서 합을 낮춘다. 
				left++;
				sum -= arr[left];
			}
			else {	//합이 작거나 같으면 right를 올려서 합을 올린다. 
				if(sum==N) pos_count++;
				right++;
				if(right>=len) break;
				sum += arr[right];
			}
		}
		
		System.out.println(pos_count);
	}

}
