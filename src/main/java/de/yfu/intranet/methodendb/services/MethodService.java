package de.yfu.intranet.methodendb.services;

import static java.lang.String.format;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.exceptions.MethodException;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.repositories.MethodLevelRepository;
import de.yfu.intranet.methodendb.repositories.MethodRepository;
import de.yfu.intranet.methodendb.repositories.MethodTypeRepository;

@Service
public class MethodService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodService.class);
	private final MethodTypeRepository methodTypeRepo; 
	private final MethodLevelRepository methodLevelRepo;
	private final MethodRepository methodRepo; 

	@Autowired
	public MethodService(final MethodTypeRepository methodTypeRepository,
			final MethodLevelRepository methodLevelRepository,
			final MethodRepository methodRepository) {
		this.methodTypeRepo = methodTypeRepository;
		this.methodLevelRepo = methodLevelRepository;
		this.methodRepo = methodRepository;
	}

	public Set<MethodType> getAllMethodTypes() {
		Set<MethodType> methodTypes = methodTypeRepo.findAll();
		if (methodTypes == null) {
			LOGGER.info("No Method Types found.");
			return Collections.emptySet();
		}
		return methodTypes;
	}

	public MethodType createMethodType(MethodType methodType) {
		return methodTypeRepo.save(methodType);
	}

	public MethodType updateMethodType(MethodType methodType) throws MethodException {
		MethodType storedMethodType = methodTypeRepo.findOne(methodType.getId());
		if (storedMethodType != null) {
			return methodTypeRepo.save(methodType);
		}
		throw new MethodException(format("No Method Type found for id [%s]. Not able to update.", methodType.getId()), HttpStatus.NOT_FOUND);

	}

	public Set<MethodLevel> getAllMethodLevels() {
		Set<MethodLevel> methodLevels = methodLevelRepo.findAll();
		if (methodLevels == null) {
			LOGGER.info("No Method Levels found.");
			return Collections.emptySet();
		}
		return methodLevels;
	}

	public MethodLevel createMethodLevel(MethodLevel methodLevel) {
		return methodLevelRepo.save(methodLevel);
	}

	public MethodLevel updateMethodLevel(MethodLevel methodLevel) throws MethodException {
		MethodLevel storedMethodLevel = methodLevelRepo.findOne(methodLevel.getId());
		if (storedMethodLevel != null) {
			return methodLevelRepo.save(methodLevel);
		}
		throw new MethodException(format("No Method Level found for id [%s]. Not able to update.", methodLevel.getId()), HttpStatus.NOT_FOUND);
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