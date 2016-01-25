package com.ssudhaiyer.fsm.framework;

/**
 * Event
 * <p/>
 * An event triggers an execution on a state. The event code determines the method invoked on the state.
 * The event code used on a State's method annotation should match the incoming event code for the trigger to work.
 *
 * @author sudha_subramanian
 */
public interface Event {
  EventCode getEventCode();

  EventType getEventType();

  void setEventCode(EventCode eventCode);

  void setEventType(EventType eventType);
}
