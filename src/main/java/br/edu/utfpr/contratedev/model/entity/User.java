package br.edu.utfpr.contratedev.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
public class User {
	@Id
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
    
    @Column
    private Role role;
    
    @OneToMany
    private Set<Tecnology> languages;

	public User(String name, String email, String password, String description, String cellphone, char gender,
			String github, Role role, Set<Tecnology> languages) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.description = description;
		this.cellphone = cellphone;
		this.gender = gender;
		this.github = github;
		this.role = role;
		this.languages = languages;
	}
}
