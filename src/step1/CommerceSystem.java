package step1;

import java.util.Scanner;

public class CommerceSystem {

    void start(Scanner sc){
        ProductList l = new ProductList();

        int sign;

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 - 전자제품 ]");
            l.getProduct();
            System.out.println("0. 종료             | 프로그램 종료");

            sign = sc.nextInt();

            if (sign == 0){
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }
        }
    }



}
