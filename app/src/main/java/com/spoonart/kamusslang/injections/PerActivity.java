package com.spoonart.kamusslang.injections;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by lafran on 4/14/17.
 */
@Scope @Retention(RetentionPolicy.RUNTIME) public @interface PerActivity {
}
