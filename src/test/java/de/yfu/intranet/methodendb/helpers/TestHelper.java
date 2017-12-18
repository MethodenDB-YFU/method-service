package de.yfu.intranet.methodendb.helpers;

import de.yfu.intranet.methodendb.models.Attachment;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.models.User;

public class TestHelper {
	
	public static MethodLevel anyMethodLevel() {
		final MethodLevel methodLevel = new MethodLevel();
		methodLevel.setId(1);
		methodLevel.setName("Method Level Test");
		methodLevel.setDescription("Lorem Ipsum dolor sit amet.");
		return methodLevel;
	}
	
	public static MethodType anyMethodType() {
		final MethodType methodType = new MethodType();
		methodType.setId(1);
		methodType.setName("Method Type Test");
		methodType.setDescription("Foo Bar McAwesome-Blah!");
		return methodType;
	}
	
	public static User anyUser() {
		final User user = new User();
		user.setRole(User.Role.EDITOR);
		return user;
	}
	
	public static Attachment anyAttachment() {
		final Attachment attachment = new Attachment();
		attachment.setId(1);
		attachment.setContent("Some Attachment Content.");
		return attachment;
	}
	
	public static Method anyMethod() {
		final Method method = new Method();
		method.setId(1);
		method.setTitle("Method Title");
		method.setContent("Some Method Content.");
		method.setSeminarType(anySeminarType());
		return method;
	}
	
	public static SeminarType anySeminarType() {
		final SeminarType seminarType = new SeminarType();
		seminarType.setId(1);
		seminarType.setName("Some Seminar Type");
		return seminarType;
	}
}