package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.StateAware;
import com.ssudhaiyer.fsm.framework.StateInfo;

public class Order implements StateAware {

  private String currentState;
  private StateInfo stateInfo;
  private String orderId;
  private String simulateAnException;

  public String getCurrentState() {
    return currentState;
  }

  public void setCurrentState(String stateCode) {
    this.currentState = stateCode;

  }

  public void setStateInfo(String stateCode, StateInfo details) {
    this.currentState = stateCode;
    this.stateInfo = details;

  }

  public StateInfo getStateInfo(String stateCode) {
    return stateInfo;

  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getSimulateAnException() {
    return simulateAnException;
  }

  public void setSimulateAnException(String simulateAnException) {
    this.simulateAnException = simulateAnException;
  }


}
