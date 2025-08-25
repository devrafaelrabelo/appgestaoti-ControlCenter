package com.controlcenter.common.repository;

import com.controlcenter.entity.common.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    // Busca exata por CEP
    Optional<Address> findFirstByPostalCode(String postalCode);

    // Lista por cidade (case-insensitive) ordenando por rua
    List<Address> findByCityIgnoreCaseOrderByStreetAsc(String city);

    // Lista por estado (UF), ordenando cidade e rua
    List<Address> findByStateIgnoreCaseOrderByCityAscStreetAsc(String state);

    // Verificação de possível duplicidade (CEP + número)
    boolean existsByPostalCodeAndNumber(String postalCode, String number);

    // Busca por país (útil se tiver prédio fora do BR)
    List<Address> findByCountryIgnoreCaseOrderByStateAscCityAsc(String country);
}
