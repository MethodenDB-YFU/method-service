package de.yfu.intranet.methods.service;

import de.yfu.intranet.methods.data.domain.MethodLevel;
import de.yfu.intranet.methods.data.repository.MethodLevelRepository;
import de.yfu.intranet.methods.exceptions.MethodException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

import static java.lang.String.format;

@Service
public class MethodLevelService {

    MethodLevelRepository methodLevelRepository;

    @Autowired
    public MethodLevelService(final MethodLevelRepository methodLevelRepository) {
        this.methodLevelRepository = methodLevelRepository;
    }

    public Set<MethodLevel> getAllMethodLevels() {
        Set<MethodLevel> methodLevels = methodLevelRepository.findAll();
        if (methodLevels == null) {
            return Collections.emptySet();
        }
        return methodLevels;
    }

    public MethodLevel createMethodLevel(MethodLevel methodLevel) {
        return methodLevelRepository.save(methodLevel);
    }

    public MethodLevel updateMethodLevel(MethodLevel methodLevel) throws MethodException {
        MethodLevel storedMethodLevel = methodLevelRepository.findById(methodLevel.getId()).orElse(null);
        if (storedMethodLevel != null) {
            return methodLevelRepository.save(methodLevel);
        }
        throw new MethodException(format("No Method Level found for id [%s]. Not able to update.", methodLevel.getId()), HttpStatus.NOT_FOUND);
    }

}
