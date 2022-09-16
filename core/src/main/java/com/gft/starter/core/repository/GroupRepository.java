package com.gft.starter.core.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.starter.core.model.Group;

//Declarando que a interface é do tipo repository e passando os parametros
// sendo o primeiro a classe Group e o segundo o tipo da chave primária
@Repository
public interface GroupRepository extends JpaRepository<Group, UUID> {

}
