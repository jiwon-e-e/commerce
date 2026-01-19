package step1;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CommerceSystem commerce = new CommerceSystem();

        commerce.start(sc);

        sc.close();
    }
}
