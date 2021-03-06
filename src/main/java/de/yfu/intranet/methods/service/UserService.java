package de.yfu.intranet.methods.service;

import java.util.UUID;

import de.yfu.intranet.methods.exceptions.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methods.data.domain.User;
import de.yfu.intranet.methods.data.repository.UserRepository;

import static java.lang.String.format;

@Service
public class UserService {
	
	private final UserRepository userRepo;
	
	@Autowired
	public UserService(final UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User findById(UUID userId) throws UserException {
		User user = userRepo.findById(userId).orElse(null);
		if (user == null) {
			throw new UserException(format("No User found for UserId [%s]", userId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return userRepo.findById(userId).orElse(null);
	}

}
