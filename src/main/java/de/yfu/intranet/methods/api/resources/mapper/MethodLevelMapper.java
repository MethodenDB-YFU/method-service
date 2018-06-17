package de.yfu.intranet.methods.api.resources.mapper;

import de.yfu.intranet.methods.api.resources.MethodLevelResource;
import de.yfu.intranet.methods.data.domain.MethodLevel;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface MethodLevelMapper {

    Set<MethodLevel> mapToMethodLevels(Set<MethodLevelResource> methodLevelResources);
    Set<MethodLevelResource> mapToMethodLevelResources(Set<MethodLevel> methodLevels);

    MethodLevel mapToMethodLevel(MethodLevelResource methodLevelResource);
    MethodLevelResource mapToResource(MethodLevel methodLevel);
}