package step2.exception;

import java.time.LocalDateTime;

public class OutOfStockException extends RuntimeException{
    private LocalDateTime timeStamp;
    public OutOfStockException(String message){
        super(message);
    }
    // 관례적으로 매개변수를 받지 않는 생성자도 만들기
    public OutOfStockException(){}

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
