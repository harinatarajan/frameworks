package com.ssudhaiyer.fsm.framework;

/**
 * StateDetails
 * <p/>
 * StateDetails helps to supplement the state with additional details as the stateaware object transitions through multiple states.
 *
 * @author sudha_subramanian
 */
public interface StateInfo {
  String getReason();

  void setReason(String reason);

  String getReferenceCode();

  void setReferenceCode(String referenceCode);

  String getMessage();

  void setMessage(String message);
}
