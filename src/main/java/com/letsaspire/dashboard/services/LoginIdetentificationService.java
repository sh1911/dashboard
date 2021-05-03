package com.letsaspire.dashboard.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsaspire.dashboard.domain.UserLoginDetails;
import com.letsaspire.dashboard.repositories.UserLoginDetailsRepository;
import com.letsaspire.dashboard.util.JwtUtil;
import com.letsaspire.dashboard.util.Utility;

import ua_parser.Client;
import ua_parser.Parser;

@Service
public class LoginIdetentificationService {

	
	@Autowired
	UserLoginDetailsRepository userLoginDetailsRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public Map<String,Object> getClaims(HttpServletRequest httpServletRequest,UUID userId) {
		String response = null;
		String hostName = null;
		String devicename = null;
		String userAgent = null;
		Map<String,Object> parameterClaims = new HashedMap<>();
		try {
			
			userAgent = getClientUA(httpServletRequest);
			
			InetAddress inetAddress = InetAddress.getByName(httpServletRequest.getRemoteAddr());
			
			hostName = inetAddress.getHostName();
			
			
			if(hostName.equalsIgnoreCase("localhost")) {
				hostName = java.net.InetAddress.getLocalHost().getHostName();
			}
			Parser uaParser = new Parser();
			Client c = uaParser.parse(userAgent);
			
			UserLoginDetails userLoginDetails = new UserLoginDetails();
			userLoginDetails.setUserId(userId);
			userLoginDetails.setDeviceName(hostName);
			if(c.userAgent.family.equals("others")&&c.userAgent.major==null) {
				userLoginDetails.setDeviceClient(userAgent);
			}else {
				userLoginDetails.setDeviceClient(c.userAgent.family+" "+c.userAgent.major+"."+c.userAgent.minor);
			}
			userLoginDetails.setDeviceOs(c.os.family);
			userLoginDetails.setStatus("UNBLOCKED");
			userLoginDetails.setLogInDateTime(Utility.getCurrentDateAndTime());
			userLoginDetailsRepository.save(userLoginDetails);
		
			parameterClaims.put("remoteaddr",httpServletRequest.getRemoteAddr());
			parameterClaims.put("os",c.os.family);
			parameterClaims.put("remoteclient",c.userAgent.family+" "+c.userAgent.major+"."+c.userAgent.minor);
			parameterClaims.put("hostname",hostName);
			
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	return parameterClaims;
		
	}
	public String getClientUA(HttpServletRequest request) {

        String userAgent = "";
        


        if (request != null) {
        	userAgent = request.getHeader("user-agent");
            if (userAgent == null || "".equals(userAgent)) {
            	userAgent = request.getRemoteAddr();
            }
        }

        return userAgent;
    }
	public boolean compareRequestPayload(String token,HttpServletRequest httpServletRequest) {
		
		String hostName = null;
		
		String userAgent = null;
		try {
			
			userAgent = getClientUA(httpServletRequest);
			InetAddress inetAddress = InetAddress.getByName(httpServletRequest.getRemoteAddr());
			hostName = inetAddress.getHostName();
			if(hostName.equalsIgnoreCase("localhost")) {
				hostName = java.net.InetAddress.getLocalHost().getHostName();
			}
			if(jwtUtil.getClaim("hostname", token).asString().equals(hostName)){
				return true;
			}else
				return false;
			
		
		}catch (UnknownHostException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
