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

// 단어 정렬
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
		});	//key에 대해서 먼저 정렬을 하면 우선 순서적으로 문자열이 정렬되는데 , 다음에 이걸로 value에 대해 정렬을 하면 같은 길이끼리는 이미 정렬된 순서에 따라 이어지는 정렬이 되는 생각을 해보았다.
		// 실제로 그렇게 되었다! 아래까지 해보면 그렇게 된다.
		
		//우선 문자열에 대해서만 정렬 
		//list.forEach( (a)->System.out.println(a.getKey()+" "+a.getValue()) ); System.out.println();
		Collections.sort(list, new Comparator< Map.Entry<String, Integer> >() {
			public int compare( Map.Entry<String, Integer> A, Map.Entry<String,Integer> B) {
				return A.getValue().compareTo(B.getValue());
			}
		});
		
		//신기하다!
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
