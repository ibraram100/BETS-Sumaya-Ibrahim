package net.SumayaIbrahim.bets.repository;

import net.SumayaIbrahim.bets.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

}
