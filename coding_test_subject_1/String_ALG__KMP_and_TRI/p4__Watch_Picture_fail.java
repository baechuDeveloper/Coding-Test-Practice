package String_ALG__KMP_and_TRI;
import java.util.*;
import java.io.*;

/**
 * 환영문자열이라는 문제이다. 
 * 이 경우 ABCDEF와 CDEFAB 가 서로 원형으로 보면 같은 문자열로 생각하는 것이다. 
 * 
 * 내가 푼 이 문제는 환영문자열로 쓰기 좋도록 각도를 비슷하게 해주었지만, 한 가지 간과한것이
 * 아래에 적어주었다.
 * */
// 10266번 - 시계 사진
public class p4__Watch_Picture_fail {

	static int[] clock_A, clock_B;	//시계 바늘이 가리키는 곳 0<= <360_000 , 360도 원의 위치로 보면된다.
	static int[] among_A, among_B;	//낮은 거부터 바로 자기 위의 큰 시계바늘에서 그 사이사이 각도를 구해본 '사이각도'
	//========================================================
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer A = new StringTokenizer(br.readLine());
		StringTokenizer B = new StringTokenizer(br.readLine());
		clock_A = new int[N]; clock_B = new int[N];
		among_A = new int[N]; among_B = new int[N];
		
		for(int i=0; i<N; i++) {
			clock_A[i] = Integer.parseInt(A.nextToken());
			clock_B[i] = Integer.parseInt(B.nextToken());
		}
		//우선 정렬을 통해서 시계바늘 사이사이 각도를 순서가 정해진 차례차레의 각도로 구하는데 쓰인다.
		Arrays.sort(clock_A);
		Arrays.sort(clock_B);
		
		//사이각도를 모두 구해둔다. 단, 마지막은 첫번쨰 바늘에서 마지막바늘을 빼는 것이라 각도가 마이너스가 나온다. 빼는 순서가 동일해야 동일한 환경의 각도이므로 각도로서 360_000을 더해 몇도인지 완성해준다.	
		for(int i=0; i<N; i++) {
			if(i==N-1) {
				among_A[i] = (clock_A[0]-clock_A[i]) + 360_000;
				among_B[i] = (clock_B[0]-clock_B[i]) + 360_000;
			}
			else {
				among_A[i] = clock_A[i+1]-clock_A[i];
				among_B[i] = clock_B[i+1]-clock_B[i];
			}
		}
		
		for(int i:among_A) System.out.print(i+" "); System.out.println(); for(int i:among_B) System.out.print(i+" "); 
		
		// 이제 순서는 맞을테니 서로의 각도로 차례차레의 각도가 같은지 체크해주다. 
		// 이것은 하나의 문자열에서 어떤 문자부터 나와서 문자를 돌아오는지 하는 것이다.
		// ABCDEF 와 CDEFAB 인것 같이 각도의 차레는 맞지만 처음 나올 순서가 다를 뿐이다.
		// 이 시계문제도 이와 같다. 내가 사이사이 각도를 바늘의 순서대로 구해놓았고, 
		// 이를 서로 같은 시계일지를 ABCDEF와 CDEFAB로 처럼 확인하는 것이다.
		
		
		/*--------------------------------------------------------------------
		 * 아래 방법이 아쉬운 부분은 
		 * 4
		 * 1000 4000 2000 3000
		 * 358000 359000 0 1000
		 * 
		 * 이렇게 하면 일치해야하지만 일치하지 않다고 나온다. 틀린 점은 사이각도는 같은 값으로 존재할 수 있어서,
		 * 이렇게 같은 값인것만 찾으면 그게 이것을 말하는지 저것을 말하는지 정확히 둘수가 없어서 항상 맞는 답을 제시할 수 없다.
		 * 그렇기에 이 방법은 정말로 각 값이 고유한 값을 가질 때만 사용가능하지 이런 상황에서는 안된다.
		  --------------------------------------------------------------------
		int i = 0;	//A의 시작점 
		int j = 0;	//B의 시작점 
		boolean check = false;	//같은 문자의 시작이 없다며 아예 서로 안된다.
		
		// A와 B의 같은 시작점을 찾아본다. A의 첫번째 문자와 같은 B의 문자를 찾아본다.
		for(j=0; j<N; j++) {
			if(among_A[0] == among_B[j]) {
				check = true;
				break;
			}
		}
		if(check==false) {
			System.out.println("impossible");
			return;
		}
		
		// 이제 서로 같은 문자에서 일렬로 진행하며 서로 같은 문자열인지 파악한다. 
		for(i=0; i<N; i++,j++) {
			if(j==N) j=0;
			if(among_A[i] != among_B[j]) {
				check = false;
				break;
			}
		}

		if(check)
			System.out.println("possible");
		else
			System.out.println("impossible");
		
		*/

	}//========================================================

}
