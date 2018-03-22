package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.exceptions.MethodException;
import de.yfu.intranet.methodendb.exceptions.UserException;
import de.yfu.intranet.methodendb.helpers.MethodMapper;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.models.User;
import de.yfu.intranet.methodendb.services.MethodService;
import de.yfu.intranet.methodendb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = MethodEndpoint.METHOD_ENDPOINT)
public class MethodEndpoint {

    public static final String METHOD_ENDPOINT = "/api/methods";
    public static final String CONTENT_TYPE_METHOD_TYPE = "application/json";
    public static final String CONTENT_TYPE_METHOD_LEVEL = "application/json";
    public static final String CONTENT_TYPE_METHOD = "application/json";

	private final MethodService methodService;
	private final MethodMapper methodMapper;
	private final UserService userService;
	
	@Autowired
	public MethodEndpoint(final MethodService methodService, 
			final MethodMapper methodMapper,
			final UserService userService) {
		this.methodService = methodService;
		this.methodMapper = methodMapper;
		this.userService = userService;
	}

    @GetMapping(
            path = "/types",
            produces = CONTENT_TYPE_METHOD_TYPE
    )
	public Set<MethodType> getAllMethodTypes() {
		return methodService.getAllMethodTypes();
	}

    @PostMapping(
            path = "/types",
            consumes = CONTENT_TYPE_METHOD_TYPE,
            produces = CONTENT_TYPE_METHOD_TYPE
    )
	public ResponseEntity<MethodTypeResource> createMethodType(
	        @RequestBody @Valid final MethodTypeResource methodTypeResource) {
		final MethodType methodType = methodMapper.mapToDataObject(methodTypeResource);
		MethodType createdMethodType = methodService.createMethodType(methodType);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(createdMethodType), HttpStatus.CREATED);
	}

    @PutMapping(
            path = "/types/{methodTypeId}",
            consumes = CONTENT_TYPE_METHOD_TYPE,
            produces = CONTENT_TYPE_METHOD_TYPE
    )
	public ResponseEntity<MethodTypeResource> updateMethodType(
	        @PathVariable("methodTypeId") UUID methodTypeId,
			@RequestBody @Valid final MethodTypeResource methodTypeResource) throws MethodException {
		final MethodType methodType = methodMapper.mapToDataObject(methodTypeResource);
		methodType.setId(methodTypeId);
		MethodType updatedMethodType = methodService.updateMethodType(methodType);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(updatedMethodType), HttpStatus.OK);
	}

    @GetMapping(
            path = "/levels",
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
	public Set<MethodLevel> getAllMethodLevels() {
		return methodService.getAllMethodLevels();
	}

    @PostMapping(
            path = "/levels",
            consumes = CONTENT_TYPE_METHOD_LEVEL,
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
	public ResponseEntity<MethodLevelResource> createMethodLevel(
	        @RequestBody @Valid final MethodLevelResource methodLevelResource) {
		final MethodLevel methodLevel = methodMapper.mapToDataObject(methodLevelResource);
		MethodLevel createdMethodLevel = methodService.createMethodLevel(methodLevel);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(createdMethodLevel), HttpStatus.CREATED);
	}

    @PutMapping(
            path = "/levels/{methodLevelId}",
            consumes = CONTENT_TYPE_METHOD_LEVEL,
            produces = CONTENT_TYPE_METHOD_LEVEL
    )
	public ResponseEntity<MethodLevelResource> updateMethodLevel(
	        @PathVariable("methodLevelId") UUID methodLevelId,
            @RequestBody @Valid final MethodLevelResource methodLevelResource) throws MethodException {
		final MethodLevel methodLevel = methodMapper.mapToDataObject(methodLevelResource);
		methodLevel.setId(methodLevelId);
		MethodLevel updatedMethodLevel = methodService.updateMethodLevel(methodLevel);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(updatedMethodLevel), HttpStatus.OK);
	}

	@GetMapping(
	        path = "",
            produces = CONTENT_TYPE_METHOD
    )
	public Set<Method> getAllMethods(
	        @RequestHeader(value = "X-User-ID", required = false) UUID userId) throws MethodException {
		return methodService.getAllMethods(userId);
	}

	@GetMapping(
	        path = "/{methodId}",
            produces = CONTENT_TYPE_METHOD
    )
    public MethodResource getMethod(
            @PathVariable("methodId") UUID methodId,
			@RequestHeader("X-User-ID") UUID userId) throws MethodException {
		return methodMapper.mapFromDataObject(methodService.getMethod(userId, methodId));
	}

    @PostMapping(
            path = "",
            consumes = CONTENT_TYPE_METHOD,
            produces = CONTENT_TYPE_METHOD
    )
	public ResponseEntity<MethodResource> createMethod(
	        @RequestHeader("X-User-ID") UUID userId,
            @RequestBody @Valid final MethodResource methodResource) throws UserException {
		final User createdBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setCreatedBy(createdBy);
		if (method.getAttachments() != null) {
			method.getAttachments().forEach(m -> m.setCreatedBy(createdBy));
		}

		final Method createdMethod = methodService.createMethod(method);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(createdMethod), HttpStatus.CREATED);
	}

    @PutMapping(
            path = "/{methodId}",
            consumes = CONTENT_TYPE_METHOD,
            produces = CONTENT_TYPE_METHOD
    )
	public ResponseEntity<MethodResource> updateMethod(
	        @PathVariable("methodId") UUID methodId,
            @RequestHeader("X-User-ID") UUID userId,
            @RequestBody @Valid final MethodResource methodResource) throws MethodException, UserException {

		final User modifiedBy = userService.findById(userId);
		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setModifiedBy(modifiedBy);
		method.setId(methodId);
		
		Method updatedMethod = methodService.updateMethod(method);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(updatedMethod), HttpStatus.OK);
	}

    @DeleteMapping(
            path = "/{methodId}"
    )
	public ResponseEntity<Void> deleteMethod(
	        @PathVariable("methodId") UUID methodId,
            @RequestHeader("X-User-ID") UUID userId) throws MethodException {
		methodService.deleteMethod(userId, methodId);
		return ResponseEntity.noContent().build();
	}
}
