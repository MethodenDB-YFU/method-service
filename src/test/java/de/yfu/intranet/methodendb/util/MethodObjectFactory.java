package de.yfu.intranet.methodendb.util;

import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.models.Method;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;

import java.util.UUID;

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
}
