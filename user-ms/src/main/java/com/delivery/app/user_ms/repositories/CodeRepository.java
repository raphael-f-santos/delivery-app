package com.delivery.app.user_ms.repositories;

import com.delivery.app.user_ms.enums.CodeStatus;
import com.delivery.app.user_ms.models.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {
    Optional<Code> findFirstByUserIdAndStatus(Long userId, CodeStatus codeStatus);
    Optional<Code> findByUserIdAndOrderIdAndCodeAndStatus(Long userId, Long orderId, int code, CodeStatus codeStatus);
}
