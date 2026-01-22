package step2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Basket {
    private Product product;
    private int quantity;

    //Basket 생성자, 장바구니 담을때마다 새로 생성
    public Basket(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    //getter, setter
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
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
