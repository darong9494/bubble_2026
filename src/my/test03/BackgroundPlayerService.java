package my.test03;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * [클래스 역할] - 플레이어 충돌 감지 서비스 (백그라운드에서 계속 돌아감) - 메인 스레드는 너무 바쁨
 */
public class BackgroundPlayerService implements Runnable {
    // ImageIcon / Image
    // 화면에 그려서 보여주기 위한 이미지
    // >> 픽셀 데이터에 직접 접근이 불가능하다.
    // BufferedImage
    // 메모리에 픽셀 배열로 저장된 이미지
    // getRGB(x,y)로 특정 좌표의 색상값을 직접 읽을 수 있다.
    private BufferedImage image; // 픽셀 이미지를 담는다.
    private Player2 player2; // 플레이어를 불러와야함

    // 의존성 주입 DI(Dependency Injection)
    // player를 생성자를 통해서 외부에서 주입받는다.
    // 이 서비스가 직접 플레이어를 생성하지 않고 외부에서 주입 받아 사용됨
    public BackgroundPlayerService(Player2 player2) {
        this.player2 = player2;
        try {
            // try = 예외처리
            image = ImageIO.read(new File("img/backgroundMapService.png"));
            // ImageIO.read = 입출력 파일을 코드안으로 들고올수있다.는 뜻
        } catch (IOException e) { // 파일이 없을수도 있으니 예외처리를 해주어야한다.
            throw new RuntimeException("충돌 감지 이미지를 찾을 수 없습니다." + e);
        }
    }

    @Override
    public void run() {
        // 게임 끝날 때 까지 계속 실행 되어야한다는 뜻
        while (true) {
            // 왼쪽색깔이
            Color leftColor = new Color(image.getRGB(player2.getX(), player2.getY() + 25));
            Color rightColor = new Color(image.getRGB(player2.getX() + 60, player2.getY() + 25));

            // 왼쪽 벽 충돌 감지 판단
            if (isRed(leftColor)) {
                // 만약 이즈레드가 왼쪽색깔이라면...
                // 충돌 상태 변수 ON
                player2.setLeftWallCrash(true);
                player2.setLeft(false); // while(false) Player2에서 플래그 설정해줬을때 다 false해둔게
                // 최초 다 멈춰있는상태이고 이동 멈춤 / Thread 종료됨
            } else {
                // 벽에서 벗어나면 즉시 해제 >> 다시 이동 가능하게 설정
                player2.setLeftWallCrash(false);
            }

            // 오른쪽 벽 충돌 감지 판단
            if(isRed(rightColor)) {
                // 충돌 상태 변수 ON
                player2.setRightWallCrash(true); // 충돌 상태 ON
                player2.setRight(false); // while(right) 종료 false가 멈춤이니까. /Thread 종료
            } else {
                player2.setRightWallCrash(false); // 벽에서 벗어나면 즉시 해제
            }

            try {
                // 예외처리
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // 255,0,0 >> 빨간색
    private boolean isRed(Color color) {
        return color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0;
    }
}