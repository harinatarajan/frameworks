package com.ssudhaiyer.fsm.engine.exception;


public class NoHandlerForEventException extends StateEngineException {
  public NoHandlerForEventException(String message) {
    super(message);
  }

  public NoHandlerForEventException(Exception exception, String message) {
    super(message);
    initCause(exception);
  }
}
