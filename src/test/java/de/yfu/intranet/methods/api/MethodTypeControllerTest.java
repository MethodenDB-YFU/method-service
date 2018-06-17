package de.yfu.intranet.methods.api;

import de.yfu.intranet.methods.api.resources.MethodTypeResource;
import de.yfu.intranet.methods.data.domain.MethodType;
import de.yfu.intranet.methods.data.repository.MethodTypeRepository;
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

import java.util.Collections;

import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethodType;
import static de.yfu.intranet.methods.util.MethodObjectFactory.anyMethodTypeResource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MethodTypeControllerTest {

    private static final String METHOD_TYPE_ENDPOINT = "/methods/types";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MethodTypeRepository methodTypeRepository;

    @Before
    public void setUp() {
        methodTypeRepository.deleteAll();
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


    @After
    public void tearDown() {
        methodTypeRepository.deleteAll();
    }

    private MethodType persistAnyMethodType() {
        return methodTypeRepository.save(anyMethodType());
    }
    private void assertEqualMethodType(MethodType methodType, MethodTypeResource methodTypeResource) {
        assertEquals(methodType.getId(), methodTypeResource.getId());
        assertEquals(methodType.getName(), methodTypeResource.getName());
        assertEquals(methodType.getDescription(), methodTypeResource.getDescription());
    }
}
