package com.delivery.app.user_ms.dtos;

import com.delivery.app.user_ms.enums.Menu;
import com.delivery.app.user_ms.enums.OrderStatus;

public class OrderDto{

    private Menu menu;
    private int quantity;
    private OrderStatus status;
    private UserRecordDto user;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public UserRecordDto getUser() {
        return user;
    }

    public void setUser(UserRecordDto user) {
        this.user = user;
    }
}
