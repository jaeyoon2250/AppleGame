import java.util.Scanner;

public class test1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("로딩 중입니다. ");
        Thread.sleep(1000); // 1초 대기
        System.out.print("\r로딩 중입니다. ."); // 커서를 맨 앞으로 이동
        Thread.sleep(1000);
        System.out.print("\r로딩 중입니다. ..");
        Thread.sleep(1000);
        System.out.print("\r로딩 중입니다. ...");
        Thread.sleep(1000);
        System.out.println("로딩 완료!"); // 완료 메시지 출력 후 줄바꿈
    }
}