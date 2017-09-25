package com.currencycloud.coolpay.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

import com.currencycloud.coolpay.mb.LoginMB;

/**
 * The purpose of this qualifier is permit the user interface verify whether
 * there is a user logged on the system.
 * 
 * @author Tranquilo Dognini Neto
 * @version 1.0
 * 
 * @see LoginMB
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD })
public @interface LoggedIn {
}