package com.ssudhaiyer.fsm.framework;

/**
 * State
 * <p/>
 * The state interface defines the methods any concrete state class should provide.
 *
 * @author sudha_subramanian
 */
public interface State {
  void enterState(StateAwareWrapper stateAwareWrapper);

  void exitState(StateAwareWrapper stateAwareWrapper);

  void persistState(StateAwareWrapper stateAwareWrapper);

  boolean isFinal();

  String getStateCode();
}