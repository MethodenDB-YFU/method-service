package de.yfu.intranet.methodendb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import de.yfu.intranet.methodendb.dtos.MethodCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelUpdateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeUpdateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodUpdateRequestDTO;
import de.yfu.intranet.methodendb.exceptions.MethodException;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.repositories.MethodLevelRepository;
import de.yfu.intranet.methodendb.repositories.MethodRepository;
import de.yfu.intranet.methodendb.repositories.MethodTypeRepository;

@Service
public class MethodService {
	
	@Autowired
	MethodTypeRepository methodTypeRepo;
	
	@Autowired
	MethodLevelRepository methodLevelRepo;

	@Autowired
	MethodRepository methodRepo;
	
	public List<Method> getAllMethods() throws MethodException {
		List<Method> methods = methodRepo.findAll();
		if(methods == null) {
			throw new MethodException("No methods found.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return methods;
	}
	
	public Method getMethod(int methodId) throws MethodException {
		Method method = methodRepo.findOne(methodId);
		if(method == null) {
			throw new MethodException(String.format("Could not find method with id [%s]", methodId), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return method;
	}

	public Method createMethod(MethodCreateRequestDTO methodCreateRequestDTO) {
		Method method = new Method(methodCreateRequestDTO);
		return methodRepo.save(method);
	}
	
	public void updateMethod(int methodId, MethodUpdateRequestDTO updateRequest) {
		Method method = new Method(methodId, updateRequest);
		methodRepo.save(method);
	}
	
	public List<MethodType> getAllMethodTypes() {
		return methodTypeRepo.findAll();
	}
	
	public MethodType getMethodType(int methodTypeId) {
		return methodTypeRepo.findOne(methodTypeId);
	}
	
	public MethodType createMethodType(MethodTypeCreateRequestDTO methodTypeCreateRequest) {
		MethodType methodType = new MethodType(methodTypeCreateRequest);
		return methodTypeRepo.save(methodType);
	}

	public MethodType updateMethodType(int typeId, MethodTypeUpdateRequestDTO updateRequest) {
		MethodType methodType = new MethodType(typeId, updateRequest);
		return methodTypeRepo.save(methodType);		
	}
	
	public List<MethodLevel> getAllMethodLevels() {
		return methodLevelRepo.findAll();
	}
	
	public MethodLevel getMethodLevel(int methodLevelId) {
		return methodLevelRepo.findOne(methodLevelId);
	}

	public MethodLevel createMethodLevel(MethodLevelCreateRequestDTO methodLevelCreateRequest) {
		MethodLevel methodLevel = new MethodLevel(methodLevelCreateRequest);
		return methodLevelRepo.save(methodLevel);
	}
	
	public MethodLevel updateMethodLevel(int levelId, MethodLevelUpdateRequestDTO methodLevelUpdateRequest) {
		MethodLevel methodLevel = new MethodLevel(levelId, methodLevelUpdateRequest);
		return methodLevelRepo.save(methodLevel);
	}
}