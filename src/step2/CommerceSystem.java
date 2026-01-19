package step2;

import java.util.Scanner;

public class CommerceSystem {
    Category category = new Category();

    void start(Scanner sc){
        int sign;

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            category.printCategoryList();
            System.out.println("0. 종료             | 프로그램 종료");

            sign = sc.nextInt();
            if(sign ==0 ){
                System.out.println("커머스 플랫폼을 종료합니다.");
                break;
            }

            switch (sign){
                case 1:
                    category.getProductList(category.elecProduct);
                    break;

                case 2:
                    category.getProductList(category.clothProduct);
                    break;

                case 3:
                    category.getProductList(category.foodProduct);
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    break;

            }
        }
    }



}
