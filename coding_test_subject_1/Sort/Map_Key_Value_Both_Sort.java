package Sort;

import java.util.*;
import java.io.*;
/**====================
12
abc
abs
bsd
avc
hrty
cdfe
kuyrr
kioyt
zsdwr
jh
ma
ab 
======================**/

// �ܾ� ����
public class Map_Key_Value_Both_Sort {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int len = 0;
		String str ="";
	
		Map<String, Integer> map = new HashMap<>();
		for(int i=0; i<N; i++) {
			str = br.readLine();
			len = str.length();
			map.put(str, len);
		}
		List< Map.Entry<String, Integer> > list = new ArrayList<>(map.entrySet());
		
		Collections.sort(list, new Comparator< Map.Entry<String, Integer> >() {
			public int compare( Map.Entry<String, Integer> A, Map.Entry<String,Integer> B) {
				return A.getKey().compareTo(B.getKey());
			}
		});	//key�� ���ؼ� ���� ������ �ϸ� �켱 ���������� ���ڿ��� ���ĵǴµ� , ������ �̰ɷ� value�� ���� ������ �ϸ� ���� ���̳����� �̹� ���ĵ� ������ ���� �̾����� ������ �Ǵ� ������ �غ��Ҵ�.
		// ������ �׷��� �Ǿ���! �Ʒ����� �غ��� �׷��� �ȴ�.
		
		//�켱 ���ڿ��� ���ؼ��� ���� 
		//list.forEach( (a)->System.out.println(a.getKey()+" "+a.getValue()) ); System.out.println();
		Collections.sort(list, new Comparator< Map.Entry<String, Integer> >() {
			public int compare( Map.Entry<String, Integer> A, Map.Entry<String,Integer> B) {
				return A.getValue().compareTo(B.getValue());
			}
		});
		
		//�ű��ϴ�!
		//list.forEach( (a)->System.out.println(a.getKey()+" "+a.getValue()) ); 
		
		list.forEach(a-> {
			try {
				bw.write(a.getKey()+"\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		bw.flush();
		bw.close();
		br.close();
	}

}
