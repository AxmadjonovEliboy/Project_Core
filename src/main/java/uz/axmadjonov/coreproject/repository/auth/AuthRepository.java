package uz.axmadjonov.coreproject.repository.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.axmadjonov.coreproject.entity.auth.AuthUser;
import uz.axmadjonov.coreproject.repository.BaseRepository;

import java.util.Optional;

/**
 * @author Axmadjonov Eliboy on Tue. 16:21. 05/07/22
 * @project CoreProject
 */
@Repository
public interface AuthRepository extends BaseRepository, JpaRepository<AuthUser, Long> {
    @Query(value = "select *  from auth_user a where a.deleted = false and a.phone_number = :phone_number ", nativeQuery = true)
    Optional<AuthUser> findPhoneNumberDeletedFalse(@Param("phone_number") String username);


    @Query(value = "select * from auth_user a where a.deleted = false and a.phone_number = :phone_number or a.email = :email", nativeQuery = true)
    Optional<AuthUser> findPhoneNumberOrEmailDeletedFalse(@Param("phone_number") String phoneNumber, @Param("email") String email);

    @Query(value = "select * from auth_user a where a.deleted = false and a.id = :id", nativeQuery = true)
    Optional<AuthUser> findByIdAndIsDeletedFalse(@Param("id") Long id);
}

