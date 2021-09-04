package Binary_search;

import java.io.*;
import java.util.*;

// 2110��-������ ��ġ
// ���� ����� �ٴ� �������� ������� �̷��� �׻� �� ���� �ݵ�� ������� ��ġ �Ǿ��Ѵٴ� ������ �ʿ��ϴ�. 
// �ٽú��ϱ� �Ǿ��� ���ϴ°� ����������, �̷��� Ǯ�� �� ������ ���� �ݵ�� ���ԵǾ�� �ϱ⶧���� �ȵȴ�. 
public class parametric_search_3_miss {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// �� ��ġ 
		int C = Integer.parseInt(st.nextToken());	// ��ġ�� ������ ����
		int[] x = new int[N];
		for(int i=0; i<N; i++) 
			x[i] = Integer.parseInt(br.readLine());
		Arrays.sort(x);
		//for(int i:x) System.out.print(i+" "); System.out.println();
		System.out.println( install_machine(C, x) );
		System.out.println(1_000_000_000);
	}//-------------------------------------------------------------------
	
	public static int install_machine(int C, int[] x) {
		//��ġ�� �����Ⱑ C�� �̹Ƿ�, �ٸ��� ���� �����ؾ��� ���� ������ x.length - C ���̴�. 
		int answer = 0;
		int len = x.length;
		int want = len - C; //����� ���� ���� ����
		int left = x[0];
		int right = x[len-1];
		int prev = x[0];
		int min = 1_000_000_000; //���� ������ ������ ����.
		int delete_house = 0; //���� ���� ���� ����
		int mid = (left+right)/2;	//�� ���� �°� (���� ũ��) ���ΰ��� ������ ���߾�� �Ѵ�. 
		// �츮�� �ᱹ ���ϰ��� �ϴ� ���� ���� ������ �Ϳ� ���ؼ���... ���� ������ �Ÿ��� mid��� �ξ� ���غ���. �׷��� ���ݵ��� ��� mid���� ũ�ų� ���ƾ� �ϸ�
		// �׷��� ���� ���� ���� ���� �Ǵ°��̴�. (�����Ⱑ �� ���� ��ġ �ȵǴ°��̴�.)
		
		while(left<=right) {
			prev = x[0];
			min = 1_000_000_000;
			delete_house = 0;
			mid = (left+right)/2;
			System.out.println("mid : "+mid);
			for(int i=1; i<len; i++) {
				if(x[i]-prev < mid) { //�ش� i���� ���� �����Ͽ� mid��ŭ�� �����̻��� ������ �غ���. 
					delete_house++;
				}
				else { //�츮�� ���غ� ���ݺ��� ũ�ų� �����Ƿ� ����� �ȴ�. 
					min = Math.min(min, x[i]-prev);
					//System.out.print(min+" ");
					prev = x[i];
				}
			}//System.out.println();
			//System.out.println("~~~count : "+delete_house);
			
			if(delete_house < want) {	// mid�� ���� ������ ���߾�� �ڴ�.
				left = mid+1;
				if(answer < min)	//���� ������ �� ������ ����(min)�� ���� ū ���� ���Ѵ�.
					answer = min;
			}
			else if(delete_house > want){
				right = mid-1;
			}
			else {
				left = mid+1;
				if(answer < min)	//���� ������ �� ������ ����(min)�� ���� ū ���� ���Ѵ�.
					answer = min;
			}
			
		}
		
		return answer;
	}//-------------------------------------------------------------------

}
