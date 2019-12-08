package br.edu.utfpr.contratedev.model.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.utfpr.contratedev.model.entity.Tecnology;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class TecnologyDTO {
    private Long id;
	
    @NotEmpty(message = "O nome não pode ser vazio")
	private String name;
	
    @Length(min = 2, max = 100, message = "Descrição da tecnologia deve conter no mínimo 2 e máximo 100 caracteres.")
	private String description;
	
	private Long experienceTime;

	public TecnologyDTO(Long id,
			String name,
			String description,
			Long experienceTime) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.experienceTime = experienceTime;
	}
	
	public TecnologyDTO(Tecnology tecnology) {
		this.id = tecnology.getId();
		this.name = tecnology.getName();
		this.description = tecnology.getDescription();
		this.experienceTime = tecnology.getExperienceTime();
	}
}
