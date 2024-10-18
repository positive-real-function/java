/**
 * @author Xiaojin
 * @date2022/10/16
 */
import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {

        Scanner cin = new Scanner(System.in);
        //输入数字
        System.out.print("donnez un nombre positif:");
        int number = cin.nextInt();

        while (number != 0) {
            if (number < 0) {
                System.out.println("svp positif");
            } else {
                //求平方根
                double sqrt = (double) Math.sqrt(number);
                System.out.println(sqrt);
            }

            System.out.print("donnez un nombre positif:");
            number = cin.nextInt();
        }
    }
}
