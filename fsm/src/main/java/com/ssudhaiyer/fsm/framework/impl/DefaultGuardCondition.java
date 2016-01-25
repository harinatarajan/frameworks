package com.ssudhaiyer.fsm.framework.impl;

import com.ssudhaiyer.fsm.framework.GuardCondition;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;

public class DefaultGuardCondition implements GuardCondition {

  public boolean execute(StateAwareWrapper stateAwareWrapper) {
    return true;
  }

}
