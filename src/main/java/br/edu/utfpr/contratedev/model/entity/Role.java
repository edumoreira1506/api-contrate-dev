package br.edu.utfpr.contratedev.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column
	private String role;
	
	@Column
	private String token;
	
	private Date created;
    private Date updated;

	public Role(String token, String role) {
		super();
		this.role = role;
		this.token = token;
	}
}
