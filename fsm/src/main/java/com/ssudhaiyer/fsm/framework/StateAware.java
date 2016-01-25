package com.ssudhaiyer.fsm.framework;

/**
 * StateAware
 * <p/>
 * This interface has to be implemented by all participating domain objects that will be run through the state machine model.
 *
 * @author sudha_subramanian
 */
public interface StateAware {
  String getCurrentState();

  void setCurrentState(String stateCode);

  void setStateInfo(String stateCode, StateInfo details);

  StateInfo getStateInfo(String stateCode);
}
