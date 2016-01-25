package com.ssudhaiyer.fsm.framework.impl;

import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.State;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;

/**
 * AbstractState provides a default implementation for State methods and calls
 * the persistState callback.
 * 
 * @author sudha_subramanian
 *
 */
public abstract class AbstractState implements State {

	final boolean finalState;

	public AbstractState(boolean finalState) {
		this.finalState = finalState;
	}

	public void enterState(StateAwareWrapper stateAwareWrapper) {
		// get the @state annotation of the calling child class
		FsmStateAnnot fsmStateAnnotation = this.getClass().getAnnotation(
				FsmStateAnnot.class);
		stateAwareWrapper.getStateAware().setCurrentState(
				fsmStateAnnotation.name());
		persistState(stateAwareWrapper);
	}

	public boolean isFinal() {
		return finalState;
	}

	public String getStateCode() {
		FsmStateAnnot fsmStateAnnotation = this.getClass().getAnnotation(
				FsmStateAnnot.class);
		return fsmStateAnnotation.name();
	}

}
