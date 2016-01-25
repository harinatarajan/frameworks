package com.ssudhaiyer.fsm.framework.states;

import com.ssudhaiyer.fsm.engine.exception.StateEngineException;
import com.ssudhaiyer.fsm.framework.FsmEventAnnot;
import com.ssudhaiyer.fsm.framework.FsmStateAnnot;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.impl.AbstractState;

@FsmStateAnnot(name = "NewOrderState")
public class NewOrderState extends AbstractState {
	// private static final EcommerceLogger logger =
	// EcommerceLogger.getInstance(NewOrderState.class);

	public NewOrderState(boolean finalState) {
		super(finalState);
	}

	@FsmEventAnnot(name = "createOrder", def = "true")
	public void createOrder(StateAwareWrapper stateAwareWrapper)
			throws StateEngineException {
		Order order = (Order) stateAwareWrapper.getStateAware();
		if (order.getSimulateAnException() != null)
			throw new StateEngineException("test message");
		System.out.println("NewOrder::createOrder called");
	}

	public void persistState(StateAwareWrapper stateAwareWrapper) {
		System.out.println("NewOrder::persistState");

	}

	public void exitState(StateAwareWrapper stateAwareWrapper) {
		System.out.println("NewOrderState::existState");
	}

}
