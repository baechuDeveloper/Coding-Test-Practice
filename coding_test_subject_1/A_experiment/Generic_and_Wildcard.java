package A_experiment;

//import javafx.util.Pair;
import java.util.*;
import java.io.*;

/**==================================================================================================
 * 사건이 발생한 순서대로 작업을 진행, 그렇다고 항상 최소만 되는걸 쫒는다면(단순 그리디) 최종 합이 최소가 안될 수 있더라.
 ===================================================================================================*/
public class Generic_and_Wildcard {

    /*public class Pair{
       // 재밌게도 이렇게 스스로 Pair라는 이름으로 클래스를 만들면 바로 이녀석이 대표가 되어서 지역적으로 이것을 사용되고, 원래 Pair는 못쓰게 된다.
       //ArrayList도 마찬가지다.
    }*/

    // Pair클래스 처럼 만드는 방법
    class King<T, S> {
        T info;
        S id;
        King(T info, S id){
            this.info = info;
            this.id = id;
        }
        /*public*/<U> void printinfo(U info) {     //이 메소드에서 U라는 형태로 자료형을 제너릭 메소드로 나타내 표현됨을 앞에서 <U>로 알려준다.
            System.out.println(info);
        }
    }//============================================
    public static void main(String[] args) throws IOException{
        
    	//Map<Integer, Integer> map = new HashMap<>();
        // Map과 달리 테이블에 담기는게 아닌, 단숞게 짝을 지어 표현을 하는 캡슐느낌이다.
        //Pair클래스는 단순히 짝을지어 표현을 할때 유용하다. 그리고 사실 제너릭을 활용해서 직접 표현을 만들어도 된다. 원래 모든 자바 클래스가 다 이런식이지
        
        /*Pair<Integer, Integer> p1 = new Pair<>(100, 500);
        Pair<Integer, String> p2 = new Pair<>(200, "King you me not block");
        
        System.out.println(p1.getKey()+" "+p1.getValue());
        System.out.println(p2.getKey()+" "+p2.getValue());
        Pair<King<Integer, Integer>, Integer> aaa;  //이렇게 pair안에 pair도 가능하다.

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
         * 자바에서 제너릭 배열을 사용하지 말아야 되는 이유
         * String[] aaa = new String[3];
         * for(int i=0; i<3; i++)
         *      aaa[i] = new String("");
         * 혹은
         * String[] aaa = {"123","234","654"};
         * 같은 일반적인 클래스의 배열은 만들고 각 객체를 생성해주는 것으로 사용하는게 기본적이고 가능하다.
         *
         * 다만 ArrayList<>, Set<>, Pair<>와 같은 제너릭 클래스 경우 배열로
         * ArrayList<Integer>[] list = new ArrayList[5];
         * for(int i=0; i<5; i++)
         *      list[i] = new ArrayList<>();
         * 이렇게 하는건 가능하고 이용도 되지만, 이렇게 접근을 해서 사용하는것을 하지 말아야 할것이다.
         *
         * https://wraithkim.wordpress.com/2015/09/09/java-%EC%A0%9C%EB%84%88%EB%A6%AD-%EB%B0%B0%EC%97%B4%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%A7%80-%EB%A7%90%EC%95%84%EC%95%BC-%EB%90%98%EB%8A%94-%EC%9D%B4%EC%9C%A0/
         *
         * 컴파일 시간에는 문제가 없지만 실행시간때 제너릭배열의 제너릭은 정확한 자료형으로 지정이 안되서 Object[]로 변환이 된다.
         * 그리고 대응되지 못하게 되어 오류가 발생할수있따. 실행시간에.
         *
         *
         * 와일드카드는 배열로 만들수있다. 구체적인타입으로 지정이 된다.
         *
         *
         * 대신에
         * ArrayList< ArrayList<Integer> > list = new ArrayList<>();    //가장 외곽의 ArrayList의 객체를 생성
         * for(int i=0; i<10; i++)
         *      list.add( new ArrayList<>() ); //그 안의 내용물들의 객체를 생성
         * 따라서 add로 행을 늘리고, 그 안의 ArrayList<Integer>로 열로써 이용을 하는게 가능하다.
         * list.get(row).get(col);
         * * */


        ArrayList< ArrayList<Integer> > list2 = new ArrayList<>();    //가장 외곽의 ArrayList의 객체를 생성
        for(int i=0; i<10; i++)
            list2.add( new ArrayList<>() );
        list2.get(1).add(3);
        System.out.println(list2.get(1).get(0));





        //아래처럼 와일드카드로는 여러개의 제너릭을 마음껏을 지정해줄 수 있다.
        List<?>[] zzz = new ArrayList[3];
        List<Integer> A1 = new ArrayList<Integer>();
        zzz[0] = A1;
        zzz[1] = new ArrayList<String>();
        zzz[2] = new ArrayList< ArrayList<Integer> >();
        A1.add(123123);


        //zzz[0].add(0);    //불가능   //와일드카드는 제네릭에서 해당 자료형이 무엇인지 알수없고 모르고 상관없다. 그래서 얘는 뭔지를 모른다.
                            //얘는 뭔지를 몰라서 어떤 지정된 제네릭 매개변수로 사용이 되는 메소드같은것에는 이용이 안될것이다.
                            //다만 내가 매개변수로 어떠한 제네릭도 안들어간다면 해당 메소드는 사용이 가능하다.
                            //얘는 아무것도 몰라서 단순히 모르는 것(제네릭)을 매개변수로 넣지 못해도, 그 외에 것은 사용이 가능하다.
                            //그리고 맨 아래에 서술하겠지만 안전성이 매우 낮은 방법으로 매개변수로 넣는게 가능하지만,
                            // 원칙적으로 어떠한 제네릭 파라미터도 들어가지 않고 이용하는게 원칙이고 그럴려고 쓰이는것이다.
        System.out.println("요건 가능 "+ zzz[0].size() );      //이건 사용가능
        System.out.println("이것도 쌉가능 "+zzz[0].get(0));

        // 아래에 이런 표현을 'Upper Bounded Wildcards'라고 한다.
        // 변수의 제한을 완화하기 위해 extends를 이용해서 해당 클래스가 자신의 부모가 될수있는 제네릭에 대해 와일드카드가 적용된다.
        List<? extends Number>[] xxx = new ArrayList[3];
        xxx[0] = new ArrayList<Integer>();
        xxx[1] = new ArrayList<Double>();
        xxx[2] = new ArrayList<Long>();
        Number asd = new Integer(1);

        // 아무것도 없는건 이렇게 Object와 동일해서 개나 소나 사용가능하다.
        List<? extends Object> ccc = new ArrayList< King<Integer,Integer> >();

        // Unbounded Wildcards. 이것은 List<?>으로 했던 표현이 바로 이것이다.
        // 아까 말했들이 사용은 첫번째로 Object에서 제공되는 기능을 사용가능하고
        // 두번째로 제네릭 클래스의 메소드들중 타입 파라미터에 의존하지 않는 메소드들만 사용할 수 있다.



        // Lower Bounded Wildcards.
        // super 키워드로 정의해서 upper bounded와 반대로 지정된 타입과 그 상위 타입만 허용이 된다.
        // 예를 들어 <? super Integer>로 했다면 Integer의 상위인 Number와 Object만 사용이 가능하다.
        List<? super Integer> vvv = new ArrayList<Integer>(); //물론 자기자신도 되지롱


        //만약 와일드인데 add를 하고싶다면 매우 안정적이지 못한 방법으로 아래가 있다. 원칙적으로 와일드카드에 쓰임새는 이러한 사용을 안하는게 맞다.
        //따라서 이렇게 쓰려면 와일드카드가 아니라 더 신경써서 제네릭으로 지정을 해야할 것이다.

        List<?> sss = new ArrayList<Integer>();

        List<Integer> www = (List<Integer>) sss;    // 와일드카드를 형변환 시켜서 제네릭클래스로 변환해서 이용이 가능하다.

        www.add(10);
        //아니면 이렇게 캐스팅을 지정하면서 해줘도 쌉가능하다. 캐스팅 변환을 한것을 메소드를 지정하는 객체에게 알려주도록 괄호를 객체 전체로도 해주면 된다.
        ( (List<Integer>)sss ).add(123_123_123);

        System.out.println("형변환한것을 다른 객체 담아 둔것 "+sss.get(0));
        System.out.println("캐스팅 변환한것을 객체 전체에게 알려주고 한것 "+sss.get(1));

        /**
         * 결론적으로 와일드카드는 어떠한 제네릭도 될수있다는 표현으로서, 해당 제네릭을 특정짓지 않더라도 모르더라도 받아줄수있다.
         *
         * 제네릭 : 지금은 이 타입을 모르지만, 이 타입이 정해지면 그 타입 특성에 맞게 사용할 것이다.
         *      제네릭 타입에 관련된 파라미터를 받는 메소드들도 사용할 수 있다.
         *
         * 와일드 카드 : 나는 전혀 관심이 없다. 즉, 지금도 이 타입을 모르고 앞으로도 모를 것이다.
         *      와일드 카드 타입에 관련된 파라미터를 받는 메소드들은 사용할 수 없다.
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
