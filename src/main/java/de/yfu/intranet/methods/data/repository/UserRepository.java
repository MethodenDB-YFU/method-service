package de.yfu.intranet.methods.data.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methods.data.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}