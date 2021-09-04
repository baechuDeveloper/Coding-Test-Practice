package Sparse_Table;
import java.util.*;
import java.io.*;

/**=============================================================
 
 * '�켱 ���� ����Ǿ��ϴ� ����'��  f : {1, 2, ..., m}��{1, 2, ..., m}�� �ִ�. �̶� fn : {1, 2, ..., m}��{1, 2, ..., m}�� �̷��� ������ ��.
 * �� ��Ģ�� �־�� �ռ��Լ����� �۾��� �����ϴ�. �׷��� �ʴٸ� ������̺��� �������� ���� ���̴�.
 
 * f1(x) = f(x)
 * f2(x) = f(f(x))
 * f4(x) = f(f3(x)) = f(f(f(f(x))))
 * fn+1(x) = f(fn(x))
 * 
 * n�� x�� �־��� �� fn(x)�� ����ϴ� ������ �����ϴ� ���α׷��� �ۼ��Ͻÿ�
 * -----------------------------------------------------

     ��� ���̺� 
 * �ռ��Լ��� �ذ��ϱ� ���� n�� �ݺ��ϴ� ���� �ƴ� �̸� ���̺��� ����� Ư�����ҿ��� 1�� ���� ��, 2�� ���� ��, 4��, 8��, 16���� ���� 
 * �������� �ڸ��� ��ŭ �̵����� ���� ���������� 2���� �迭�� ��� ����� ���� ���Դϴ�.
 * 
 * ���� ��� f(1,2,3,4) -> (2,1,4,3) ��� �Լ��� ���� ��
 * f(f(f(f(f(f(1))))))�� ���� '6'�� �ռ��� �ռ��Լ��� ���ϱ� ���� �켱 '6'�� '������'�� �ٲߴϴ�. 6 = (110)2
 * ���� ���� ���������� �ڸ����� 1�� ���� �ǹ̴� �ش� �� ��ŭ ���ڸ� �ٲپ� �ָ� �Ǵ� ���Դϴ�.
 * 1�� 4(100)�� �ű�� ���¼��� 2(10)�� �ű�� ���ϴ� ������ ���� �� �ִ� ��������.
 * 1�� 4�� �ű�� ������ �̸� ������� ������̺��� ���� �� �� �ֽ��ϴ�. 
 * ���� �� 6�� �ɸ��� ������ ������̺� �̿��Կ� ���� 2������ ���� �� �ִ� ��������.

 * f(1), f(2), f(3), ... , f(n)���� f(x)�Լ��� �Ű����� x�� ���� �Լ� ���� ����ִ�.
 * f2(x)�� ���Ϸ��� f(f(x))�̸� �����ϰ�
 * f4(x)�� ���Ϸ��� f2(f2(x))�̸� �����ϰ�
 * f8(x)�� ���Ϸ��� f4(f4(x))�̸� �����մϴ�. 
 * f_n(x) x���� n�� �̵� �� ��� �ִ��� �Դϴ�.
 * 
 * �̷��� ���� �̿��ؼ� �������� �������� �۾��� ���ô�.
 ================================================================*/

/*
 * �������� �ռ��Լ��� �ƴ� ���� �ܼ��� n^kó�� n�� k�� ������ �������� �ش� ���� ���ݾ� ������ �°� �ٿ����� Ǫ�°��̰�, ���� ������ ���������� �ƴ� �ռ��Լ��μ��� �����̴�. 
 * ���� �ռ� ������ ���Ǳ��� ���ϸ�... ��� ������ ������ ������ ��Ȯ�� 1���� ���� �׷����� �ְ�, � �������� �����ؼ� ���� ��Ȯ�� k�� ������ Ÿ�� �����ڴ�. 
 * �׷��� k���� �̵����� ������ ������� �˷��ִ� �Ͱ� ����.
 * �� ���ô� Ʈ���� ���� ��쵵 1���� ����׷����� ������ ���̴�. 
 * */

// 17435�� - �ռ��Լ��� ����
public class p1__Sparse_Table {
	
