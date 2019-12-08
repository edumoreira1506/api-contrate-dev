package br.edu.utfpr.contratedev.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "role")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Role {
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column
	private String role;

	public Role(String token, String role) {
		super();
		this.role = role;
	}
}
