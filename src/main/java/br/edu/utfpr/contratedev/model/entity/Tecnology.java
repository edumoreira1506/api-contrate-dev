package br.edu.utfpr.contratedev.model.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tecnology")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Tecnology {
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Long experienceTime;

	public Tecnology(String name, String description, Long experienceTime) {
		super();
		this.name = name;
		this.description = description;
		this.experienceTime = experienceTime;
	}
}
