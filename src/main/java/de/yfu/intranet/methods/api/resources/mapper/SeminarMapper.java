package de.yfu.intranet.methods.api.resources.mapper;

import de.yfu.intranet.methods.api.resources.SeminarGoalResource;
import de.yfu.intranet.methods.data.domain.SeminarGoal;
import org.mapstruct.Mapper;

@Mapper
public interface SeminarMapper {

    SeminarGoal mapToDataObject(SeminarGoalResource seminarGoalResource);
    SeminarGoalResource mapFromDataObject(SeminarGoal seminarGoal);


}
