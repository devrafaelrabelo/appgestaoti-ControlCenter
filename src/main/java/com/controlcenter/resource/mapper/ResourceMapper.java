package com.controlcenter.resource.mapper;

import com.controlcenter.entity.resource.Resource;
import com.controlcenter.resource.dto.ResourceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    @Mapping(source = "statusId", target = "status.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "currentUserId", target = "currentUser.id")
    @Mapping(source = "resourceTypeId", target = "resourceType.id")
    Resource toEntity(ResourceDTO dto);

    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "currentUser.id", target = "currentUserId")
    @Mapping(source = "resourceType.id", target = "resourceTypeId")
    ResourceDTO toDto(Resource entity);
}