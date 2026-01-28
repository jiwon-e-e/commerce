package step2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    // ---------- 속성 ----------
    Category category = Category.getInstance();
    Scanner sc = Main.sc;
    OrderProduct orderProduct = new OrderProduct();
    Administrator a = new Administrator(orderProduct);
    Customer nowCustomer = orderProduct.me;


    // ---------- 생성자 ----------
    public CommerceSystem(){
    };

    // ---------- 기능 ----------

    /**
     * 메인메뉴 출력, 선택받아 각 메뉴 별 메소드 호출
     * 1~3: 카테고리 : ifChooseCategory
     * 4: 장바구니 확인 : checkBasket
     * 5. 구현 없음
     * 6. 관리자 모드 진입 : startAdmin
     * 7. 내 정보 출력 : getCustomer
     */
    void start(){
        int sign;

        while (true) {
            System.out.println(" [ 실시간 커머스 플랫폼 메인 ]");
            category.printCategoryList();
            System.out.println("0. 종료             | 프로그램 종료");
            System.out.println(" [ 주문 관리 ]");
            System.out.println("4. 장바구니 확인     | 장바구니를 확인 후 주문합니다.");
            System.out.println("5. 주문 취소        | 진행중인 주문을 취소합니다.");
            System.out.println("6. 관리자 모드      | 관리자 모드로 진입합니다.");
            System.out.println("7. 내 정보 확인     | 내 정보를 확인합니다. ");

            int chk=0;

            while(true){
                try{
                    System.out.print("메뉴 번호 입력: ");
                    sign = sc.nextInt();
                    sc.nextLine();

                    if(sign ==0 ){
                        System.out.println("커머스 플랫폼을 종료합니다.");
                        chk = 1;
                        break;
                    }else if (sign<1 && sign >7){
                        System.out.println("범위를 벗어난 입력입니다. ");
                    }else{
                        break;
                    }
                }catch (InputMismatchException e){
                    System.out.println("숫자만 입력해주세요.");
                    sc.nextLine();
                }
            }
            if (chk==1) break;

            switch (sign){
                case 1,2,3:
                    ifChooseCategory(category.getCategoryList(sign-1));
                    break;
                case 4:
                    orderProduct.checkBasket();
                    break;
                case 6:
                    int adminChk = adminLogin(a);
                    startAdmin(adminChk, a);
                    break;
                case 7:
                    System.out.println(" [ 내 정보 ]");
                    nowCustomer.getCustomer();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    //Category 를 보고싶을 때 (start에서 getCategoryList 를 사용하여 categoryName 전달)

    /**
     * 원하는 조건의 상품 리스트를 출력하고 해당 리스트 내부 상품 선택
     * (inputNum
     * @param categoryName
     */
    void ifChooseCategory(String categoryName){
        System.out.println(" [ "+categoryName+ " 카테고리 ]");
        System.out.println("1. 전체 상품 보기\n2. 가격대 필터링: 카테고리 평균 이상\n3. 가격대 필터링: 카테고리 평균 이하\n0.뒤로가기");

        List<Product> products=null;
        int chk = 0;
        while(true){
            int sign = 0;

            try {
                sign = sc.nextInt();
                //sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다.");
                sc.nextLine();
            }
            double averagePrice = category.getProductByCategory(categoryName).stream()
                    .mapToInt(p -> p.getpPrice()).average().orElseThrow();

            if (sign ==0){
                chk = 1;
                break;
            }
            else if (sign ==1){
                products = category.getProductByCategory(categoryName);
                break;
            }else if (sign ==2){
                products = category.getProductByCategory(categoryName).stream()
                        .filter(p -> p.getpPrice()>=averagePrice)
                        .toList();
                break;
            }else if (sign ==3){
                products = category.getProductByCategory(categoryName).stream()
                        .filter(p -> p.getpPrice()<=averagePrice)
                        .toList();
                break;
            }else{
                System.out.println("잘못된 입력입니다.");
                continue;
            }
        }

        if(chk==1) return;

        if (products == null || products.isEmpty()){
            System.out.println("상품이 없어요");
        }else{
            category.printProductsByList(products);
        }
        selectProductAndAddToBasket(products);
    }

    //카테고리 목록 중에서 원하는 Product 입력받아 상세정보 출력, 장바구니에 추가할건지 AddBasket 호출

    /**
     * List 에서 제품번호를 입력받고 해당 제품을 장바구니에 추가할건지 확인
     * ifAddBasket 호출
     * @param list
     */
    void selectProductAndAddToBasket(List<Product> list){
        int idx;
        while(true){
            System.out.print("확인하고 싶은 제품 번호를 입력해주세요: ");
            try{
                idx = sc.nextInt();
                sc.nextLine();
                if (idx>=1 && idx <= list.size()){
                    break;
                }else{
                    System.out.println("범위를 벗어난 입력입니다. ");
                }
            }catch (InputMismatchException e) {
                System.out.println("숫자만 입력해주세요.");
                sc.nextLine();
            }
        }
        Product p = list.get(idx-1);
        System.out.print(idx);
        p.printProduct();
        requestAddToBasket(p);
    }

    /**
     * 장바구니에 추가할건지 확인
     * 수량입력 받기
     * orderProduct 클래스의 addProductToBasket 호출
     * @param p 추가할 제품 객체
     */
    void requestAddToBasket(Product p){
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");

        while (true){
            try{
                int sign = sc.nextInt();
                sc.nextLine();
                if (sign !=1 && sign != 2){
                    System.out.println("알맞은 숫자만 입력해주세요.");
                    continue;
                }
                if (sign == 1){
                    System.out.println("담을 수량을 입력해주세요.");
                    int quantity;
                    while(true){
                        quantity = sc.nextInt();
                        if (quantity<=0){
                            System.out.println("담을 개수를 정확히 입력해주세요.");
                            continue;
                        }
                        break;
                    }
                    orderProduct.addProductToBasket(p, quantity);
                    sc.nextLine();
                    break;
                }else{
                    break;
                }
            }catch(InputMismatchException e){
                System.out.println("숫자만 입력해주세요.");
                sc.nextLine();
            }
        }
    }

    /**
     * 관리자를 입력받아 비밀번호 확인 받기, 3번틀리면 오류메시지 출력
     * @param a 관리자 객체
     * @return
     */
    int adminLogin(Administrator a) {

        System.out.print("관리자 비밀번호를 입력해주세요:");
        int chk = 0;

        for (int i = 0; i < 3; i++) {
            String pw = sc.next();
            if (a.chkPW(pw)) {
                System.out.println("관리자 로그인 완료 ! ! ");
                chk = 1;
                break;
            } else {
                System.out.println("비밀번호가 틀렸습니다." + (i + 1) + "/3");
            }
        }
        return chk;
    }

    /**
     * 관리자 메뉴 실행
     * 1: 추가, 2: 수정, 3: 삭제, 4: 현재 메뉴 확인
     * 관리자 비밀번호를 입력했는지 한 번 더 확인
     * @param chk 관리자 인증 통과했는지 확인
     * @param a 관리자 객체
     */
    void startAdmin(int chk, Administrator a){
        if (chk!=1) return;

        int sign;
        while (true){
            a.printAdminMenu();
            sign = sc.nextInt();
            if (sign == 0) break;

            if (sign<1 || sign>4){
                System.out.println("메뉴 번호를 입력해주세요.");
                continue;
            }
            sc.nextLine();

            switch(sign){
                case 1:
                    a.adminAddProduct();
                    break;

                case 2:
                    if (a.adminFixProduct() == 1) continue;
                    break;

                case 3:
                    a.adminRmProduct();
                    //orderProduct.rmProductFromBasket(productToDelete);
                    break;
                case 4:
                    a.adminPrintProduct();
                    break;
            }

        }
    }
}
