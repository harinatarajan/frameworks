package com.ssudhaiyer.fsm.framework;

/**
 * TransitionType
 * <p/>
 * TransitionType specifies the type of transition to make for the state machine engine. A state level transition,
 * will cause the state machine to make the transition to the target state but not execute any methods. The execution
 * will have to be triggered by an external request. If a method level transition is configured, the state machine
 * will make the transition to the target state and also execute the default method.
 *
 * @author sudha_subramanian
 */
public enum TransitionType {
  METHOD, STATE
}
