package step2;

import java.util.Scanner;

public class Input {
    private Scanner sc;

    public Input(Scanner sc){
        this.sc = sc;
    }

    public String nextLine(){
        return sc.nextLine();
    }

    public int nextInt(){
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }
}
