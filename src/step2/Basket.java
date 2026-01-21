package step2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {
    private Product product;
    private int quantity;

    public Basket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    //장바구니 수량 관리하기
    public void setQuantity(int quantity) {
        if (quantity <= 0 ){
            System.out.println("장바구니를 삭제합니다. ");

        }else{
            this.quantity = quantity;
        }
    }
}
