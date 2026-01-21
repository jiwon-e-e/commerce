package step2;

public class OrderProduct {
    Category category = Category.getInstance();
    private Basket basket;
    private Customer customer;

//    public OrderProduct(Customer customer, Basket basket){
//        this.basket = basket;
//        this.customer = customer;
//    }

    public Basket getBasket() {
        return basket;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addProduct(Product p, int num){
        if (chkStock(p,num)){
            Basket b = new Basket(p,num);
            System.out.println(p.getpName()+" "+num+" 개가 장바구니에 추가되었습니다. ");
        }
    }

        boolean chkStock(Product p, int num){
        if (p.getpStock()<num){
            System.out.println("재고가 부족합니다. (현재 재고: " + p.getpStock() +" 개)" );
            return false;
        }
        return true;
    }

}
