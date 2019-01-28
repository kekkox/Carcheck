package it.carcheck.fastcrud.core;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import it.carcheck.fastcrud.core.enums.PKType;
import it.carcheck.fastcrud.core.enums.Type;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public
@interface EntityType
{
	Type type();
	PKType pkType();
}
