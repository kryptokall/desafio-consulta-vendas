package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SaleSellerDTO(sale.id, sale.amount, sale.date, sale.seller.name) " +
            "FROM Sale sale " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",

    countQuery = "SELECT COUNT(sale) " +
            "FROM Sale sale " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "AND UPPER(sale.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))"
    )
    Page<SaleSellerDTO> getReport(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate,
            @Param("name") String name,
            Pageable pageable
    );

    @Query(value = "SELECT new com.devsuperior.dsmeta.dto.SellerAmountDTO(sale.seller.name, SUM(sale.amount)) " +
            "FROM Sale sale " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY sale.seller.name",

    countQuery = "SELECT COUNT(sale) " +
            "FROM Sale sale " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY sale.seller.name"
    )
    List<SellerAmountDTO> getSummary(
            @Param("minDate") LocalDate minDate,
            @Param("maxDate") LocalDate maxDate
    );

}
