package step1;

import java.util.ArrayList;
import java.util.List;

public class Product {
    String pName;
    int pPrice;
    String pDescription;
    int pStock;

    Product(String pName, int pPrice, String pDescription, int pStock){
        this.pName = pName;
        this.pPrice = pPrice;
        this.pDescription = pDescription;
        this.pStock=  pStock;
    }

    public void setpStock(int pStock) {
        this.pStock = pStock;
    }


}
