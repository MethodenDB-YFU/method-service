package de.yfu.intranet.methodendb.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.yfu.intranet.methodendb.dtos.MethodTypeCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeUpdateRequestDTO;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.repositories.MethodTypeRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class MethodServiceTest {
	
	@Mock
	private MethodTypeRepository methodTypeRepo;
	
	@InjectMocks
	MethodService methodService;
	MockMvc mockMvc;
	
	MethodType methodType;
	
	private static final MethodTypeCreateRequestDTO METHOD_TYPE_CREATE_REQUEST = new MethodTypeCreateRequestDTO("Method Type Test", "Foo");
	private static final MethodTypeUpdateRequestDTO METHOD_TYPE_UPDATE_REQUEST = new MethodTypeUpdateRequestDTO("Method Type Update", "Bar");
	private static final MethodType METHOD_TYPE_CREATE = new MethodType(METHOD_TYPE_CREATE_REQUEST);
	private static final MethodType METHOD_TYPE_UPDATE = new MethodType(1, METHOD_TYPE_UPDATE_REQUEST);
	
	@Before
	public void setUp() throws Exception {
		methodTypeRepo = mock(MethodTypeRepository.class);
		methodType = new MethodType(METHOD_TYPE_CREATE_REQUEST);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createMethodType() {
		when(methodTypeRepo.save(eq(METHOD_TYPE_CREATE))).thenReturn(METHOD_TYPE_CREATE);
		
		methodType = methodService.createMethodType(METHOD_TYPE_CREATE_REQUEST);
		
		assertThat("methodType", methodType, is(sameInstance(METHOD_TYPE_CREATE)));
				
		verify(methodTypeRepo).save(METHOD_TYPE_CREATE);		
	}
	
	@Test
	public void updateMethodType() {
		when(methodTypeRepo.save(eq(METHOD_TYPE_UPDATE))).thenReturn(METHOD_TYPE_UPDATE);
		
		methodType = methodService.updateMethodType(1, METHOD_TYPE_UPDATE_REQUEST);
		
		assertThat("methodType", methodType, is(sameInstance(METHOD_TYPE_UPDATE)));
		
		verify(methodTypeRepo).save(METHOD_TYPE_UPDATE);
		
	}
}
