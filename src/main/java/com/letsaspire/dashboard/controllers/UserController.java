package com.letsaspire.dashboard.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.interfaces.Claim;
import com.letsaspire.dashboard.domain.UserCredentials;
import com.letsaspire.dashboard.repositories.UserCredentialsRepository;
import com.letsaspire.dashboard.repositories.UserLoginDetailsRepository;
import com.letsaspire.dashboard.request.AuthRequest;
import com.letsaspire.dashboard.request.RegisterRequest;
import com.letsaspire.dashboard.services.LoginIdetentificationService;
import com.letsaspire.dashboard.util.ClientInfoUtil;
import com.letsaspire.dashboard.util.JwtUtil;


@RestController
@RequestMapping("resource")
public class UserController {
	
	
	@Autowired
	UserCredentialsRepository userCredentialsRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	ClientInfoUtil clientInfoUtil;
	
	@Autowired
	UserLoginDetailsRepository userLoginDetailsRepository;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	LoginIdetentificationService loginIdetentificationService;
	
	private UserCredentials user;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody RegisterRequest newRegister) {
		String response = null;
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail(newRegister.getEmail());
		userCredentials.setPhoneNumber(newRegister.getPhoneNumber());
		userCredentials.setId(newRegister.getUserId());
		userCredentials.setRole("USER");
		userCredentials.setPassword(newRegister.getPassword());
		userCredentialsRepository.save(userCredentials);
		response = "Sucess";
		return response;
	}
	@PostMapping(path = "/authenticate",consumes = "application/json")
	public ResponseEntity<String> generateToken(HttpServletRequest httpServletRequest,@RequestBody AuthRequest authRequest) throws Exception 
	{
		String tokenString = null;
		try 
		{
			if(authRequest.getPhonenumber() == null) {
				this.user = userCredentialsRepository.findByEmail(authRequest.getEmail());
				authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(),authRequest.getPassword()));    
				tokenString = authRequest.getEmail();
			}else if(authRequest.getEmail() ==null) {
					this.user = userCredentialsRepository.findByPhoneNumber(authRequest.getPhonenumber());
					if(this.user !=null) {
					authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(this.user.getEmail(),authRequest.getPassword())); 
					tokenString = String.valueOf(authRequest.getPhonenumber());
					}
					else {
						authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getPhonenumber(),authRequest.getPassword())); 
						
					}
			}
			
			Map<String,Object> claims = loginIdetentificationService.getClaims(httpServletRequest,this.user.getId());
			return new ResponseEntity<String>(jwtUtil.generateToken(tokenString,claims),HttpStatus.OK);
			
		} 
		catch (Exception ex)
		{
			try {
				this.user=null;
				if (authRequest.getPhonenumber() != null && authRequest.getPhonenumber() == 0)
					return new ResponseEntity<String>("Bad Credentials,Invalid phone number",HttpStatus.UNAUTHORIZED);
				else if (authRequest.getEmail() != null && authRequest.getEmail().equals("") )
					return new ResponseEntity<String>("Bad Credentials,Invalid emailId",HttpStatus.UNAUTHORIZED);
				if(authRequest.getPhonenumber() == null) {
					this.user=userCredentialsRepository.findByEmail(authRequest.getEmail());
				}else if(authRequest.getEmail() ==null) {
					this.user=userCredentialsRepository.findByPhoneNumber(authRequest.getPhonenumber());
				}				
				if(this.user==null )
					return new ResponseEntity<String>("Your are not yet registerd..Please register then try again",HttpStatus.UNAUTHORIZED);
				}catch (Exception e) {
					return new ResponseEntity<String>("Something went wrong...Try again",HttpStatus.INTERNAL_SERVER_ERROR);
				}
			return new ResponseEntity<String>("Bad Credentials,Incorrect email or password",HttpStatus.UNAUTHORIZED);
		        
		    }
		    
	}
	
	@GetMapping("/extract/{key}")
	public String getAllclaims(@PathVariable("key") String key,HttpServletRequest httpServletRequest,@RequestBody com.letsaspire.dashboard.request.Token token) {
		Claim response = null;
		String tokenValue = token.getToken();
		response = jwtUtil.getClaim(key, tokenValue);
		return response.toString();
		
	}
	@GetMapping("/check")
	public boolean check(HttpServletRequest httpServletRequest) {
		String tokenValue = "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MTk5NTU3NzIsIm9zIjoiT3RoZXIiLCJzdWIiOiJub29yaHNhaWsxNTE4QGdtYWlsLmNvbSIsImlhdCI6MTYxOTk0ODU3MiwicmVtb3RlYWRkciI6IjEyNy4wLjAuMSIsImhvc3RuYW1lIjoic2hhaWtub29yIiwicmVtb3RlY2xpZW50IjoiT3RoZXIgbnVsbC5udWxsIn0.DdVUMTIY_A7l9fRZrUfkntJQGW7UlCynG95u9wvZn0w";
		
		return loginIdetentificationService.compareRequestPayload(tokenValue, httpServletRequest);
	}

}
