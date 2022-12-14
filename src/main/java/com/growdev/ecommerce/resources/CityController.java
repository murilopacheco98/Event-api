package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.CityDTO;
import com.growdev.ecommerce.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/city")
public class CityController {
  @Autowired
  private CityService cityService;

  @GetMapping("/get/pageable")
  public ResponseEntity<Page<CityDTO>> findAllPageable(
    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,//Primeira página
    @RequestParam(value = "linhasPorPagina", defaultValue = "1") Integer linhasPorPagina,//quantidade de registros por pagina
    @RequestParam(value = "direction", defaultValue = "ASC") String direction,//direção Crescente
    @RequestParam(value = "ordenado", defaultValue = "name") String nome //Ordem
    //exemplo de URL: /city/get/pageable?pagina=0&linhasPorPagina=12&nome=teste
  ) {
    PageRequest list = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direction), nome);
    Page<CityDTO> cityDTOS = cityService.findAllPaged(list);
    return ResponseEntity.ok().body(cityDTOS);
  }

  @GetMapping("/get/all")
  public ResponseEntity<List<CityDTO>> findAllPageable() {
    return ResponseEntity.ok().body(cityService.findAll());
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<CityDTO> findById(@PathVariable("id") Long id) {
    CityDTO dto = cityService.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
    cityService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/post")
  public ResponseEntity<CityDTO> insert(@Valid @RequestBody CityDTO cityDTO) {
    cityDTO = cityService.save(cityDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
      .buildAndExpand(cityDTO.getId()).toUri();
    return ResponseEntity.created(uri).body(cityDTO);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<CityDTO> atualizar(@Valid @RequestBody CityDTO cityDTO, @PathVariable Long id) {
    cityDTO = cityService.save(cityDTO);
    return ResponseEntity.ok().body(cityDTO);
  }
}
