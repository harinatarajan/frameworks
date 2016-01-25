package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.Event;
import com.ssudhaiyer.fsm.framework.StateAware;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.StateInfo;

public class Context implements StateAwareWrapper {

  private StateAware stateAware;
  private Event event;

  public String getCurrentState() {
    return stateAware.getCurrentState();
  }

  public void setCurrentState(String stateCode) {
    stateAware.setCurrentState(stateCode);
  }

  public void setStateInfo(String stateCode, StateInfo details) {
    stateAware.setStateInfo(stateCode, details);

  }

  public StateInfo getStateInfo(String stateCode) {
    return stateAware.getStateInfo(stateCode);
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;

  }

  public void setStateAware(StateAware stateAware) {
    this.stateAware = stateAware;

  }

  public StateAware getStateAware() {
    return stateAware;
  }


}
