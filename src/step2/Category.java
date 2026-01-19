package step2;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private List<String> CategoryList = List.of("전자제품", "의류", "식품");

    public void printCategoryList() {
        int i = 1;
        for (String list : CategoryList){
            System.out.println(i + ". " + list);
            i++;
        }
    }

}
