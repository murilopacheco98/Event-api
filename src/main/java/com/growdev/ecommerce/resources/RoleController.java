package com.growdev.ecommerce.resources;

import com.growdev.ecommerce.dto.CityDTO;
import com.growdev.ecommerce.dto.RoleDTO;
import com.growdev.ecommerce.services.CityService;
import com.growdev.ecommerce.services.RoleService;
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
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/get/pageable")
    public ResponseEntity<Page<RoleDTO>> findAllPageable(
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,//Primeira página
            @RequestParam(value = "linhasPorPagina", defaultValue = "1") Integer linhasPorPagina,//quantidade de registros por pagina
            @RequestParam(value = "direction", defaultValue = "ASC") String direction,//direção Crescente
            @RequestParam(value = "ordenado", defaultValue = "nome") String nome //Ordem
            //exemplo de URL: /city/pageable?pagina=0&linhasPorPagina=12&nome=teste
    ) {
        PageRequest list = PageRequest.of(pagina, linhasPorPagina, Sort.Direction.valueOf(direction), nome);
        Page<RoleDTO> roleDTOS = roleService.findAllPaged(list);
        return ResponseEntity.ok().body(roleDTOS);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<RoleDTO>> findAllPageable() {
        return ResponseEntity.ok().body(roleService.findAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<RoleDTO> findById(@PathVariable("id") Long id) {
        RoleDTO roleDTO = roleService.findById(id);
        return ResponseEntity.ok().body(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/post")
    public ResponseEntity<RoleDTO> insert(@Valid @RequestBody RoleDTO roleDTO) {
        roleDTO = roleService.save(roleDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(roleDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(roleDTO);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<RoleDTO> atualizar(@Valid @RequestBody RoleDTO roleDTO, @PathVariable Long id) {
        roleDTO = roleService.save(roleDTO);
        return ResponseEntity.ok().body(roleDTO);
    }
}

