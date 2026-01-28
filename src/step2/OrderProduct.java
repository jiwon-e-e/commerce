package step2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OrderProduct {
    // ---------- 속성 ----------
    //Scanner sc = new Scanner(System.in);
    Scanner sc = Main.sc;
    Category category = Category.getInstance();
    private List<Basket> basket = new ArrayList<>();
    private Customer customer;

    // ============ 임시 고객 만들기 ============
    Customer me = new Customer("권지원", "jiwonee@example.com", 300000);
    // =======================================

    // ---------- 생성자 ---------- 기본생성자 사용

    // ---------- 기능 ----------
    public List<Basket> getBasket() {
        return basket;
    }

    public int getQuantityByProduct(Product p){
        for (Basket b : basket){
            if (b.getProduct().equals(p)){
                return b.getQuantity();
            }
        }
        return 0;
    }

    /**
     * basket 에 담긴 상품과 해당 상품의 개수를 출력합니다.
     * @return boolean : 장바구니가 비었다면 false, 하나라도 들었다면 true
     */
    public boolean printBaskets() {

        if (basket.size()==0){
            System.out.println("장바구니에 아무것도 없네요! 쇼핑해보세요 ㅎㅎ");
            return false;
        }
        System.out.println("아래와 같이 주문하시겠습니까?");
        System.out.println("[ 장바구니 내역 ]");

        for (Basket b : basket) {
            System.out.printf("%15s | %4d 개 | 각 %d 원\n", b.getProduct().getpName(), b.getQuantity(), b.getProduct().getpPrice());
        }
        return true;
    }

    //장바구니에 Product p num개 추가

    /**
     * 장바구니에 상품(p) (num) 개를 추가합니다
     * @param p 장바구니에 추가할 상품
     * @param num 해당 상품 개수
     */
    public void addProductToBasket(Product p, int num){
        if (category.chkStock(p,num)){

            for (Basket b : basket){
                if (b.getProduct().equals(p)){
                    b.setQuantity(num);
                    return;
                }
            }

            Basket newBasket = new Basket(p,num);
            basket.add(newBasket);
        }
    }

    /**
     * 장바구니에 담긴 상품(p) 를 삭제합니다.
     * p를 기준으로 basket 을 찾아 basketList 에서 삭제함
     * @param p 삭제할 상품
     */
    public void rmProductFromBasket(Product p){
        Basket rmBasket = null;
        for (Basket b : basket){
            if (b.getProduct().equals(p)){
                rmBasket = b;
                break;
            }
        }
        basket.remove(rmBasket);
    }

    /**
     * 장바구니 내역 확인 후 주문
     * 주문 확정시 금액출력, 장바구니 비우기, 총계 출력, Product재고 차감
     */
    void checkBasket(){
        if(printBaskets()){
            System.out.println("[ 총 주문 금액 ]");
            int sum = 0;
            for (Basket b: getBasket()){
                sum += b.getProduct().getpPrice() * b.getQuantity();
            }
            System.out.println("할인 전 금액: " +sum+"원");
            int sale = (sum* me.getRank().sale)/100;
            int finalPrice = sum - sale;
            System.out.println(me.getRank() + "등급 할인("+me.getRank().sale+"%): -"+sale+"원");
            System.out.println("최종 결제 금액: "+finalPrice+"원");


            System.out.println("1. 주문 확정      2. 메인으로 돌아가기");
            int sign = sc.nextInt();
            sc.nextLine();

            if (sign ==1){
                for (Basket b: getBasket()){
                    category.minusStock(b.getProduct(), b.getQuantity());
                }
                System.out.println("주문이 완료되었습니다! 총 금액: "+finalPrice+"원");
                plusTotalPrice(me, finalPrice);
                getBasket().clear();
            }
        }
    }

    /**
     * 고객의 total price 업데이트
     * @param c 고객 객체
     * @param price 추가된 금액
     */
    public void plusTotalPrice(Customer c, int price){
            c.setTotalPrice(price);
        }

}
