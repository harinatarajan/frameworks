package com.ssudhaiyer.fsm.engine;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssudhaiyer.fsm.engine.exception.IncorrectStateException;
import com.ssudhaiyer.fsm.engine.exception.NoHandlerForEventException;
import com.ssudhaiyer.fsm.engine.exception.StateEngineException;
import com.ssudhaiyer.fsm.framework.Event;
import com.ssudhaiyer.fsm.framework.EventType;
import com.ssudhaiyer.fsm.framework.State;
import com.ssudhaiyer.fsm.framework.StateAwareWrapper;
import com.ssudhaiyer.fsm.framework.StateModel;
import com.ssudhaiyer.fsm.framework.Transition;
import com.ssudhaiyer.fsm.framework.impl.DefaultEvent;
import com.ssudhaiyer.fsm.framework.states.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config-framework.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFsmEngine {
	
	@Autowired
	StateModel orderStateModel;
	@Autowired
	NewOrderState newOrderState;
	@Autowired
	AuthorizePendingState authorizePendingState;
	@Autowired
	CancelOrderState cancelOrderState;
	@Autowired
	FulfillmentPendingState fulfillPendingState;

	StateEngine engine = new StateEngine();

	static {
		//This will set the default value for the class DataSourceConfig
		System.setProperty("config.dir", System.getProperty("user.dir")+"/src/test/resources/fsm");
        System.setProperty("log4j2.config", System.getProperty("user.dir") + "/src/test/resources/fsm");
		System.setProperty("spring.profiles.active", "/test");
		System.setProperty("test.mode", "false");
		 	
	}
		@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testPrepStateAwareForExecution_with_null_state()
			throws StateEngineException {
		Order order = createNewOrder();
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		engine.prepStateAwareForExecution(orderStateModel, wrapper);
		assertTrue(wrapper.getStateAware().getCurrentState()
				.equals(newOrderState.getStateCode()));
	}

	@Test
	public void testPrepStateAwareForExecution_with_pre_defined_state()
			throws StateEngineException {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		engine.prepStateAwareForExecution(orderStateModel, wrapper);
		assertTrue(wrapper.getStateAware().getCurrentState()
				.equals(authorizePendingState.getStateCode()));
	}

	@Test
	public void testGetCurrentStateFromStateAwareInstance_with_preset_state()
			throws IncorrectStateException {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		State s = engine.getCurrentStateFromStateAwareInstance(wrapper,
				orderStateModel);
		assertTrue(s.equals(authorizePendingState));
	}

	@Test
	public void testGetCurrentStateFromStateAwareInstance_incorrect_state_exception()
			throws IncorrectStateException {
		Order order = createNewOrder();
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		thrown.expect(IncorrectStateException.class);
		State s = engine.getCurrentStateFromStateAwareInstance(wrapper,
				orderStateModel);
	}

	@Test
	public void testGetMethod_invoke_def_method()
			throws NoHandlerForEventException {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		Method method = engine.getMethod(authorizePendingState, wrapper);
		assertTrue(method.getName().equals("authorize"));
	}

	@Test
	public void testGetMethod_invoke_event_method()
			throws NoHandlerForEventException {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.preAuthenticate);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);

		Method method = engine.getMethod(authorizePendingState, wrapper);
		assertTrue(method.getName().equals("authorize"));
	}

	@Test
	public void testGetMethod_expect_nohandlerforevent_exception()
			throws NoHandlerForEventException {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.cancelOrder);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		thrown.expect(NoHandlerForEventException.class);

		Method method = engine.getMethod(authorizePendingState, wrapper);

	}

	@Test
	public void testInvokeHandlerMethodOfState() throws Exception {
		Order order = createNewOrder();
		order.setCurrentState(authorizePendingState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.preAuthenticate);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		try {
			engine.invokeHandlerMethodOfState(authorizePendingState, wrapper);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(1 == 0);
		}
	}

	@Test
	public void testGetValidTransition_test_transition_to_cancelorder()
			throws Exception {
		Order order = createNewOrder();
		order.setCurrentState(newOrderState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.createOrder);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		List<Transition> transitionsFromSourceState = orderStateModel
				.getTransitionsFromState(newOrderState);
		Transition t = engine.getValidTransition(newOrderState,
				orderStateModel, wrapper, transitionsFromSourceState);
		assertTrue(t.getToState().equals(cancelOrderState));
	}

	@Test
	public void testGetValidTransition_test_transition_to_authorizestate()
			throws Exception {
		Order order = createNewOrder();
		order.setOrderId(null);
		order.setCurrentState(newOrderState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.createOrder);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		List<Transition> transitionsFromSourceState = orderStateModel
				.getTransitionsFromState(newOrderState);
		Transition t = engine.getValidTransition(newOrderState,
				orderStateModel, wrapper, transitionsFromSourceState);
		assertTrue(t.getToState().equals(authorizePendingState));
	}

	@Test
	public void testGetValidTransition_test_null_transition() throws Exception {
		Order order = createNewOrder();
		order.setCurrentState(fulfillPendingState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.fulFillOrderLineItem);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		List<Transition> transitionsFromSourceState = orderStateModel
				.getTransitionsFromState(fulfillPendingState);
		Transition t = engine.getValidTransition(fulfillPendingState,
				orderStateModel, wrapper, transitionsFromSourceState);
		assertTrue(t == null);
	}

	@Test
	public void testTransitionState_from_new_to_authorize_state()
			throws Exception {
		Order order = createNewOrder();
		order.setOrderId(null);
		order.setCurrentState(newOrderState.getStateCode());
		Event preAuth = new DefaultEvent();
		preAuth.setEventCode(OrderEventCodes.createOrder);
		preAuth.setEventType(EventType.triggeredTransition);
		StateAwareWrapper wrapper = createStateAwareWrapper(order);
		wrapper.setEvent(preAuth);
		engine.transitionState(newOrderState, wrapper, orderStateModel);
		assertTrue(wrapper.getStateAware().getCurrentState()
				.equals(authorizePendingState.getStateCode()));
	}

	@Test
	public void testExecuteEngine() throws Exception {
		Order order = createNewOrder();
		order.setOrderId(null);
		order.setCurrentState(newOrderState.getStateCode());
		StateAwareWrapper wrapper = createStateAwareWrapper(order);

		engine.executeModel(orderStateModel, wrapper);
		assertTrue(wrapper.getStateAware().getCurrentState()
				.equals(fulfillPendingState.getStateCode()));

	}

	@Test
	public void testExecuteEngineExpectException() {
		Order order = createNewOrder();
		order.setSimulateAnException("throw exception");
		order.setOrderId(null);
		order.setCurrentState(newOrderState.getStateCode());
		StateAwareWrapper wrapper = createStateAwareWrapper(order);

		try {
			engine.executeModel(orderStateModel, wrapper);
		}catch (Exception e) {
			System.out.println("testExecuteEngineException " + e.getMessage());
			assert (1 == 1);
		}

	}

	public StateAwareWrapper createStateAwareWrapper(Order order) {
		StateAwareWrapper wrapper = new Context();
		wrapper.setStateAware(order);
		return wrapper;
	}

	public Order createNewOrder() {
		Order newOrder = new Order();
		newOrder.setOrderId("123");
		return newOrder;
	}
	
}
