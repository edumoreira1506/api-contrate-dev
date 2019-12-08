package br.edu.utfpr.contratedev.model.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.edu.utfpr.contratedev.model.entity.Tecnology;
import br.edu.utfpr.contratedev.model.entity.User;

public class UserDTO {
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
	@Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]*)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+",
    message = "Insira o seu nome completo iniciando com letras maíusculas.")
	private String name;
	
	@NotEmpty
	private String email;
    
	@NotEmpty
	@Length(min = 6, max = 15, message = "Senha dev conter no mínimo 6 e máximo 15 caracteres.")
    private String password;
    
	@Length(min = 2, max = 100, message = "Descrição deve conter no mínimo 2 e máximo 100 caracteres.")
	private String description;
    
	private String cellphone;
    
	@NotEmpty
	private char gender;
    
    private String github;
    
    @NotEmpty
    private RoleDTO role;
    
    private Set<TecnologyDTO> languages;
    
    public UserDTO(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
    	this.email = user.getEmail();
    	this.password = user.getPassword();
    	this.description = user.getDescription();
    	this.cellphone = user.getCellphone();
    	this.gender = user.getGender();
    	this.github = user.getGithub();
    	this.role = new RoleDTO(user.getRole());
    	this.languages = this.parseAllTecnologies(user.getLanguages());
    }

	public UserDTO(Long id,
			String name,
			String email,
			String password,
			String description,
			String cellphone,
			char gender,
			String github,
			RoleDTO role,
			Set<Tecnology> languages) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.description = description;
		this.cellphone = cellphone;
		this.gender = gender;
		this.github = github;
		this.role = role;
		this.languages = this.parseAllTecnologies(languages);
	}
	
	private Set<TecnologyDTO> parseAllTecnologies(Set<Tecnology> tecnologies) {
		Set<TecnologyDTO> tecnologiesDTO = null;
		
		for(Tecnology tecnology : tecnologies) {
			tecnologiesDTO.add(new TecnologyDTO(tecnology));
		}
		
		return tecnologiesDTO;
	}
}
