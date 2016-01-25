package com.ssudhaiyer.fsm.framework.impl;

import java.util.ArrayList;
import java.util.List;

import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.GuardCondition;
import com.ssudhaiyer.fsm.framework.State;
import com.ssudhaiyer.fsm.framework.StateModel;
import com.ssudhaiyer.fsm.framework.Transition;
import com.ssudhaiyer.fsm.framework.TransitionType;

public class DefaultStateModel implements StateModel {

	private final List<State> states;
	private final List<Transition> transitions;
	private final State initState;

	public DefaultStateModel(State initState, List<State> states,
			List<Transition> transitions) {
		this.states = states;
		this.transitions = transitions;
		this.initState = initState;
	}

	public List<State> getStates() {
		return states;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public State getInitState() {
		return initState;
	}

	public List<Transition> getTransitionsFromState(State state) {
		List<Transition> result = new ArrayList<Transition>();

		for (Transition transition : transitions) {
			if (transition.getFromState().equals(state)) {
				result.add(transition);
			}
		}
		return result;
	}

	public List<Transition> getTransitionsToState(State state) {
		List<Transition> result = new ArrayList<Transition>();

		for (Transition transition : transitions) {
			if (transition.getToState().equals(state)) {
				result.add(transition);
			}
		}
		return result;
	}

	public List<Transition> getTransitionsFromStateByType(State state,
			TransitionType type) {

		List<Transition> result = new ArrayList<Transition>();

		for (Transition transition : transitions) {
			if (transition.getFromState().equals(state)
					&& transition.getTransitionType().equals(type)) {
				result.add(transition);
			}
		}
		return result;
	}

	public State getStateFromStateCode(String code) {
		State result = null;

		for (State state : states) {
			FsmStateAnnot fsmStateAnnotation = state.getClass().getAnnotation(
					FsmStateAnnot.class);
			if (fsmStateAnnotation.name().equals(code)) {
				result = state;
				break;
			}
			fsmStateAnnotation = null;
		}
		return result;
	}

	public Transition getTransitionWithDefaultGuardConditionFromState(
			State state) {
		Transition result = null;
		for (Transition transition : transitions) {
			if (transition.getFromState().equals(state)
					&& transitionHasDefaultGuardCondition(transition)) {
				result = transition;
				break;
			}
		}

		return result;
	}

	private boolean transitionHasDefaultGuardCondition(Transition transition) {
		boolean result = false;
		List<GuardCondition> guardConditions = transition.getGuardConditions();
		for (GuardCondition guardCondition : guardConditions) {
			if (guardCondition.getClass().equals(DefaultGuardCondition.class)) {
				result = true;
				break;
			}
		}
		return result;
	}

}
