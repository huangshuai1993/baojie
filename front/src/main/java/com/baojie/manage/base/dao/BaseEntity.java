package com.baojie.manage.base.dao;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.persistence.Id;

import org.apache.log4j.Logger;

public abstract class BaseEntity implements IEntity {
	
	private static final long serialVersionUID = -589196211003058465L;

	private static final Logger logger = Logger.getLogger(BaseEntity.class);
	
	private static final ConcurrentMap<Class<?>, Field> PK_FIELD_CACHE = new ConcurrentHashMap<Class<?>, Field>();

	public Long pkValue() {
		Class<?> clazz = this.getClass();
        Field field = null;
        if (PK_FIELD_CACHE.containsKey(clazz)) {
            field = PK_FIELD_CACHE.get(clazz);
        } else {
            synchronized (clazz) {
                field = getPrimaryKeyField(clazz);
                PK_FIELD_CACHE.put(clazz, field);
            }
        }
        
        if (field != null) {
            try {
                Object value = field.get(this);
                if (value == null) {
                    return null;
                }
                if (value instanceof Long) {
                    return (Long) value;
                } else if (value instanceof Integer) {
                    return (long) ((Integer) value).intValue();
                }
            } catch (Exception e) {
                logger.warn(clazz + " get primary key value error", e);
            }
        }

        return null;
	}

	private Field getPrimaryKeyField(Class<?> clazz) {
        AccessibleObject[] fields = clazz.getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (AccessibleObject ao : fields) {
                ao.setAccessible(true);
                Field field = (Field) ao;
                Id primaryKey = (Id) field.getAnnotation(Id.class);
                if (primaryKey != null) {
                    return field;
                }
            }
        }
        return null;
    }
}
