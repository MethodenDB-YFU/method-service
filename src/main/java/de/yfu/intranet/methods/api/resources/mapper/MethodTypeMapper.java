package de.yfu.intranet.methods.api.resources.mapper;

        import de.yfu.intranet.methods.api.resources.MethodTypeResource;
        import de.yfu.intranet.methods.data.domain.MethodType;
        import org.mapstruct.Mapper;

        import java.util.Set;

@Mapper
public interface MethodTypeMapper {

    Set<MethodType> mapToMethodTypes(Set<MethodTypeResource> methodTypeResources);
    Set<MethodTypeResource> mapToMethodTypeResources(Set<MethodType> methodTypes);

    MethodType mapToMethodType(MethodTypeResource methodTypeResource);
    MethodTypeResource mapToResource(MethodType methodType);
}
