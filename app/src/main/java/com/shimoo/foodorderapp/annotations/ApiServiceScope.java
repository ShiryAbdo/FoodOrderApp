package com.shimoo.foodorderapp.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApiServiceScope {
    // Could be replaced by Singleton
}