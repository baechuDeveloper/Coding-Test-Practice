package Priority_Queue;

import java.util.*;
import java.io.*;

// 1655번 백준 - 가운데를 말해요 
// 더위 사냥 기법 - 왼쪽 우선수위 큐와 오른쪽 우선순위 큐를 둔다. (왼쪽은 오름차순, 오른쪽은 내림차순) 
public class p4__Dual_Queue_for_mid {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> max = new PriorityQueue<>();	//오름차순 , 오른쪽 더위사냥  작은 값부터 아이스크림이 나온다.
		Queue<Integer> min = new PriorityQueue<>( new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b.compareTo(a);	//내림차순, 왼쪽 더위사냥 큰 값부터 아이스크림이 나온다. 반대로 보면 min은 똥구멍으로 나오고 max는 입으로 나오네
			}	
			//b를 기준으로 값을 지정한다는 의미이며, 
			//중심이 되는 기준인 b에서 compareTo를 진행해서 매개변수로 a로 넣을때 나오는 값이 양수라면 '나 자신'한테 상대a보다 더 큰 가중치를 주고
			// 음수가 나오면 '나 자신'이 상대 a보다 더 작은 가중치, 다시말해 상대에게 더 가중치를 주겠다는 것이다.
			// 오른차순은 작은 가중치인 값부터 큰 가중치인 값으로 나열해주고, 반대로 내림차순은 더 큰 가중치부터 작은 가중치로 나열해준다.
			// 아무튼 기억할 것은 나자신.compareTo(상대) 로서 '나 자신'기준을 잡고 나온 값이 양수이면 나를 더 크게 음수이면 상대를 더 크게 본다는 의미이다.
		} );
		//--------------------------------------------------------
		
		while(N-->0) {	// 마치 수렴하는 것 같이 보이네; 
			int num = Integer.parseInt(br.readLine());
			
			max.add(num);	//항상 먼저 오름차순 큐에 넣어서 가장 작은 것을 표시해준다.. 
			
			int size = max.size() + min.size();	//현재 총 개수	... 맨처음은 당연히 한 개 들어올 때부터이니 홀수부터 시작이다. 
			
			if(size%2 != 0) {	// 전체 개수가 홀수(그렇다면 현재 min큐의 개수가 한개씩 부족하다.)
				min.add(max.poll());	// 그렇다면 max에게 있는 가장 작은 값을 min에게 넣어준다.  
			}
			else if(min.peek() > max.peek()) {	// 전체 짝수이면서 (짝수라면 현재 동등하게 개수를 가지고있다)
										// max에게 방금 넣은 값이 min이 제시해준 최대 값보다 작다면 min에게 그 값을 max에게 넘겨주고 max가 제시해줄 가장 작은 값을 min이 받는다. 
				max.add(min.poll());	
				min.add(max.poll());
			}
			sb.append(min.peek()+"\n");	//min이 제시해준 가장 큰 값이 항상 중간값을 뜻하게 된다. 
		}							//근데 만약에 문제에서 크기가 짝수일때는 가운데 2개의 값중 큰 값을 고른다고 하면 min이 먼저 받고 max에게 넘겨주는 역활을 해야한다.
									//그리고 마지막 sb에도 max.peek()를 주면 된다.
		
		/**================================================================================
		 * 이렇게 더위사냥 기법은 max와 min인 우선순위 큐를 활용하는데, 
		 *  총 크기 9로 9 4 6 2 1 3 7 5 8 순으로 값(사실 중복된 값도 가능)이 들어온다면 최종적으로 이런 모양이 된다.
		 * 	    min			       max
		 * |- 1 2 3 4 5 ->|   |<- 6 7 8 9 -|
		 * 			         서로  
		 * 			   	 나오는
		 * 			   	  구멍
		 * 
		 * 핵심은 항상 min의 크기는 max와 1개 차이로 크거나 서로 같다. 이 규칙은 항상 지켜줘야 한다. 이유는 아래에 서술. 
		 * 반복문을 통해 새로운 값은 항상 max부터 들어오는데, 현재 크기가 짝수면 서로 내용물을 바꿔야할지 안할지를 봐야한다. 
		 * 왜냐하면 새로운 것은 항상 max에게 들어오는데 이때 들어오는게 사실 min에 들어와야 하지 않을까 체크하는것이다.
		 * 동시에 서로 같은 크기를 유지해야 하므로 max가 가지고있는 가장 작은 값은 min이 가지고있는 가장 큰 값보다 항상 작아야한다.
		 * 반대로 현재 크기가 홀수라면 당연히 방금 max에 들어온 값으로 인해 max가 더 크게 존재 하고있는데 이를 min이 더 크기를 크게 해주어서
		 * 다음에 max에 값이 들어올때 서로의 크기 균형을 일정하게 잡을 수 있도록 규칙을 정해둔 것이다.  
		 * ==================================================================================**/
		
		bw.write(sb.toString());
		bw.flush();
	}

}
