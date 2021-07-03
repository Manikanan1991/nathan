package com.audit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.audit.domain.ClientDomain;
import com.audit.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public String saveClient(ClientDomain clientDomain) {
		try {
			clientRepository.save(clientDomain);
		} catch (Exception e) {
			return "failure.";
		}
		return "sucess";
	}
}
