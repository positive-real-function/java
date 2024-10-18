import java.util.Scanner;

/**
 * @author Xiaojin
 * @date2022/10/17
 */
public class Harmonic_series {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        System.out.print("please input the number n:");
        int n = cin.nextInt();
        float sum=0;

        for (int i = 1; i <= n; i++) {
            sum+= 1.0/i;
        }

        System.out.println(sum);
    }
}
