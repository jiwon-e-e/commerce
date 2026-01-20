package step2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    private Category category = new Category();
    Scanner sc;

    CommerceSystem(Scanner sc){
        this.sc = sc;
    };

    void start(){
        int sign;

        while (true) {
            System.out.println("[ 실시간 커머스 플랫폼 메인 ]");
            category.printCategoryList();
            System.out.println("0. 종료             | 프로그램 종료");

            int chk=0;

            while(true){
                try{
                    System.out.print("자세히 보고싶은 카테고리 번호를 입력해주세요: ");
                    sign = sc.nextInt();

                    if(sign ==0 ){
                        System.out.println("커머스 플랫폼을 종료합니다.");
                        chk = 1;
                        break;
                    }

                    if (sign>=1 && sign <= category.getCategoryMap().size()){
                        break;
                    }else{
                        System.out.println("범위를 벗어난 입력입니다. ");
                    }
                }catch (InputMismatchException e){
                    System.out.println("숫자만 입력해주세요.");
                    sc.next();
                }
            }
            if (chk==1) break;




            List<Product> nowCategory = new ArrayList<>();

            switch (sign){
                case 1:
                    nowCategory = category.getElecProduct();
                    break;

                case 2:
                    nowCategory = category.getClothProduct();
                    break;

                case 3:
                    nowCategory = category.getFoodProduct();
                    break;

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    break;

            }
            category.getProductList(nowCategory);
            inputNum(nowCategory);

        }
    }

    void inputNum (List<Product> list){
        int idx;
        while(true){
            System.out.print("확인하고 싶은 제품 번호를 입력해주세요: ");
            try{
                idx = sc.nextInt();
                if (idx>=1 && idx <= list.size()){
                    break;
                }else{
                    System.out.println("범위를 벗어난 입력입니다. ");
                }
            }catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                sc.next(); //잘못된 입력 제거
            }
        }
        Product p = list.get(idx-1);
        System.out.printf(idx+". %-15s |%,10d 원| 재고: %,3d 개 | %s\n",p.getpName(),p.getpPrice(),p.getpStock(),p.getpDescription());
    }
}
