package step2;

import step2.exception.OutOfStockAndPriceException;

import java.util.Objects;

public class Product {
    // ---------- 속성 ----------
    private static int numbering = 0;
    private int id;
    private String pName;
    private int pPrice;
    private String pDescription;
    private int pStock;



    // ---------- 생성자 ----------
    Product(String pName, int pPrice, String pDescription, int pStock){
        this.pName = pName;
        setpPrice(pPrice);
        this.pDescription = pDescription;
        setpStock(pStock);
        this.id = ++numbering;
    }

    // ---------- 기능 ----------
    public void setpStock(int stock){
        if(stock<0){
            throw new OutOfStockAndPriceException("재고가 부족합니다.");
        }
        this.pStock = stock;
    }

    public void setpPrice(int price){
        if(price<=0){
            throw new OutOfStockAndPriceException("가격은 0 이상이어야합니다.");
        }
        this.pPrice = price;
    }

    public void setpDescription(String str){
        this.pDescription = str;
    }

    public String getpName() {
        return pName;
    }

    public int getpPrice() {
        return pPrice;
    }

    public String getpDescription() {
        return pDescription;
    }

    public int getpStock() {
        return pStock;
    }

    public void printProduct(){
        System.out.printf(" %-15s |%,10d 원| 재고: %,3d 개 | %s\n",getpName(),getpPrice(),getpStock(),getpDescription());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
