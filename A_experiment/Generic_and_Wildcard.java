package A_experiment;

//import javafx.util.Pair;
import java.util.*;
import java.io.*;

/**==================================================================================================
 * ����� �߻��� ������� �۾��� ����, �׷��ٰ� �׻� �ּҸ� �Ǵ°� �i�´ٸ�(�ܼ� �׸���) ���� ���� �ּҰ� �ȵ� �� �ִ���.
 ===================================================================================================*/
public class Generic_and_Wildcard {

    /*public class Pair{
       // ��հԵ� �̷��� ������ Pair��� �̸����� Ŭ������ ����� �ٷ� �̳༮�� ��ǥ�� �Ǿ ���������� �̰��� ���ǰ�, ���� Pair�� ������ �ȴ�.
       //ArrayList�� ����������.
    }*/

    // PairŬ���� ó�� ����� ���
    class King<T, S> {
        T info;
        S id;
        King(T info, S id){
            this.info = info;
            this.id = id;
        }
        /*public*/<U> void printinfo(U info) {     //�� �޼ҵ忡�� U��� ���·� �ڷ����� ���ʸ� �޼ҵ�� ��Ÿ�� ǥ������ �տ��� <U>�� �˷��ش�.
            System.out.println(info);
        }
    }//============================================
    public static void main(String[] args) throws IOException{
        
    	//Map<Integer, Integer> map = new HashMap<>();
        // Map�� �޸� ���̺� ���°� �ƴ�, �ܙ��� ¦�� ���� ǥ���� �ϴ� ĸ�������̴�.
        //PairŬ������ �ܼ��� ¦������ ǥ���� �Ҷ� �����ϴ�. �׸��� ��� ���ʸ��� Ȱ���ؼ� ���� ǥ���� ���� �ȴ�. ���� ��� �ڹ� Ŭ������ �� �̷�������
        
        /*Pair<Integer, Integer> p1 = new Pair<>(100, 500);
        Pair<Integer, String> p2 = new Pair<>(200, "King you me not block");
        
        System.out.println(p1.getKey()+" "+p1.getValue());
        System.out.println(p2.getKey()+" "+p2.getValue());
        Pair<King<Integer, Integer>, Integer> aaa;  //�̷��� pair�ȿ� pair�� �����ϴ�.

        Pair<Integer, Integer>[] arrPair = new Pair[100];
        arrPair[1] = new Pair<>(100, 100);
        System.out.println(arrPair[1].getKey()+" "+arrPair[1].getKey());
        for(int i=0; i<100; i++){
            //arrPair[i].getValue();
        }
        */
        
        String[] a = new String[3];
        for(int i=0; i<3; i++)
            a[i] = "234";
        System.out.println(a[0]);

        List<Integer>[] list = new ArrayList[100];

        /**
         * �ڹٿ��� ���ʸ� �迭�� ������� ���ƾ� �Ǵ� ����
         * String[] aaa = new String[3];
         * for(int i=0; i<3; i++)
         *      aaa[i] = new String("");
         * Ȥ��
         * String[] aaa = {"123","234","654"};
         * ���� �Ϲ����� Ŭ������ �迭�� ����� �� ��ü�� �������ִ� ������ ����ϴ°� �⺻���̰� �����ϴ�.
         *
         * �ٸ� ArrayList<>, Set<>, Pair<>�� ���� ���ʸ� Ŭ���� ��� �迭��
         * ArrayList<Integer>[] list = new ArrayList[5];
         * for(int i=0; i<5; i++)
         *      list[i] = new ArrayList<>();
         * �̷��� �ϴ°� �����ϰ� �̿뵵 ������, �̷��� ������ �ؼ� ����ϴ°��� ���� ���ƾ� �Ұ��̴�.
         *
         * https://wraithkim.wordpress.com/2015/09/09/java-%EC%A0%9C%EB%84%88%EB%A6%AD-%EB%B0%B0%EC%97%B4%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%EB%90%98%EB%8A%94-%EC%9D%B4%EC%9C%A0/
         *
         * ������ �ð����� ������ ������ ����ð��� ���ʸ��迭�� ���ʸ��� ��Ȯ�� �ڷ������� ������ �ȵǼ� Object[]�� ��ȯ�� �ȴ�.
         * �׸��� �������� ���ϰ� �Ǿ� ������ �߻��Ҽ��ֵ�. ����ð���.
         *
         *
         * ���ϵ�ī��� �迭�� ������ִ�. ��ü����Ÿ������ ������ �ȴ�.
         *
         *
         * ��ſ�
         * ArrayList< ArrayList<Integer> > list = new ArrayList<>();    //���� �ܰ��� ArrayList�� ��ü�� ����
         * for(int i=0; i<10; i++)
         *      list.add( new ArrayList<>() ); //�� ���� ���빰���� ��ü�� ����
         * ���� add�� ���� �ø���, �� ���� ArrayList<Integer>�� ���ν� �̿��� �ϴ°� �����ϴ�.
         * list.get(row).get(col);
         * * */


        ArrayList< ArrayList<Integer> > list2 = new ArrayList<>();    //���� �ܰ��� ArrayList�� ��ü�� ����
        for(int i=0; i<10; i++)
            list2.add( new ArrayList<>() );
        list2.get(1).add(3);
        System.out.println(list2.get(1).get(0));





        //�Ʒ�ó�� ���ϵ�ī��δ� �������� ���ʸ��� �������� �������� �� �ִ�.
        List<?>[] zzz = new ArrayList[3];
        List<Integer> A1 = new ArrayList<Integer>();
        zzz[0] = A1;
        zzz[1] = new ArrayList<String>();
        zzz[2] = new ArrayList< ArrayList<Integer> >();
        A1.add(123123);


        //zzz[0].add(0);    //�Ұ���   //���ϵ�ī��� ���׸����� �ش� �ڷ����� �������� �˼����� �𸣰� �������. �׷��� ��� ������ �𸥴�.
                            //��� ������ ���� � ������ ���׸� �Ű������� ����� �Ǵ� �޼ҵ尰���Ϳ��� �̿��� �ȵɰ��̴�.
                            //�ٸ� ���� �Ű������� ��� ���׸��� �ȵ��ٸ� �ش� �޼ҵ�� ����� �����ϴ�.
                            //��� �ƹ��͵� ���� �ܼ��� �𸣴� ��(���׸�)�� �Ű������� ���� ���ص�, �� �ܿ� ���� ����� �����ϴ�.
                            //�׸��� �� �Ʒ��� �����ϰ����� �������� �ſ� ���� ������� �Ű������� �ִ°� ����������,
                            // ��Ģ������ ��� ���׸� �Ķ���͵� ���� �ʰ� �̿��ϴ°� ��Ģ�̰� �׷����� ���̴°��̴�.
        System.out.println("��� ���� "+ zzz[0].size() );      //�̰� ��밡��
        System.out.println("�̰͵� �԰��� "+zzz[0].get(0));

        // �Ʒ��� �̷� ǥ���� 'Upper Bounded Wildcards'��� �Ѵ�.
        // ������ ������ ��ȭ�ϱ� ���� extends�� �̿��ؼ� �ش� Ŭ������ �ڽ��� �θ� �ɼ��ִ� ���׸��� ���� ���ϵ�ī�尡 ����ȴ�.
        List<? extends Number>[] xxx = new ArrayList[3];
        xxx[0] = new ArrayList<Integer>();
        xxx[1] = new ArrayList<Double>();
        xxx[2] = new ArrayList<Long>();
        Number asd = new Integer(1);

        // �ƹ��͵� ���°� �̷��� Object�� �����ؼ� ���� �ҳ� ��밡���ϴ�.
        List<? extends Object> ccc = new ArrayList< King<Integer,Integer> >();

        // Unbounded Wildcards. �̰��� List<?>���� �ߴ� ǥ���� �ٷ� �̰��̴�.
        // �Ʊ� ���ߵ��� ����� ù��°�� Object���� �����Ǵ� ����� ��밡���ϰ�
        // �ι�°�� ���׸� Ŭ������ �޼ҵ���� Ÿ�� �Ķ���Ϳ� �������� �ʴ� �޼ҵ�鸸 ����� �� �ִ�.



        // Lower Bounded Wildcards.
        // super Ű����� �����ؼ� upper bounded�� �ݴ�� ������ Ÿ�԰� �� ���� Ÿ�Ը� ����� �ȴ�.
        // ���� ��� <? super Integer>�� �ߴٸ� Integer�� ������ Number�� Object�� ����� �����ϴ�.
        List<? super Integer> vvv = new ArrayList<Integer>(); //���� �ڱ��ڽŵ� ������


        //���� ���ϵ��ε� add�� �ϰ�ʹٸ� �ſ� ���������� ���� ������� �Ʒ��� �ִ�. ��Ģ������ ���ϵ�ī�忡 ���ӻ��� �̷��� ����� ���ϴ°� �´�.
        //���� �̷��� ������ ���ϵ�ī�尡 �ƴ϶� �� �Ű�Ἥ ���׸����� ������ �ؾ��� ���̴�.

        List<?> sss = new ArrayList<Integer>();

        List<Integer> www = (List<Integer>) sss;    // ���ϵ�ī�带 ����ȯ ���Ѽ� ���׸�Ŭ������ ��ȯ�ؼ� �̿��� �����ϴ�.

        www.add(10);
        //�ƴϸ� �̷��� ĳ������ �����ϸ鼭 ���൵ �԰����ϴ�. ĳ���� ��ȯ�� �Ѱ��� �޼ҵ带 �����ϴ� ��ü���� �˷��ֵ��� ��ȣ�� ��ü ��ü�ε� ���ָ� �ȴ�.
        ( (List<Integer>)sss ).add(123_123_123);

        System.out.println("����ȯ�Ѱ��� �ٸ� ��ü ��� �а� "+sss.get(0));
        System.out.println("ĳ���� ��ȯ�Ѱ��� ��ü ��ü���� �˷��ְ� �Ѱ� "+sss.get(1));

        /**
         * ��������� ���ϵ�ī��� ��� ���׸��� �ɼ��ִٴ� ǥ�����μ�, �ش� ���׸��� Ư������ �ʴ��� �𸣴��� �޾��ټ��ִ�.
         *
         * ���׸� : ������ �� Ÿ���� ������, �� Ÿ���� �������� �� Ÿ�� Ư���� �°� ����� ���̴�.
         *      ���׸� Ÿ�Կ� ���õ� �Ķ���͸� �޴� �޼ҵ�鵵 ����� �� �ִ�.
         *
         * ���ϵ� ī�� : ���� ���� ������ ����. ��, ���ݵ� �� Ÿ���� �𸣰� �����ε� �� ���̴�.
         *      ���ϵ� ī�� Ÿ�Կ� ���õ� �Ķ���͸� �޴� �޼ҵ���� ����� �� ����.
         * */
    }//=========================================================

     /*static int solve(int first, int second) {
        if(first==W || second==W){
            return 0;
        }
        int ret = dp[first][second];
        if(ret != -1) return ret;

        int next = Math.max(first, second);
        int dist1, dist2;

        if(first==0)
            dist1 = Math.abs(pr.get(next-1).getKey()-1) + Math.abs(pr.get(next-1).getValue()-1);
        else
            dist1 = Math.abs(pr.get(next-1).getKey() - pr.get(first-1).getKey())
                    + Math.abs(pr.get(next-1).getValue() - pr.get(first-1).getValue());

        if(second==0)
            dist2 = Math.abs(pr.get(next-1).getKey()-N) + Math.abs(pr.get(next-1).getValue()-N);
        else
            dist2 = Math.abs(pr.get(next-1).getKey() - pr.get(second-1).getKey())
                    + Math.abs(pr.get(next-1).getValue() - pr.get(second-1).getValue());

        int left = solve(next, second) + dist1;
        int right = solve(first, next) + dist2;

        ret = Math.min(left,right);
        return ret;
    }//=========================================================
    */
}
