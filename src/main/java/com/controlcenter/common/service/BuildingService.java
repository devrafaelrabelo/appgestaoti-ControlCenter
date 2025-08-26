package com.controlcenter.common.service;

import com.controlcenter.common.dto.AddressDTO;
import com.controlcenter.common.dto.BuildingDTO;
import com.controlcenter.common.dto.BuildingUpsertDTO;
import com.controlcenter.common.repository.BuildingCompanyRepository;
import com.controlcenter.common.repository.BuildingRepository;
import com.controlcenter.common.repository.CompanyRepository;
import com.controlcenter.entity.common.Address;
import com.controlcenter.entity.common.Building;
import com.controlcenter.entity.common.BuildingCompany;
import com.controlcenter.entity.common.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingCompanyRepository buildingCompanyRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public BuildingDTO create(BuildingUpsertDTO dto) {
        Building entity = new Building();
        applyUpsert(dto, entity);
        entity = buildingRepository.save(entity);
        return toDTO(entity);
    }

    /** Overload para o padrão “ID no JSON”. */
    @Transactional
    public BuildingDTO update(BuildingUpsertDTO dto) {
        if (dto.id() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is required");
        }
        Building entity = buildingRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));
        applyUpsert(dto, entity);
        entity = buildingRepository.save(entity);
        return toDTO(entity);
    }

    @Transactional(readOnly = true)
    public BuildingDTO get(UUID id) {
        Building entity = buildingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));
        return toDTO(entity);
    }

    @Transactional
    public void linkCompany(UUID buildingId, UUID companyId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));

        if (buildingCompanyRepository.existsByBuildingAndCompany(building, company)) return;

        BuildingCompany bc = BuildingCompany.builder()
                .building(building)
                .company(company)
                .build();
        buildingCompanyRepository.save(bc);
    }

    @Transactional
    public void unlinkCompany(UUID buildingId, UUID companyId) {
        Building building = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Building not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found"));

        buildingCompanyRepository.findByBuildingAndCompany(building, company)
                .ifPresent(buildingCompanyRepository::delete);
    }

    /* ============ helpers ============ */

    private void applyUpsert(BuildingUpsertDTO dto, Building entity) {
        if (dto.name() != null)        entity.setName(dto.name());
        if (dto.code() != null)        entity.setCode(dto.code());
        if (dto.description() != null) entity.setDescription(dto.description());
        if (dto.active() != null)      entity.setActive(dto.active());

        if (dto.address() != null) {
            Address a = new Address();
            a.setStreet(dto.address().getStreet());
            a.setNumber(dto.address().getNumber());
            a.setComplement(dto.address().getComplement());
            a.setNeighborhood(dto.address().getNeighborhood());
            a.setCity(dto.address().getCity());
            a.setState(dto.address().getState());
            a.setCountry(dto.address().getCountry());
            a.setPostalCode(dto.address().getPostalCode());
            entity.setAddress(a);
        }
    }

    private BuildingDTO toDTO(Building b) {
        AddressDTO addressDTO = null;
        if (b.getAddress() != null) {
            Address a = b.getAddress();
            addressDTO = new AddressDTO();
            addressDTO.setStreet(a.getStreet());
            addressDTO.setNumber(a.getNumber());
            addressDTO.setComplement(a.getComplement());
            addressDTO.setNeighborhood(a.getNeighborhood());
            addressDTO.setCity(a.getCity());
            addressDTO.setState(a.getState());
            addressDTO.setCountry(a.getCountry());
            addressDTO.setPostalCode(a.getPostalCode());
        }

        return new BuildingDTO(
                b.getId(),
                b.getName(),
                b.getCode(),
                b.getDescription(),
                addressDTO,
                b.isActive(),
                b.getCreatedAt(),
                b.getUpdatedAt()
        );
    }
}
