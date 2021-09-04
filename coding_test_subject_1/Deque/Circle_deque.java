package Deque;

import java.util.*;
import java.io.*;

/**----------------------------------------------------------------------
 * �����̴� �� ť���� ������ ���� 3���� ������ ������ �� �ִ�.
 
  1. ù ��° ���Ҹ� �̾Ƴ���. �� ������ �����ϸ�, ���� ť�� ���Ұ� [a1, ..., ak]�̾��� ���� [a2, ..., ak]�� ���� �ȴ�.
  2. �������� �� ĭ �̵���Ų��. �� ������ �����ϸ�, [a1, ..., ak]�� [a2, ..., ak, a1]�� �ȴ�.
  3. ���������� �� ĭ �̵���Ų��. �� ������ �����ϸ�, [a1, ..., ak]�� [ak, a1, ..., ak-1]�� �ȴ�.
  
 * ť�� ó���� ���ԵǾ� �ִ� �� N�� �־�����. �׸��� �����̰� �̾Ƴ����� �ϴ� ������ ��ġ�� �־�����. (�� ��ġ�� ���� ó�� ť������ ��ġ�̴�.) 
 * �̶�, �� ���Ҹ� �־��� ������� �̾Ƴ��µ� ��� 2��, 3�� ������ �ּڰ��� ����ϴ� ���α׷��� �ۼ��Ͻÿ�.
 
 * ������ �������ְ� �׿����� ������ ��ġ�� �ֱ⿡ �Ź� �ּҰ��� ã�ư��� �ȴ�.
  -----------------------------------------------------------------------**/
public class Circle_deque {

	//main�Լ�
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	//ť�� ũ��		N<=50
		int M = Integer.parseInt(st.nextToken());	//�̾Ƴ����� �ϴ� ���� ����	M<=N
		int now=1, end, count=0;
		int val, choice_idx, left=-1, right=-1;
		LinkedList<Integer> list = new LinkedList<>();
		for(int i=1; i<=N; i++)
			list.add(i);	//list�� �̰����ϴ� val�� ���� �ε����� ������ ������ ������ 1���� �����Ѵ�.  
		
		/**------------------------------------------
		 * �ε����� �����̴� �ɷ� �ϰڴ�. ������ 0�϶���. 1�϶��� �ڵ带 ���ƶ�
		 * ���� �����ϴ� ��ġ�� now�������� �����ʿ� ������... ���� ������
		 * left�� ���� ����� now��ŭ �������� ���� ���ʿ� �����ϰ� +1 �ؼ� ���� ������ �ε����� �����ϰ� �ű⼭���� �� �̵���Ű�� ���̴�.
		 * left = now + 1 + (end-choice);
		 * 
		 * ���� ������ right�� ���� ����� choice - now �ϸ� ��ٷ� ����. �̰� �� ���������� �� ��ġ�� ���� �ذ���ȴ�.
		 * ���� �ϸ� �ش� ���� list���� �����Ѵ�. list�δ� �ش� ���� ���� �ε��� ���� �˷��ִ� ������ �ȴ�.
		 * 
		 * now�������� ���ʿ� �ִٸ� 
		 * left = now - choice
		 * right = (end-now) + 1 + choice
		 -------------------------------------------**/
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			//list.forEach(a->System.out.print(a+" ")); System.out.println();
			val = Integer.parseInt(st.nextToken());	// ������ ������� ��
			choice_idx = list.indexOf(val)+1;				// �� ���� ���� �ε�����ġ
			end = list.size();
			if( now < choice_idx ) { //�ڱ⺸�� �����ʿ� �ִ�.
				left = now + (end-choice_idx);
				right = choice_idx-now; 
				count += Math.min(left, right);
			}
			else if( now > choice_idx ){
				left = now - choice_idx;
				right = (end-now) + choice_idx;
				count += Math.min(left, right);
			}
			//���� �ڱ� �ڽ��̶�� count�� �߰����� �ʾƵ� �ȴ�.
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
