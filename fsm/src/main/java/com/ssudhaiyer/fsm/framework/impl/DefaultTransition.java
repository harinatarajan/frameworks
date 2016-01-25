package com.ssudhaiyer.fsm.framework.impl;

import java.util.List;

import com.ssudhaiyer.fsm.framework.Event;
import com.ssudhaiyer.fsm.framework.GuardCondition;
import com.ssudhaiyer.fsm.framework.State;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.Transition;
import com.ssudhaiyer.fsm.framework.TransitionType;

/**
 * DefaultTransition provides default implementation for transition
 * 
 * @author sudha_subramanian
 *
 */
public class DefaultTransition implements Transition {

	private final List<GuardCondition> conditions;
	private final State fromState;
	private final State toState;
	private final TransitionType transitionType;
	private Event event = null;

	public DefaultTransition(List<GuardCondition> conditions, State fromState,
			State toState, TransitionType transitionType) {
		this.conditions = conditions;
		this.fromState = fromState;
		this.toState = toState;
		this.transitionType = transitionType;
	}

	public DefaultTransition(List<GuardCondition> conditions, State fromState,
			State toState, TransitionType transitionType, Event event) {
		this.conditions = conditions;
		this.fromState = fromState;
		this.toState = toState;
		this.transitionType = transitionType;
		this.event = event;
	}

	public List<GuardCondition> getGuardConditions() {
		return conditions;
	}

	public State getFromState() {
		return fromState;
	}

	public State getToState() {
		return toState;
	}

	public TransitionType getTransitionType() {
		return transitionType;
	}

	public Event getEvent() {
		return event;
	}

}
