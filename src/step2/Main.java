package step2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommerceSystem commerce = new CommerceSystem(sc);

        commerce.start();

        sc.close();
    }
}
