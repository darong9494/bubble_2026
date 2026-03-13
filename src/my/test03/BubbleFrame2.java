package my.test03;

import test01.BubbleFrame;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BubbleFrame2 extends JFrame {

    private JLabel backgroundMap; // 바탕 그림을 지정해줄거야
    private Player2 player2; // 플레이어를 넣어줄거임

    public BubbleFrame2() {
        initData();
        setInitLayout();
        addEventListener();
        // 충돌 감시 백그라운드 서비스 시작

    }

    private void initData() {
        // 데이터에 넣을거 만들기
        setTitle("Bubble Bubble"); // 페이지 이름 지정하기
        setSize(1000, 640); // 창 크기 지정하기
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 닫히게 하는거
        //-----------------------------------
        backgroundMap = new JLabel(new ImageIcon("img/backgroundMap.png")); // 그림 넣어주기
        setContentPane(backgroundMap); // add. 한거를 여기서 바로붙이려고 만든거임

        player2 = new Player2();
    }

    private void setInitLayout() {
        setLayout(null); // 좌표값으로 지정할거야
        setResizable(false); // 창 크기 고정할거야
        setLocationRelativeTo(null); // 화면 정중앙에 배치할거다 프레임을

        backgroundMap.add(player2);
        setVisible(true); // 화면에 표시되게 할거다
    }

    private void addEventListener() {
        this.addKeyListener(new KeyAdapter() {
            // KeyAdapter = 필요한 오버라이드만 불러내서 사용할때 쓰는것.
            @Override
            public void keyPressed(KeyEvent e) {
                // 방향키 코드를 Player.의 이동 메소드 연결
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        // 이동중이 아니고 AND 벽에 충돌하지 않은 상태일 때만 left() 호출가능
                    if(player2.isLeft() == false && player2.isLeftWallCrash() == false) {
                        player2.left();
                    }
                    break;
                    case KeyEvent.VK_RIGHT:
                        // 이동중이 아니고 AND 벽에 충돌하지 않은 상태일 때만 right() 호출가능
                        if(player2.isRight() == false && player2.isRightWallCrash() == false) {
                            player2.right();
                        }
                        break;
                    case KeyEvent.VK_UP:
                        player2.up();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        player2.setLeft(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        player2.setRight(false);
                        break;
                }
            }
        });
    }

    // 테스트 코드
    public static void main(String[] args) {
        new BubbleFrame();
    }
}
