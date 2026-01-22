package step2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Administrator {

    // ---------- 속성 ----------
    Category category = Category.getInstance();
    OrderProduct o;
    private String PassWord = "admin123";
    Scanner sc = new Scanner(System.in);

    // ---------- 생성자 ----------
    public Administrator(){
    }


    // ---------- 기능 ----------

    /**
     * @param pw: 사용자가 입력하는 pw
     * @return 기본 PassWord와 사용자 입력이 동일하면 true 반환
     */
    public boolean chkPW (String pw){
        if (pw.equals(PassWord)) return true;
        else{
            System.out.print("다시 입력해주세요: ");
            return false;
        }
    }

    /**
     * Admin 시작하면 출력되는 메뉴 정의
     */
    public void printAdminMenu(){
        System.out.println(" [ 관리자 모드 ]");
        System.out.println("1. 상품 추가");
        System.out.println("2. 상품 수정");
        System.out.println("3. 상품 삭제");
        System.out.println("4. 전체 상품 현황");
        System.out.println("0. 메인으로 돌아가기");
    }

    /**
     * 상품명, 가격, 설명, 재고를 입력받아 new Product 만들고 반환
     * 카테고리명은 어느 카테고리에 추가되는지 알려주는 용도
     * @param categoryName
     * @return 새로 만든 Product 반환
     */
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

    /**
     * 상품추가 재확인 후 addProductWithCategoryName 호출
     * @param categoryName 실제 Map 에 추가할 함수의 매개변수로 전달
     * @param p 추가될 product
     */
    public void checkAndAddProduct(String categoryName, Product p){
        p.printProduct();
        System.out.println("위 정보로 상품을 추가하시겠습니까?");
        System.out.println("1. 확인      2. 취소");
        while (true){
            int chk = sc.nextInt();
            if (chk==1){
                //this.category.addProductWithCategoryName(categoryName, p);
                this.category.getCategoryMap().get(categoryName).add(p);
                System.out.println("상품이 성공적으로 추가되었습니다!");
                break;
            }else{
                System.out.println("상품추가를 취소합니다.");
                break;
            }
        }
    }

    /**
     * 관리자가 상품 추가 메뉴 선택시 실행
     * makeNewProduct 호출해서 상품 만들고,
     * checkAndAddProduct 호출해서 더블체크 후 추가
     */
    public void adminAddProduct(){
        //sc.nextLine();
        System.out.println("어떤 카테고리에 상품을 추가하시겠습니까?");
        category.printCategoryList();
        int num = sc.nextInt();
        String categoryName = category.getCategoryList(num-1);
        Product newProduct = makeNewProduct(categoryName);
        checkAndAddProduct(categoryName, newProduct);
    }

    /**
     * 관리자가 상품 수정 메뉴 선택시 실행
     * 수정할 상품명을 입력받아 Product 찾기 (Product의 pName 은 모두 고유하다고 가정)
     * productName 이 없을 경우를 대비해 Optional 로 받고 풀어주기
     * 현재 상품 정보를 출력 후 수정할 항목 입력받기
     * switch 문을 돌며 setter 함수 호출하여 수정
     * @return
     */
    public int adminFixProduct(){
        //sc.nextLine();
        System.out.print("수정할 상품명을 입력해주세요: ");
        String pNameToFix = sc.nextLine();
        Optional<Product> productToFixBeforeChk = category.getProductByPName(pNameToFix);

        if(productToFixBeforeChk.isEmpty()){
            System.out.println("유효한 상품명이 아닙니다.");
            return 1;
        }
        Product productToFix = productToFixBeforeChk.orElseThrow();
        System.out.print("현재 상품 정보: ");
        productToFix.printProduct();

        System.out.println("수정할 항목을 선택해주세요:");
        System.out.println("1. 가격\n2. 설명\n3. 재고수량\n4. 취소");
        int num = sc.nextInt();
        sc.nextLine();

        if (num==1){
            System.out.println("현재 가격: "+productToFix.getpPrice());
            System.out.print("새로운 가격을 입력해주세요: ");
            productToFix.setpPrice(CheckPriceAndStock());
        } else if (num==2){
            System.out.println("현재 설명: "+productToFix.getpDescription());
            System.out.print("새로운 설명을 입력해주세요: ");
            String str = sc.nextLine();
            productToFix.setpDescription(str);
        } else if (num ==3){
            System.out.println("현재 재고: "+productToFix.getpStock());
            System.out.print("변경된 재고를 입력해주세요: ");
            productToFix.setpStock(CheckPriceAndStock());
        } else if (num ==4){
            System.out.println("취소했습니다. ");
        } else{
            System.out.println("잘못된 입력입니다.");
        }
        return 0;
    }

    /**
     * 관리자가 상품 삭제 메뉴 선택시 실행
     * 삭제할 상품명을 입력받아 Product 찾기 (Product의 pName 은 모두 고유하다고 가정)
     * productName 이 없을 경우를 대비해 Optional 로 받고 풀어주기
     * 상품 삭제 후, 장바구니에 담긴 동일 Product 도 삭제
     */
    public void adminRmProduct(){

        System.out.println("category Instance: "+ category);
        System.out.println("sc = " + sc);
        System.out.println("this = " + this);

        sc.nextLine();
        System.out.print("삭제할 상품명을 입력해주세요: ");
        String pNameToDelete = sc.nextLine();
        Optional<Product> productToDeleteBeforeChk = category.getProductByPName(pNameToDelete);

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
                for (List<Product> list: category.getCategoryMap().values()){
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

    /**
     * 카테고리명 별 전체 상품 현황을 출력
     */
    public void adminPrintProduct(){
        System.out.println("[ 전체 상품 현황 ]");
        for (String str: category.getKeyList()){
            System.out.println(" [ 카테고리 명: "+str+" ]");
            category.printProductsByList(category.getProductByCategory(str));
        }
    }

    /**
     * 상품 수정 시 재고 또는 가격 부분에서 0 또는 음수가 입력되었는지 확인
     * 정상적인 입력이라면 해당 입력 반환
     * @return
     */
    public int CheckPriceAndStock(){
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
