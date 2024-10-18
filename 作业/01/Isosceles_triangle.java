import java.util.Scanner;
/**
 * @author Xiaojin
 * @date2022/10/17
 */
public class Isosceles_triangle {
    public static void main(String[] args) {
        Scanner cin =new Scanner(System.in);

        System.out.println("combien de lignes ?");
        int n = cin.nextInt();
        String x="*";
        System.out.println();
        for (int i=1;i<=n;i++)
        {
            for(int j=1;j<=n-i;j++){
                System.out.print(" ");
            }
            for(int k=1;k<=2*i-1;k++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
