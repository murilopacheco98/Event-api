package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.EventDTO;
import com.growdev.ecommerce.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
public class EventController {

  @Autowired
  private EventService eventService;

  @GetMapping("/get/pageable")
  public ResponseEntity<Page<EventDTO>> findAll(
    @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
    @RequestParam(value = "linhasPorPagina", defaultValue = "10") Integer linhasPorPagina,
    @RequestParam(value = "ordenado", defaultValue = "name") String nome,
    @RequestParam(value = "direction", defaultValue = "ASC") String direction//direção Crescente
  ) {
    //qual pagina que eu quero (0), quantidade linhas, direação, ordenação
    PageRequest list = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direction), nome);
    Page<EventDTO> dtos = eventService.findAll(list);
    return ResponseEntity.ok().body(dtos);
  }

  @PostMapping("/post")
  public ResponseEntity<EventDTO> create(@RequestBody EventDTO eventDTO) {
    eventService.save(eventDTO);
    return ResponseEntity.ok().body(eventDTO);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
    eventService.update(eventDTO, id);
    return ResponseEntity.ok().body(eventDTO);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    eventService.delete(id);
    return ResponseEntity.ok().body("Event deleted successfully.");
  }
}
