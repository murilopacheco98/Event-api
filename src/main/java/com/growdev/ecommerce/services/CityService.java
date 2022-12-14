package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.CityDTO;
import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

  @Autowired
  private CityRepository cityRepository;

  @Transactional(readOnly = true)
  public List<CityDTO> findAll() {
    List<City> cityList = cityRepository.findAll();
    return cityList.stream().map(CityDTO::new).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public CityDTO findById(Long id) {
    City city = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
    return new CityDTO(city);
  }


  public void delete(Long id) {
    try {
      cityRepository.deleteById(id);
    }//é do spring e serve para reclamar que nao executou nada no banco
    //na reclamação(exceção) devemos instancia/propagar uma excecao personalizada
    catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Not found " + id);
    }//caso a categoria tenha dados vinculados a ela, não poderá ser excluida, nisso, apresentamos
    //um erro de violação dos dados integrados.
    catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Intregrity Violation");
    }
  }

  public CityDTO save(CityDTO dto) {
    City entity = new City();
    entity.setName(dto.getName());
    try {
      cityRepository.save(entity);
    } catch (Exception e) {
      throw new DatabaseException("Can't save this city.");
    }
    return new CityDTO(entity);
  }

  public CityDTO update(CityDTO cityDTO, Long id) {
    try {
      City entity = cityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
      entity.setName(cityDTO.getName());
      cityRepository.save(entity);
      return new CityDTO(entity);
    } catch (ResourceNotFoundException e) {
      throw new ResourceNotFoundException("Not Found ID " + id);
    }
  }

  @Transactional(readOnly = true)
  public Page<CityDTO> findAllPaged(PageRequest pageRequest) {
    Page<City> list = cityRepository.findAll(pageRequest);
    return list.map(CityDTO::new);
  }
}
