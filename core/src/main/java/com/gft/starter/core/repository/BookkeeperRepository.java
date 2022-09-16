package com.gft.starter.core.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gft.starter.core.model.Bookkeeper;

@Repository
public interface BookkeeperRepository extends JpaRepository<Bookkeeper, UUID> {

}
