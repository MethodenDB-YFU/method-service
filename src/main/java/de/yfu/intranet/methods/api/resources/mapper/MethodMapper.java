package de.yfu.intranet.methods.api.resources.mapper;

import de.yfu.intranet.methods.api.resources.AttachmentResource;
import de.yfu.intranet.methods.api.resources.MethodLevelResource;
import de.yfu.intranet.methods.api.resources.MethodTypeResource;
import de.yfu.intranet.methods.data.domain.Attachment;
import de.yfu.intranet.methods.data.domain.MethodLevel;
import de.yfu.intranet.methods.data.domain.MethodType;
import org.mapstruct.Mapper;

import de.yfu.intranet.methods.api.resources.MethodResource;
import de.yfu.intranet.methods.data.domain.Method;

import java.util.Set;

@Mapper
public interface MethodMapper {
	
	Method mapToDataObject(MethodResource methodResource);
	MethodResource mapFromDataObject(Method method);

	Attachment mapToDataObject(AttachmentResource attachmentResource);
	AttachmentResource mapFromDataObject(Attachment attachment);

	Set<MethodType> mapToMethodTypes(Set<MethodTypeResource> methodTypeResources);
	Set<MethodTypeResource> mapToMethodTypeResources(Set<MethodType> methodTypes);

	MethodType mapToDataObject(MethodTypeResource methodTypeResource);
	MethodTypeResource mapFromDataObject(MethodType methodType);

	MethodLevel mapToDataObject(MethodLevelResource methodLevelResource);
	MethodLevelResource mapFromDataObject(MethodLevel methodLevel);
	
}
