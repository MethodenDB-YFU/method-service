package de.yfu.intranet.methods.api;

import de.yfu.intranet.methods.api.resources.MethodLevelResource;
import de.yfu.intranet.methods.data.domain.MethodLevel;
import de.yfu.intranet.methods.data.repository.MethodLevelRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethodLevel;
import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethodLevelResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MethodLevelControllerTest {

    private static final String METHOD_LEVEL_ENDPOINT = "/methods/levels";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MethodLevelRepository methodLevelRepository;

    @Before
    public void setUp() {
        methodLevelRepository.deleteAll();
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
        methodLevelRepository.deleteAll();
    }

    private void assertEqualMethodLevel(MethodLevel methodLevel, MethodLevelResource methodLevelResource) {
        assertEquals(methodLevel.getId(), methodLevelResource.getId());
        assertEquals(methodLevel.getName(), methodLevelResource.getName());
        assertEquals(methodLevel.getDescription(), methodLevelResource.getDescription());
    }

    private MethodLevel persistAnyMethodLevel() {
        return methodLevelRepository.save(anyMethodLevel());
    }
}
