package br.edu.utfpr.contratedev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.contratedev.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
