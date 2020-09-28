package com.banvien.myplatform.web.editor;


import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CustomTimestampEditor extends PropertyEditorSupport {
	private transient final Log log = LogFactory.getLog(CustomTimestampEditor.class);
	private String dateFormat = "dd-MM-yyyy";
	public CustomTimestampEditor(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	public CustomTimestampEditor(){}
	public void setAsText(String text) throws IllegalArgumentException {
		if (text != null && text.trim().length() > 1) {
			SimpleDateFormat format = new SimpleDateFormat(dateFormat);
			try {
				Date d = (Date) format.parse(text);
				setValue(new Timestamp((d.getTime())));
			} catch (Exception e) {
				// NO NEED TO PRINT OUT ERROR HERE
				//log.error("Invalid date format [" + dateFormat + "]" + e.getMessage());
			}
		}

	}
}
