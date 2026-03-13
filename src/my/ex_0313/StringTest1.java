package my.ex_0313;

public class StringTest1 {
    public static void main(String[] args) {
        // 스트링 클래스는 불변이다.
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        String str3 = new String("World");
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode()); // hashCode = 실제 메모리 긴 주소를 축약해서 쓰는거

        System.out.println(str1.concat(str3));
        //           Hello + World
        // str1과 str3 두 문자열을 연결해서 새로운 String 객체를 반환한다.

        String str4 = str1.concat(str3);
        //     str4 =   Hello + World
        System.out.println(str4);

        System.out.println(str1.hashCode());

        String str5 = new String("안녕");
        System.out.println("고유 주소 확인: " + System.identityHashCode(str5));
        // identityHashCode는 실제 메모리 엄청 긴 주소 고유값
        str5.concat("반가워");
        System.out.println("고유 주소 확인: " + System.identityHashCode(str5));
        // 한번 주소값 지정하면 변하지 않는다.
        // 그래서 고유값이 같다는 말이다.
    }
}
