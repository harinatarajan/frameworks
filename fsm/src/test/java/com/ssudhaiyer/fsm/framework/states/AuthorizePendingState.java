package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.FsmEventAnnot;
import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.impl.AbstractState;

@FsmStateAnnot(name = "AuthorizePendingState")
public class AuthorizePendingState extends AbstractState {

  //private static final EcommerceLogger logger = EcommerceLogger.getInstance(AuthorizePendingState.class);

  public AuthorizePendingState(boolean finalState) {
    super(finalState);

  }

  @FsmEventAnnot(name = "preAuthenticate", def = "true")
  public void authorize(StateAwareWrapper stateAwareWrapper) {
	  System.out.println("AuthorizePendingState::authorize");
  }

  public void exitState(StateAwareWrapper stateAwareWrapper) {
	  System.out.println("AuthorizePendingState::exitState");

  }

  public void persistState(StateAwareWrapper stateAwareWrapper) {
	  System.out.println("AuthorizePendingState::persistState");
  }

}
