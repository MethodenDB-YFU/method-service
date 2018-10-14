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
import de.yfu.intranet.methods.data.repository.MethodLevelRepository;
import de.yfu.intranet.methods.data.repository.MethodRepository;

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

	public Set<Method> getAllMethods() throws MethodException {
		Set<Method> methods = methodRepo.findAll();
		if(methods == null) {
			throw new MethodException("No methods found.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return methods;
	}
	
	public Method getMethod(UUID methodId) throws MethodException {
		Method method = methodRepo.findById(methodId).orElse(null);
		if(method == null) {
			throw new MethodException(String.format("Could not find method with id [%s]", methodId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return method;
	}
	
	public Method createMethod(Method method) {
		return methodRepo.save(method);
	}
	
	public Method updateMethod(Method method) throws MethodException {
		Method storedMethod = methodRepo.findById(method.getId()).orElse(null);
		if (storedMethod != null) {
			return methodRepo.save(method);
		}
		throw new MethodException(format("No Method found for ID [%s].", method.getId()), HttpStatus.NOT_FOUND);
	}

    public void deleteMethod(UUID methodId) throws MethodException {
		Method storedMethod = methodRepo.findById(methodId).orElse(null);
		if (storedMethod == null) {
			throw new MethodException(format("No Method found for ID [%s].", methodId), HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Deleting Method [{}]", methodId);
		methodRepo.delete(storedMethod);
    }
}