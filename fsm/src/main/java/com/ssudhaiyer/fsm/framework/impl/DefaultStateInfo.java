package com.ssudhaiyer.fsm.framework.impl;

import com.ssudhaiyer.fsm.framework.StateInfo;

public class DefaultStateInfo implements StateInfo {

  private String reason;
  private String referenceCode;
  private String message;

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;

  }

  public String getReferenceCode() {
    return this.referenceCode;
  }

  public void setReferenceCode(String referenceCode) {
    this.referenceCode = referenceCode;

  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;

  }

}
