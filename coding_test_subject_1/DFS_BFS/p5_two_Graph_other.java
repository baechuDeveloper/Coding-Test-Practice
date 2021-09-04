package DFS_BFS;
import java.util.*;
import java.io.*;
// 1707번 - 이분 그래프
//문제상 양방향그래프이다.
//시간은 1272ms가 나온다.

//간선이 없는 점 하나하나를 제외하지 말고 하나의 구조로 본다. 점 하나가 있는게 하나의 구조.
//다른 점은 모든 정점에 대한 마크를 진행하는 것으로 set과 nextNode라는 리스트를 없애고 진행한다. 
//이전과 다르게 모든 정점을 마크하여 0으로 남은 정점은 없으며, 간선이 없는 정점들은 사실 전부 청기(혹은 백기)라도 영향을 안주니 칠하고 바로 넘어간다.
//방문확인은 다음에 갈 곳을 정할 때 아직 방문을 안한 곳에 대해서 미리 정해두는것도 시간을 줄이는 방법이다. 정석은 아니지만 이 구조에서는 가능하다. 다만 큰 차이는 없다 50ms 차이정도 
/**==============================================================================
 * 내가 생각한 방법 
 *  1. 우선 간선이 존재 하지 않은 그외의 점들은 모두 생각에서 제외해도 된다. 아무것도 연결이 안된 정점이 1개든 100개든 모두 1개인것과 마찬가지고, 1개인것은 0개인것과 마찬가지이다. 
 *     없어도 된다. 1-2-3  4 5 이렇게 연결이 되어있다면 1-2-3인것과 마찬가지이며 [1과 3] [2] 이렇게 나눌수가 있다. 
 *  2. 간선이 있는 정점에서 BFS하게 시작해서 우선 들어온건 청기, 청기에게 붙어있는건 백기, 백기에게 붙어있는건 청기 이 구조를 계속 유지하는 방향으로 진행하면 된다. 도중에 백기인곳에 청기처럼 들어왔다면 아닌것이다.
 *  3. 1-2-3 와 4-5-6 이렇게 두 개의 연결체가 있고 그 연결체끼리 떨어 져있는 구조는 서로가 독립적으로 구해봐도 된다. 청[1,3]와 백[2] 그리고 (백/청)[4,6]와 (청/백)[5] 이렇게 나뉘어지면 떨어져있는건 따로 분리되어서 
 *     청백구조만 계속 지켜지기만 하면 되었다. 다시말해 떨어져있는건 독립적으로 청백기를 가지고 있게해서 그 구조만 각각 된다면 모든 구조가 가능한것과 동일하다.  
 *  4. 모든 간선에 대해서 진행을 하는 것을 마치면 종료하는 것으로 한다. 
 =================================================================================**/
public class p5_two_Graph_other {
	static ArrayList< ArrayList<Integer> > list;	//간선에 대해서 나옴
	static Queue<Node> q;
	static int[] group;
	//===================================================================
	static class Node {	//정점 
		int index, color;	//정점 번호, 정점 색깔
		Node(int i, int c){
			index=i; color=c;
		}
	}//===================================================================
	public static void main(String[] args) throws IOException, InterruptedException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			//정보 입력 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int z=0; z<=V; z++) 
				list.add(new ArrayList<Integer>());
			group = new int[V+1];	//0은 아직, 1은 청기, 2는 백기
			
			//간선정보와 시작정점을 입력.
			for(int z=0; z<E; z++) {
				st = new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				list.get(x1).add(x2);
				list.get(x2).add(x1);
			}
			
			boolean check = true;	//만들수있는지 없는지.
			q = new LinkedList<>();
			
			//이분 그래프 탐색 
			for(int start=0; start<V; start++) {
				if(group[start]!=0) continue;	//이미 색이 칠해졌다면 다음 정점을 확인
				
				//한 연결된 구조의 시작 (각 구조마다 하나의 start로 그 구조 정점을 모두 돌게된다.)
				q.add(new Node(start, 1));	// 구조의 처음 시작은 청기로 시작해보자.
				group[start] = 1;
				
				//하나의 독립된 구조에 대해서 알아보자. 
				while(!q.isEmpty()) {	
					Node now = q.poll(); //방문  
					int index=now.index, color=now.color, nextColor;
					
					//다음에 갈 색상을 지정
					if(color==1) nextColor=2;
					else nextColor=1;
					
					//다음에 갈 정점에 대하여 알아봄
					for(int next : list.get(index)) {
						if(group[next]==0) { //아직 방문을 하지 않았다면 방문이 가능하다 .
							group[next] = nextColor;	//좀더 일찍 방문 정보에 대해 알려준다. 
							q.add(new Node(next, nextColor));
						}
						else if(group[next]==color) {	//만약 현재 색상이랑 같은게 옆에 있다면 바로 탈퇴 
							check = false;
							break;
						}
					}
					
					if(check==false) break;	
				}//하나의 독립 구조 탐색 종료
				
				if(check==false) break;
			}//이분그래프 탐색 종료
			
			//출력
			if(check==false) 
				bw.write("NO\n");
			else bw.write("YES\n");
		}
		bw.flush();
	}//===================================================================

}
