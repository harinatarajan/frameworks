package com.ssudhaiyer.fsm.framework;

/**
 * GuardCondition
 * <p/>
 * The guard condition on a transition will be executed to determine which transition the state engine should take.
 *
 * @author sudha_subramanian
 */
public interface GuardCondition {
  boolean execute(StateAwareWrapper stateAwareWrapper) throws Exception;
}
