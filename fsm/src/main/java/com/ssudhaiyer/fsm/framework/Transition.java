package com.ssudhaiyer.fsm.framework;

import java.util.List;

/**
 * Transition
 * <p/>
 * A transition defines the path a state should take based on the guard
 * condition. If a guard condition is evaluated to true, the state transitions
 * from the 'source' state to the 'target' state.
 * <p/>
 * Additionally, a transition can specify the event it wants to trigger on the
 * target state. If no event is specified, and the transition type is Method,
 * the default method on the target state will be executed. For a state level
 * transition, its not required to provide an event. The state level transition
 * will only transition to the state and not execute any method. Typically, a
 * state level transition will happen to an end state.
 *
 * @author sudha_subramanian
 */
public interface Transition {

  List<GuardCondition> getGuardConditions();

  State getFromState();

  State getToState();

  TransitionType getTransitionType();

  Event getEvent();

}
