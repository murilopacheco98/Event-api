package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.CityDTO;
import com.growdev.ecommerce.dto.RoleDTO;
import com.growdev.ecommerce.entities.City;
import com.growdev.ecommerce.entities.Role;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.CityRepository;
import com.growdev.ecommerce.repositories.RoleRepository;
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
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Transactional(readOnly = true)
    public List<RoleDTO> findAll() {
        List<Role> roleList = roleRepository.findAll();
        return roleList.stream().map(RoleDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
        return new RoleDTO(role);
    }


    public void delete(Long id) {
        try {
            roleRepository.deleteById(id);
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

    public RoleDTO save(RoleDTO roleDTO) {
        Role role = new Role();
        role.setAuthority(roleDTO.getAuthority());
        try {
            roleRepository.save(role);
        } catch (Exception e) {
            throw new DatabaseException("Can't save this city.");
        }
        return new RoleDTO(role);
    }

    public RoleDTO update(RoleDTO roleDTO, Long id) {
        try {
            Role role = roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("NOT FOUND " + id));
            role.setAuthority(roleDTO.getAuthority());
            roleRepository.save(role);
            return new RoleDTO(role);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Not Found ID " + id);
        }
    }

    @Transactional(readOnly = true)
    public Page<RoleDTO> findAllPaged(PageRequest pageRequest) {
        Page<Role> list = roleRepository.findAll(pageRequest);
        return list.map(RoleDTO::new);
    }
}

