package de.yfu.intranet.methodendb.helpers;

import de.yfu.intranet.methodendb.dtos.SeminarTypeResource;
import de.yfu.intranet.methodendb.dtos.SeminarGoalResource;
import de.yfu.intranet.methodendb.models.SeminarType;
import de.yfu.intranet.methodendb.models.SeminarGoal;
import org.mapstruct.Mapper;

@Mapper
public interface SeminarMapper {

    SeminarGoal mapToDataObject(SeminarGoalResource seminarGoalResource);
    SeminarGoalResource mapFromDataObject(SeminarGoal seminarGoal);

    SeminarType mapToDataObject(SeminarTypeResource seminarTypeResource);
    SeminarTypeResource mapFromDataObject(SeminarType seminarType);
}
