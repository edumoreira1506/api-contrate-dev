package br.edu.utfpr.contratedev.model.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.edu.utfpr.contratedev.model.entity.Company;
import br.edu.utfpr.contratedev.model.entity.Job;
import br.edu.utfpr.contratedev.model.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JobDTO {
	private Long id;
	
	@NotEmpty(message = "O nome não pode ser vazio")
    @Pattern(regexp = "^(\\s?[A-ZÀ-Ú][a-zà-ú]*)+(\\s[a-zà-ú]*)?(\\s[A-ZÀ-Ú][a-zà-ú]*)+",
            message = "Insira o seu nome completo iniciando com letras maíusculas.")
	private String name;

	private Long salary;
	
	@Length(min = 2, max = 100, message = "Descrição da vaga deve ter entre 2 e 100 caracteres")
	private String description;

	private Set<UserDTO> candidates;

	@NotEmpty(message = "Empresa não pode ser vazio")
	private CompanyDTO company;

	public JobDTO(Long id,
			String name,
			Long salary,
			String description,
			Set<User> candidates,
			Company company) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = this.parseAllUsers(candidates);
		this.company = new CompanyDTO(company);
	}
	
	public JobDTO(Job job) {
		this.id = job.getId();
		this.name = job.getName();
		this.salary = job.getSalary();
		this.description = job.getDescription();
		this.candidates = this.parseAllUsers(job.getCandidates());
		this.company = new CompanyDTO(job.getCompany());
	}
	
	private Set<UserDTO> parseAllUsers(Set<User> users) {
		Set<UserDTO> dtoUsers = null;
		
		for (User user : users) {
			dtoUsers.add(new UserDTO(user));
		}
		
		return dtoUsers;
	}
}
