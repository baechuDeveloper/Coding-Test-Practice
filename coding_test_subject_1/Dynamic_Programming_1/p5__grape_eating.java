package Dynamic_Programming_1;

import java.io.*;

// 2156번 - 포도주 시식
// 이 떄 시점을 하나씩 포도주가 보이기 시작한다. 라고 하면 좋다. 왼쪽에서 시작하는데  i=1이면 지금은 하나의 포도주로만 보이넹 i=2이면 
// 이제 2개의 포도주가 보이네 그떄의 최대는... 이제 포도 n개가 보이네 이렇게 
public class p5__grape_eating {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] podo = new int[N+1];
		int[] score = new int[N+1];	//얻을수있는 최대 점수 
		for(int i=1; i<=N; i++)
			podo[i] = Integer.parseInt(br.readLine());
		
		score[0] = 0;	//score[3]을 위해서라도 존재함
		score[1] = podo[1]; //포도1개가 보임
		if(N==1) {System.out.println(score[1]); return;}
		score[2] = podo[2] + podo[1];	//포도 2개가 보임
		if(N==2) {System.out.println(score[2]); return;}
		
		for(int i=3; i<=N; i++) {
			score[i] = podo[i] + Math.max(score[i-3]+podo[i-1], score[i-2]);
			//여기서 굉장히 중요한 느낌이 있는데
			// 원래 마지막에 포도주까지 살펴보고 둘중에 좋은걸로 선택하는것인데.
			// 이를 사실 i=k까지 진행을 했을 때 k-1과 비교를 해서 이전의 것이 더좋았다면 k까지 바라본 포도주의 최대값은 k-1까지 본 최대값이 더 유리하기에 그 값을 이어가고 
			// k까지 본 포도주의 값이 그렇게 정해지는 형태가 가능하다.
			score[i] = Integer.max(score[i], score[i-1]);
		}
		//System.out.println(Integer.max(score[N], score[N-1]));
		System.out.println(score[N]);
	}

}
