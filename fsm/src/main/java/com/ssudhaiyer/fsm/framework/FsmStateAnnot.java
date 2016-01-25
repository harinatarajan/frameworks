package com.ssudhaiyer.fsm.framework;

/**
 * FSMState
 * <p/>
 * Annotate the concrete state classes with this annotation.
 * <p/>
 * The name specified in the annotation will be used by the AbstractStateClass onEntry method to set the
 * current state of the state aware object appropriately.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface FsmStateAnnot {
	String name();
}
