package de.yfu.intranet.methods.service;

import de.yfu.intranet.methods.data.domain.MethodType;
import de.yfu.intranet.methods.data.repository.MethodTypeRepository;
import de.yfu.intranet.methods.exceptions.MethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

import static java.lang.String.format;

@Service
public class MethodTypeService {

    private final MethodTypeRepository methodTypeRepo;

    @Autowired
    public MethodTypeService(final MethodTypeRepository methodTypeRepository) {
        this.methodTypeRepo = methodTypeRepository;
    }
    
    public Set<MethodType> getAllMethodTypes() {
        Set<MethodType> methodTypes = methodTypeRepo.findAll();
        if (methodTypes == null) {
            return Collections.emptySet();
        }
        return methodTypes;
    }

    public MethodType createMethodType(MethodType methodType) {
        return methodTypeRepo.save(methodType);
    }

    public MethodType updateMethodType(MethodType methodType) throws MethodException {
        MethodType storedMethodType = methodTypeRepo.findById(methodType.getId()).orElse(null);
        if (storedMethodType != null) {
            return methodTypeRepo.save(methodType);
        }
        throw new MethodException(format("No Method Type found for id [%s]. Not able to update.", methodType.getId()), HttpStatus.NOT_FOUND);

    }
}
