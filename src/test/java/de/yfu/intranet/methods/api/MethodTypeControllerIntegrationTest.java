package de.yfu.intranet.methods.api;

import de.yfu.intranet.methods.api.resources.MethodResource;
import de.yfu.intranet.methods.api.resources.MethodTypeResource;
import de.yfu.intranet.methods.api.resources.mapper.MethodTypeMapper;
import de.yfu.intranet.methods.api.resources.mapper.MethodTypeMapperImpl;
import de.yfu.intranet.methods.data.domain.Method;
import de.yfu.intranet.methods.data.domain.MethodType;
import de.yfu.intranet.methods.service.MethodTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Set;

import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethodType;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
public class MethodTypeControllerIntegrationTest {

    MethodTypeMapper methodTypeMapper;
    MethodTypeController methodTypeController;

    @Mock
    MethodTypeService methodTypeService;

    @Before
    public void setUp() {
        this.methodTypeMapper = new MethodTypeMapperImpl();
        this.methodTypeController = new MethodTypeController(this.methodTypeService,this.methodTypeMapper);
    }

    @Test
    public void getAllMethodTypes_returnsMethodTypeResource_ifServiceReturnsAny() {
        MethodType anyMethodType = anyMethodType();
        MethodTypeResource anyMethodTypeResource = methodTypeMapper.mapToResource(anyMethodType);

        when(methodTypeService.getAllMethodTypes()).thenReturn(Collections.singleton(anyMethodType));

        Set<MethodTypeResource> response = methodTypeController.getAllMethodTypes();
        assertEqualMethodTypeResource(anyMethodTypeResource, response.iterator().next());
    }

    @Test
    public void getAllMethodTypes_returnsEmptySet_ifServiceReturnsNone() {
        when(methodTypeService.getAllMethodTypes()).thenReturn(Collections.emptySet());

        Set<MethodTypeResource> response = methodTypeController.getAllMethodTypes();
        assertEquals(response, Collections.emptySet());
    }

    @Test
    public void createMethodType_returnsCreatedMethodType_ifServiceReturnsCreatedMethod() {
        MethodType anyMethodType = anyMethodType();
        MethodTypeResource anyMethodTypeResource = methodTypeMapper.mapToResource(anyMethodType);
        anyMethodTypeResource.setId(null);

        when(methodTypeService.createMethodType(any(MethodType.class))).thenReturn(anyMethodType);

        ResponseEntity<MethodTypeResource> response = methodTypeController.createMethodType(anyMethodTypeResource);
        MethodType createdMethodType = methodTypeMapper.mapToMethodType(response.getBody());

        assertEqualMethodType(anyMethodType, createdMethodType);
    }

    @Test
    public void updateMethodType_returnsUpdatedMethodType_ifServiceReturnsUpdatedMethod() {
        //@Todo implement
    }


    private void assertEqualMethodTypeResource(MethodTypeResource actual, MethodTypeResource other) {
        assertEquals(actual, other);
    }

    private void assertEqualMethodType(MethodType actual, MethodType other) {
        assertEquals(actual, other);
    }

}