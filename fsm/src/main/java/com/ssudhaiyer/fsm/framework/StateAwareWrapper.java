package com.ssudhaiyer.fsm.framework;

/**
 * StateAwareWrapper
 * <p/>
 * A wrapper interface to StateAware interface to provide additional attributes that are required by the state machine
 * to determine the next course of action. For instance, the state machine looks at the event defined on the stateawarewrapper object, to
 * know which method to call on the state upon entry.
 *
 * @author sudha_subramanian
 */
public interface StateAwareWrapper extends StateAware {
  Event getEvent();

  void setEvent(Event event);

  void setStateAware(StateAware stateAware);

  StateAware getStateAware();

}
