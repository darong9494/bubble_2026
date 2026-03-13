package my.test03;

import javax.swing.*;

public class Player2 extends JLabel implements Moveable2 {
    // 플레이어 현재 좌표 상태 값
    private int x;
    private int y;

    // 좌우 방향 이미지 (방향키에 따라서 이미지 전환 시키는 그림들 넣기)
    private ImageIcon playerR; // 오른쪽
    private ImageIcon playerL; // 왼쪽

    // 속도 상수
    private final int SPEED = 4; // 좌우 이동 속도 (픽셀)
    private final int JUMP_SPEED = 2; // 점프/ 낙하 속도
    private final int JUMP_HEIGHT = 130; // 점프 최대 높이

    // 이동 상태 플래그 >> 최초의 프레임에서의 동작은 멈춰있으므로 모두 false가 되는것이다.
    // true = 해당 방향으로 이동중 (while 루프 조건)
    // false = 멈춤 (while 루프탈출 >> Thread 종료)
    private boolean left = false;
    private boolean right = false;
    private boolean up = false;
    private boolean down = false;

    // 벽 충돌 상태 플래그
    private boolean leftWallCrash;
    private boolean rightWallCrash;

    ///  getter
    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isLeftWallCrash() {
        return leftWallCrash;
    }

    public boolean isRightWallCrash() {
        return rightWallCrash;
    }


    /// setter
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeftWallCrash(boolean leftWallCrash) {
        this.leftWallCrash = leftWallCrash;
    }

    public void setRightWallCrash(boolean rightWallCrash) {
        this.rightWallCrash = rightWallCrash;
    }


    public Player2() {
        initData();
        setInitLayout();
    }

    private void initData() {
        // 데이터에 그림을 집어 넣을거야
        playerR = new ImageIcon("img/playerR.png"); // 오른쪽 그림 집어넣기
        playerL = new ImageIcon("img/playerL.png"); // 왼쪽 그림 집어넣기
    }


    private void setInitLayout() {
        // 캐릭터 처음에 어디에 위치할건지 정할거야
        x = 55; // x, y 좌표는 Player가 JLabel이기 때문에 프레임 오는 위치에 생성되어라는 뜻
        y = 535;
        setSize(50, 50); // 캐릭터 가로 세로 크기는 이렇게 할거야
        setLocation(x, y); // 이 프레임 setLocation x,y 좌표에다가 표시하겠다는 뜻이다.
    }


    // 메소드 실행 시킬거임
    @Override
    public void left() {
        if (left) {
            return; // 이미 이동중이면 중복 Thread 생성방지
        }
        left = true;
        setIcon(playerL);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // left가 true인 동안 계속 이동
                // keyReleased에서 setLeft(false)가 호출이 되면 while 탈출
                while (left) {
                    x = x - SPEED;
                    setLocation(x, y);
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        }).start();

    }

    @Override
    public void right() {
        if(right) {
            return;
        }
        right = true;
        setIcon(playerR);

    }

    @Override
    public void up() {

    }

    @Override
    public void down() {

    }
}
