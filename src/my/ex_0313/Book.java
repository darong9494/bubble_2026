package my.ex_0313;

/**
 *  Object 클래스는 모든 클래스의 최상위 클래스다.
 */

public class Book {

    private String title;
    private String author;
    private int bookTypeId;


    // 생성자
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        // 물리적 주소 비교가 아니라 논리적으로 같다라고 판별하고 싶다면, 자식 클래스에서 재정의해야함.
        // 만약 책 제목이 같다면 논리적으로 같은 책이라고 판단하고 ture반환, 아니라면 false
        if(obj instanceof Book) {
            // Book 타입 먼저 확인
            // 제목.equals ("제목")
            Book targetBook = (Book) obj;
            // 데미안 equals 데미안
            if(this.title.equals(targetBook.title)) {
                //만약 이 타이틀이 타겟북의 제목과 같다면?
                return true;
                // true를 반환해라.
            }
        }
        return false;
        // true가 아니라면 false를 반환해라.
    }

    // equals()를 재정의 할때 해시코드 메소드를 함께 재정의 해야 의도치 않은 결과를 예방함
    // hashCode는 equals() 가 true를 반환하는 두 객체는 반드시 동일한 hashCode()를 반환해야 하므로
    // equals() 재정의 할 때 반드시 hashCode 오버라이딩 해줘야함
    @Override
    public int hashCode() {
        return bookTypeId;
    }

    public void showInfo() {
        //... 상태값 보이게 하는거
    }

    @Override
    public String toString() {
        return "제목: " + title + ", " + "저자: " + author;
    }

    // 테스트 코드
    public static void main(String[] args) {
        Book book = new Book("데미안","헤르만헤세");
        // Book 안에 title과 author을 생성했으니까 new Book 에는 타이틀과 저자를 입력해야한다.
        System.out.println(book.toString());
        // book 안에 toString 불러와서 입력시킴

        Book book1 = new Book("데미안", "헤르만헤세");
        Book book2 = new Book("데미안", "헤르만헤세");
        Book book3 = book1;

        System.out.println(book1);
        System.out.println(book2);
        if(book1 == book2) {
            System.out.println("Heap 메모리 영역에 같은 객체를 가리키고 있다.");
        } else {
            System.out.println("서로 다른 주소를 가지고 있다. 다른 객체");
        }

        // Object클래스의 메소드인 equals()를 사용해서 각 객체의 주소값을 비교해보자.
        boolean result1 = book1.equals(book2);
        // 결과는 북1과 북2가 같으면 참, 아니면 거짓.
        System.out.println("결과 book1과 book2의 논리적인 비교 결과: " + result1);

        boolean result2 = book1.equals(book3);
        // 결과는 북1과 북3이 같으면 참, 아니면 거짓.
        System.out.println("결과 book1과 book3의 논리적인 비교 결과: " + result2);
    }


}

