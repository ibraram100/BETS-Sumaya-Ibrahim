package net.SumayaIbrahim.bets.service;

import net.SumayaIbrahim.bets.entity.Role;
import net.SumayaIbrahim.bets.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}