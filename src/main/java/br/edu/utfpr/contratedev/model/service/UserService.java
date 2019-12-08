package br.edu.utfpr.contratedev.model.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.contratedev.model.entity.Role;
import br.edu.utfpr.contratedev.model.entity.Tecnology;
import br.edu.utfpr.contratedev.model.entity.User;
import br.edu.utfpr.contratedev.model.repository.RoleRepository;
import br.edu.utfpr.contratedev.model.repository.UserRepository;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Sha256Generator;

@Service
public class UserService {
	public static final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	public void init() {
		String adminToken = Sha256Generator.generate("admin@admin|admin");
		Role roleAdmin = new Role(adminToken, Constants.ADMIN);
		roleRepository.save(roleAdmin);

		String emptyString = null;
		Set<Tecnology> emptyTecnologies = null;
		User admin = new User("Antonio Eduardo Moreira", "admin@admin", Sha256Generator.generate("admin"), emptyString, emptyString, 'M', emptyString, roleAdmin, emptyTecnologies);
	
		log.info("Inicializando o BD 1 admin");
		log.info("email: admin@admin");
		log.info("senha: admin");
		
		this.save(admin);
	}
	
	public User save(User user) throws DataIntegrityViolationException {
        log.info("Inserindo o usuário: {}", user.getName());
        return userRepository.save(user);
    }
}
