package de.yfu.intranet.methods.api;

import com.microsoft.azure.spring.autoconfigure.aad.UserPrincipal;
import de.yfu.intranet.methods.api.resources.MethodResource;
import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.exceptions.UserException;
import de.yfu.intranet.methods.api.resources.mapper.MethodMapper;
import de.yfu.intranet.methods.data.domain.Method;
//import de.yfu.intranet.methods.data.domain.User;
import de.yfu.intranet.methods.service.MethodService;
//import de.yfu.intranet.methods.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = MethodController.METHOD_ENDPOINT)
public class MethodController {

    public static final String METHOD_ENDPOINT = "/methods";
    public static final String CONTENT_TYPE_METHOD = "application/json";

	private final MethodService methodService;
	private final MethodMapper methodMapper;

	@Autowired
	public MethodController(final MethodService methodService,
							final MethodMapper methodMapper) {
		this.methodService = methodService;
		this.methodMapper = methodMapper;
	}

	@GetMapping(
	        path = "",
            produces = CONTENT_TYPE_METHOD
    )
	public Set<Method> getAllMethods(
	        @RequestHeader(value = "X-User-ID", required = false) UUID userId,
			PreAuthenticatedAuthenticationToken authToken) throws MethodException {
		return methodService.getAllMethods();
	}

	@GetMapping(
	        path = "/{methodId}",
            produces = CONTENT_TYPE_METHOD
    )
    public MethodResource getMethod(
            @PathVariable("methodId") UUID methodId,
			@RequestHeader(value = "X-User-ID", required = false) UUID userId) throws MethodException {
		return methodMapper.mapFromDataObject(methodService.getMethod(methodId));
	}

    @PostMapping(
            path = "",
            consumes = CONTENT_TYPE_METHOD,
            produces = CONTENT_TYPE_METHOD
    )
	public ResponseEntity<MethodResource> createMethod(
	        @RequestHeader(value = "X-User-ID", required = false) UUID userId,
            @RequestBody @Valid final MethodResource methodResource) throws UserException {
		final Method method = methodMapper.mapToDataObject(methodResource);
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
            @RequestHeader(value = "X-User-ID", required = false) UUID userId,
            @RequestBody @Valid final MethodResource methodResource) throws MethodException, UserException {

		final Method method = methodMapper.mapToDataObject(methodResource);
		method.setId(methodId);

		Method updatedMethod = methodService.updateMethod(method);
		return new ResponseEntity<>(methodMapper.mapFromDataObject(updatedMethod), HttpStatus.OK);
	}

    @DeleteMapping(
            path = "/{methodId}"
    )
	public ResponseEntity<Void> deleteMethod(
	        @PathVariable("methodId") UUID methodId,
            @RequestHeader(value = "X-User-ID", required = false) UUID userId) throws MethodException {
		methodService.deleteMethod(methodId);
		return ResponseEntity.noContent().build();
	}
}
