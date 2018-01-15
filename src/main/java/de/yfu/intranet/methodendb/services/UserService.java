package de.yfu.intranet.methodendb.services;

import java.util.UUID;

import de.yfu.intranet.methodendb.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methodendb.models.User;
import de.yfu.intranet.methodendb.repositories.UserRepository;

import static java.lang.String.format;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	
	@Autowired
	public UserService(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User findById(UUID userId) throws UserException {
		User user = userRepo.findOne(userId);
		if (user == null) {
			throw new UserException(format("No User found for UserId [%s]", userId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userRepo.findOne(userId);
	}

}
