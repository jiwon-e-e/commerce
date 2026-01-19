package step2;

import java.util.List;

public class ProductList {

    List<Product> elecProduct = List.of(
            new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 10),
            new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 22),
            new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 4),
            new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 12)
    );

    List<Product> clothProduct = List.of(
            new Product("레이어드 티셔츠", 23500, "기모 레이스 랩 셔링 겨울 티셔츠", 5),
            new Product("플리츠 스커트", 31000, "클래식한 프리츠 미디스커트", 5),
            new Product("롱 팬츠 츄리닝", 14500, "여유 인어 부츠컷 올밴딩 소프트 트레이닝 팬츠", 5),
            new Product("울 혼용 니트", 27700, "포우나인 소매 버튼 여리핏 니트 그레이", 5),
            new Product("배색 니트", 35600, "앤드모어 일체형 레이어드 배색 니트", 5)
    );

    List<Product> foodProduct = List.of(
            new Product("두찜 한우실비곱찜닭", 40900, "국내산 한우대창과 실비김치 찜닭", 1),
            new Product("카레", 10500, "거대 감자,당근,소고기 들어간 카레", 4),
            new Product("된장찌개", 16000, "고추장 찌개 느낌의 매콤 된장찌개", 4),
            new Product("딸기아이스크림 3set", 33000, "생딸기가 들어간 상하목장 딸기우유 아이스크림", 3),
            new Product("수제 토스트", 12000, "계란지단 베이컨 불닭마요 토스트", 2)
    );

    public void getProductList(List<Product> products){
        int i =1;
        for (Product p : products){
            System.out.printf(i+". %-20s |%,10d 원|  %-30s\n",p.pName,p.pPrice,p.pDescription);
            i++;
        }
    }
}
