package Binary_search;

public class basic_method {

	public static void main(String[] args) {
		
		/**  �ε����� ���� ���� �ƴ�, ���� ���� �߰����� �����ϴ� �̺�Ž��. ���������� ������ �ʼ�
		 * �⺻�� �Ǵ� ��Ģ�� ���ϴ� ����� �����ϴ� mid�� ã�� ���̴�. mid�� �ְ� �ȴ�. 
		 * right�� left�� �߰������� mid�� �����ϰ�... ���� ���ϴ� ����� �ƴ϶�� right�� ���̰ų�, left�� �ø��� ������� 
		 * mid�� ���� ������ ���� ���̴�. ���⼭ �߿��� ����Ʈ�� �ѹ� �ƴ϶�� ���� mid���� ���� right���Դ� mid-1, left���� mid+1
		 * �ϴ� ���̸� ��� �� ������ mid�� �������� �׻� Ŀ���ų� Ȥ�� �׻� �۾����� �ȴ�. ���� mid-1�� right���� �ߴٸ� �ش� mid���� �ִ뵵 mid-1�� �������� �ȴٴ� ���� �ȴ�.
		 * ���߿� ���� ���� right�� mid-1 �ѹ��ϰ� ������ ���� left�� mid+1�� �ϸ� ���� mid���� mid-1�� ���� �����ϴ� ��찡 �ǵ� right�� left�� mid�� ������ ���ִ� �ִ�, �ּҰ� �ȴ�.
		 * ���� �翬�� �߰��� ��ġ�� ���̴� ���������� Ʋ���� ���� ���̴�. 
		 * **/
		
		int[] arr = {1, 5, 7, 15, 23, 56};
		
		int left = 0;
		int right = arr[arr.length-1];
		int mid = (left+right)/2; 	//mid�� ��Ÿ���� �� right�� left ���̿� ������ �ȴ�. 
		
		while(left<=right) {	//right = mid-1 , left = mid+1 �� �ִ� �ּҰ��� �ٲٴ� �ϵ������� ���Ḧ ���� ������ �ִ� ���̽��� �ȴ�.
			mid = (left+right)/2;
			
			
		}
		
		System.out.println(left+" "+right+" "+mid);
	}

}
