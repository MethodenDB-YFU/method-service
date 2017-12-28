package de.yfu.intranet.methodendb.services;

import de.yfu.intranet.methodendb.exceptions.SeminarException;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.repositories.SeminarGoalRepository;
import de.yfu.intranet.methodendb.repositories.SeminarTypeRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;



import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.anySeminarGoal;
import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.anySeminarType;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SeminarServiceTest {

    @Mock
    SeminarTypeRepository seminarTypeRepository;
    @Mock
    SeminarGoalRepository seminarGoalRepository;

    private SeminarService seminarService;

    @Before
    public void setUp() {
        seminarService = new SeminarService(seminarTypeRepository, seminarGoalRepository);
    }

    @Test
    public void updateSeminarType_callsRepositoryWithCorrectParams_ifASeminarTypeWithGivenIdIsFound() throws SeminarException {
        final SeminarType oldSeminarType = anySeminarType();
        final SeminarType newSeminarType = anySeminarType();
        newSeminarType.setId(oldSeminarType.getId());
        when(seminarTypeRepository.findOne(newSeminarType.getId())).thenReturn(oldSeminarType);

        seminarService.updateSeminarType(newSeminarType);
        verify(seminarTypeRepository).save(newSeminarType);
    }

    @Test
    public void updateSeminarGoal_callsRepositoryWithCorrectParams_ifASeminarGoalWithGivenIdIsFound() throws SeminarException {
        final SeminarGoal oldSeminarGoal = anySeminarGoal();
        final SeminarGoal newSeminarGoal = anySeminarGoal();
        newSeminarGoal.setId(oldSeminarGoal.getId());
        when(seminarGoalRepository.findOne(newSeminarGoal.getId())).thenReturn(oldSeminarGoal);

        seminarService.updateSeminarGoal(newSeminarGoal);
        verify(seminarGoalRepository).save(newSeminarGoal);
    }

    @Test(expected = SeminarException.class)
    public void updateSeminarGoal_throwsException_ifNoSeminarGoalWithGivenIdIsFound() throws SeminarException {
        final SeminarGoal anySeminarGoal = anySeminarGoal();
        when(seminarGoalRepository.findOne(anySeminarGoal.getId())).thenReturn(null);

        seminarService.updateSeminarGoal(anySeminarGoal);
    }

    @Test(expected = SeminarException.class)
    public void updateSeminarType_throwsException_ifNoSeminarTypeWithGivenIdIsFound() throws SeminarException {
        final SeminarType anySeminarType = anySeminarType();
        when(seminarTypeRepository.findOne(anySeminarType.getId())).thenReturn(null);

        seminarService.updateSeminarType(anySeminarType);
    }

    @Test
    public void findSeminarType_returnsTheSetThatWasReturnedByTheRepository() {
        final Set<SeminarType> expectedSeminarTypes = Collections.singleton(anySeminarType());
        when(seminarTypeRepository.findAll()).thenReturn(expectedSeminarTypes);
        final Set<SeminarType> actualSeminarTypes = seminarService.getSeminarTypes();
        assertEquals(actualSeminarTypes, expectedSeminarTypes);
    }

    @Test
    public void findSeminarGoal_returnsTheSetThatWasReturnedByTheRepository() {
        final UUID seminarTypeId = UUID.randomUUID();
        final Set<SeminarGoal> expectedSeminarGoals = Collections.singleton(anySeminarGoal());
        when(seminarGoalRepository.findAllBySeminarTypeId(seminarTypeId)).thenReturn(expectedSeminarGoals);
        final Set<SeminarGoal> actualSeminarGoals = seminarService.getSeminarGoals(seminarTypeId);
        assertEquals(actualSeminarGoals, expectedSeminarGoals);
    }

    @Test
    public void createSeminarType_callsRepositoryWithCorrectArgumentsAndReturnsCreatedSeminarType() {
        final SeminarType anySeminarType = anySeminarType();
        when(seminarTypeRepository.save(anySeminarType)).thenReturn(anySeminarType);
        final SeminarType createdSeminarType = seminarService.createSeminarType(anySeminarType);
        verify(seminarTypeRepository).save(anySeminarType);
        assertThat(createdSeminarType).isEqualTo(anySeminarType);
    }

    @Test
    public void createSeminarGoal_callsRepositoryWithCorrectArgumentsAndReturnsCreatedSeminarGoal() {
        final SeminarGoal anySeminarGoal = anySeminarGoal();
        when(seminarGoalRepository.save(anySeminarGoal)).thenReturn(anySeminarGoal);
        final SeminarGoal createdSeminarGoal = seminarService.createSeminarGoal(anySeminarGoal);
        verify(seminarGoalRepository).save(anySeminarGoal);
        assertThat(createdSeminarGoal).isEqualTo(anySeminarGoal);
    }




    @After
    public void tearDown() throws Exception {
    }
}