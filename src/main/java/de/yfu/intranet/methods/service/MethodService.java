package de.yfu.intranet.methods.service;

import static java.lang.String.format;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.data.domain.Method;
import de.yfu.intranet.methods.data.domain.MethodLevel;
import de.yfu.intranet.methods.data.domain.MethodType;
import de.yfu.intranet.methods.data.repository.MethodLevelRepository;
import de.yfu.intranet.methods.data.repository.MethodRepository;
import de.yfu.intranet.methods.data.repository.MethodTypeRepository;

@Service
public class MethodService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodService.class);
	private final MethodLevelRepository methodLevelRepo;
	private final MethodRepository methodRepo; 

	@Autowired
	public MethodService(final MethodLevelRepository methodLevelRepository,
			final MethodRepository methodRepository) {
		this.methodLevelRepo = methodLevelRepository;
		this.methodRepo = methodRepository;
	}



	public Set<Method> getAllMethods(UUID userId) throws MethodException {
		LOGGER.info("Getting all Methods for User [{}]", userId);
		Set<Method> methods = methodRepo.findAll();
		if(methods == null) {
			throw new MethodException("No methods found.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return methods;
	}
	
	public Method getMethod(UUID userId, UUID methodId) throws MethodException {
		LOGGER.info("Getting Method [{}] for User [{}]", methodId, userId);
		Method method = methodRepo.findOne(methodId);
		if(method == null) {
			throw new MethodException(String.format("Could not find method with id [%s]", methodId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return method;
	}
	
	public Method createMethod(Method method) {
		return methodRepo.save(method);
	}
	
	public Method updateMethod(Method method) throws MethodException {
		Method storedMethod = methodRepo.findOne(method.getId());
		if (storedMethod != null) {
			return methodRepo.save(method);
		}
		throw new MethodException(format("No Method found for ID [%s].", method.getId()), HttpStatus.NOT_FOUND);
	}

    public void deleteMethod(UUID userId, UUID methodId) throws MethodException {
		Method storedMethod = methodRepo.findOne(methodId);
		if (storedMethod == null) {
			throw new MethodException(format("No Method found for ID [%s].", methodId), HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Deleting Method [{}] by request of User [{}].", methodId, userId);
		methodRepo.delete(methodId);
    }
}