package com.banvien.myplatform.web.editor;


import java.beans.PropertyEditorSupport;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PojoEditor extends PropertyEditorSupport {
	private Class pojoType = null;
	
	private transient final Log log = LogFactory.getLog(PojoEditor.class);
	
	private String idFieldName;
	
	private Class idFieldType;
	
	
	public PojoEditor(Class pojoType, String idFieldName, Class idFieldType) {
		this.pojoType = pojoType;
		this.idFieldName = idFieldName;
		this.idFieldType = idFieldType;
	}

	public void setAsText(String id) throws IllegalArgumentException {
		if (pojoType == null) {
			setValue(null);
		} else {
			try {
				Object pojo = pojoType.newInstance();
				Method m = pojoType.getMethod("set" + StringUtils.capitalize(idFieldName),
						new Class[] { idFieldType });
				if (idFieldName.equals(Integer.class)) {
					m.invoke(pojo, Integer.valueOf(id));
				}else if (idFieldType.equals(Long.class)) {
					m.invoke(pojo, Long.valueOf(id));
				}else if(idFieldType.equals(String.class)) {
					m.invoke(pojo, id);
				}
				setValue(pojo);
			} catch (Exception e) {
				log.debug(e.getMessage());
				setValue(null);
			}
		}

	}
}
