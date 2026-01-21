package step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductList {

    private static ProductList instance = new ProductList();

    private HashMap<String, List<Product>> categoryMap;

    private ProductList(){
        //System.out.println("ProductList 생성됨"+ this);
        categoryMap = new HashMap<>();

        categoryMap.put("전자제품", elecProduct);
        categoryMap.put("의류", clothProduct);
        categoryMap.put("식품", foodProduct);
    }

    public static ProductList getInstance(){
        //System.out.println("ProductList getInstance 호출됨");
        return instance;
    }

    //HashMap 전체를 반환
    public HashMap<String, List<Product>> getCategoryMap() {
        return categoryMap;
    }

    //카테고리 이름으로 ProductList만 list로 반환
    public List<Product> getProductByCategory(String category){
        //System.out.println(categoryMap.get(category));
        return categoryMap.get(category);
    }

    //카테고리 이름 (key)만 list로 반환
    public List<String> getCategory(){
        //System.out.println(categoryMap.keySet());
        return new ArrayList<>(categoryMap.keySet());
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

}
