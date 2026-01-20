package step2;

import java.util.HashMap;
import java.util.List;

public class Category {
    //속성
    private HashMap<String, List<Product>> categoryMap = new HashMap<String, List<Product>>();

    //생성자
    public Category(){
        categoryMap.put("전자제품", elecProduct);
        categoryMap.put("의류", clothProduct);
        categoryMap.put("식품", foodProduct);
    }

    //기능
    HashMap<String, List<Product>> getCategoryMap(){
        return categoryMap;
    }

    //keyset 을 돌면서 카테고리 이름 출력
    public void printCategoryList() {
        int i = 1;
        for (String list : categoryMap.keySet()){
            System.out.println(i + ". " + list);
            i++;
        }
    }

    private List<Product> elecProduct = List.of(
            new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10),
            new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 22),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 4),
            new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 12)
    );

    private List<Product> clothProduct = List.of(
            new Product("T-shirt", 23500, "기모 레이스 레이어드 겨울 티셔츠", 5),
            new Product("Pleated Skirt", 31000, "클래식한 프리츠 미디스커트", 5),
            new Product("Pants", 14500, "여유 인어 부츠컷 올밴딩 소프트 트레이닝 팬츠", 5),
            new Product("Polo Knit", 27700, "포우나인 소매 버튼 여리핏 니트 그레이", 5),
            new Product("Color Knit", 35600, "앤드모어 일체형 레이어드 배색 니트", 5)
    );

    private List<Product> foodProduct = List.of(
            new Product("dooJjim", 40900, "국내산 한우대창과 실비김치 찜닭", 1),
            new Product("Curry", 10500, "거대 감자,당근,소고기 들어간 카레", 4),
            new Product("Pasta stew", 16000, "고추장 찌개 느낌의 매콤 된장찌개", 4),
            new Product("iceCream 3set", 33000, "생딸기가 들어간 상하목장 딸기우유 아이스크림", 3),
            new Product("Handmade Toast", 12000, "계란지단 베이컨 불닭마요 토스트", 2)
    );

    public List<Product> getElecProduct() {
        return elecProduct;
    }

    public List<Product> getClothProduct() {
        return clothProduct;
    }

    public List<Product> getFoodProduct() {
        return foodProduct;
    }

    //각각의 product 출력
    public void getProductList(List<Product> products){
        int i =1;
        for (Product p : products){
            System.out.printf(i+". %-15s |%,10d 원| %s\n",p.getpName(),p.getpPrice(),p.getpDescription());
            i++;
        }
    }
}
