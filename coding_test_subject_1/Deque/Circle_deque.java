package Deque;

import java.util.*;
import java.io.*;

/**----------------------------------------------------------------------
 * 지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.
 
  1. 첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 [a1, ..., ak]이었던 것이 [a2, ..., ak]와 같이 된다.
  2. 왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, [a1, ..., ak]가 [a2, ..., ak, a1]이 된다.
  3. 오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, [a1, ..., ak]가 [ak, a1, ..., ak-1]이 된다.
  
 * 큐에 처음에 포함되어 있던 수 N이 주어진다. 그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) 
 * 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.
 
 * 순서는 정해져있고 그에따라 정해지 위치도 있기에 매번 최소값만 찾아가면 된다.
  -----------------------------------------------------------------------**/
public class Circle_deque {

	//main함수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//큐의 크기		N<=50
		int M = Integer.parseInt(st.nextToken());	//뽑아내려고 하는 수의 개수	M<=N
		int now=1, end, count=0;
		int val, choice_idx, left=-1, right=-1;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<=N; i++)
			list.add(i);	//list는 뽑고자하는 val에 대한 인덱스를 값으로 가지고 있으며 1부터 시작한다.  
		
		/**------------------------------------------
		 * 인덱스가 움직이는 걸로 하겠다. 기준이 0일때다. 1일때는 코드를 보아라
		 * 현재 가야하는 위치가 now기준으로 오른쪽에 있을때... 가는 방향을
		 * left로 갈때 비용은 now만큼 움직여서 가장 왼쪽에 도달하고 +1 해서 가장 오른쪽 인덱스에 도달하고 거기서부터 쭉 이동시키는 것이다.
		 * left = now + 1 + (end-choice);
		 * 
		 * 가는 방향을 right로 갈때 비용은 choice - now 하면 곧바로 간다. 이값 중 작은값으로 그 위치로 도달 해가면된다.
		 * 도달 하면 해당 값은 list에서 제거한다. list로는 해당 값의 현재 인덱스 값을 알려주는 정보가 된다.
		 * 
		 * now기준으로 왼쪽에 있다면 
		 * left = now - choice
		 * right = (end-now) + 1 + choice
		 -------------------------------------------**/
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			//list.forEach(a->System.out.print(a+" ")); System.out.println();
			val = Integer.parseInt(st.nextToken());	// 나보고 뽑으라는 값
			choice_idx = list.indexOf(val)+1;				// 그 값의 현재 인덱스위치
			end = list.size();
			if( now < choice_idx ) { //자기보다 오른쪽에 있다.
				left = now + (end-choice_idx);
				right = choice_idx-now; 
				count += Math.min(left, right);
			}
			else if( now > choice_idx ){
				left = now - choice_idx;
				right = (end-now) + choice_idx;
				count += Math.min(left, right);
			}
			//만약 자기 자신이라면 count를 추가하지 않아도 된다.
			//System.out.println(choice_idx+" "+left+" "+right);
			
			list.remove(choice_idx-1);
			if(choice_idx==end)
				now = 1;
			else 
				now = choice_idx;
		}

		System.out.println(count);
	}//================================================================

}
