package br.edu.utfpr.contratedev.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.contratedev.model.dto.UserDTO;
import br.edu.utfpr.contratedev.model.entity.Role;
import br.edu.utfpr.contratedev.model.entity.User;
import br.edu.utfpr.contratedev.model.repository.RoleRepository;
import br.edu.utfpr.contratedev.model.repository.UserRepository;
import br.edu.utfpr.contratedev.model.service.RoleService;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Response;
import br.edu.utfpr.contratedev.util.Sha256Generator;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin(origins = "*")
public class AdminController {
	public static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@PostMapping
	public ResponseEntity<Response<UserDTO>> post(@Valid @RequestBody UserDTO dto, BindingResult result,  @RequestHeader Map<String, String> headers) {
		String token = null;
		
		for(Entry<String, String> headerField : headers.entrySet()) {
			if (headerField.getKey().equals("token")) {
				token = headerField.getValue();
			}
		}
		
		Response<UserDTO> response = new Response<>();
		
		if (token == null) {
			response.addError("Token inexistente.");
			return ResponseEntity.badRequest().body(response);
		}
		
		Role role = roleRepository.findByToken(token);
		
		if (role == null || !role.getRole().equals(Constants.ADMIN)) {
			response.addError("Token inválido.");
			return ResponseEntity.badRequest().body(response);
		}
		
		if (result.hasErrors()) {
			response.setErrors(result);
			return ResponseEntity.badRequest().body(response);
		}

		if (dto.getId() != null) {
			Optional<User> o = userRepository.findById(dto.getId());
			if (o.isPresent()) {
				response.addError("Usuário já cadastrado.");
				return ResponseEntity.badRequest().body(response);
			}
		}
		String tokenAdmin = Sha256Generator.generate(dto.getEmail() + "|admin");
		Role roleAdmin = new Role(tokenAdmin, Constants.ADMIN);
		roleService.save(roleAdmin);

		User user = new User(dto.getName(), dto.getEmail(), Sha256Generator.generate(dto.getPassword()),
				dto.getDescription(), dto.getCellphone(), dto.getGender(), dto.getGithub(), role, null);
		user = userService.save(user);

		dto = new UserDTO(user);
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
}
