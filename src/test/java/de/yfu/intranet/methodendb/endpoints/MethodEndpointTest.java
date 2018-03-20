package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.models.*;
import de.yfu.intranet.methodendb.repositories.*;
import de.yfu.intranet.methodendb.services.MethodService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.Collections;

import static de.yfu.intranet.methodendb.util.MethodObjectFactory.*;
import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.anySeminarGoal;
import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.anySeminarType;
import static de.yfu.intranet.methodendb.util.UserObjectFactory.anyAdmin;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MethodEndpointTest {

    private static final String METHOD_ENDPOINT = "/api/methods";
    private static final String METHOD_TYPE_ENDPOINT = METHOD_ENDPOINT + "/types";
    private static final String METHOD_LEVEL_ENDPOINT = METHOD_ENDPOINT + "/levels";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MethodTypeRepository methodTypeRepository;

    @Autowired
    private MethodLevelRepository methodLevelRepository;

    @Autowired
    private MethodRepository methodRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SeminarTypeRepository seminarTypeRepository;

    @Autowired
    private SeminarGoalRepository seminarGoalRepository;

    @Mock
    private MethodService methodService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        seminarGoalRepository.deleteAll();
        methodTypeRepository.deleteAll();
        methodLevelRepository.deleteAll();
        seminarTypeRepository.deleteAll();
        methodRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void getMethodTypes_returnsEmptySet_ifNoMethodTypesExist() {
        ResponseEntity<MethodTypeResource[]> response = restTemplate.getForEntity(METHOD_TYPE_ENDPOINT, MethodTypeResource[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(response.getBody().length == 0);
    }

    @Test
    public void getMethodTypes_returnsMethodTypes_ifMethodTypeServiceReturnsAMethodType() {
        final MethodType anyMethodType = persistAnyMethodType();
        ResponseEntity<MethodTypeResource[]> response = restTemplate.getForEntity(METHOD_TYPE_ENDPOINT, MethodTypeResource[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEqualMethodType(anyMethodType, response.getBody()[0]);
        methodTypeRepository.delete(anyMethodType.getId());
    }

    @Test
    public void createMethodType_returnsMethodType_ifNameIsProvided() {
        final MethodTypeResource anyMethodTypeResource = anyMethodTypeResource();
        ResponseEntity<MethodTypeResource> response = restTemplate.postForEntity(METHOD_TYPE_ENDPOINT, anyMethodTypeResource, MethodTypeResource.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(anyMethodTypeResource.getName());
        methodTypeRepository.delete(response.getBody().getId());
    }

    @Test
    public void updateMethodType_returns400_ifNoIdIsGivenInUrl() {
        final MethodTypeResource anyMethodTypeResource = anyMethodTypeResource();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<MethodTypeResource> request = new HttpEntity<>(anyMethodTypeResource, headers);

        ResponseEntity<MethodTypeResource> response = restTemplate.exchange(
                METHOD_TYPE_ENDPOINT,
                HttpMethod.PUT,
                request,
                MethodTypeResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void updateMethodType_returnsUpdatedMethodType_ifIdIsGivenAsUrlParameter() {
        final MethodType anyMethodType = persistAnyMethodType();
        final MethodTypeResource anyMethodTypeResource = anyMethodTypeResource();
        anyMethodTypeResource.setId(anyMethodType.getId());

        assertThat(anyMethodType.getName()).isNotEqualTo(anyMethodTypeResource.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MethodTypeResource> request = new HttpEntity<>(anyMethodTypeResource, headers);
        ResponseEntity<MethodTypeResource> response = restTemplate.exchange(
                METHOD_TYPE_ENDPOINT + "/" + anyMethodType.getId(),
                HttpMethod.PUT,
                request,
                MethodTypeResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isNotEqualTo(anyMethodType.getName());
        assertThat(response.getBody().getName()).isEqualTo(anyMethodTypeResource.getName());
        methodTypeRepository.delete(anyMethodType.getId());
    }

    @Test
    public void getMethodLevels_returnsEmptySet_ifNoMethodLevelsExist() {
        ResponseEntity<MethodLevelResource[]> response = restTemplate.getForEntity(METHOD_LEVEL_ENDPOINT, MethodLevelResource[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(response.getBody().length == 0);
    }

    @Test
    public void getMethodLevels_returnsMethodLevels_ifMethodLevelServiceReturnsAMethodLevel() {
        final MethodLevel anyMethodLevel = persistAnyMethodLevel();
        ResponseEntity<MethodLevelResource[]> response = restTemplate.getForEntity(METHOD_LEVEL_ENDPOINT, MethodLevelResource[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertEqualMethodLevel(anyMethodLevel, response.getBody()[0]);
        methodLevelRepository.delete(anyMethodLevel.getId());
    }

    @Test
    public void createMethodLevel_returnsMethodLevel_ifNameIsProvided() {
        final MethodLevelResource anyMethodLevelResource = anyMethodLevelResource();
        ResponseEntity<MethodLevelResource> response = restTemplate.postForEntity(METHOD_LEVEL_ENDPOINT, anyMethodLevelResource, MethodLevelResource.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(anyMethodLevelResource.getName());
        methodLevelRepository.delete(response.getBody().getId());
    }

    @Test
    public void updateMethodLevel_returns400_ifNoIdIsGivenInUrl() {
        final MethodLevelResource anyMethodLevelResource = anyMethodLevelResource();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<MethodLevelResource> request = new HttpEntity<>(anyMethodLevelResource, headers);
        ResponseEntity<MethodLevelResource> response = restTemplate.exchange(METHOD_LEVEL_ENDPOINT, HttpMethod.PUT, request, MethodLevelResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void updateMethodLevel_returnsUpdatedMethodLevel_ifIdIsGivenAsUrlParameter() {
        final MethodLevel anyMethodLevel = persistAnyMethodLevel();
        final MethodLevelResource anyMethodLevelResource = anyMethodLevelResource();
        anyMethodLevelResource.setId(anyMethodLevel.getId());

        assertThat(anyMethodLevel.getName()).isNotEqualTo(anyMethodLevelResource.getName());

        HttpEntity<MethodLevelResource> request = new HttpEntity<>(anyMethodLevelResource);
        ResponseEntity<MethodLevelResource> response = restTemplate.exchange(
                METHOD_LEVEL_ENDPOINT + "/" + anyMethodLevel.getId(),
                HttpMethod.PUT,
                request,
                MethodLevelResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isNotEqualTo(anyMethodLevel.getName());
        assertThat(response.getBody().getName()).isEqualTo(anyMethodLevelResource.getName());
        methodLevelRepository.delete(anyMethodLevel.getId());
    }

    @After
    public void tearDown() {
        methodTypeRepository.deleteAll();
        methodLevelRepository.deleteAll();
        seminarGoalRepository.deleteAll();
        seminarTypeRepository.deleteAll();
        methodRepository.deleteAll();
        userRepository.deleteAll();
    }


    private void assertEqualMethodType(MethodType methodType, MethodTypeResource methodTypeResource) {
        assertEquals(methodType.getId(), methodTypeResource.getId());
        assertEquals(methodType.getName(), methodTypeResource.getName());
        assertEquals(methodType.getDescription(), methodTypeResource.getDescription());
    }

    private void assertEqualMethodLevel(MethodLevel methodLevel, MethodLevelResource methodLevelResource) {
        assertEquals(methodLevel.getId(), methodLevelResource.getId());
        assertEquals(methodLevel.getName(), methodLevelResource.getName());
        assertEquals(methodLevel.getDescription(), methodLevelResource.getDescription());
    }

    private MethodType persistAnyMethodType() {
        return methodTypeRepository.save(anyMethodType());
    }
    private MethodLevel persistAnyMethodLevel() {
        return methodLevelRepository.save(anyMethodLevel());
    }
    private User persistAnyAdmin() {
        return userRepository.save(anyAdmin());
    }
    private Method persistAnyMethod() {
        return methodRepository.save(anyMethod(persistAnyAdmin()));
    }

    private SeminarType persistAnySeminarType() {
        return seminarTypeRepository.save(anySeminarType());
    }

    private SeminarGoal persistAnySeminarGoal() {
        SeminarGoal anySeminarGoal = anySeminarGoal();
        anySeminarGoal.setSeminarType(persistAnySeminarType());
        return seminarGoalRepository.save(anySeminarGoal);
    }
}
