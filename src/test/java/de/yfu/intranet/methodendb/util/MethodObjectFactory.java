package de.yfu.intranet.methodendb.util;

import de.yfu.intranet.methodendb.dtos.AttachmentResource;
import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.models.*;

import java.util.Collections;
import java.util.UUID;

import static de.yfu.intranet.methodendb.util.SeminarObjectFactory.anySeminarGoal;

public class MethodObjectFactory {

    public static MethodType anyMethodType() {
        final MethodType methodType = new MethodType();
        methodType.setId(UUID.randomUUID());
        methodType.setName("Any Method Type Name");
        methodType.setDescription("Any Method Type Description");
        return methodType;
    }

    public static MethodTypeResource anyMethodTypeResource() {
        final MethodTypeResource methodTypeResource = new MethodTypeResource();
        methodTypeResource.setId(UUID.randomUUID());
        methodTypeResource.setName("Any Method Type Resource Name");
        methodTypeResource.setDescription("Any Method Type Resource Description");
        return methodTypeResource;
    }

    public static MethodLevel anyMethodLevel() {
        final MethodLevel methodLevel = new MethodLevel();
        methodLevel.setId(UUID.randomUUID());
        methodLevel.setName("Any Method Level Name");
        methodLevel.setDescription("Any Method Level Description");
        return methodLevel;
    }

    public static MethodLevelResource anyMethodLevelResource() {
        final MethodLevelResource methodLevelResource = new MethodLevelResource();
        methodLevelResource.setId(UUID.randomUUID());
        methodLevelResource.setName("Any Method Level Resource Name");
        methodLevelResource.setDescription("Any Method Level Resource Description");
        return methodLevelResource;
    }

    public static Attachment anyAttachment(User anyAdmin) {
        final Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID());
        attachment.setContent("Any Attachment Content");
        attachment.setCreatedBy(anyAdmin);
        attachment.setModifiedBy(anyAdmin);
        return attachment;
    }

    public static AttachmentResource anyAttachmentResource(User anyAdmin) {
        final AttachmentResource attachmentResource = new AttachmentResource();
        attachmentResource.setId(UUID.randomUUID());
        attachmentResource.setContent("Any Attachment Resource Content");
        attachmentResource.setCreatedBy(anyAdmin);
        attachmentResource.setModifiedBy(anyAdmin);
        return attachmentResource;
    }

    public static Method anyMethod(User anyAdmin) {
        final Method method = new Method();
        method.setId(UUID.randomUUID());
        method.setTitle("Any Method Title");
        method.setContent("Any Method Content");
        method.setAttachments(Collections.singleton(anyAttachment(anyAdmin)));
        method.setMethodLevels(Collections.singleton(anyMethodLevel()));
        method.setMethodTypes(Collections.singleton(anyMethodType()));
        method.setSeminarGoals(Collections.singleton(anySeminarGoal()));
        method.setCreatedBy(anyAdmin);
        method.setModifiedBy(anyAdmin);
        return method;
    }

    public static MethodResource anyMethodResource(
            MethodLevel anyMethodLevel,
            MethodType anyMethodType,
            SeminarGoal anySeminarGoal,
            User anyAdmin) {
        final MethodResource methodResource = new MethodResource();
        methodResource.setId(UUID.randomUUID());
        methodResource.setTitle("Any Method Resource Title");
        methodResource.setContent("Any Method Resource Content");
        methodResource.setAttachments(Collections.singleton(anyAttachment(anyAdmin)));
        methodResource.setMethodLevels(Collections.singleton(anyMethodLevel));
        methodResource.setMethodTypes(Collections.singleton(anyMethodType));
        methodResource.setSeminarGoals(Collections.singleton(anySeminarGoal));
        methodResource.setCreatedBy(anyAdmin);
        methodResource.setModifiedBy(anyAdmin);
        return methodResource;
    }
}
