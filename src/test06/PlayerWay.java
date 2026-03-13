package test06;

/**
 * [enum] 플레이어의 방향 상태
 * enum을 사용하는 이유
 * -boolean 두개 (isLeft, isRight)로 방향을 관리하면
 * 둘다 true가 되는 잘못된 상태가 생길 수 있음
 * enum은 정해진 값 중 하나만 가질 수 있어서 안전함
 * 사용방법
 * PlayerWay = PlayerWay.LEFT // 값 설정
 * if(playerWay -- PlayerWay.LEFT) {} // 값 비교
 * 왜 사용?
 * 나의 프로젝드나 논리 안에서 데이터 범위를 지정하고 싶을때 사용
 * 범위를 지정한다. A~Z 중에서 A,B,C,D 만 쓸때
 */
public enum PlayerWay {
    LEFT, RIGHT
}
