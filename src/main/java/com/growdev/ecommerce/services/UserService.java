package com.growdev.ecommerce.services;

import com.growdev.ecommerce.dto.RoleDTO;
import com.growdev.ecommerce.dto.user.UserDTO;
import com.growdev.ecommerce.dto.user.UserInsertDTO;
import com.growdev.ecommerce.dto.user.UserUpdateDTO;
import com.growdev.ecommerce.entities.Role;
import com.growdev.ecommerce.entities.UserEntity;
import com.growdev.ecommerce.exceptions.DatabaseException;
import com.growdev.ecommerce.exceptions.ResourceNotFoundException;
import com.growdev.ecommerce.repositories.RoleRepository;
import com.growdev.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//implements UserDetailsService
@Service
@Transactional
public class UserService { //foi implementado porque é ele que retorna
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public Page<UserDTO> findAllPaged(Pageable pageable) {
    Page<UserEntity> usuarios = userRepository.findAll(pageable);
    return usuarios.map(UserDTO::new);
  }

  @Transactional(readOnly = true)
  public UserDTO findById(Long id) {
    UserEntity usuario = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
    return new UserDTO(usuario);
  }

  public UserDTO save(UserInsertDTO userInsertDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setNome(userInsertDTO.getNome());
    userEntity.setEmail(userInsertDTO.getEmail());
    //vamos ter que pegar a senha e converter para bycrypt
    userEntity.setSenha(passwordEncoder.encode(userInsertDTO.getSenha()));
    if (userInsertDTO.getRoles().isEmpty()) {
      Role role = roleRepository.findByAuthority("ROLE_CLIENT");
      if (role == null) {
        throw new ResourceNotFoundException("A role client não existe.");
      }
      userRepository.save(userEntity);
      return new UserDTO(userEntity);
    }
    for (RoleDTO roleDTO : userInsertDTO.getRoles()) {
      Role role = roleRepository.findById(roleDTO.getId())
              .orElseThrow(() -> new ResourceNotFoundException("Role não encontrada."));
      userEntity.getRoles().add(role);
    }
    userRepository.save(userEntity);
    return new UserDTO(userEntity);
  }

  public UserDTO update(Long id, UserUpdateDTO userUpdateDTO) {
    UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
    userEntity.setNome(userUpdateDTO.getNome());
    userEntity.setEmail(userUpdateDTO.getEmail());
    // vamos ter que pegar a senha e converter para bycrypt
    userEntity.setSenha(passwordEncoder.encode(userUpdateDTO.getSenha()));
    // PERCORRO O DTO que vai no endpoint(controller) e tiro dali a lista de roles
    if (userUpdateDTO.getRoles().isEmpty()) {
      throw new ResourceNotFoundException("A role é obrigatória.");
    }
    for (RoleDTO roleDTO : userUpdateDTO.getRoles()) {
      Role role = roleRepository.findById(roleDTO.getId())
              .orElseThrow(() -> new ResourceNotFoundException("Role não encontrad"));
      userEntity.getRoles().add(role);
    }
    userRepository.save(userEntity);
    return new UserDTO(userEntity);
  }

  public void delete(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Id not found " + id);
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Intregrity violation");
    }
  }

  //O loadUserByUsername você faz o seguinte, recebe o usuario e verifica se ele existe para ter acesso
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    UserEntity usuario = userRepository.findByEmail(username);
//    if(usuario == null){
//      throw new UsernameNotFoundException("Email não existe");
//    }
//    return usuario;
//  }
}
