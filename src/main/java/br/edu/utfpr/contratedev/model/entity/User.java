package br.edu.utfpr.contratedev.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import br.edu.utfpr.contratedev.model.dto.TecnologyDTO;
import br.edu.utfpr.contratedev.model.dto.UserDTO;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String description;

	@Column
	private String cellphone;

	@Column
	private char gender;

	@Column
	private String github;

	@OneToOne
	private Role role;

	@OneToMany
	private Set<Tecnology> tecnologies;

	private Date created;
	private Date updated;

	public User(String name, String email, String password, String description, String cellphone, char gender,
			String github, Role role, Set<Tecnology> tecnologies) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.description = description;
		this.cellphone = cellphone;
		this.gender = gender;
		this.github = github;
		this.role = role;
		this.tecnologies = tecnologies;
	}

	public User(UserDTO user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.description = user.getDescription();
		this.cellphone = user.getCellphone();
		this.gender = user.getGender();
		this.github = user.getGithub();
		this.role = new Role(user.getRole());
		this.tecnologies = this.parseAllTecnologies(user.getTecnologies());
	}

	@PrePersist
	public void onSave() {
		final Date now = new Date();
		this.created = now;
		this.updated = now;
	}

	private Set<Tecnology> parseAllTecnologies(Set<TecnologyDTO> dtos) {
		Set<Tecnology> tecnologies = null;

		for (TecnologyDTO tecnology : dtos) {
			tecnologies.add(new Tecnology(tecnology));
		}

		return tecnologies;
	}

	public void update(UserDTO dto) {
		this.name = dto.getName();
		this.email = dto.getEmail();
		this.description = dto.getDescription();
		this.cellphone = dto.getCellphone();
		this.gender = dto.getGender();
		this.github = dto.getGithub();
	}
}
