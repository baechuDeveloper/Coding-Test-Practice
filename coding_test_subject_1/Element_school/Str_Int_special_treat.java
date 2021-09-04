package Element_school;

import java.io.*;
import java.util.*;

// List�� ���鼭 contains�� �ϴµ� ���� ������ �ͺ��� �� �⺻���� Ŭ������ ���� ���� �κ��� �־ ����д�.
public class Str_Int_special_treat {

	public static void main(String[] args) throws IOException{

		List<Integer> list = new ArrayList<>();
		
		list.add(3);
		list.add(3);
		list.forEach(a->System.out.println(a));
		//list.remove(3); �̰� �ε����� �ν� Object�������� �ؾ� remove�� ��ü�� �����ؼ� ����
		list.remove(new Integer(3));
		if(list.contains(3))
			System.out.println("wdw");
		list.forEach(a->System.out.println(a)); System.out.println();
		//---------------------------
		List<String> str = new ArrayList<>();
		
		str.add("�ȳ�");
		str.add("�ȳ�");
		str.forEach(a->System.out.println(a));
		str.remove("�ȳ�");
		if(str.contains("�ȳ�"))
			System.out.println("gggg");
		str.remove(new String("�ȳ�"));	//�Ѵ� �ν��� �Ǿ���... �̻�Ƿ� ���� ���۷��� ������ �߿�ġ �ʴ� �ΰ�..?
		str.forEach(a->System.out.println(a)); System.out.println();
		//---------------------------
		AA a = new AA(1, 2);
		AA b = new AA(1, 2);
		List<AA> aa = new ArrayList<>();
		
		aa.add(a);
		aa.add(b);
		aa.forEach(v->System.out.println(v.x+" "+v.y)); System.out.println("--");
		aa.remove(new AA(1, 2));	//���⼭�� ����� ��ü�� ���� ���۷����μ� �� ���� �ٸ� ��ü�̹Ƿ� �������� �ʴ´�.
		aa.forEach(v->System.out.println(v.x+" "+v.y));
		if(aa.contains( new AA(1, 2) ) == true)
			System.out.println("�̷��� Ŭ���� ��ü���� ������ �ʴ�.");
		//---------------------------
		
		String q = "�ȳ�";
		String w = "�ȳ�";
		String e = new String("�ȳ�");
		String r = new String(q);
		if( q==w )
			System.out.println("����� ���̴� ���ڿ��� ������ ���۷��� ���� �����Ѵ�.");
		if( q==e || w==e ) 
			System.out.println("new �����ڷ� ����ٸ� ���� ���ο� ��ü�̸� ���ο� ���۷������� ������. ���� ������ �ٸ� ���۷��� �ּҰ��̴�.");
		if( q==r || w==r )
			System.out.println("���������� �ڽ��� ���ڿ��� ������� ������ new�����ڷ� ���ο� ��ü�� ����� ���ο� ���۷��� ���̴�.");
		if( e==r )
			System.out.println("�翬�� ���� �� ����.");
		if(q.equals(r))
			System.out.println("���ڿ� ��ü�� �̷��� ���ϴ°� �����̸�, ���ڿ� ������ ������ �����ִ�.");
		//---------------------------
		
		List<String> str_list = new LinkedList<>();
		str_list.add(q);
		str_list.forEach( v-> System.out.println(v));
		if( str_list.contains(r) == true )
			System.out.println("���� �ɷ� ���");
		if( str_list.contains( new String("�ȳ�") ) == true)
			System.out.println("�̰͵�");
		if( e == new String("�ȳ�") )
			System.out.println("�׷��ٰ� ���۷����� ������ �ƴϴ�.");
		
		
		System.out.println("\n��! Collections���� String�� Integer�� �⺻���� Ŭ������ ���ؼ��� 'Ư�����'�� ���ִµ�\n"
				+ "�̿� ���� Ŭ������ List���� Collections������ ���۷����� ������ ���θ� ���� �ʰ� �׾��� ���̳� ���ڿ��� ���� �񱳱��� ���ֵ���+\n"
				+ "�� ��ü���� ���� �ش�. ������ �����غ���.");
		
	
	}//===================================================
	
	// ����� ���� Ŭ����
	static class AA{
		int x, y;
		AA(int a, int b){
			x=a;
			y=b;
		}
	}//===================================================
}
