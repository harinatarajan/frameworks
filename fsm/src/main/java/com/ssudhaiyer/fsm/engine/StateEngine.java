package com.ssudhaiyer.fsm.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.ssudhaiyer.fsm.framework.*;
import com.ssudhaiyer.fsm.engine.exception.*;
import com.ssudhaiyer.fsm.framework.impl.*;

/**
 * StateEngine
 * <p/>
 * The state engine executes the state model by running the state aware object.
 * through it.
 *
 * @author sudha_subramanian
 */
public class StateEngine {

	/**
	 * Execute the model.
	 *
	 * @param stateModel
	 *            the state model.
	 * @param stateAwareWrapper
	 *            the state aware wrapper.
	 * @throws Exception
	 */
	public void executeModel(StateModel stateModel,
			StateAwareWrapper stateAwareWrapper) throws Exception {

		StateAware sa = stateAwareWrapper.getStateAware();
		prepStateAwareForExecution(stateModel, sa);
		boolean done = false;

		while (!done) {
			State currentState = getCurrentStateFromStateAwareInstance(sa,
					stateModel);

			invokeEntryMethodOfState(currentState, stateAwareWrapper);
			invokeHandlerMethodOfState(currentState, stateAwareWrapper);
			done = transitionState(currentState, stateAwareWrapper, stateModel);
			if (done) {
				break;
			}
		}
	}

	/**
	 * PrepStateAwareForExecution
	 * <p/>
	 * This sets the current state to the initial state of the state model.
	 *
	 * @param stateModel
	 *            - the state model.
	 * @param sa
	 *            - state aware instance model object
	 */
	public void prepStateAwareForExecution(StateModel stateModel, StateAware sa) {
		if (sa.getCurrentState() == null) {
			sa.setCurrentState(stateModel.getInitState().getStateCode());
		}
	}

	/**
	 * Transition state.
	 *
	 * @param currentState
	 *            the current state.
	 * @param stateAwareWrapper
	 *            the state aware wrapper.
	 * @param stateModel
	 *            the state model.
	 * @return
	 * @throws Exception
	 */
	public boolean transitionState(State currentState,
			StateAwareWrapper stateAwareWrapper, StateModel stateModel)
			throws Exception {
		List<Transition> transitionsFromSourceState = stateModel
				.getTransitionsFromState(currentState);
		if (transitionsFromSourceState == null || currentState.isFinal()) {
			return true;
		}
		Transition takeTransition = getValidTransition(currentState,
				stateModel, stateAwareWrapper, transitionsFromSourceState);

		// None of the guard conditions of the transition evaluated to true.
		// so leave the object in its current state and return. The object
		// will be in this state till an external trigger happens that
		// makes the guard evaluation to true
		if (takeTransition == null) {
			return true;
		}
		// leave the current state
		invokeExitMethodOfState(currentState, stateAwareWrapper);

		// if the transition is a state level transition, make the transition
		// to target state and exit the engine. The object will be in this state
		// till an external trigger happens to take the state machine forward.
		if (takeTransition.getTransitionType().equals(TransitionType.STATE)) {
			stateAwareWrapper.setCurrentState(takeTransition.getToState()
					.getStateCode());
			takeTransition.getToState().enterState(stateAwareWrapper);
			return true;
		}

		// if we are here, it means, we found a valid transition
		// the transition is a method level transition and current state is not
		// the final state => so, the state machine should continue processing
		// set the event from the transition, if any, for further processing by
		// the
		// state machine
		stateAwareWrapper.setEvent(takeTransition.getEvent());

		stateAwareWrapper.setCurrentState(takeTransition.getToState()
				.getStateCode());

		return false;
	}

	public Transition getValidTransition(State currentState,
			StateModel stateModel, StateAwareWrapper stateAwareWrapper,
			List<Transition> transitionsFromSourceState) throws Exception {
		Transition takeTransition = stateModel
				.getTransitionWithDefaultGuardConditionFromState(currentState);

		for (Transition transition : transitionsFromSourceState) {
			boolean guardResult = false;
			List<GuardCondition> guardConditions = transition
					.getGuardConditions();
			for (GuardCondition guardCondition : guardConditions) {
				if (!guardCondition.getClass().equals(
						DefaultGuardCondition.class)) {
					guardResult = guardCondition.execute(stateAwareWrapper);
					if (guardResult) {
						takeTransition = transition;
						break;
					}
				}

			}
		}
		return takeTransition;

	}

	/**
	 * Gets the state object corresponding to the StateAware instance's current
	 * state from the incoming model.If no valid state found in the model,
	 * throws an IncorrectStateException
	 *
	 */
	public State getCurrentStateFromStateAwareInstance(StateAware sa,
			StateModel stateModel) throws IncorrectStateException {
		State s = stateModel.getStateFromStateCode(sa.getCurrentState());
		if (s == null) {
			throw new IncorrectStateException("Current State "
					+ sa.getCurrentState() + " not in model");
		}

		return s;
	}

	public Method getMethod(State currentState,
			StateAwareWrapper stateAwareWrapper)
			throws NoHandlerForEventException {

		Event event = stateAwareWrapper.getEvent();
		Method callMethod = null;
		if (event == null) {
			callMethod = getMethodMarkedDefault(currentState);
		} else {
			callMethod = getMethodAnnotatedWithEvent(currentState, event);
		}

		if (callMethod == null) {
			throw new NoHandlerForEventException(
					"No valid or default handler available on "
							+ currentState.getStateCode());
		}
		return callMethod;
	}

	/**
	 * Get a method handler on the state object that is annotated with the
	 * incoming event.
	 *
	 */
	public Method getMethodAnnotatedWithEvent(State currentState, Event event) {
		Method[] methods = currentState.getClass().getMethods();
		Method result = null;
		for (int i = 0; i < methods.length; i++) {
			FsmEventAnnot eventAnnotation = methods[i]
					.getAnnotation(FsmEventAnnot.class);
			if (eventAnnotation != null) {
				if (eventAnnotation.name().equals(
						event.getEventCode().getText())) {
					result = methods[i];
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Get a method handler on the state object marked as default.
	 *
	 */
	public Method getMethodMarkedDefault(State currentState) {
		Method[] methods = currentState.getClass().getMethods();
		Method result = null;
		for (int i = 0; i < methods.length; i++) {
			FsmEventAnnot eventAnnotation = methods[i]
					.getAnnotation(FsmEventAnnot.class);
			if (eventAnnotation != null) {
				if (eventAnnotation.def().equals("true")) {
					result = methods[i];
					break;
				}
			}
		}

		return result;

	}

	public void invokeEntryMethodOfState(State currentState,
			StateAwareWrapper stateAwareWrapper) {
		currentState.enterState(stateAwareWrapper);
	}

	public void invokeHandlerMethodOfState(State currentState,
			StateAwareWrapper stateAwareWrapper) throws Exception {
		Method methodToInvokeOnState = getMethod(currentState,
				stateAwareWrapper);

		try {
			methodToInvokeOnState.invoke(currentState, stateAwareWrapper);
		} catch (InvocationTargetException e) {
			throw (Exception) e.getCause();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw e;
		}

	}

	public void invokeExitMethodOfState(State currentState,
			StateAwareWrapper stateAwareWrapper) {
		currentState.exitState(stateAwareWrapper);
	}

}
