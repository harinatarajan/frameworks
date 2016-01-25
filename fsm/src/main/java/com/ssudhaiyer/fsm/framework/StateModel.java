package com.ssudhaiyer.fsm.framework;

import java.util.List;

/**
 * StateModel binds the states and the transitions together.
 * <p/>
 * The state engine runs the stateaware object through the provided statemodel.
 * <p/>
 * A statemodel allows an instance of a state to be the initial or a final state
 * within that model.
 * <p/>
 * For instance, A state instance S1 in a state model instance SM can be defined
 * as a final state. A state instance S1' in a state model instance SM' can be
 * defined as the initial state. The StateModel provides that flexibility in
 * configuring a state to behave differently vs a design where the transitions
 * are directly modeled as a part of the state.
 *
 * @author sudha_subramanian
 */
public interface StateModel {
  List<State> getStates();

  List<Transition> getTransitions();

  State getInitState();

  List<Transition> getTransitionsFromState(State state);

  List<Transition> getTransitionsToState(State state);

  List<Transition> getTransitionsFromStateByType(State state,
                                                 TransitionType type);

  Transition getTransitionWithDefaultGuardConditionFromState(State state);

  State getStateFromStateCode(String code);
}
