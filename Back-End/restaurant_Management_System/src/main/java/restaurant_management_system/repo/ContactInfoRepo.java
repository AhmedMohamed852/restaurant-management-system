package restaurant_management_system.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant_management_system.model.ContactInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {

    Optional<ContactInfo> findByName(String name);

   Optional<List<ContactInfo>> findAllByUsersId(Long id);
    @Query("SELECT c FROM ContactInfo c WHERE c.replyMessage IS NULL")
    List<ContactInfo> findAllWhereReplyIsNull();

}
