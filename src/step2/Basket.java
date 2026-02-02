package step2;

import step2.exception.OutOfStockAndPriceException;

import java.util.Objects;

public class Basket {
    // ---------- 속성 ----------
    private Product product;
    private int quantity;

    // ---------- 생성자 ----------
    // 장바구니 담을 때 마다 새로 생성됨
    public Basket(Product product, int quantity) {
        this.product = product;
        //this.quantity = quantity;
        setQuantity(quantity);
    }

    // ---------- 기능 ----------
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity){
        if (quantity < 0) {
            throw new OutOfStockAndPriceException("재고가 부족합니다.");
        }
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Basket basket)) return false;
        return Objects.equals(product, basket.product);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(product);
    }
}
