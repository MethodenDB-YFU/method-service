package de.yfu.intranet.methods.api;

import de.yfu.intranet.methods.api.resources.MethodTypeResource;
import de.yfu.intranet.methods.api.resources.mapper.MethodTypeMapper;
import de.yfu.intranet.methods.data.domain.MethodType;
import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.service.MethodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = MethodTypeController.METHOD_TYPE_ENDPOINT)
public class MethodTypeController {

    public static final String METHOD_TYPE_ENDPOINT = "/methods/types";
    public static final String CONTENT_TYPE_METHOD_TYPE = "application/json";

    private final MethodTypeMapper methodTypeMapper;
    private final MethodTypeService methodTypeService;

    @Autowired
    public MethodTypeController(final MethodTypeService methodTypeService,
                                final MethodTypeMapper methodTypeMapper) {
        this.methodTypeMapper = methodTypeMapper;
        this.methodTypeService = methodTypeService;
    }

    @GetMapping(
            produces = CONTENT_TYPE_METHOD_TYPE
    )
    public Set<MethodTypeResource> getAllMethodTypes() {
        return methodTypeMapper.mapToMethodTypeResources(methodTypeService.getAllMethodTypes());
    }

    @PostMapping(
            consumes = CONTENT_TYPE_METHOD_TYPE,
            produces = CONTENT_TYPE_METHOD_TYPE
    )
    public ResponseEntity<MethodTypeResource> createMethodType(
            @RequestBody @Valid final MethodTypeResource methodTypeResource) {
        final MethodType methodType = methodTypeMapper.mapToMethodType(methodTypeResource);
        MethodType createdMethodType = methodTypeService.createMethodType(methodType);
        return new ResponseEntity<>(methodTypeMapper.mapToResource(createdMethodType), HttpStatus.CREATED);
    }

    @PutMapping(
            path = "/{methodTypeId}",
            consumes = CONTENT_TYPE_METHOD_TYPE,
            produces = CONTENT_TYPE_METHOD_TYPE
    )
    public ResponseEntity<MethodTypeResource> updateMethodType(
            @PathVariable("methodTypeId") UUID methodTypeId,
            @RequestBody @Valid final MethodTypeResource methodTypeResource) throws MethodException {
        final MethodType methodType = methodTypeMapper.mapToMethodType(methodTypeResource);
        methodType.setId(methodTypeId);
        MethodType updatedMethodType = methodTypeService.updateMethodType(methodType);
        return new ResponseEntity<>(methodTypeMapper.mapToResource(updatedMethodType), HttpStatus.OK);
    }
}
