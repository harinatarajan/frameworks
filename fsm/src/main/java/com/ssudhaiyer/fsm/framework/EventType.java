package com.ssudhaiyer.fsm.framework;

/**
 * EventType
 * <p/>
 * EventType determines if the event was triggered externally as a result of user action or system action OR
 * triggered by a local transition from one state to an other.
 * <p/>
 * The State class can use the trigger type to determine its action.
 *
 * @author sudha_subramanian
 */
public enum EventType {
  localTransistion, triggeredTransition
}
