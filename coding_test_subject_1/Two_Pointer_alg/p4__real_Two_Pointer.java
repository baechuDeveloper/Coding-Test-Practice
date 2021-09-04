package Two_Pointer_alg;

// 백준 - 다이어트 - 1484번 - 완벽한 투 포인터 알고리즘. 
// https://www.acmicpc.net/problem/1484
// 투 포인터 방식에 맞게 자신이 원하는 값보다 커지면 L을 움직이고, 작이지거나 같으면 R을 움직이고 하면서 찾아간다.

/**==============================================================
 *  G킬로그램은 성원이의 현재 몸무게의 제곱에서 성원이가 기억하고 있던 몸무게의 제곱을 뺀 것이다.
 *  성원이의 현재 몸무게로 가능한 것을 모두 출력하는 프로그램을 작성하시오.
  ------------------------------------------------------
   “안돼. G 킬로그램이나 더 쪘어ㅜㅠ”
   G = a^2 - b^2   |  a는 현재 몸무게, b는 예상했던 몸무게
 *  첫째 줄에 G가 주어진다. G는 100,000보다 작거나 같은 자연수이다.
 *  첫째 줄부터 한 줄에 하나씩 가능한 성원이의 현재 몸무게를 오름차순으로 출력한다. 
 *  가능한 몸무게가 없을 때는 -1을 출력한다. 
 *  현재 몸무게는 자연수로 떨어지지 않을 수도 있는데, 이런 경우는 제외해야 한다.
     즉 몸무게에 대해서는 자연수이다. 
   더 쪗기에 둘이 같은 몸무게 일수가 없다. 
 또한 몸무게는 0이 될수없다 ㅎ;;
 ===============================================================**/

import java.util.ArrayList;
import java.io.*;

public class p4__real_Two_Pointer {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		// 생각의 전환은 R이 커지면 그에 따른 제곱으로 커지기에 R과 L의 차이가 1이 난다고 해도 둘의 숫자가 커지면 커질수록 그 제곱끼리의 차이는 훨씬 커진다. 
		// 어느순간에는 원하는 범위를  벗어나기에 그때까지만 작업을 해가면 된다. 
		int R = 1;	//현재 몸무게 , a
		int L = 1;  //예상 몸무게 , b
		int diff = 0;
		ArrayList<Integer> list = new ArrayList<>();
		// 원하는 목표에 도달했을때, L을 이동시킬지 R을 이동시킬지는 더 넓은 범위를 커버하도록 가능한 경우를 커버하도록 해주어야한다. 
		// 올바른 방향으로 가도록 정해두어야한다.
		// 여기서는 도달하면 그다음 L을 더 증가시켜봤자 전부 오답인것도 있고, R을 곧바로 증가시키지 않고 L을 움직인다면 해당 R에서 필요한 L을 만나지 못할수있다.
		// 그래서 원하는 목표에 도달하면 R을 움직여한다. 
		
		// 종료조건도 조금 독특하게 R과 L의 제곱의 차이가 원하는 목표보다 커지면 그 이후는 항상 목표에 도달할수가 없다. 
		// 그래서 R과 L의 차이가 1일때의 제곱의 차이가 목표보다 커지면 멈추면된다.
 		while(true) {
			//System.out.println(L+" "+R);
			if(diff == N) {
				//System.out.println(R+" "+L);
				list.add(R);
				R++;
			}
			else if(diff > N) {	
				if(R == L+1) break; //더이상 L을 증가할수도 없고, 여기서 R을 늘려봤자 더이상 제곱의 차이에서 좁혀질수가 없게된다.
				L++;
			}
			else {
				R++;
			}
			diff = R*R - L*L;
		}
		
		list.sort(null);
		if(list.size() == 0)
			list.add(-1);
		for(Integer i:list)
			bw.write(i+"\n");
		bw.flush();
		bw.close();
		br.close();
	}

}
