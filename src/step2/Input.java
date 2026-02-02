package step2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    private Scanner sc;

    public Input(Scanner sc){
        this.sc = sc;
    }

    public String nextLine(){
        return sc.nextLine();
    }

    public int nextInt() {
        try {
            int n = sc.nextInt();
            sc.nextLine();
            return n;
        } catch (InputMismatchException e) {
            sc.nextLine(); // 스캐너 비우기
            throw new IllegalArgumentException("숫자만 입력 가능");
        }
    }

}
