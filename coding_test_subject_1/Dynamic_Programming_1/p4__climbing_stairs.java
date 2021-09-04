package Dynamic_Programming_1;

import java.io.*;

// 2579번 - 계단오르기 
// 기존 최대점수에서 어떻게 진행하는게 좋을지 보면된다. 최종점수에서 계단하나 얹고 가든가. 최종점수에서 바로 진행을하던가.
// 마지막을 생각해보면 점화식이 세워진다.
public class p4__climbing_stairs {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] stair = new int[N+1];
		int[] score = new int[N+1];	//얻을수있는 최대 점수 
		for(int i=1; i<=N; i++)
			stair[i] = Integer.parseInt(br.readLine());
		
		score[0] = 0;	//score[3]을 위해서라도 존재함
		score[1] = stair[1];
		if(N==1) {System.out.println(score[1]); return;}
		score[2] = stair[2] + stair[1];
		if(N==2) {System.out.println(score[2]); return;}
		
		for(int i=3; i<=N; i++) 
			score[i] = stair[i] + Math.max(score[i-3]+stair[i-1], score[i-2]);
		
		System.out.println(score[N]);
	}

}
