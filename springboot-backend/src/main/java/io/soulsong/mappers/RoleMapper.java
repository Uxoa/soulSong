package io.soulsong.mappers;

import io.soulsong.dtos.RoleDTO;
import io.soulsong.entities.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {
    
    public RoleMapper() {}
    
    public static RoleDTO fromEntity(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());
        return dto;
    }
    
    public static Role toEntity(RoleDTO dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setName(dto.getName());
        return role;
    }
    
    public static List<RoleDTO> fromEntities(List<Role> roles) {
        return roles.stream().map(RoleMapper::fromEntity).collect(Collectors.toList());
    }
    
    public static List<Role> toEntities(List<RoleDTO> roles) {
        return roles.stream().map(RoleMapper::toEntity).collect(Collectors.toList());
    }
}