	static int[][] next; 		// next[j][i] = ��� �� i���� 2^j�� �̵��� ���� ���� 
	// [ ���� ][ �Լ��� ���� ��� ������ ���� ��(�� �Լ��� ���� ������� f2, f4 ó�� �̷� 2�� ������ ����̱⿡ DP������ �Ʒ��� �ݵ�� �׾� �÷����Եȴ�.) ]
	// ���� ���� ��ǻ� �켱�Ǵ� ����  fn : {1, 2, ..., m}��{1, 2, ..., m} �� �ֱ⿡ ������ ����̴�. 
	// ���� �����ȿ� ���� �ϳ��� ����Ǿ� ���������� �ռ��ϴ� �Լ��� ���� ����Ǳ⿡ �����ϴ�. 
	
	
	static int MAX = 200_001;
	static int MAX_DIGIT = 19;	// 2^k >= MAX�� �ּ��� k 	200_000�� 2^19���� �׻� �۴� .
	//=======================================================
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());	// 1���� M������ �谳������ ���� f(x)�Լ��� ���� ��Ÿ���ٰ��̴�.	1<= M <=200,000
		next = new int[MAX_DIGIT][MAX];
		// f(1)���� f(m)���� ���� �־��� 
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=m; i++) 
			next[0][i] = Integer.parseInt(st.nextToken());	//2^0�� �ش��ϴ� �� ���� �־��ش�. 	
		
		/**--------------------------------------------------------------
		 ���� j���� �迭�� ä�����鼭 ��ü �迭�� ä�� �� �ִ�.

		 �츮�� ������ ��ȭ���� f2(x) = f1(f1(x)), f4(x) = f2(f2(x)), f8(x) = f4(f4(x)) �̷��� ���ذ��°��̴�. 
		
		 �̰� ���� ������ �Ἥ ���̸� 
		 f2(i) = f(2^1)(i)  = f(2^0)( f(2^0)(i) )
			   = next[1][i] = next[0][ next[0][i] ]
		
		 f4(i) = f(2^2)(i)  = f(2^1)( f(2^1)(i) )
			   = next[2][i] = next[1][ next[1][i] )
		
		 f8(i) = f(2^3)(i)  = f(2^2)( f(2^2)(i) )
			   = next[3][i] = next[2][ next[2][i] ]
		
		 *z�� 2�� ��� ���� ���� �ϳ�
		 fz(i) = f(2^(j+1))(i)  = f(2^j)( f(2^j)(i) )
		 	   = next[j+1][i]   = next[j][ next[j][i] ]		
		
	 	������ �̰� �ƴϴ�. f(j+1)(i) = fj(f1(i))	���� �׳��̸� ������ ���� j�� �δ°� '����'�� �׷��� �� �� ��ü�� �ƴϴ�. 
	
		�׷��� �̷��� ��ȭ���� ������� �� �ִ�. 
		next[j+1][i] = next[ j ][ next[j][i] ]
		
		MAX_DIGIT��ŭ ä���ָ� �˰����ϴ� n�� ���� �������� �ξ� �� �ش��ϴ� �������� �ϳ��� ����� ���� ������� ������ ����.  
		--------------------------------------------------------------**/
		for(int j=1; j<MAX_DIGIT; j++) {
			for(int i=1; i<=m; i++) {	
				next[j][i] = next[ j-1 ][ next[j-1][i] ];
			}
		}
		
		/**--------------------------------------------------------------
		 * ���� ��� 13�̸�	 13 = 8+4+1 = 2^3 + 2^2 + 2^0
		 * f13(2)�� ���϶�� �Ѵٸ� f13(2) = f8(f4(f1(2))) = f2^3( f2^2( f(2^0(2)) ) ) 	//���� ���Ⱑ f4(f1(f8(2))) �̶� �Ȱ���.
		 --------------------------------------------------------------**/
		int Q = Integer.parseInt(br.readLine());	//������ ��,	1<= Q <= 200,000
		for(int q=0; q<Q; q++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			//fn(x)�� ���ض�!
			for(int j=0; j<MAX_DIGIT; j++) {
				if( (n&(1<<j)) > 0) {
					x = next[j][x];	//�켱 �ش� j���� 2�� �������� ��������  fj(x)�� ���ؼ� ���� �Ű������� �ٲپ� �� ������ �Ѵ�. fj(x)�� �ش��ϴ� ���� �̹� ���صξ��� 
				}
			}
			//x���� �������� ������ ������ �˷��ش�. 
			sb.append(x+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}//=======================================================
	
}