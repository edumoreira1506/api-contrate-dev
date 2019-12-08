package br.edu.utfpr.contratedev.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "company")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String name;

	@Column
	private String description;

	@OneToOne
	private User manager;
	
	private Date created;
    private Date updated;

	public Company(String name, String description, User manager) {
		super();
		this.name = name;
		this.description = description;
		this.manager = manager;
	}
}
