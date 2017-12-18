package de.yfu.intranet.methodendb.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import static java.util.Collections.emptyList;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.yfu.intranet.methodendb.dtos.MethodLevelCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodLevelUpdateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeCreateRequestDTO;
import de.yfu.intranet.methodendb.dtos.MethodTypeUpdateRequestDTO;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.repositories.MethodLevelRepository;
import de.yfu.intranet.methodendb.repositories.MethodTypeRepository;
import static de.yfu.intranet.methodendb.helpers.TestHelper.anyMethodType;
import static de.yfu.intranet.methodendb.helpers.TestHelper.anyMethodLevel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public class MethodServiceTest {
	
	@Mock
	private MethodTypeRepository methodTypeRepo;
	@Mock
	private MethodLevelRepository methodLevelRepo;
	
	@InjectMocks
	MethodService methodService;
	
	MockMvc mockMvc;
	
	MethodType methodType;
	MethodLevel methodLevel;
	
	private static final MethodTypeCreateRequestDTO METHOD_TYPE_CREATE_REQUEST = new MethodTypeCreateRequestDTO("Method Type Test", "Foo");
	private static final MethodTypeUpdateRequestDTO METHOD_TYPE_UPDATE_REQUEST = new MethodTypeUpdateRequestDTO("Method Type Update", "Bar");
	private static final MethodType METHOD_TYPE_CREATE = new MethodType(METHOD_TYPE_CREATE_REQUEST);
	private static final MethodType METHOD_TYPE_UPDATE = new MethodType(1, METHOD_TYPE_UPDATE_REQUEST);
	private static final MethodLevelCreateRequestDTO METHOD_LEVEL_CREATE_REQUEST = new MethodLevelCreateRequestDTO("Method Level Test", "Bar");
	private static final MethodLevelUpdateRequestDTO METHOD_LEVEL_UPDATE_REQUEST = new MethodLevelUpdateRequestDTO("Method Level Update", "Foo");
	private static final MethodLevel METHOD_LEVEL_CREATE = new MethodLevel(METHOD_LEVEL_CREATE_REQUEST);
	private static final MethodLevel METHOD_LEVEL_UPDATE = new MethodLevel(1, METHOD_LEVEL_UPDATE_REQUEST);

	@Before
	public void setUp() throws Exception {
		methodTypeRepo = mock(MethodTypeRepository.class);
		methodType = new MethodType(METHOD_TYPE_CREATE_REQUEST);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getMethodTypes_returnsEmptyList_ifNoMethodTypeIsPresent() {
		when(methodTypeRepo.findAll()).thenReturn(emptyList());
		final List<MethodType> methodTypes = methodService.getAllMethodTypes();
		verify(methodTypeRepo).findAll();
		assertThat(methodTypes, is(emptyList()));
	}
	
	@Test
	public void getMethodTypes_returnsOneMethodType_ifIdIsGiven() {
		MethodType dummyMethodType = anyMethodType();
		when(methodTypeRepo.findOne(anyInt())).thenReturn(dummyMethodType);
		final MethodType methodType = methodService.getMethodType(dummyMethodType.getId());
		verify(methodTypeRepo).findOne(dummyMethodType.getId());
		assertThat(methodType, is(sameInstance(dummyMethodType)));
	}
	
	@Test
	public void getMethodLevel_returnsOneMethodType_ifIdIsGiven() {
		MethodLevel dummyMethodLevel = anyMethodLevel();
		when(methodLevelRepo.findOne(anyInt())).thenReturn(dummyMethodLevel);
		final MethodLevel methodLevel = methodService.getMethodLevel(dummyMethodLevel.getId());
		verify(methodLevelRepo).findOne(dummyMethodLevel.getId());
		assertThat(methodLevel, is(sameInstance(dummyMethodLevel)));
	}
	
	@Test
	public void getMethodLevels_returnsEmptyList_ifNoMethodLevelIsPresent() {
		when(methodLevelRepo.findAll()).thenReturn(emptyList());
		final List<MethodLevel> methodLevels = methodService.getAllMethodLevels();
		verify(methodLevelRepo).findAll();
		assertThat(methodLevels, is(emptyList()));
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
	
	@Test
	public void createMethodLevel() {
		when(methodLevelRepo.save(eq(METHOD_LEVEL_CREATE))).thenReturn(METHOD_LEVEL_CREATE);
		
		methodLevel = methodService.createMethodLevel(METHOD_LEVEL_CREATE_REQUEST);
		
		assertThat("methodLevel", methodLevel, is(sameInstance(METHOD_LEVEL_CREATE)));
		
		verify(methodLevelRepo).save(METHOD_LEVEL_CREATE);
	}
	
	@Test
	public void updateMethodLevel() {
		when(methodLevelRepo.save(eq(METHOD_LEVEL_UPDATE))).thenReturn(METHOD_LEVEL_UPDATE);
		
		methodLevel = methodService.updateMethodLevel(1, METHOD_LEVEL_UPDATE_REQUEST);
		
		assertThat("methodLevel", methodLevel, is(sameInstance(METHOD_LEVEL_UPDATE)));
		
		verify(methodLevelRepo).save(METHOD_LEVEL_UPDATE);
	}
	
	
}
