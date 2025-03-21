package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSellerDTO;
import com.devsuperior.dsmeta.dto.SellerAmountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private LocalDate min;
	private LocalDate max;

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SaleSellerDTO> getReport(String minDate, String maxDate, String name, Pageable pageable) {
		dateValidation(minDate, maxDate);
		return repository.getReport(min, max, name, pageable);
	}

	public List<SellerAmountDTO> getSummary(String minDate, String maxDate) {
		dateValidation(minDate, maxDate);
		return repository.getSummary(min, max);

	}

	private void dateValidation(String minDate, String maxDate) {
		max = (maxDate.isEmpty()) ? LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()) : LocalDate.parse(maxDate);
		min = (minDate.isEmpty()) ? max.minusYears(1L) : LocalDate.parse(minDate);
	}
}
