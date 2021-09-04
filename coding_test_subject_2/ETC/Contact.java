package ETC;

import java.io.*;

/*
 * (100+1+ | 01)+   ���� �۾��� �ߴ°�.
 * 
 *	+��� Ư¡�� �߿��ϴ�. ���� �߿��� ���� '��� �ѹ��� ���'�� �־�� �Ѵٴ� ���̴�.
 *  or�� Ư¡�� ���߿� �ϳ��� ���ð��̴�. �̹Ƿ� �� Ư¡�� ��ǰ� ������ �� ���°� ����.
 *  
 *  ���� �� ���� 1�� �����ϳ� 0���� �����ϳİ� or�� ������ ���̴�. �� ó���� 0�̸� 01�� �۾��� ��ġ�ǰ� ��ſ� 01���� �̾����� +�� ������ �ٽ� or�� �Ѿ��. 0�� �ٷ� or�� �Ѿ�� �߿��� �����̴�.
 *  
 *  ���� �� ó���� 1�̸� +��� ���ǻ� 100�� ��� �ѹ��� ���� �տ� ���;� �Ѵ�. �� ���Ŀ� 0�� ����̳� �ݺ� ������ �ľ��ϰ� �ݺ��� �����ٸ� 1�� ����Ǵ��� üũ�ϸ� �ȴ�. ���������� 1�� �ݵ�� �ѹ��� ���;��Ѵ�.
 *  ���⼭ 0�� ������ 2���� �þ������ 01�� ������ �� �ִ� ���� ������.
 * */

// 1013�� - Contact
public class Contact {

	public static void main(String[] args) throws IOException{
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw =  new BufferedWriter(new OutputStreamWriter(System.out));	
		StringBuilder sb = new StringBuilder();
		int testcase = Integer.parseInt(br.readLine());
		while(testcase-->0) {
			char[] c = br.readLine().toCharArray();
			int len = c.length;
			int i = 0;
			boolean check = true;

			while(i<len) {
				//�ش� if-else���ǹ��� ���� ū ( | )+ �� �ش��ϴ� ��Ʈ�� or�� ������. 
				
				if(c[i]=='0') {
					int next = i+1;
					if(next<len && c[next]=='1') {
						i = next+1;
					}
					else if(next<len && c[next]=='0' && i-2>=0 && c[i-1]=='1' && c[i-2]=='1') {	//�� �� ���� '00' �ΰ�ó�� ���Դµ� �̹� Ʋ�� ��� �Գ�... �� �ٵ� ������ ��ȸ�μ� �� Ȥ��... 1+���� �߰� 1�� �����ϰ��ִ�? �׷��ٸ� ���� 100+��Ʈ�� �Ѿ�� �ǰڴ�!
						i--; // 100+ ��Ʈ�� �Ѿ�� Ȱ���غ����ִ�. 	
					}
					else { //��� ��ȸ�� ���������� ������...
						check = false;
						break; //�־�� �ϴ� ��Ʈ�̹Ƿ� ���ٸ� �ٷ� ����
					}
				}
				else {
					/** 0+ �� ��� �Ǵ��� Ȯ���� �Ѵ�. **/
					int next_1 = i+1, next_2 = i+2;
					//��� �ѹ� �־���ϴ� 100
					if(next_1<len && next_2<len && c[next_1]=='0' && c[next_2]=='0') {
						i = next_2 + 1;
					}
					else {
						check = false;
						break; //�־�� �ϴ� ��Ʈ�̹Ƿ� ���ٸ� �ٷ� ����
					}
					//������ 0�� ����� ������ üũ�غ���. 
					while(i<len && c[i]=='0') {		
						i++; // �߰��� Ȯ���� ���ϴ� ������ �ȵȴٸ� �Ѿ�� �ȴ�..	
					}
	
					/** 1+ �� ��� �Ǵ��� Ȯ���Ѵ�. **/ 
					//��� �ѹ� ���;��ϴ� 1
					if(i<len && c[i]=='1') {
						i++;
					}
					else {
						check = false;
						break; //�־�� �ϴ� ��Ʈ�̹Ƿ� ���ٸ� �ٷ� ����
					}
					//������ 1�� ����� ������ üũ�غ���. 
					while(i<len && c[i]=='1') {
						i++; // �߰��� Ȯ���� ���ϴ� ������ �ȵȴٸ� �Ѿ�� �ȴ�.
					}
					
				}
	
			} // �� ������ ����.
			sb.append( check ? "YES\n" : "NO\n" );

		}
		bw.write(sb.toString());
		bw.flush();
	}

}
