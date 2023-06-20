package cop.project.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cop.project.user.dbo.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

    @Query("SELECT u FROM Location u WHERE u.businessId = :businessId")
    List<Location> findByBusinessId(@Param("businessId") Long businessId);

}

