package Math;

import java.io.*;

// 에라토스테네스의 체 ( 소수 빠르게 구하는 알고리즘 )
public class Eratostenes {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		//--------------------------------------
		// 가장 효과 좋은 방법

		boolean[] arr = new boolean[num+1];    //false 인것은 모두 소수 
		arr[0] = arr[1] = true; //true가 된것은 prime소수가 '될 수 없는 자'들이다.
	
		//2 부터 숫자를 키워가며 배수들을 제외
		for(int i=2; i*i<=num; i++) {	//i는 1씩 증가하면서 새로 배치될 배수를 정해주는 역활
			for(int j=i*i; j<=num; j+=i) { //j는 해당 i의 제곱부터 배수만큼 올라간다. (제곱에 배수를 더한 형태는 수학적으로 소수가 아님을 보여주게 되었나라카드라)
				arr[j] = true;        //2를 제외한 2의 배수 true
			}
		}
		
		for(int i=0; i<=num; i++) {
			if(false == arr[i]) {
				System.out.print(i + " "); // 소수인 값
			}
		}

System.out.println();
		//-------------------------------------- 더 직관적인 방법 2 이지만 효율적으로 위가 2배 훨씬 좋다.
		boolean[] primeNum = new boolean[num+1];
		primeNum[1] = true;

		for(int i= 2; i <= num; i++) {
			for(int j = 2; i*j <= num; j++) {
				primeNum[i*j] = true;
			}
		}
		for(int i=1; i <= num; i++) {
			if(!primeNum[i]) System.out.print(i+" ");
		}
System.out.println();

		//-------------------------------------------

	}

}
