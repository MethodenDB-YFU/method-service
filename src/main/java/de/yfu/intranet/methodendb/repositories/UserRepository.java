package de.yfu.intranet.methodendb.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.yfu.intranet.methodendb.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {

}