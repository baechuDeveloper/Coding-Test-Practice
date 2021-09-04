package Divide_and_Conquer;
// ��͸� �����ϴ� �˰���, ���� ����.

import java.util.*;
import java.io.*;

// 1629�� ���� ���� - �ŵ������� ���� ������������ ������ ����ϴ� ���.
/**
 * ���� ȿ������ mod�� ���!
  A mod B ... �� A % B�� �Ѵٸ� 
  A = (a*b*c*d) �϶�
  (a*b*c*d) % B ��� ���°� �Ǵ°� �˰������ٵ� �̿� �׻� ���� ����
  ( (a%B) * (b%B) * (c%B) * (d%B) ) % B  �̴�.
   �׸���
   ( (110 % 23) * (45 % 23) * 120 * 68) % 23 �̷��� A �ȿ� �κ������� mod�� �����ص� ������.
   �������� mod B�� �ϴ� ���� ��� �̷��� A �ȿ� ����� mod B�� ���͵� ������ ����.
  
    ��������� ���� �ٱ� mod B�� ��� ( A )%B �� ���¸� ������ �ִ� �� 
  A�� �κ����� �μ��� ���ؼ� ����� mod B�� ���� ����� ����. 
  A�� ��� �μ��� ����'% B' �� ���ְ� "����������" ���������� ��! '% B'�� ���ִ� ���� ���� �ϸ� �ȴ�.    
  
 * **/
public class Power_mul {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long A, B, C; // A�� B�� ���� ���� C�� ����� �ش� ������ ���� ���Ѵ�.
		StringTokenizer st = new StringTokenizer(br.readLine());
		A = Long.parseLong(st.nextToken()); //��������
		B = Long.parseLong(st.nextToken()); //������
		C = Long.parseLong(st.nextToken()); //mod C
		//������ "���������� mod c"�� �� ���� ����ٸ� �̷��� ����� ����� �� �ִ�.
		System.out.println(power(A%C,B,C));	//��������� C�� �������� ���صѰǵ� �ش� ���� ���ؼ� ���� mod�� �ص� �Ʒ� ó�� ���� �ϴ�.
		
		/*System.out.println( (5*6*112*23*56)%9 );
		System.out.println( ( (5%9) * (6%9) * (112%9) * (23%9) * (56%9) ) %9 ); 
		System.out.println( (5%9 * 6%9 * 112%9 * 23%9 * 56%9)%9); //��ȣ�� ���� �ȴ�. ��������
		System.out.println( 5%9 * 6%9 * 112%9 * 23%9 * 56%9 %9); //��ȣ�� ���� �ȴ�. ��������
		System.out.println( ( 5%9 * 6%9 * 112 * 23%9 * 56 )%9); //�̷��� ���������� �ص� ���ص� ����� ����. �� �ȿ��� ����� mod�� ���� �����ϴ�. 
		*/
		// ��������� ���� �ٱ� mod B�� ��� ( A )%B �� ���¸� ������ �ִ� �� 
		// A�� �κ����� �μ��� ���ؼ� ����� mod B�� ���� ����� ����. 
			
	}
	public static long power(long a, long b, long c) {
		
		if(b==0) return 1;
		if(b==1) return a;
		
		long temp = power(a%c, b/2, c);
		
		if(b%2 == 0) {
			return (temp * temp) % c;
		}
		else {
			return (((temp*temp)%c)*a)%c;	//mod c�� �ȿ� ����̳� ���� ������ ������ long�� �ִ�ũ�⸦ �����ʵ��� ������ mod c�� ���ִ°͵� ����̴�. 
		}
	}

}
