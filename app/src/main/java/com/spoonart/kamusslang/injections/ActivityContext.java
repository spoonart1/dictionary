package com.spoonart.kamusslang.injections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Qualifier;

/**
 * Created by lafran on 4/14/17.
 */
@Qualifier @Retention(RetentionPolicy.RUNTIME) public @interface ActivityContext {
}
