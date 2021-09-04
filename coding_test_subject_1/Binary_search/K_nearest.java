package Binary_search;
import java.io.*;
//1300번 - K번째 수
//이차원 배열 A와 일차원 배열 B의 인덱스는 1부터 시작한다. 
//N은 10의 5제곱보다 작거나 같은 자연수, k는 min(10^9, N^2)보다 작거나 같은 자연수
//당연히 N^2이 10억을 넘기에 그냥 정렬을 하면 메모리초과가 난다.
/**===================================================================================
  	1  2  3  4  5  6  7  8
     ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
 1|	1  2  3  4  5  6  7  8
 2|	2  4  6  8  10 12 14 16
 3|	3  6  9  12 15 18 21 24
 4| 4  8  12 16 20 24 28 32
 5|	5  10 15 20 25 30 35 40 
 6|	6  12 18 24 30 36 42 48
 7|	7  14 21 28 35 42 49 56
 8|	8  16 24 32 40 48 56 64 
  
 * 근데 10의 9제곱이나 되는 양을 일일히 arr[][]로 집어 넣는거 자체도 굉장히 많은 시간이 들어간다. 그래서인지 배열도 구성하는걸 피해본다.
  
 * xxx 지극히 주관적인 생각 - 이건 안된다.
 * xxx 임의의 수 q에서 q의 제곱을 한 값에서 대각선으로 상단 왼쪽 삼각형으로는 항상 q^2의 값을 넘을수 없는 최고의 값이다.
 * xxx 반대로 하단 오른쪽 삼각형으로는 q^2의 값보다 같거나 큰 수로만 이어져 간다. 
 -------------------------------------------------------------------------------------
 *  정답은 A[i][j]에서 i행에 속한 숫자들은 i*j이므로 '모두 i의 배수'이다. 
 *  그러므로 min(mid/i, N)이 i번째 행에서 mid보다 작은(=임의의 m보다 작은)숫자들의 개수가 된다.
 * 
 *  만약 N=1000인 경우, 첫 mid가 1000*1000/2 = 50만이 되는데, 50만/i가 N을 넘어갈 수 있으므로 min(mid/i, N)을 해준다.
 *  
 *  다시 쉽게 쓰자면, i행의 숫자들은 i*1, i*2, i*3, ..., i*N으로 구성되어 있다. 
 *  i행의 숫자들 중 m보다 작거나 같은 숫자는 (i*j <= m)를 만족하는 j의 개수이고 이는 i*1, i*2, i*3, ..., i*j이므로 m/i와 같은 값이 된다.
 *  i*j는 해당 직사각형에서 최강자이다 가슴이 웅장해진다. 
 =====================================================================================**/
public class K_nearest {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //N은 이차원 배열의 가로 세로 크기
		int k = Integer.parseInt(br.readLine()); //오름차순 정렬된 일차원 배열에서 k번째 값
		
		//left와 right 사이에서 k번째 값을 찾는다. left와 right는 번호가 아닌 값을 뜻하게 된다.
		long left = 1;	// 값은 최소 1부터이다. 
		long right = k;	// 최대 k값. 번호의 의미가 아니다. k번째에 해당하는 수는 최대 k인 값(N이 1이라면)이라서 k보다 작을텐데 그런 절대적인 의미에서 k값을 부여해주면 좋다.
		long ans = 0;	// left와 right라는 값의 범위 안에서 원하는 k번째 값을 찾아본다.
		
		//이분탐색 수행
		while(left<=right) {
			long mid = (left+right)/2;	//임의의 수 mid를 지정해서 mid보다 작은 숫자의 개수를 파악해서 k번째 숫자를 구한다.
			long cnt = 0;
			
			//mid보다 작거나 같은 수는 몇 개인지 계산
			for(int i=1; i<=N; i++) {	//각 i행마다 계산
				cnt += Math.min(mid/i, N);	// mid보다 작거나 같은 j의 수 (i + j <= mid)
											// 그래도 N개가 최대이니 그 개수를 넘어갈 정도라도 N개만큼만 가져가게 된다. 
			}
			
			if(cnt < k) {	//아직 개수가 부족하다면 더 개수를 가질수있도록 left를 상승해서 기준점을 상승시켜 더 많은 개수를 찾는다.
				left = mid+1;
			}
			else {	//개수가 많거나 같다면  그 개수를 해당 수치에 맞추도록 범위 선정을 줄이도록 한다. 또한 이 형태에서 정답을 유의미하게 맞춰갈수있다. 
					//여러개의 같은 수가 있을 수도 있으며 당연히 맞는 표현이다. 
				ans = mid;
				right = mid-1;
			}
		}
		
		System.out.println(ans);	
	}

}
