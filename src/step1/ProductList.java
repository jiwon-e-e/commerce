package step1;

import java.util.List;

public class ProductList {

    List<Product> products = List.of(
            new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10),
            new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 10),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 10),
            new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 10)
    );

    public void getProduct(){
        int i =1;
        for (Product p : products){
            //System.out.println(i+". "+p.pName+"     |"+p.pPrice+"   |"+p.pDescription);
            System.out.printf(i+". %-15s |%,10d 원|  %-20s\n",p.pName,p.pPrice,p.pDescription);
            i++;
        }
    }
}
