package br.edu.utfpr.contratedev.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.edu.utfpr.contratedev.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
}
