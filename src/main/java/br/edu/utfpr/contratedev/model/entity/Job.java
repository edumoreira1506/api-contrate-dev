package br.edu.utfpr.contratedev.model.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "job")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column
	private String name;
    
	@Column
    private Long salary;
    
	@Column
    private String description;
    
    @ManyToMany
    private Set<User> candidates;
    
    @ManyToOne
    private Company company;
    
    private Date created;
    private Date updated;

	public Job(String name, Long salary, String description, Set<User> candidates, Company company) {
		super();
		this.name = name;
		this.salary = salary;
		this.description = description;
		this.candidates = candidates;
		this.company = company;
	}
    
    
}
