import java.util.Random;
import java.util.Scanner;

public class AppleGame {
    public static void main(String[] args) {

        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        int score = 0;

        // 화면에 출력될 10x10 배열 생성 후 출력

        Integer[][] apple = new Integer[10][10];

        for (int i = 0; i < apple.length; i++) {
            for (int y = 0; y < apple[0].length; y++) {
                apple[i][y] = rand.nextInt(9) + 1; // 1~9 사이의 정수 생성
            }
        }

        // 출력된 화면 기준으로 합 10을 만들어 공백으로 만듬

        do {

            int sum = 0;

            printBoard(apple);
            System.out.print("점수 : " + score + ", 좌표를 입력하세요 ex)0,A,1,B : ");
            String in = input.nextLine();

            if (in.length() == 7) {
                String[] inputs = in.split(",");
                if (inputs.length == 4) {

                    int num = calculate(inputs[1].charAt(0));
                    int num2 = calculate(inputs[3].charAt(0));
                    int num1 = Integer.parseInt(inputs[0]);
                    int num3 = Integer.parseInt(inputs[2]);

                    int rowStart = Math.min(num, num2);
                    int rowEnd = Math.max(num, num2);
                    int colStart = Math.min(num1, num3);
                    int colEnd = Math.max(num1, num3);

                    for (int a = colStart; a <= colEnd; a++) {
                        System.out.println(a);
                        for (int b = rowStart; b <= rowEnd; b++) {
                            System.out.println(b);
                            sum = apple[b][a] + sum;
                        }
                    }

                    if (sum == 10) {
                        for (int a = colStart; a <= colEnd; a++) {
                            for (int b = rowStart; b <= rowEnd; b++) {
                                if (apple[b][a] != 0) {
                                    score++;
                                    apple[b][a] = 0;
                                }
                            }
                        }
                    } else {
                        System.out.println("합이 10이 되지 않습니다. 다시 입력해주세요.");
                        continue;
                    }
                }else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                continue;
            }

        } while (score < 50);

        input.close();
    }

    public static int calculate(char a) {

        return (a - 65);
    }

    public static void printBoard(Integer[][] apple) {

        System.out.print("  ");
        for (int a = 0; a < 10; a++) {
            System.out.print(a + " ");
        }
        System.out.println();

        for (int i = 0; i < apple.length; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < apple[0].length; j++) {
                System.out.print((apple[i][j] == 0 ? "_ " : apple[i][j] + " "));
            }
            System.out.println();
        }
    }
}