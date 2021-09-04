package Binary_search;

public class basic_method {

	public static void main(String[] args) {
		
		/**  인덱스에 대한 것이 아닌, 값에 대해 중간값을 진행하는 이분탐색. 마찬가지로 정렬은 필수
		 * 기본이 되는 원칙은 원하는 결과에 도달하는 mid를 찾는 것이다. mid가 주가 된다. 
		 * right와 left의 중간값으로 mid를 도출하고... 만약 원하는 결과가 아니라면 right를 줄이거나, left를 늘리는 방법으로 
		 * mid의 값을 변경해 가는 것이다. 여기서 중요한 포인트는 한번 아니라고 나온 mid값에 대해 right에게는 mid-1, left에게 mid+1
		 * 하는 것이며 적어도 이 행위로 mid는 이전보다 항상 커지거나 혹은 항상 작아지게 된다. 또한 mid-1을 right에게 했다면 해당 mid값의 최대도 mid-1로 굳혀지게 된다는 점이 된다.
		 * 나중에 돌고 돌아 right에 mid-1 한번하고 수없이 많은 left에 mid+1을 하면 원래 mid값은 mid-1인 값에 도달하는 경우가 되듯 right와 left는 mid가 존재할 수있는 최대, 최소가 된다.
		 * 물론 당연히 중간에 위치한 값이니 수학적으로 틀릴리 없는 말이다. 
		 * **/
		
		int[] arr = {1, 5, 7, 15, 23, 56};
		
		int left = 0;
		int right = arr[arr.length-1];
		int mid = (left+right)/2; 	//mid를 나타내는 건 right와 left 사이에 있으면 된다. 
		
		while(left<=right) {	//right = mid-1 , left = mid+1 은 최대 최소값을 바꾸는 일도하지만 종료를 위한 조건을 주는 케이스도 된다.
			mid = (left+right)/2;
			
			
		}
		
		System.out.println(left+" "+right+" "+mid);
	}

}
