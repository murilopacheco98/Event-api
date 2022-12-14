package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.dto.user.UserInsertDTO;
import com.growdev.ecommerce.dto.user.UserUpdateDTO;
import com.growdev.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  private UserService service;

  @GetMapping("/get/pageable")
  public ResponseEntity<Page<UserDTO>> findAllPageable(Pageable pageable) {
    Page<UserDTO> list = service.findAllPaged(pageable);
    return ResponseEntity.ok().body(list);
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<UserDTO> findById(Long id) {
    UserDTO dto = service.findById(id);
    return ResponseEntity.ok().body(dto);
  }

  @PostMapping("/post")
  public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserInsertDTO userInsertDTO){
    UserDTO newDTO = service.save(userInsertDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newDTO.getId())
      .toUri();
    return ResponseEntity.ok().body(newDTO);
  }

  @PutMapping("/put/{id}")
  public ResponseEntity<UserDTO> update(@Valid @RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id){
    UserDTO updatedDTO = service.update(id, userUpdateDTO);
    return ResponseEntity.ok().body(updatedDTO);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id){
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
