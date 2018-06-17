package de.yfu.intranet.methods.api;

import de.yfu.intranet.methods.api.resources.MethodLevelResource;
import de.yfu.intranet.methods.api.resources.mapper.MethodLevelMapper;
import de.yfu.intranet.methods.data.domain.MethodLevel;
import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.service.MethodLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = MethodLevelController.METHOD_LEVEL_ENDPOINT)
public class MethodLevelController {

    public static final String METHOD_LEVEL_ENDPOINT = "/methods/levels";
    public static final String CONTENT_TYPE_METHOD_LEVEL = "application/json";

    MethodLevelService methodLevelService;
    MethodLevelMapper methodLevelMapper;

    @Autowired
    public MethodLevelController(final MethodLevelService methodLevelService,
                                 final MethodLevelMapper methodLevelMapper) {
        this.methodLevelMapper = methodLevelMapper;
        this.methodLevelService = methodLevelService;
    }


    @GetMapping(
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
    public Set<MethodLevelResource> getAllMethodLevels() {
        return methodLevelMapper.mapToMethodLevelResources(methodLevelService.getAllMethodLevels());
    }

    @PostMapping(
            consumes = CONTENT_TYPE_METHOD_LEVEL,
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
    public ResponseEntity<MethodLevelResource> createMethodLevel(
            @RequestBody @Valid final MethodLevelResource methodLevelResource) {
        final MethodLevel methodLevel = methodLevelMapper.mapToMethodLevel(methodLevelResource);
        MethodLevel createdMethodLevel = methodLevelService.createMethodLevel(methodLevel);
        return new ResponseEntity<>(methodLevelMapper.mapToResource(createdMethodLevel), HttpStatus.CREATED);
    }

    @PutMapping(
            path = "{methodLevelId}",
            consumes = CONTENT_TYPE_METHOD_LEVEL,
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
    public ResponseEntity<MethodLevelResource> updateMethodLevel(
            @PathVariable("methodLevelId") UUID methodLevelId,
            @RequestBody @Valid final MethodLevelResource methodLevelResource) throws MethodException {
        final MethodLevel methodLevel = methodLevelMapper.mapToMethodLevel(methodLevelResource);
        methodLevel.setId(methodLevelId);
        MethodLevel updatedMethodLevel = methodLevelService.updateMethodLevel(methodLevel);
        return new ResponseEntity<>(methodLevelMapper.mapToResource(updatedMethodLevel), HttpStatus.OK);
    }
}
