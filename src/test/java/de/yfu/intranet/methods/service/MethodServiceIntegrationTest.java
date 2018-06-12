package de.yfu.intranet.methods.service;

import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.data.domain.Method;
import de.yfu.intranet.methods.data.domain.User;
import de.yfu.intranet.methods.data.repository.MethodLevelRepository;
import de.yfu.intranet.methods.data.repository.MethodRepository;
import de.yfu.intranet.methods.data.repository.MethodTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethod;
import static de.yfu.intranet.methods.util.UserObjectFactory.anyEditor;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodServiceIntegrationTest {

    private static final User USER = anyEditor();

    @Mock
    MethodRepository methodRepository;
    @Mock
    MethodTypeRepository methodTypeRepository;
    @Mock
    MethodLevelRepository methodLevelRepository;
    @Autowired
    MethodService methodService;

    @Before
    public void setUp() {
        methodService = new MethodService(
                methodTypeRepository,
                methodLevelRepository,
                methodRepository);
    }

    @Test
    public void persistMethod_returnsMethod_ifAllInfoIsProvided() {
        Method method = anyMethod(USER);
        when(methodRepository.save(method)).thenReturn(method);
        Method persistedMethod = methodService.createMethod(method);
        verify(methodRepository).save(method);
        assertThat(persistedMethod).isEqualTo(method);
    }

    @Test
    public void getMethod_returnsMethod_ifMethodExists() throws MethodException {
        Method method = anyMethod(USER);
        when(methodRepository.findOne(method.getId())).thenReturn(method);
        Method result = methodService.getMethod(USER.getId(), method.getId());
        verify(methodRepository).findOne(method.getId());
        assertThat(result).isEqualTo(method);
    }

    @Test
    public void getMethod_returnsEmptySet_ifNoMethodExistsWithGivenId() throws MethodException {
        when(methodRepository.findAll()).thenReturn(Collections.emptySet());
        Set<Method> result = methodService.getAllMethods(USER.getId());
        verify(methodRepository).findAll();
        assertThat(result).isEqualTo(Collections.emptySet());
    }

    @Test
    public void getAllMethods_returnsMethods_ifMethodsExist() throws MethodException {
        Set<Method> methods = Collections.singleton(anyMethod(USER));
        when(methodRepository.findAll()).thenReturn(methods);
        Set<Method> result = methodService.getAllMethods(USER.getId());
        verify(methodRepository).findAll();
        assertThat(methods).isEqualTo(result);
    }

    @Test
    public void updateMethod_returnsUpdatedMethod_ifMethodHasId() throws MethodException {
        final Method oldMethod = anyMethod(USER);
        final Method newMethod = anyMethod(USER);
        newMethod.setId(oldMethod.getId());
        when(methodRepository.findOne(newMethod.getId())).thenReturn(oldMethod);

        methodService.updateMethod(newMethod);
        verify(methodRepository).save(newMethod);

    }
}
