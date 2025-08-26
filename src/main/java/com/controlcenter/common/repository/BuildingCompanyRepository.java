package com.controlcenter.common.repository;

import com.controlcenter.entity.common.Building;
import com.controlcenter.entity.common.BuildingCompany;
import com.controlcenter.entity.common.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BuildingCompanyRepository extends JpaRepository<BuildingCompany, UUID> {

    boolean existsByBuildingAndCompany(Building building, Company company);

    Optional<BuildingCompany> findByBuildingAndCompany(Building building, Company company);

    boolean existsByBuildingIdAndCompanyId(UUID buildingId, UUID companyId);

    Optional<BuildingCompany> findByBuildingIdAndCompanyId(UUID buildingId, UUID companyId);
}