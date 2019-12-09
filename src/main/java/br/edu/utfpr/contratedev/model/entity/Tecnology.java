package br.edu.utfpr.contratedev.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.utfpr.contratedev.model.dto.TecnologyDTO;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private Long experienceTime;

	private Date created;
    private Date updated;
	
	public Tecnology(String name, String description, Long experienceTime) {
		super();
		this.name = name;
		this.description = description;
		this.experienceTime = experienceTime;
	}
	
	public Tecnology(TecnologyDTO tecnology) {
		this.name = tecnology.getName();
		this.description = tecnology.getDescription();
		this.experienceTime = tecnology.getExperienceTime();
	}
}
