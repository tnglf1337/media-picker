package com.mediapicker.db;

import com.mediapicker.domain.mediathek.Mediathek;
import com.mediapicker.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface MediathekDao extends JpaRepository<Mediathek, UUID> {

  Mediathek findByUser(User user);

}
