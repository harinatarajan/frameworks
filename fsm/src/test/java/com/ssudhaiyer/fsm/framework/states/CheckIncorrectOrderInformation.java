package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.GuardCondition;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;

public class CheckIncorrectOrderInformation implements GuardCondition {

  public boolean execute(StateAwareWrapper stateAwareWrapper) {
    Order o = (Order) stateAwareWrapper.getStateAware();
    if (o.getOrderId() == null)
      return false;
    else
      return true;
  }

}
