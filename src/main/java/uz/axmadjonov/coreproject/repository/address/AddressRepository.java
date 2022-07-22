package uz.axmadjonov.coreproject.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.axmadjonov.coreproject.entity.address.Address;
import uz.axmadjonov.coreproject.repository.BaseRepository;

/**
 * @author Axmadjonov Eliboy on Tue. 21:02. 19/07/22
 */
@Repository
public interface AddressRepository extends BaseRepository, JpaRepository<Address, Long> {
}
