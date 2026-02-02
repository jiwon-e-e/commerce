package step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Category {


    // ---------- 속성 ----------
    private List<Product> elecProduct = new ArrayList<>(List.of(
            new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10),
            new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 22),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 4),
            new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 12)
    ));

    private List<Product> clothProduct = new ArrayList<>(List.of(
            new Product("T-shirt", 23500, "기모 레이스 레이어드 겨울 티셔츠", 5),
            new Product("Pleated Skirt", 31000, "클래식한 프리츠 미디스커트", 5),
            new Product("Pants", 14500, "여유 인어 부츠컷 올밴딩 소프트 트레이닝 팬츠", 5),
            new Product("Polo Knit", 27700, "포우나인 소매 버튼 여리핏 니트 그레이", 5),
            new Product("Color Knit", 35600, "앤드모어 일체형 레이어드 배색 니트", 5)
    ));

    private List<Product> foodProduct = new ArrayList<>(List.of(
            new Product("dooJjim", 40900, "국내산 한우대창과 실비김치 찜닭", 1),
            new Product("Curry", 10500, "거대 감자,당근,소고기 들어간 카레", 4),
            new Product("Pasta stew", 16000, "고추장 찌개 느낌의 매콤 된장찌개", 4),
            new Product("iceCream 3set", 33000, "생딸기가 들어간 상하목장 딸기우유 아이스크림", 3),
            new Product("Handmade Toast", 12000, "계란지단 베이컨 불닭마요 토스트", 2)
    ));

    private static Category instance;
    private HashMap<String, List<Product>> categoryMap;

    // ---------- 생성자 ----------
    private Category() {
        categoryMap = new HashMap<>();

        categoryMap.put("전자제품", elecProduct);
        categoryMap.put("의류", clothProduct);
        categoryMap.put("식품", foodProduct);
    }

    // ---------- 기능 ----------
    public static Category getInstance() {
        //System.out.println("ProductList getInstance 호출됨");
        if (instance == null) {
            instance = new Category();
        }
        return instance;
    }

    //getter - HashMap 전체를 반환
    public HashMap<String, List<Product>> getCategoryMap() {
        return categoryMap;
    }

    //getter - String 카테고리 이름을 매개변수로 해당 ProductList만 list<Product>로 반환
    public List<Product> getProductByCategory(String categoryName) {
        return categoryMap.get(categoryName);
    }

    //getter - String 상품 이름을 매개변수로 해당 Product 만 Optional<Product> 로 반환
    public Optional<Product> getProductByPName(String pName) {
        for (List<Product> list : categoryMap.values()) {
            for (Product p : list) {
                if (p.getpName().equalsIgnoreCase(pName)) {
                    return Optional.of(p);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Product 의 재고가 사용자가 원하는 만큼 차감해도 되는지 확인
     * @param p 사용자가 장바구니에 담으려는 Product
     * @param num 사용자가 원하는 재고
     * @return
     */
    boolean chkStock(Product p, int num){
        if (p.getpStock()<num){
            System.out.println("재고가 부족합니다. (현재 재고: " + p.getpStock() +" 개)" );
            return false;
        }
        return true;
    }

    //주문 확정시 해당 Product 의 Stock 차감
    public void minusStock(Product p, int num){
        System.out.printf("%s 의 재고가 %d 개 -> %d 개로 수정되었습니다. \n",p.getpName(), p.getpStock(), p.getpStock()-num);
        p.setpStock(p.getpStock()-num);
    }

    /**
     * n 번째 카테고리명 1개 반환
     * ArrayList 가 순서를 반영하지 않기 때문에 전달해줄 때 결정
     * @param n
     * @return n 번째 카테고리 이름
     */
    public String getCategoryList(int n) {
        return getKeyList().get(n);
    }

    /**
     * 매개변수로 받은 리스트 내에 있는 Product 들을 출력
     * @param products 리스트
     */
    public void printProductsByList(List<Product> products) {
        int i = 1;
        for (Product p : products) {
            System.out.printf(i + ". %-15s |%,10d 원| %s\n", p.getpName(), p.getpPrice(), p.getpDescription());
            i++;
        }
    }

    //카테고리 이름 (keyset)만 list로 반환
    public List<String> getKeyList() {
        return new ArrayList<>(categoryMap.keySet());
    }

    //keyset 을 돌면서 카테고리 이름 출력
    public void printCategoryList() {
        int i = 1;
        for (String list : getKeyList()) {
            System.out.println(i + ". " + list);
            i++;
        }
    }

    public boolean remove(Product product) {
        for (List<Product> list : categoryMap.values()) {
            if (list.remove(product)) {
                return true; // 삭제 성공
            }
        }
        return false; // 어떤 카테고리에도 없음
    }
}

