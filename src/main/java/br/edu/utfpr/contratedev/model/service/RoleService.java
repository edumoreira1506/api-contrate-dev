package br.edu.utfpr.contratedev.model.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.contratedev.model.entity.Role;
import br.edu.utfpr.contratedev.model.repository.RoleRepository;

@Service
public class RoleService {
	public static final Logger log = LoggerFactory.getLogger(RoleService.class);

	@Autowired
	RoleRepository roleRepository;

	public Role save(Role role) throws DataIntegrityViolationException {
        return roleRepository.save(role);
    }
}
