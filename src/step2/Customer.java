package step2;

import static step2.Rank.*;

public class Customer {
    // ---------- 속성 ----------
    private String cName;
    private String cMail;
    private Rank rank;
    private int totalPrice;

    // ---------- 생성자 ----------
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

    // ---------- 기능 ----------
    // totalPrice 에 새 주문 금액 추가
    public void setTotalPrice(int newOrderPrice) {
        this.totalPrice = this.totalPrice+newOrderPrice;
        this.rank = chkRank(totalPrice);
    }

    //total Price 를 매개변수로 받아 Rank update
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

    //고객 정보 출력
    public void printCustomerInf(){
        System.out.println("성명: "+cName);
        System.out.println("메일: "+cMail);
        System.out.println("등급: "+rank);
    }

    public Rank getRank() {
        return rank;
    }
}
