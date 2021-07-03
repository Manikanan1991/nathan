package com.audit.controller;

import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.audit.AuthencationResponse;
import com.audit.AuthenticationRequest;
import com.audit.JwtUtil;
import com.audit.MyUserDetailsService;
import com.audit.domain.ClientDomain;
import com.audit.service.ClientService;
import com.audit.util.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
public class ClientController {

	final static Logger logger = Logger.getLogger(ClientController.class);

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;

	@Autowired
	ClientService clientService;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JSONUtil jsonUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthencationToken(@RequestBody AuthenticationRequest authencationRequest)
			throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authencationRequest.getUsername(), authencationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect Username or Password", e);
		}
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authencationRequest.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthencationResponse(jwt));
	}

	@GetMapping("/home")
	public String homeInit() {
		return "home";
	}

	@GetMapping("/homes/{id}")
	public String getMembers(@PathParam("id") String id) {
		return "homes";
	}

	@PostMapping(path = "/members")
	public String saveClient(@RequestBody String persistenceRequest) {
		ClientDomain clientDomain;
		try {
			logger.info("Savemthod is calling");
			clientDomain = jsonUtil.getObjectMapper().readValue(persistenceRequest, ClientDomain.class);
			return clientService.saveClient(clientDomain);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return "true";
	}

}
