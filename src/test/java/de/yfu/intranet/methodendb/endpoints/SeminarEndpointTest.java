package de.yfu.intranet.methodendb.endpoints;

import de.yfu.intranet.methodendb.dtos.SeminarGoalResource;
import de.yfu.intranet.methodendb.dtos.SeminarTypeResource;
import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.repositories.SeminarGoalRepository;
import de.yfu.intranet.methodendb.repositories.SeminarTypeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UriComponentsBuilder;

import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.*;
import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles="test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class SeminarEndpointTest {

    private static final String SEMINAR_GOAL_ENDPOINT = "/api/seminars/goals";
    private static final String SEMINAR_TYPE_ENDPOINT = "/api/seminars/types";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private SeminarTypeRepository seminarTypeRepository;

    @Autowired
    private SeminarGoalRepository seminarGoalRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        seminarTypeRepository.deleteAll();
    }

    @Test
    public void getSeminarTypes_returnsEmptySet_ifNoSeminarTypesExist() {
        ResponseEntity<SeminarTypeResource[]> response = restTemplate.exchange(
                SEMINAR_TYPE_ENDPOINT,
                HttpMethod.GET,
                getContentTypeHeader(),
                SeminarTypeResource[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(response.getBody().length == 0);
    }


    @Test
    public void getSeminarTypes_returnsSeminarTypes_ifSeminarTypeServiceReturnsASeminarType() {
        final SeminarType anySeminarType = persistAnySeminarType();

        ResponseEntity<SeminarTypeResource[]> response = restTemplate.exchange(
                SEMINAR_TYPE_ENDPOINT,
                HttpMethod.GET,
                getContentTypeHeader(),
                SeminarTypeResource[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(response.getBody().length);
        assertEqualSeminarType(anySeminarType, response.getBody()[0]);
        seminarTypeRepository.delete(anySeminarType.getId());
    }

    @Test
    public void createSeminarType_returnsSeminarType_ifNameIsProvided() {
        final SeminarTypeResource anySeminarTypeResource = anySeminarTypeResource();
        ResponseEntity<SeminarTypeResource> response = restTemplate.postForEntity(SEMINAR_TYPE_ENDPOINT, anySeminarTypeResource, SeminarTypeResource.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo(anySeminarTypeResource.getName());
        seminarTypeRepository.delete(response.getBody().getId());
    }

    @Test
    public void updateSeminarType_returns405_ifNoIdIsGivenInUrl() {
        final SeminarTypeResource anySeminarTypeResource = anySeminarTypeResource();

        HttpEntity<SeminarTypeResource> request = new HttpEntity<>(anySeminarTypeResource);
        ResponseEntity<SeminarTypeResource> response = restTemplate.exchange(SEMINAR_TYPE_ENDPOINT, HttpMethod.PUT, request, SeminarTypeResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    public void updateSeminarType_returnsUpdatedSeminarType_ifIdIsGivenAsUrlParameter() {
        final SeminarType anySeminarType = persistAnySeminarType();
        final SeminarTypeResource anySeminarTypeResource = anySeminarTypeResource();
        anySeminarTypeResource.setId(anySeminarType.getId());

        assertThat(anySeminarType.getName()).isNotEqualTo(anySeminarTypeResource.getName());

        HttpEntity<SeminarTypeResource> request = new HttpEntity<>(anySeminarTypeResource);
        ResponseEntity<SeminarTypeResource> response = restTemplate.exchange(
                SEMINAR_TYPE_ENDPOINT + "/" + anySeminarType.getId(),
                HttpMethod.PUT,
                request,
                SeminarTypeResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isNotEqualTo(anySeminarType.getName());
        assertThat(response.getBody().getName()).isEqualTo(anySeminarTypeResource.getName());
        seminarTypeRepository.delete(anySeminarType.getId());
    }

    @Test
    public void getSeminarGoals_returnsEmptySet_ifNoSeminarGoalExist() {
        final SeminarType anySeminarType = persistAnySeminarType();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(SEMINAR_GOAL_ENDPOINT)
                .queryParam("seminarType", anySeminarType.getId());

        ResponseEntity<SeminarGoalResource[]> response = restTemplate
                .exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        getContentTypeHeader(),
                        SeminarGoalResource[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(response.getBody().length == 0);

        seminarTypeRepository.delete(anySeminarType.getId());
    }

    @Test
    public void getSeminarGoalsForSeminarType_returnsSeminarGoalsForSeminarType_ifSeminarGoalsExist() {
        final SeminarGoal anySeminarGoal = persistAnySeminarGoal();
        final SeminarType anySeminarType = anySeminarGoal.getSeminarType();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(SEMINAR_GOAL_ENDPOINT)
                .queryParam("seminarType", anySeminarType.getId());

        ResponseEntity<SeminarGoalResource[]> response = restTemplate
                .exchange(
                        builder.build().encode().toUri(),
                        HttpMethod.GET,
                        getContentTypeHeader(),
                        SeminarGoalResource[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        seminarGoalRepository.delete(anySeminarGoal.getId());
        seminarTypeRepository.delete(anySeminarType.getId());
    }

    @Test(expected = SeminarException.class)
    @Ignore
    public void getSeminarGoals_returnsError_ifNoSeminarTypeIsProvided() {
        ResponseEntity<SeminarGoalResource[]> response = restTemplate.getForEntity(SEMINAR_GOAL_ENDPOINT, SeminarGoalResource[].class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createSeminarGoal_returnsSeminarGoal_ifAllRequiredInfoIsProvided() {
        final SeminarType anySeminarType = persistAnySeminarType();
        final SeminarGoalResource anySeminarGoalResource = anySeminarGoalResource();
        anySeminarGoalResource.setSeminarType(anySeminarType);

        ResponseEntity<SeminarGoal> response = restTemplate.postForEntity(SEMINAR_GOAL_ENDPOINT, anySeminarGoalResource, SeminarGoal.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isNotNull();
        assertThat(response.getBody().getSeminarType()).isEqualTo(anySeminarType);
        assertThat(response.getBody().getName()).isEqualTo(anySeminarGoalResource.getName());
        assertThat(response.getBody().getDescription()).isEqualTo(anySeminarGoalResource.getDescription());

        seminarGoalRepository.delete(response.getBody().getId());
        seminarTypeRepository.delete(anySeminarType.getId());
    }

    @Test
    public void updateSeminarGoal_returnsUpdatedSeminarGoal_ifIdIsProvidedAsPathParam() {
        final SeminarGoal anySeminarGoal = persistAnySeminarGoal();
        final SeminarGoalResource anySeminarGoalResource = anySeminarGoalResource();
        anySeminarGoalResource.setId(anySeminarGoal.getId());
        anySeminarGoalResource.setSeminarType(anySeminarGoal.getSeminarType());

        assertThat(anySeminarGoal.getName()).isNotEqualTo(anySeminarGoalResource.getName());

        HttpEntity<SeminarGoalResource> request = new HttpEntity<>(anySeminarGoalResource);
        ResponseEntity<SeminarGoalResource> response = restTemplate.exchange(
                SEMINAR_GOAL_ENDPOINT + "/" + anySeminarGoal.getId(),
                HttpMethod.PUT,
                request,
                SeminarGoalResource.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(anySeminarGoalResource.getName());

        seminarGoalRepository.delete(anySeminarGoal.getId());
        seminarTypeRepository.delete(anySeminarGoal.getSeminarType().getId());
    }

    @After
    public void tearDown() {
        seminarGoalRepository.deleteAll();
        seminarTypeRepository.deleteAll();
    }


    private void assertEqualSeminarType(SeminarType seminarType, SeminarTypeResource seminarTypeResource) {
        assertEquals(seminarType.getId(), seminarTypeResource.getId());
        assertEquals(seminarType.getName(), seminarTypeResource.getName());
    }

    private SeminarType persistAnySeminarType() {
        return seminarTypeRepository.save(anySeminarType());
    }

    private SeminarGoal persistAnySeminarGoal() {
        SeminarGoal anySeminarGoal = anySeminarGoal();
        anySeminarGoal.setSeminarType(persistAnySeminarType());
        return seminarGoalRepository.save(anySeminarGoal);
    }

    private HttpEntity<?> getContentTypeHeader() {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");

        return new HttpEntity<>(headers);
    }
}