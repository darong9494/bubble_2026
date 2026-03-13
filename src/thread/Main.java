package thread;

public class Main {
    // 메인 스레드
    public static void main(String[] args) {
        // 최초 10만원을 가지고 있음
        BankAccount bankAccount = new BankAccount();

        Father father = new Father(bankAccount);
        Mother mother = new Mother(bankAccount);

        // 아버지가 먼저 입금을 함 (1만원)
        father.start();
        // 어머니가 장을 보기 위해 출금함 (5천원)
        mother.start();

        // 10만원 + 1만원 - 5천원 = 10만 5천원...

    } // end of main
}
