package step2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Administrator {
    Category c = Category.getInstance();
    private String PassWord = "admin123";
    Scanner sc;
    ProductList pList = ProductList.getInstance();

    Administrator(Scanner sc){
        this.sc = sc;
    }

    public boolean chkPW (String pw){
        if (pw.equals(PassWord)) return true;
        else{
            System.out.print("다시 입력해주세요: ");
            return false;
        }
    }

    public void printAdminMenu(){
        System.out.println(" [ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    public Product makeNewProduct(String categoryName){
        System.out.println(" [ "+categoryName+"카테고리에 상품 추가 ]");

        while (true){
            sc.nextLine();
            try{
                System.out.print("상품명을 입력해주세요:");
                String pName = sc.nextLine();

                System.out.print("가격을 입력해주세요: ");
                int pPrice = sc.nextInt();
                sc.nextLine();

                System.out.print("상품 설명을 입력해주세요: ");
                String pDescription = sc.nextLine();

                System.out.print("재고를 입력해주세요: ");
                int pStock = sc.nextInt();
                sc.nextLine();

                Product p = new Product(pName, pPrice, pDescription, pStock);

                return p;
            }catch (InputMismatchException e){
                System.out.println("첨부터 제대로 입력하세요 -_-");
                sc.next();
            }
        }
    }

    public void addProduct (String category, Product p){
        p.printProduct();
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인      2. 취소");
        while (true){
            int chk = sc.nextInt();
            if (chk==1){
                pList.addProductWithCategoryName(category, p);
                System.out.println("상품이 성공적으로 추가되었습니다!");
                break;
            }else{
                System.out.println("상품추가를 취소합니다.");
                break;
            }
        }
    }
}
