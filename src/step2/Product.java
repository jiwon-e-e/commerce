package step2;

public class Product {
    private String pName;
    private int pPrice;
    private String pDescription;
    private int pStock;

    Product(String pName, int pPrice, String pDescription, int pStock){
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pStock=  pStock;
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
}
