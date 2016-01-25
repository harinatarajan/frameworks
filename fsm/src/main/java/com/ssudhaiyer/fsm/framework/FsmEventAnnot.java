package com.ssudhaiyer.fsm.framework;

/**
 * FsmEventAnnot
 * <p/>
 * Use FsmEventAnnot on a State class'es method, to define the event the method handles.
 * <p/>
 * If an event matching the event code of the method arrives, the method will be executed by the state engine.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface FsmEventAnnot {

	String name();

	String def() default "false";
}
