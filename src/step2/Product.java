package step2;

public class Product {
    // ---------- 속성 ----------
    private String pName;
    private int pPrice;
    private String pDescription;
    private int pStock;

    // ---------- 생성자 ----------
    Product(String pName, int pPrice, String pDescription, int pStock){
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pStock=  pStock;
    }

    // ---------- 기능 ----------
    public void setpStock(int stock){
        System.out.printf("%s 의 재고가 %d 개 -> %d 개로 수정되었습니다. \n",pName, pStock, stock);
        this.pStock = stock;
    }

    public void setpPrice(int price){
        System.out.printf("%s 의 가격이 %d 원 -> %d 원으로 수정되었습니다. \n",pName, pPrice, price);
        this.pPrice = price;
    }

    public void setpDescription(String str){
        System.out.printf("%s 의 설명이 수정되었습니다. \n",pName);
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
}
