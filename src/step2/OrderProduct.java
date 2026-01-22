package step2;

import java.util.ArrayList;
import java.util.List;

public class OrderProduct {
    Category category = Category.getInstance();
    private List<Basket> basket = new ArrayList<>();
    private Customer customer;

    public List<Basket> getBasket() {
        return basket;
    }

    public boolean printBaskets() {
        for (Basket b : basket){
            System.out.printf("%15s | %4d 개 | 각 %d 원\n", b.getProduct().getpName(), b.getQuantity(), b.getProduct().getpPrice());
        }
        if (basket.size()==0){
            System.out.println("장바구니에 아무것도 없네요! 쇼핑해보세요 ㅎㅎ");
            return false;
        }
        return true;
    }

    //장바구니에 Product p num개 추가
    public void addProduct(Product p, int num){
        if (chkStock(p,num)){
            Basket b = new Basket(p,num);
            //System.out.println(b);
            basket.add(b);
            //printBaskets();
            System.out.println(p.getpName()+" "+num+" 개가 장바구니에 추가되었습니다. ");
        }
    }

    public void rmProduct(Product p){
        Basket rmBasket = null;
        for (Basket b : basket){
            if (b.getProduct().equals(p)){
                rmBasket = b;
                break;
            }
        }
        basket.remove(rmBasket);
    }

    //재고확인 하고싶은 Product와 주문예정 개수 num을 매개변수로 받아 확인
    boolean chkStock(Product p, int num){
        if (p.getpStock()<num){
            System.out.println("재고가 부족합니다. (현재 재고: " + p.getpStock() +" 개)" );
            return false;
        }
        return true;
    }

    //주문 확정시 Stock 차감
    public void minusStock(Product p, int num){
        int beforeMinus = p.getpStock();
        p.setpStock(p.getpStock()-num);
        System.out.println(p.getpName() + "재고가 "+beforeMinus+"개 -> "+p.getpStock()+"개로 업데이트 되었습니다.");
    }

    //주문 확정시 totalPrice 추가
    public void plusTotalPrice(Customer c, int price){
            c.setTotalPrice(price);
        }

}
