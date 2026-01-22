package step2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Administrator {
    Category c = Category.getInstance();
    OrderProduct o;
    private String PassWord = "admin123";
    Scanner sc = new Scanner(System.in);

    public Administrator(){
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
                c.addProductWithCategoryName(category, p);
                System.out.println("상품이 성공적으로 추가되었습니다!");
                break;
            }else{
                System.out.println("상품추가를 취소합니다.");
                break;
            }
        }
    }


    public void adminAddProduct(Administrator a){
        //sc.nextLine();
        System.out.println("어떤 카테고리에 상품을 추가하시겠습니까?");
        c.printCategoryList();
        int num = sc.nextInt();
        String categoryName = c.getCategoryList(num-1);
        Product newProduct = a.makeNewProduct(categoryName);
        a.addProduct(categoryName, newProduct);
    }

    public int adminFixProduct(){
        //sc.nextLine();
        System.out.print("수정할 상품명을 입력해주세요: ");
        String pNameToFix = sc.nextLine();
        Optional<Product> productToFixBeforeChk = c.getProductByPName(pNameToFix);

        if(productToFixBeforeChk.isEmpty()){
            System.out.println("유효한 상품명이 아닙니다.");
            return 1;
        }
        Product productToFix = productToFixBeforeChk.orElseThrow();
        System.out.print("현재 상품 정보: ");
        productToFix.printProduct();

        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격\n2. 설명\n3. 재고수량\n4. 취소");
        int numToFix = sc.nextInt();
        //if (numToFix<1 || numToFix>3) continue;
        sc.nextLine();

        if (numToFix==1){
            System.out.println("현재 가격: "+productToFix.getpPrice());
            System.out.print("새로운 가격을 입력해주세요: ");
            productToFix.setpPrice(setPriceOrStockChk());
        } else if (numToFix==2){
            System.out.println("현재 설명: "+productToFix.getpDescription());
            System.out.print("새로운 설명을 입력해주세요: ");
            String str = sc.nextLine();
            productToFix.setpDescription(str);
        } else if (numToFix ==3){
            System.out.println("현재 재고: "+productToFix.getpStock());
            System.out.print("변경된 재고를 입력해주세요: ");
            productToFix.setpStock(setPriceOrStockChk());
        } else if (numToFix ==4){
            System.out.println("취소했습니다. ");
        } else{
            System.out.println("잘못된 입력입니다.");
        }
        return 0;
    }

    public void adminRmProduct(){

        System.out.println("category Instance: "+ c);
        System.out.println("sc = " + sc);
        System.out.println("this = " + this);

        sc.nextLine();
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String pNameToDelete = sc.nextLine();
        Optional<Product> productToDeleteBeforeChk = c.getProductByPName(pNameToDelete);

        if(productToDeleteBeforeChk.isEmpty()){
            System.out.println("유효한 상품명이 아닙니다.");
            return;
        }
        Product productToDelete = productToDeleteBeforeChk.orElseThrow();

        System.out.print("삭제할 상품 정보: ");
        productToDelete.printProduct();

        try {
            System.out.println("삭제하시겠습니까? (예: 1번)");
            int t = sc.nextInt();

            if (t==1){
                for (List<Product> list: c.getCategoryMap().values()){
                    try{
                        list.remove(productToDelete);
                        System.out.printf("상품 %s 가 삭제되었습니다.\n",productToDelete.getpName());
                        break;
                    }catch (Exception e){
                        System.out.println(e);
                    }
                }
                o.rmProductFromBasket(productToDelete);

            }else{
                System.out.println("삭제가 취소되었습니다.");
            }
        } catch (InputMismatchException e) {
            throw new InputMismatchException("숫자만 입력하시오. . .");
        }
    }

    public void adminPrintProduct(){
        System.out.println("[ 전체 상품 현황 ]");
        for (String str: c.getKeyList()){
            System.out.println(" [ 카테고리 명: "+str+" ]");
            c.printProductsByList(c.getProductByCategory(str));
        }
    }

    public int setPriceOrStockChk(){
        int temp;
        while(true){
            temp = sc.nextInt();
            if (temp<=0){
                System.out.println("0 또는 음수로 입력할 수 없습니다.");
                continue;
            }
            return temp;
        }
    }
}
