package step2.exception;

import java.time.LocalDateTime;

public class OutOfStockAndPriceException extends RuntimeException{
    private LocalDateTime timeStamp;
    public OutOfStockAndPriceException(String message){
        super(message);
    }
    // 관례적으로 매개변수를 받지 않는 생성자도 만들기
    public OutOfStockAndPriceException(){}

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
