package Bitmask_DP;

import java.util.*;
import java.io.*;

// 11723�� ���� - ����
// ��Ʈ����ũ�� ����ؼ� ���� �޸𸮸� ȿ�������� ���. 
/**---------------------------------------------------------
 * int �ڷ��� a�� �����ϸ� 4����Ʈ(4 * 8bit)�� �޸𸮿� �Ҵ�޾�
 * �� 32���� �뿡 ��, ������ �Ǵ��� �� �ְ� �ȴ�.
 * a = 00000000 00000000 00000000 00000000(2)�� �޸𸮿� �Ҵ�޴´�.
 * ����, �� 0~31���� ���� ������ ��Ÿ�� �� �ִ�.
 * 2^0�ڸ� �� 0�� true, false
   2^1�ڸ� �� 1�� true, false
   2^2�ڸ� �� 2�� true, false
   ...
   2^30�ڸ� �� 30�� true, false
   2^31�ڸ� �� 31�� true, false
   
   �� ���¸� ��Ʈ����ũ DP ���¶�� ���� ����̴�.
============================================================**/
public class p1__by_bitmask {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int bitMask = 0;	//���� 32��Ʈ�� �ƹ��͵� ������� ���� ���� 
		
		while(N-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			int num = -1;
			if(st.hasMoreTokens())
				num = Integer.parseInt(st.nextToken());
		
			switch (cmd) {	//swtich�������� String�� ���ؼ� equals�� ó�� �ȴ�. '=='���� ������� �ʾҴ�. �� cmd �ȿ� �ִ� ���빰�� ���� �����̴�? ��� ����°� ����. swtich���� �ٸ� �ڷ����� ���ؼ��� �ּҰ��� �ƴ� ���빰�� ���� �;� �Ұ��̴�.
				case "add":
					bitMask = bitMask | 1<<(num-1); break;	//���� ��Ʈ����ũ�� �ش� ��ȣ�� ��Ʈ�� 1�� �־��ֵ��� | ��Ʈor������ ���ش�.
				
				case "remove":
					bitMask = bitMask & ~(1<<(num-1)); break; // ~�� �̿��ؼ� �ش� �κи� ��Ʈ���� 0���� �ٲپ� & ��Ʈand������ �������־� �����ش�. 
				
				case "check":
					if( (bitMask & 1<<(num-1))  ==  1<<(num-1) )	//(������)�ش� �κ��� ��Ʈ�� ���ʿ��� �ִ°��� &�����ڷ� ������ ��Ʈ�� ��� 0���� �ٲ� �񱳸� �� �� �ִ�. 
						sb.append(1+"\n");
					else
						sb.append(0+"\n");
					break;
				
				case "toggle":
					bitMask = bitMask ^ 1<<(num-1);	break;	// ^�� �̿��ؼ� �ش� �κ� ��Ʈ�� 1�� XOR��Ʈ������ �����Ѵ�. 
					// �׷����ϸ� ������ ��Ʈ���� ��� 0���θ� ^������ ����ǹǷ� ���� 1�̸� ���δ޶� 1�� ������, 0�̸� ���� ���Ƽ� 0���� ������ �Ǿ�����. 
					// �ش� ��Ʈ �κ��� 1�� ������ �ϴµ� ���� ���� 1�� �������ִٸ� ���� ���Ƽ� 0���� ������ �ǰ�, 0�� �������ִٸ� �޶� 1�� �������� ����ȴ�.
					// ���������� �����ڸ� �����ʿ� �������ִ� ��Ʈ���� 0��Ʈ�� �����ִ°� ������ �� ��Ʈ���� ������ ���ְڴ� �̸�, �����ʿ��� 1��Ʈ�� �ִ� �κи� üũ�ؼ� �ִٸ� ���ְ� ���ٸ� �ְ� ������ �ǵ��� �ϴ� ����̴�. 
				
				case "all":
					bitMask = ~0; break;	// �ſ� ������ ��� �ڷ����� ��� ��Ʈ�� 1�� �ٲپ� ���� �ֱ�
				
				case "empty":
					bitMask = 0; break;
			}
		}//�ݺ��� ���� 
		
		bw.write(sb.toString());
		bw.flush();
	}

}
