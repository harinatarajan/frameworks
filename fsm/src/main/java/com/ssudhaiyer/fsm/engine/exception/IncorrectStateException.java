package com.ssudhaiyer.fsm.engine.exception;


public class IncorrectStateException extends StateEngineException {
  public IncorrectStateException(String message) {
    super(message);
  }

  public IncorrectStateException(Exception exception, String message) {
    super(message);
    initCause(exception);
  }
}
