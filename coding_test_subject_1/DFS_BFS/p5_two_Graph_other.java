package DFS_BFS;
import java.util.*;
import java.io.*;
// 1707�� - �̺� �׷���
//������ �����׷����̴�.
//�ð��� 1272ms�� ���´�.

//������ ���� �� �ϳ��ϳ��� �������� ���� �ϳ��� ������ ����. �� �ϳ��� �ִ°� �ϳ��� ����.
//�ٸ� ���� ��� ������ ���� ��ũ�� �����ϴ� ������ set�� nextNode��� ����Ʈ�� ���ְ� �����Ѵ�. 
//������ �ٸ��� ��� ������ ��ũ�Ͽ� 0���� ���� ������ ������, ������ ���� �������� ��� ���� û��(Ȥ�� ���)�� ������ ���ִ� ĥ�ϰ� �ٷ� �Ѿ��.
//�湮Ȯ���� ������ �� ���� ���� �� ���� �湮�� ���� ���� ���ؼ� �̸� ���صδ°͵� �ð��� ���̴� ����̴�. ������ �ƴ����� �� ���������� �����ϴ�. �ٸ� ū ���̴� ���� 50ms �������� 
/**==============================================================================
 * ���� ������ ��� 
 *  1. �켱 ������ ���� ���� ���� �׿��� ������ ��� �������� �����ص� �ȴ�. �ƹ��͵� ������ �ȵ� ������ 1���� 100���� ��� 1���ΰͰ� ����������, 1���ΰ��� 0���ΰͰ� ���������̴�. 
 *     ��� �ȴ�. 1-2-3  4 5 �̷��� ������ �Ǿ��ִٸ� 1-2-3�ΰͰ� ���������̸� [1�� 3] [2] �̷��� �������� �ִ�. 
 *  2. ������ �ִ� �������� BFS�ϰ� �����ؼ� �켱 ���°� û��, û�⿡�� �پ��ִ°� ���, ��⿡�� �پ��ִ°� û�� �� ������ ��� �����ϴ� �������� �����ϸ� �ȴ�. ���߿� ����ΰ��� û��ó�� ���Դٸ� �ƴѰ��̴�.
 *  3. 1-2-3 �� 4-5-6 �̷��� �� ���� ����ü�� �ְ� �� ����ü���� ���� ���ִ� ������ ���ΰ� ���������� ���غ��� �ȴ�. û[1,3]�� ��[2] �׸��� (��/û)[4,6]�� (û/��)[5] �̷��� ���������� �������ִ°� ���� �и��Ǿ 
 *     û�鱸���� ��� �������⸸ �ϸ� �Ǿ���. �ٽø��� �������ִ°� ���������� û��⸦ ������ �ְ��ؼ� �� ������ ���� �ȴٸ� ��� ������ �����ѰͰ� �����ϴ�.  
 *  4. ��� ������ ���ؼ� ������ �ϴ� ���� ��ġ�� �����ϴ� ������ �Ѵ�. 
 =================================================================================**/
public class p5_two_Graph_other {
	static ArrayList< ArrayList<Integer> > list;	//������ ���ؼ� ����
	static Queue<Node> q;
	static int[] group;
	//===================================================================
	static class Node {	//���� 
		int index, color;	//���� ��ȣ, ���� ����
		Node(int i, int c){
			index=i; color=c;
		}
	}//===================================================================
	public static void main(String[] args) throws IOException, InterruptedException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCase = Integer.parseInt(br.readLine());
		while(testCase-->0) {
			//���� �Է� 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for(int z=0; z<=V; z++) 
				list.add(new ArrayList<Integer>());
			group = new int[V+1];	//0�� ����, 1�� û��, 2�� ���
			
			//���������� ���������� �Է�.
			for(int z=0; z<E; z++) {
				st = new StringTokenizer(br.readLine());
				int x1=Integer.parseInt(st.nextToken());
				int x2=Integer.parseInt(st.nextToken());
				list.get(x1).add(x2);
				list.get(x2).add(x1);
			}
			
			boolean check = true;	//������ִ��� ������.
			q = new LinkedList<>();
			
			//�̺� �׷��� Ž�� 
			for(int start=0; start<V; start++) {
				if(group[start]!=0) continue;	//�̹� ���� ĥ�����ٸ� ���� ������ Ȯ��
				
				//�� ����� ������ ���� (�� �������� �ϳ��� start�� �� ���� ������ ��� ���Եȴ�.)
				q.add(new Node(start, 1));	// ������ ó�� ������ û��� �����غ���.
				group[start] = 1;
				
				//�ϳ��� ������ ������ ���ؼ� �˾ƺ���. 
				while(!q.isEmpty()) {	
					Node now = q.poll(); //�湮  
					int index=now.index, color=now.color, nextColor;
					
					//������ �� ������ ����
					if(color==1) nextColor=2;
					else nextColor=1;
					
					//������ �� ������ ���Ͽ� �˾ƺ�
					for(int next : list.get(index)) {
						if(group[next]==0) { //���� �湮�� ���� �ʾҴٸ� �湮�� �����ϴ� .
							group[next] = nextColor;	//���� ���� �湮 ������ ���� �˷��ش�. 
							q.add(new Node(next, nextColor));
						}
						else if(group[next]==color) {	//���� ���� �����̶� ������ ���� �ִٸ� �ٷ� Ż�� 
							check = false;
							break;
						}
					}
					
					if(check==false) break;	
				}//�ϳ��� ���� ���� Ž�� ����
				
				if(check==false) break;
			}//�̺б׷��� Ž�� ����
			
			//���
			if(check==false) 
				bw.write("NO\n");
			else bw.write("YES\n");
		}
		bw.flush();
	}//===================================================================

}
