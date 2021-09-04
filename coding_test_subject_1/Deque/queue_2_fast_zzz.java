package Deque;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
push X: ���� X�� ť�� �ִ� �����̴�.
pop: ť���� ���� �տ� �ִ� ������ ����, �� ���� ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
size: ť�� ����ִ� ������ ������ ����Ѵ�.
empty: ť�� ��������� 1, �ƴϸ� 0�� ����Ѵ�.
front: ť�� ���� �տ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
back: ť�� ���� �ڿ� �ִ� ������ ����Ѵ�. ���� ť�� ����ִ� ������ ���� ��쿡�� -1�� ����Ѵ�.
 **/
public class queue_2_fast_zzz {
	//�������
	private static StringBuilder sb;
	//=======================================================================
	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		String cmd ="";
		sb = new StringBuilder();
		My_Queue q = new My_Queue(N);
		while((--N)>=0) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			int val = -1;
			if(st.countTokens()==1) 	//���� ��ū�� �ִٸ� �װ� push�� ���� ��
				val = Integer.parseInt(st.nextToken());
			
			q.command(cmd, val);	//�̷��� �غ��⵵, �ƿ� �� �ڵ带 ���⿡ �ٿ��� �ôµ� ū���̰� ���� ������ �̰� ���ݴ� ������. 
			//ȣ���� �������ص� �ᱹ ������ ���ÿ� ������ �ʰ� ��ٷ� ������� �־ ũ�� ������ ���� ȣ���� �Ҷ� ��� ������ ���̰� ������ ũ�� �Ű� �Ƚᵵ �ȴ�. 
		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================================
	//���� Ŭ����
	static class My_Queue{
		private int[] arr; 
		private int first, last, size;	//last�� ������� ǥ�� last��ü ���� ��ȣ�� ������ �ȵǰ� �� �������� ����
		
		My_Queue(int N){
			arr = new int[N+1];
			first = 0;
			last = 0;
			size = 0;
		}
		public void command(String cmd, int val) {
		
			if(cmd.equals("push")) {
				arr[last++] = val;
				++size;
			}
			else if(cmd.equals("pop")) {
				if(size==0) 
					sb.append(-1+"\n");
				else {
					sb.append(arr[first++]+"\n");
					--size;
				}
			}
			else if(cmd.equals("size")) {
				sb.append(size+"\n");
			}
			else if(cmd.equals("empty")) {
				if(size==0) sb.append(1+"\n");
				else sb.append(0+"\n");
			}
			else if(cmd.equals("front")) {
				if(size==0) sb.append(-1+"\n");
				else sb.append(arr[first]+"\n");
			}
			else if(cmd.equals("back")) {
				if(size==0) sb.append(-1+"\n");
				else sb.append(arr[last-1]+"\n");
			}
		}
	}//=======================================================================

}
