package net.SumayaIbrahim.bets.service.RoleImpl;

import lombok.AllArgsConstructor;
import net.SumayaIbrahim.bets.entity.Role;
import net.SumayaIbrahim.bets.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl {
    private RoleRepository roleRepository;
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
