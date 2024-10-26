package net.SumayaIbrahim.bets.repository;


import net.SumayaIbrahim.bets.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// what we are basically doing is extending Spring Data JPA's JpaRepository
// which provide methods for performing CRUD (Create, Read, Update, Delete) operations on your entities
public interface UserRepository extends JpaRepository<User, Long> {

}
