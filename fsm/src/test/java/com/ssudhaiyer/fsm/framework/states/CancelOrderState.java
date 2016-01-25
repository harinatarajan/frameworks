package com.ssudhaiyer.fsm.framework.states;


import com.ssudhaiyer.fsm.framework.FsmEventAnnot;
import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.impl.AbstractState;

@FsmStateAnnot(name = "CancelOrder")
public class CancelOrderState extends AbstractState {

  public CancelOrderState(boolean finalState) {
    super(finalState);
  }

  @FsmEventAnnot(name = "cancelOrder", def = "true")
  public void cancelOrder(StateAwareWrapper stateAwareWrapper) {
    System.out.println("Cancel Order default method");
  }

  public void exitState(StateAwareWrapper stateAwareWrapper) {
	  System.out.println("Cancel Order Exit State");

  }

  public void persistState(StateAwareWrapper stateAwareWrapper) {
	  System.out.println("Cancel Order persist state");
  }

}
