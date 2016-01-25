package com.ssudhaiyer.fsm.framework;

/**
 * Marker interface for all event codes
 * <p/>
 * The application specific event codes Enum class will extend the EventCode interface.
 * <p/>
 * The state methods will be annotated with the event code and serve as handler methods for the events.
 *
 * @author sudha_subramanian
 */
public interface EventCode {
  String getText();
}
