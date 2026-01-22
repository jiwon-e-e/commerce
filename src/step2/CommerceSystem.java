package step2;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {
    Category category = Category.getInstance();
    Scanner sc;
    OrderProduct orderProduct = new OrderProduct();

    // ============ 임시 고객 만들기 ============
    Customer me = new Customer("권지원", "jiwonee@example.com", 300000);
    // =======================================

    //commerce System 생성자
    CommerceSystem(Scanner sc){
        this.sc = sc;
    };

    // 수정 필요: Main 플랫폼에서 선택
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

                    if(sign ==0 ){
                        System.out.println("커머스 플랫폼을 종료합니다.");
                        chk = 1;
                        break;
                    }

                    if (sign>=1 && sign <= 7){
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

            switch (sign){
                case 1:
                    //category.getCategoryList.get(0)
                    ifChooseCategory(category.getCategoryList(0));
                    break;

                case 2:
                    ifChooseCategory(category.getCategoryList(1));
                    break;

                case 3:
                    ifChooseCategory(category.getCategoryList(2));
                    break;
                case 4:
                    System.out.println("아래와 같이 주문하시겠습니까?\n");
                    checkBasket();
                    break;
                case 6:
                    adminLogin();
                    break;

                case 7:
                    System.out.println(" [ 내 정보 ]");
                    me.getCustomer();

                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                    break;

            }


        }
    }

    //Category 를 보고싶을 때 (start에서 getCategoryList 를 사용하여 categoryName 전달)
    void ifChooseCategory(String categoryName){
        //System.out.println("categoryName = " + categoryName);
        List<Product> products = category.getProducts(categoryName);
        //System.out.println("products: "+products);

        if (products == null || products.isEmpty()){
            System.out.println("상품이 없어요");
        }else{
            category.ListtoString(products);
        }
        inputNum(products);
    }

    //카테고리 목록 중에서 원하는 Product 입력받아 상세정보 출력, 장바구니에 추가할건지 AddBasket 호출
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
        System.out.print(idx);
        p.printProduct(p);
        ifAddBasket(p);
    }

    //Product 정보를 받아서 추가하기 , 주문 클래스 (OrderProduct)의 addProduct 호출
    void ifAddBasket(Product p){
        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
        System.out.println("1. 확인       2. 취소");

        while (true){
            try{
                int sign = sc.nextInt();
                if (sign !=1 && sign != 2){
                    System.out.println("알맞은 숫자만 입력해주세요.");
                    continue;
                }
                if (sign == 1){
                    System.out.println("담을 수량을 입력해주세요.");
                    int quantity = sc.nextInt();
                    orderProduct.addProduct(p, quantity);
                    break;
                }else{
                    break;
                }

            }catch(InputMismatchException e){
                System.out.println("숫자만 입력해주세요.");
            }
        }
    }

    //장바구니 내역 확인
    //주문 확정시 금액출력, 장바구니 비우기, 총계 출력, Product재고 차감
    void checkBasket(){
        System.out.println("[ 장바구니 내역 ]");
        orderProduct.printBaskets();

        System.out.println("[ 총 주문 금액 ]");
        int sum = 0;
        for (Basket b: orderProduct.getBasket()){
            sum += b.getProduct().getpPrice() * b.getQuantity();
        }
        System.out.println(sum+"원");

        System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
        int sign = sc.nextInt();

        if (sign ==1){
            for (Basket b: orderProduct.getBasket()){
                orderProduct.minusStock(b.getProduct(), b.getQuantity());
            }
            System.out.println("주문이 완료되었습니다! 총 금액: "+sum+"원");
            orderProduct.plusTotalPrice(me, sum);
            orderProduct.getBasket().removeAll(orderProduct.getBasket());
        }
    }

    void adminLogin(){
        Administrator a = new Administrator(sc);
        System.out.print("관리자 비밀번호를 입력해주세요:");

        int chk = 0;

        for (int i= 0; i<3; i++){
            String pw = sc.next();
            if (a.chkPW(pw)){
                System.out.println("관리자 로그인 완료 ! ! ");
                chk =1;
                break;
            }else{
                System.out.println("비밀번호가 틀렸습니다."+(i+1)+"/3");
            }
        }

        if (chk==1){
            int sign;
            while (true){
                a.printAdminMenu();
                sign = sc.nextInt();
                if (sign == 0) break;

                if (sign<1 || sign>4){
                    System.out.println("메뉴 번호를 입력해주세요.");
                    continue;
                }

                switch(sign){
                    case 1:
                        System.out.println("어떤 카테고리에 상품을 추가하시겠습니까?");
                        category.printCategoryList();
                        int num = sc.nextInt();
                        String categoryName = category.getCategoryList(num-1);
                        Product newProduct = a.makeProduct(categoryName);
                        a.addProduct(categoryName, newProduct);
                        break;

                    case 2:
                        System.out.print("수정할 상품명을 입력해주세요: ");

                        break;

                    case 3:
                        System.out.print("삭제할 상품명을 입력해주세요: ");

                        break;
                    case 4:
                        System.out.println("[ 전체 상품 현황 ]");
                        for (String str: category.getCategoryList()){
                            System.out.println(" [ 카테고리 명: "+str+" ]");
                            category.ListtoString(category.getProducts(str));
                        }

                        break;
                }
            }

        }

    }
}
