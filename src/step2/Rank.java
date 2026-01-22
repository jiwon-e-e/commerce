package step2;

public enum Rank {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    //할인율
    int sale;
    Rank(int sale){
        this.sale= sale;
    }
}
