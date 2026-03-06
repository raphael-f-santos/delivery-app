package com.delivery.app.user_ms.models;

import com.delivery.app.user_ms.enums.CodeStatus;
import jakarta.persistence.*;

@Entity
public class Code {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long orderId;

    private int code;

    @Enumerated(EnumType.STRING)
    private CodeStatus status;

    public Code() { }

    public Code(Long userId, int code, CodeStatus status, Long orderId) {
        this.userId = userId;
        this.code = code;
        this.status = status;
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CodeStatus getStatus() {
        return status;
    }

    public void setStatus(CodeStatus status) {
        this.status = status;
    }
}
