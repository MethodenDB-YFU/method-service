package de.yfu.intranet.methods.service;

import de.yfu.intranet.methods.exceptions.MethodException;
import de.yfu.intranet.methods.data.domain.Method;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MethodServiceIntegrationTest {

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
                methodLevelRepository,
                methodRepository);
    }

    @Test
    public void persistMethod_returnsMethod_ifAllInfoIsProvided() {
        Method method = anyMethod();
        when(methodRepository.save(method)).thenReturn(method);
        Method persistedMethod = methodService.createMethod(method);
        verify(methodRepository).save(method);
        assertThat(persistedMethod).isEqualTo(method);
    }

    @Test
    public void getMethod_returnsMethod_ifMethodExists() throws MethodException {
        Method method = anyMethod();
        when(methodRepository.findById(method.getId())).thenReturn(java.util.Optional.ofNullable(method));
        Method result = methodService.getMethod(method.getId());
        verify(methodRepository).findById(method.getId());
        assertThat(result).isEqualTo(method);
    }

    @Test
    public void getMethod_returnsEmptySet_ifNoMethodExistsWithGivenId() throws MethodException {
        when(methodRepository.findAll()).thenReturn(Collections.emptySet());
        Set<Method> result = methodService.getAllMethods();
        verify(methodRepository).findAll();
        assertThat(result).isEqualTo(Collections.emptySet());
    }

    @Test
    public void getAllMethods_returnsMethods_ifMethodsExist() throws MethodException {
        Set<Method> methods = Collections.singleton(anyMethod());
        when(methodRepository.findAll()).thenReturn(methods);
        Set<Method> result = methodService.getAllMethods();
        verify(methodRepository).findAll();
        assertThat(methods).isEqualTo(result);
    }

    @Test
    public void updateMethod_returnsUpdatedMethod_ifMethodHasId() throws MethodException {
        final Method oldMethod = anyMethod();
        final Method newMethod = anyMethod();
        newMethod.setId(oldMethod.getId());
        when(methodRepository.findById(newMethod.getId())).thenReturn(java.util.Optional.ofNullable(oldMethod));

        methodService.updateMethod(newMethod);
        verify(methodRepository).save(newMethod);

    }
}
