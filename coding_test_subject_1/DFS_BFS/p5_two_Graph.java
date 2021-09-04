package DFS_BFS;
import java.util.*;
import java.io.*;
// 1707번 - 이분 그래프
//문제상 양방향그래프이다.
//시간은 2140ms가 나온다.
/**==============================================================================
 * 내가 생각한 방법 
 *  1. 우선 간선이 존재 하지 않은 그외의 점들은 모두 생각에서 제외해도 된다. 아무것도 연결이 안된 정점이 1개든 100개든 모두 1개인것과 마찬가지고, 1개인것은 0개인것과 마찬가지이다. 
 *     없어도 된다. 1-2-3  4 5 이렇게 연결이 되어있다면 1-2-3인것과 마찬가지이며 [1과 3] [2] 이렇게 나눌수가 있다. 
 *  2. 간선이 있는 정점에서 BFS하게 시작해서 우선 들어온건 청기, 청기에게 붙어있는건 백기, 백기에게 붙어있는건 청기 이 구조를 계속 유지하는 방향으로 진행하면 된다. 도중에 백기인곳에 청기처럼 들어왔다면 아닌것이다.
 *  3. 1-2-3 와 4-5-6 이렇게 두 개의 연결체가 있고 그 연결체끼리 떨어 져있는 구조는 서로가 독립적으로 구해봐도 된다. 청[1,3]와 백[2] 그리고 (백/청)[4,6]와 (청/백)[5] 이렇게 나뉘어지면 떨어져있는건 따로 분리되어서 
 *     청백구조만 계속 지켜지기만 하면 되었다. 다시말해 떨어져있는건 독립적으로 청백기를 가지고 있게해서 그 구조만 각각 된다면 모든 구조가 가능한것과 동일하다.  
 *  4. 모든 간선에 대해서 진행을 하는 것을 마치면 종료하는 것으로 한다. 
 =================================================================================**/
public class p5_two_Graph {
	static ArrayList< ArrayList<Integer> > list;	//간선에 대해서 나옴
	static ArrayList<Integer> nextNode;	//다음에 해볼 정점 
	static Set<Integer> set;	//들어있는 정점은 아직 방문을 안한 정점.
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
			nextNode = new ArrayList<>();
			set = new HashSet<>();
			group = new int[V+1];	//0은 아직, 1은 청기, 2는 백기
			
			//간선정보와 시작정점을 입력.
			for(int z=0; z<E; z++) {
				st = new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				list.get(x1).add(x2);
				list.get(x2).add(x1);
				if(!set.contains(x1)) {
					nextNode.add(x1);
					set.add(x1);
				}
				if(!set.contains(x2)) {
					nextNode.add(x2);
					set.add(x2);
				}
			}
			
			int nodeCount = nextNode.size();
			boolean check = true;	//만들수있는지 없는지.
			
			//이분 그래프 탐색 
			for(int z=0; z<nodeCount; z++) {
				
				int start = nextNode.get(z);	//한 연결된 구조의 시작 (각 구조마다 하나의 start로 그 구조 정점을 모두 돌게된다.)
				if(group[start]!=0) continue;
				
				q = new LinkedList<>();
				q.add(new Node(start, 1));	// 구조의 처음 시작은 청기로 시작해보자.
			
				//하나의 독립된 구조에 대해서 알아보자. 
				while(!q.isEmpty()) {	
					Node now = q.poll(); //방문  
					int index=now.index, color=now.color, nextColor;
				
					//다음에 갈 색상을 지정
					if(color==1) nextColor=2;
					else nextColor=1;
					
					//지금 방문한 정점의 색상이 있다면
					if(group[index] == color) //이미 동일한 색상으로 방문을 했다면 다음으로.
						continue; 
					else if(group[index] == nextColor ) { //다른 색상으로 방문했다면 구조가 될수없으니 그만둔다.
						check = false;
						break;
					}
					
					//방문을 안 했다면 방문기록.
					group[index] = color;
					
					//다음에 갈 정점에 대하여 알아봄
					for(int next : list.get(index)) {
						if(group[next]==0) { //아직 방문을 하지 않았다면 방문이 가능하다 .
							q.add(new Node(next, nextColor));
						}
						else if(group[next]==color) {	//만약 현재 색상이랑 같은게 옆에 있다면 바로 탈퇴 
							check = false;
							break;
						}
					}
					
					if(check==false) break;	
				}
				
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
