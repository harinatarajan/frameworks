package com.ssudhaiyer.fsm.framework.impl;

import com.ssudhaiyer.fsm.framework.Event;
import com.ssudhaiyer.fsm.framework.EventCode;
import com.ssudhaiyer.fsm.framework.EventType;

public class DefaultEvent implements Event {

  private EventCode eventCode;
  private EventType eventType;

  public EventCode getEventCode() {
    return eventCode;
  }

  public EventType getEventType() {
    return eventType;
  }

  public void setEventCode(EventCode eventCode) {
    this.eventCode = eventCode;

  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;

  }

}
