package step2;

import java.util.Scanner;

public class Main {
    //public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Input input = new Input(s);
        CommerceSystem commerce = new CommerceSystem(input);

        commerce.start();

        s.close();
    }
}
