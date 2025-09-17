import java.util.Random;

public class test {
    public static void main(String[] args) {

        Random rand = new Random();

        char[] value = new char[3];

        char[] line = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        for (int a = 0; a < 3; a++) {
            int ranint = rand.nextInt(9);
            char aa = line[ranint];
            value[a] = aa;
        }

        for (int b = 0; b < value.length; b++) {
            System.out.print(value[b] + " ");
        }
    }
}
