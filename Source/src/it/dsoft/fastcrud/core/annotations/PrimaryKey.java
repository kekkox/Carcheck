package it.dsoft.fastcrud.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import it.dsoft.fastcrud.core.enums.PrimaryKeyOption;

/**
 * Database primary key annotation
 * @author Daniele De Falco
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public
@interface PrimaryKey {
	PrimaryKeyOption option();
}