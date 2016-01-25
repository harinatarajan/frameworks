package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.EventCode;

public enum OrderEventCodes implements EventCode {
  createOrder("createOrder"), preAuthenticate("preAuthenticate"), retryAuthentication(
      "retryAuthentication"), fulfillOrder("fulfillOrder"), fulFillOrderLineItem(
      "fulFillOrderLineItem"), cancelOrder("cancelOrder"), settleOrder(
      "settleOrder"), settleOrderLineItem("settleOrderLineItem");

  private String text;

  private OrderEventCodes(String text) {
    this.text = text;
  }

  public String getText() {
    return text;
  }

}
