package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.framework.FsmEventAnnot;
import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.impl.AbstractState;

@FsmStateAnnot(name = "FulfillmentPending")
public class FulfillmentPendingState extends AbstractState {

	public FulfillmentPendingState(boolean finalState) {
		super(finalState);

	}

	public void exitState(StateAwareWrapper stateAwareWrapper) {
		System.out.println("FulfillmentPendingState::existState");

	}

	@FsmEventAnnot(name = "fulFillOrderLineItem", def = "true")
	public void fulfillLineItem(StateAwareWrapper stateAwareWrapper) {
		System.out.println("FulfillmentPendingState::fulfillLineItem");
	}

	public void persistState(StateAwareWrapper stateAwareWrapper) {
		System.out.println("FulfillmentPendingState::persistState");

	}

}
