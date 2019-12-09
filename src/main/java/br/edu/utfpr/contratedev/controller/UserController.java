package br.edu.utfpr.contratedev.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.contratedev.model.dto.UserDTO;
import br.edu.utfpr.contratedev.model.entity.Role;
import br.edu.utfpr.contratedev.model.entity.User;
import br.edu.utfpr.contratedev.model.repository.UserRepository;
import br.edu.utfpr.contratedev.model.service.RoleService;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Constants;
import br.edu.utfpr.contratedev.util.Response;
import br.edu.utfpr.contratedev.util.Sha256Generator;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UserController {
	public static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	UserRepository userRepository;

	@GetMapping(value = "/inicializa")
	public void init() {
		userService.init();
	}

	@PostMapping
	public ResponseEntity<Response<UserDTO>> post(@Valid @RequestBody UserDTO dto, BindingResult result) {
		Response<UserDTO> response = new Response<>();
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
		String token = Sha256Generator.generate(dto.getEmail() + "|user");
		Role role = new Role(token, Constants.USER);
		roleService.save(role);

		User user = new User(dto.getName(), dto.getEmail(), Sha256Generator.generate(dto.getPassword()),
				dto.getDescription(), dto.getCellphone(), dto.getGender(), dto.getGithub(), role, null);
		user = userService.save(user);

		dto = new UserDTO(user);
		response.setData(dto);
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<UserDTO>> findById(@PathVariable Long id) {

		Response<UserDTO> response = new Response<>();

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new EntityNotFoundException();
		}

		UserDTO dto = new UserDTO(user.get());
		response.setData(dto);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Response<String>> deleteById(@PathVariable Long id) {
		Response<String> response = new Response<>();
		Optional<User> o = userRepository.findById(id);

		if (!o.isPresent()) {
			log.info("Erro ao remover");
			response.addError("Erro ao remover, registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.userRepository.deleteById(id);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody UserDTO dto, BindingResult result) {
		Response<UserDTO> response = new Response<>();
		if (result.hasErrors()) {
			response.setErrors(result);
			return ResponseEntity.badRequest().body(response);
		}

		Optional<User> o = userRepository.findById(id);
		if (!o.isPresent()) {
			response.addError("Usuário não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		User s = o.get();

		if (!s.getEmail().equals(dto.getEmail())) {
			response.addError("Não é possível atualizar o e-mail.");
			return ResponseEntity.badRequest().body(response);
		}

		s.update(dto);
		s = userService.save(s);

		dto = new UserDTO(s);
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
}
