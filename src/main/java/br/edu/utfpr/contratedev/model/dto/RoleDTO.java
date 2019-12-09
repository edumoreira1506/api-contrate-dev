package br.edu.utfpr.contratedev.model.dto;


import javax.validation.constraints.NotEmpty;

import br.edu.utfpr.contratedev.model.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RoleDTO {
	private Long id;

	@NotEmpty(message = "A alçada não pode ser vazia")
	private String role;
	
	@NotEmpty
	private String token;

	public RoleDTO(Long id, @NotEmpty(message = "A alçada não pode ser vazia") String role) {
		super();
		this.id = id;
		this.role = role;
		this.token = token;
	}
	
	public RoleDTO(Role role) {
		this.id = role.getId();
		this.role = role.getRole();
		this.token = role.getToken();
	}
}
