package de.yfu.intranet.methods.api;


import de.yfu.intranet.methods.api.resources.MethodResource;
import de.yfu.intranet.methods.api.resources.mapper.MethodMapperImpl;
import de.yfu.intranet.methods.data.domain.*;
import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.exceptions.UserException;
import de.yfu.intranet.methods.api.resources.mapper.MethodMapper;
import de.yfu.intranet.methods.service.MethodService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static de.yfu.intranet.methods.util.MethodObjectFactory.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ActiveProfiles(profiles = "test")
public class MethodEndpointIntegrationTest {


    @Mock
    private MethodService methodService;
    @Autowired
    private MethodController methodController;
    @Autowired
    private MethodMapper methodMapper;

    @Before
    public void setUp() {
        this.methodMapper = new MethodMapperImpl();
        this.methodController = new MethodController(methodService,this.methodMapper);
    }

    @Test
    public void createMethod_returnsCreatedMethod_ifServiceReturnsCreatedMethod() throws UserException, MethodException {
        Method anyMethod = anyMethod();
        MethodResource anyMethodResource = methodMapper.mapFromDataObject(anyMethod);
        anyMethodResource.setId(null);
        anyMethodResource.getAttachments().iterator().next().setId(null);

        when(methodService.createMethod(any(Method.class))).thenReturn(anyMethod);

        ResponseEntity<MethodResource> response = methodController.createMethod(UUID.randomUUID(), anyMethodResource);
        Method createdMethod = methodMapper.mapToDataObject(response.getBody());

        verify(methodService).createMethod(any(Method.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdMethod.getId()).isNotNull();
        assertEqualMethod(anyMethod, createdMethod);
    }

    @Test
    public void getMethod_returnsMethod_ifMethodWithGivenIdExists() throws MethodException {
        Method anyMethod = anyMethod();
        when(methodService.getMethod(anyMethod.getId())).thenReturn(anyMethod);

        MethodResource response = methodController.getMethod(anyMethod.getId(), UUID.randomUUID());
        Method method = methodMapper.mapToDataObject(response);
        verify(methodService).getMethod(anyMethod.getId());
        assertThat(anyMethod).isEqualToComparingFieldByField(method);
    }

    private void assertEqualMethod(Method actual, Method other) {
        assertThat(other.getContent()).isEqualTo(actual.getContent());
        assertEqualAttachment(
                other.getAttachments().iterator().next(),
                actual.getAttachments().iterator().next());
        assertEqualTypeLevelGoal(other, actual);
    }

    private void assertEqualAttachment(Attachment actual, Attachment other) {
        assertThat(actual.getCreatedAt()).isEqualTo(other.getCreatedAt());
        assertThat(actual.getContent()).isEqualTo(other.getContent());
    }

    private void assertEqualTypeLevelGoal(Method actual, Method other) {
        assertThat(actual.getMethodTypes()).isEqualTo(other.getMethodTypes());
        assertThat(actual.getMethodLevels()).isEqualTo(other.getMethodLevels());
        assertThat(actual.getSeminarGoals()).isEqualTo(other.getSeminarGoals());
    }
}
