package com.letsaspire.dashboard.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class ClientInfoUtil {
	
	public static String getClientIp(HttpServletRequest request) {

        String remoteAddr = "";
        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            result.put(key, value);
            System.out.println("key: "+key+"value: "+value);
        }


        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

	public static String getClientUA(HttpServletRequest request) {

        String userAgent = "";
        


        if (request != null) {
        	userAgent = request.getHeader("user-agent");
            if (userAgent == null || "".equals(userAgent)) {
            	userAgent = request.getRemoteAddr();
            }
        }

        return userAgent;
    }

}
