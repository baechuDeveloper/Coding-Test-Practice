package Strongly_Connected_Component;
import java.util.*;
import java.io.*;

// https://m.blog.naver.com/kks227/220802519976
/**
 * Ÿ�� �˰������� SCC�� ã�ƺ��Ҵ�. 
 * ���� �߿������� dfsn�� Ȱ���ؼ� ���±��� ���� stack���빰�� Ǯ� �ϳ��� SCC�� ������ִ� ��.
 * 
 * �������� ������ ������ ���ؿ� ����� ��, �� ������ �༮�� ���� �༮(�ڽ�����)�� ������ ������ �ö󰡼� �׵鳢�� �������� SCC�� �Ǵ� ���� 
 * */

// 2150�� - Strongly Connected Component ���� ���� ��� 
public class p1__SCC {
	
	//SN : SCC����, sn[i] : i�� ���� SCC�� ��ȣ
	static int V, E;
	static ArrayList<ArrayList<Integer>> list, SCC;	//list:���Ⱓ��, SCC : �� ������ SCC�� ��Ƶ� 
	static Deque<Integer> stack;
	static int cnt, SN; 
	static int[] dfsn, sn;	//dfsn�� �� ������ �湮 ������� ��ȣ�� �ű� ��. ���ʸ��� �����ϴ� DFS�� ���� ��ȣ�� �ο��Ѵ�. 
	static boolean[] finished;	//SCC �и��� ���� ������ true
	//===========================================
	
	//�ڽ��� ������� �����ϴ� DFS�Լ� 
	static int DFS(int cur) {
		dfsn[cur] = ++cnt;	//dfsn ����
		stack.push(cur);	//���ÿ� �ڽ��� push
		
		// �ڽ��� dfsn, �����ϸ� �ڽĵ��� ����� dfsn �� ���� ���� ��ȣ�� result�� ����. �ڽ� ���� ���� �������� ���� ���� ���� ����̸� �ڽ��� ���ư� ���� ���� ��.
		int result = dfsn[cur];
		for(int next : list.get(cur)) {
			//���� �湮���� ���� �̿�
			if(dfsn[next]==0) 
				result = Math.min(result, DFS(next));
			//�湮�� ������ ���� SCC�� ��������� ���� �̿�
			else if(!finished[next]) 
				result = Math.min(result, dfsn[next]);
		}
		
		//�ڽ�, �ڽ��� �ڼյ��� ���� ������ ���� ���� ������ �ڽ��� ��� SCC����
		if(result == dfsn[cur]) {
			ArrayList<Integer> curSCC = new ArrayList<>();	//�ϳ��� SCC
			//���ÿ��� �ڽ��� ���� ������ pop�� �Ͽ� �� ���빰������ ���� SCC�� �ϼ��ȴ�.
			while(true) {
				int t = stack.pop();
				curSCC.add(t);
				finished[t] = true;
				sn[t] = SN;	//���� �׷����� �˷���
				if(t == cur) break;		
			}
			//����� ���� ���� ����
			curSCC.sort(null);
			//SCC ����
			SCC.add(curSCC);
			SN++; //�ϳ��� SCC�� �ϼ� �Ǿ����� ���� ������ SCC�� ��ȣ�� �����Ѵ�. 
		}
		
		return result;
	}//===========================================
	
	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// V���� ����
		int E = Integer.parseInt(st.nextToken());	// E���� ���� 
		list = new ArrayList<>();
		for(int i=0; i<=V; i++)
			list.add(new ArrayList<>());

		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			list.get(A).add(B);
		}
		cnt = 0;
		SN = 0;
		stack = new ArrayDeque<>();
		dfsn = new int[V+1];
		sn = new int[V+1];
		finished = new boolean[V+1];
		SCC = new ArrayList<>();

		for(int i=1; i<=V; i++)
			if(dfsn[i] == 0) //�̹� SCC�� �ϼ��Ȱ� �������� �ʾƵ� �ȴ�.
				DFS(i);
			
		//���ڸ��� ������ SCC���� �������ش�. 
		Collections.sort(SCC, new Comparator< ArrayList<Integer> >() {
			public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
				return o1.get(0) - o2.get(0);
			}
		});	
		 
		bw.write(SN+"\n");	//SCC ����
		for(ArrayList<Integer> curSCC : SCC) {
			for(int cur : curSCC)
				bw.write(cur+" ");
			bw.write(-1+"\n");
		}
		bw.flush();
	}//===========================================

}
