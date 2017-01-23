package ru.romaberendeev.numbers.di.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by roma on 19.12.2016.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerInstance {
}
