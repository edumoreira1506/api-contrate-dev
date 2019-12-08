package br.edu.utfpr.contratedev.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.utfpr.contratedev.model.entity.Company;
import br.edu.utfpr.contratedev.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CompanyDTO {
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
	private String name;
	
	@Length(min = 2, max = 100, message = "Descrição da empresa deve ter entre 2 e 100 caracteres")
	private String description;
	
	@NotEmpty(message = "Gerente da empresa é obrigatório")
	private UserDTO manager;

	public CompanyDTO(Long id, String name, String description, User manager) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.manager = new UserDTO(manager);
	}
	
	public CompanyDTO(Company company) {
		this.id = company.getId();
		this.name = company.getName();
		this.description = company.getDescription();
		this.manager = new UserDTO(company.getManager());
	}
}
