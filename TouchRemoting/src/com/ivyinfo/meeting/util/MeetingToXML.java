package com.ivyinfo.meeting.util;

public class MeetingToXML {
	
	public static StringBuffer createTagFromString(String tag, String propertyValue) {
        StringBuffer output = new StringBuffer();
        if (propertyValue != null) {
            if (propertyValue.length() > 0) {
                output.append('<').append(tag).append('>');
//                output.append(StringTools.escapeBasicXml(new String(Base64.encode(propertyValue.getBytes()))));
                output.append(propertyValue);
                output.append("</").append(tag).append('>');
            } else {
            	output.append('<').append(tag).append('>');
                output.append("</").append(tag).append(">");
            }
        }
        return output;
    }
}
