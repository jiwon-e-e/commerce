package step2;

import java.util.List;

public class Category {
    private static Category instance;

    ProductList productList = ProductList.getInstance();
    //속성

    //생성자
    private Category(){
        //System.out.println("생성자 실행 됨");
    };

    //싱글톤 객체가 없다면 생성, 있다면 getter
    public static Category getInstance(){
        if(instance ==null){
            instance = new Category();
        }

        return instance;
    }

    // 카테고리 이름을 매개변수로 받아 Product List 를 반환
    public List<Product> getProducts(String category){
        //System.out.println(productList.getProductByCategory(category));
        return productList.getProductByCategory(category);
    }

    public List<String> getCategoryList(){
        return productList.getCategory();
    }

    //keyset 을 돌면서 카테고리 이름 출력
    public void printCategoryList() {
        int i = 1;
        for (String list : productList.getCategory()){
            System.out.println(i + ". " + list);
            i++;
        }
    }



    //인덱스 n 을 기준으로 특정 카테고리명 출력
    //ArrayList 가 순서를 반영하지 않기 때문에 전달해줄 때 결정
    public String getCategoryList(int n){
        return productList.getCategory().get(n);
    }

    //Product List 를 매개변수로 받아 각각의 product 출력
    public void ListtoString(List<Product> products){
        int i =1;
        for (Product p : products){
            System.out.printf(i+". %-15s |%,10d 원| %s\n",p.getpName(),p.getpPrice(),p.getpDescription());
            i++;
        }
    }
}
