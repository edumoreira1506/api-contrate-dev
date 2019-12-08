package br.edu.utfpr.contratedev.controller;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.contratedev.model.dto.UserDTO;
import br.edu.utfpr.contratedev.model.entity.User;
import br.edu.utfpr.contratedev.model.repository.UserRepository;
import br.edu.utfpr.contratedev.model.service.UserService;
import br.edu.utfpr.contratedev.util.Response;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UserController {
	public static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value = "/inicializa")
	public void init() {
		userService.init();
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
}
