package com.ssudhaiyer.fsm.framework;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssudhaiyer.fsm.framework.impl.DefaultTransition;
import com.ssudhaiyer.fsm.framework.states.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-config-framework.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFsmFramework {
	@Autowired
	StateModel orderStateModel;
	
	@Autowired
	NewOrderState newOrderState;
	
	@Autowired
	DefaultTransition authroizePendingTransition;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOrderStateModelNotNull() {
		assertNotNull(orderStateModel);
	}
	
	@Test
	public void testGetTransitionsFromStateByType(){
		List<Transition> transitions = orderStateModel.getTransitionsFromStateByType(newOrderState, TransitionType.STATE);
		assertTrue(transitions.size() == 1);
	}
	
	@Test
	public void testGetTransitionsFromState(){
		List<Transition> transitions = orderStateModel.getTransitionsFromState(newOrderState);
		assertTrue(transitions.size() == 2);
	}

	@Test
	public void testGetStateFromStateCode(){
		State result = orderStateModel.getStateFromStateCode("NewOrderState");
		assertTrue(result.getStateCode().equals("NewOrderState"));
	}
	
	@Test
	public void testGetTransitionWithDefaultGuardConditionFromState(){
		Transition t= orderStateModel.getTransitionWithDefaultGuardConditionFromState(newOrderState);
		assertTrue(t.equals(authroizePendingTransition));
	}
	
	@Test
	public void testGetTransitionWithNoDefaultGuardConditionFromState(){
		Transition t= orderStateModel.getTransitionWithDefaultGuardConditionFromState(orderStateModel.getStateFromStateCode("FulfillmentPending"));
		assertTrue(t == null);
	}
	
}
