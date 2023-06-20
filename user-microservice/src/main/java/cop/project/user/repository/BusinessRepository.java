package cop.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cop.project.user.dbo.Business;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
