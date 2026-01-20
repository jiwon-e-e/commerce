package step2;

import static step2.Rank.*;

public class Customer {
    private String cName;
    private String cMail;
    private Rank rank;
    private int totalPrice;

    //생성자
    public Customer(String cName, String cMail){
        this.cName = cName;
        this.cMail = cMail;
        this.rank = BRONZE;
        this.totalPrice = 0;
    }
    // 누적금액이 있는 고객 생성자
    public Customer(String cName, String cMail, int totalPrice) {
        if (totalPrice < 0 ){
            throw new IllegalArgumentException("누적 주문 금액은 0 이상으로 설정해주세요.");
        }
        this.cName = cName;
        this.cMail = cMail;
        this.totalPrice = totalPrice;
        this.rank = chkRank(totalPrice);
    }


    private Rank chkRank(int totalPrice){
        if (totalPrice<500000){
            return BRONZE;
        }else if (totalPrice<1000000){
            return SILVER;
        }else if (totalPrice<2000000){
            return GOLD;
        }else{
            return PLATINUM;
        }
    }

    public void getCustomer(){
        System.out.printf("""
                성명: %s
                email: %s
                누적 주문금액: %d
                등급: %Rank
                """, cName, cMail, totalPrice, rank);
    }
}
