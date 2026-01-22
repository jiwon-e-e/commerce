package step2;

public enum Rank {
    BRONZE(0),
    SILVER(5),
    GOLD(10),
    PLATINUM(15);

    int sale;
    Rank(int sale){
        this.sale= sale;
    }
}
