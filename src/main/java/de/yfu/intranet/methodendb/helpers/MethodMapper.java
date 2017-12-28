package de.yfu.intranet.methodendb.helpers;

import de.yfu.intranet.methodendb.dtos.AttachmentResource;
import de.yfu.intranet.methodendb.dtos.MethodLevelResource;
import de.yfu.intranet.methodendb.dtos.MethodTypeResource;
import de.yfu.intranet.methodendb.models.Attachment;
import de.yfu.intranet.methodendb.models.MethodLevel;
import de.yfu.intranet.methodendb.models.MethodType;
import org.mapstruct.Mapper;

import de.yfu.intranet.methodendb.dtos.MethodResource;
import de.yfu.intranet.methodendb.models.Method;

@Mapper
public interface MethodMapper {
	
	Method mapToDataObject(MethodResource methodResource);
	MethodResource mapFromDataObject(Method method);

	Attachment mapToDataObject(AttachmentResource attachmentResource);
	AttachmentResource mapFromDataObject(Attachment attachment);

	MethodType mapToDataObject(MethodTypeResource methodTypeResource);
	MethodTypeResource mapFromDataObject(MethodType methodType);

	MethodLevel mapToDataObject(MethodLevelResource methodLevelResource);
	MethodLevelResource mapFromDataObject(MethodLevel methodLevel);
	
}
