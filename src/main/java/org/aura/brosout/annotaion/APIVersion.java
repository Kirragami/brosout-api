package org.aura.brosout.annotaion;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@RequestMapping
@RestController
public @interface APIVersion {
@AliasFor(annotation = RequestMapping.class, attribute = "path")
    String apiPath() default "/api/v1";
}
