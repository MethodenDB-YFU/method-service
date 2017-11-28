package de.yfu.intranet.methodendb.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import de.yfu.intranet.methodendb.MethodenDB;
import de.yfu.intranet.methodendb.models.Attachment;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.models.User;
import de.yfu.intranet.methodendb.repositories.AttachmentRepository;
import de.yfu.intranet.methodendb.repositories.MethodLevelRepository;
import de.yfu.intranet.methodendb.repositories.MethodRepository;
import de.yfu.intranet.methodendb.repositories.MethodTypeRepository;
import de.yfu.intranet.methodendb.repositories.SeminarTypeRepository;
import de.yfu.intranet.methodendb.repositories.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = {MethodenDB.class})
@ActiveProfiles(profiles = "test")
public class MethodServiceIT {
	
	private static final String METHOD_LEVEL_NAME = "testML";
	private static final String METHOD_TYPE_NAME = "testMT";
	private static final String METHOD_NAME = "test Method";
	private static final String SEMINAR_TYPE_NAME_1 = "test Seminar";
	private static final String SEMINAR_TYPE_NAME_2 = "another test Seminar";
	private static final String ATTACHMENT_NAME = "test Attachment";
	
	private static User user;
	private static SeminarType seminarTypeOne;
	private static SeminarType seminarTypeTwo;
	
	@Autowired
	MethodLevelRepository methodLevelRepo;
	
	@Autowired
	MethodTypeRepository methodTypeRepo;
	
	@Autowired
	MethodRepository methodRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	SeminarTypeRepository seminarTypeRepo;
	
	@Autowired
	AttachmentRepository attachmentRepo;
	
	@Before
	public void setup() {
		user = userRepo.save(new User(User.Role.EDITOR));
		seminarTypeOne = seminarTypeRepo.save(new SeminarType(SEMINAR_TYPE_NAME_1));
		seminarTypeTwo = seminarTypeRepo.save(new SeminarType(SEMINAR_TYPE_NAME_2));
	}
	
	@After
	public void cleanup() {	
		methodLevelRepo.deleteAll();
		methodTypeRepo.deleteAll();
		methodRepo.deleteAll();
		attachmentRepo.deleteAll();
		userRepo.deleteAll();
		seminarTypeRepo.deleteAll();
	}
	
	@Test
	public void createAndGetMethodLevel() {
		List<MethodLevel> empty = methodLevelRepo.findAll();
		assertTrue(empty.isEmpty());
		
		MethodLevel level = new MethodLevel(METHOD_LEVEL_NAME, null);
		MethodLevel result = methodLevelRepo.save(level);
		assertEquals(METHOD_LEVEL_NAME, result.getName());
		
		List<MethodLevel> saved = methodLevelRepo.findAll();
		assertTrue(saved.contains(result));
	}
	
	@Test
	public void createAndGetMethodType() {
		List<MethodType> empty = methodTypeRepo.findAll();
		assertTrue(empty.isEmpty());
		
		MethodType type = new MethodType(METHOD_TYPE_NAME, null);
		MethodType result = methodTypeRepo.save(type);
		assertEquals(METHOD_TYPE_NAME, result.getName());
		
		List<MethodType> saved = methodTypeRepo.findAll();
		assertTrue(saved.contains(result));
	}
	
	@Test
	public void createAndGetMethod_PersistMethodAndAttachment() {
		Attachment attachment = new Attachment(ATTACHMENT_NAME, user, null);
		Set<Attachment> attachments = new HashSet<Attachment>();
		attachments.add(attachment);
		
		
		Method method = new Method(METHOD_NAME, null, null, null, seminarTypeOne, attachments, user, null);
		Method result = methodRepo.save(method);
		
		assertTrue(result.getAttachments().contains(attachment));		
	}
	
	@Test 
	public void createAndGetMethod_IfTwoMethodsWithSameSeminarTypeGetPersisted() {
		Method methodOne = new Method(METHOD_NAME, null, null, null, seminarTypeOne, null, user, null);
		Method methodTwo = new Method(METHOD_NAME, null, null, null, seminarTypeTwo, null, user, null);
		
		Method resultOne = methodRepo.save(methodOne);
		Method resultTwo = methodRepo.save(methodTwo);
		
		assertTrue(resultOne.getSeminarType().equals(seminarTypeOne));
		assertTrue(resultTwo.getSeminarType().equals(seminarTypeTwo));
	}
	
	
	
}