package step2;

public class Customer {
    String cName;
    String cMail;
    Rank rank;
    int totalPrice;

    Customer(String cName, String cMail){
        this.cName = cName;
        this.cMail = cMail;
        this.rank = Rank.BRONZE;
        this.totalPrice = 0;
    }

    public Customer(String cName, String cMail, int totalPrice) {
        if (totalPrice < 0 ){
            throw new IllegalArgumentException("누적 주문 금액은 0 이상으로 설정해주세요.")
        }
        this.cName = cName;
        this.cMail = cMail;
        this.totalPrice = totalPrice;
        this.rank = chkRank(totalPrice);
    }

    Rank chkRank(int totalPrice){
        if (totalPrice<500000){
            return Rank.BRONZE;
        }else if (totalPrice<1000000){
            return Rank.SILVER;
        }else if (totalPrice<2000000){
            return Rank.GOLD;
        }else{
            return Rank.PLATINUM;
        }
    }
}
