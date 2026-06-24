package com.spaceX.Space.X.Rocket.Repository;


import com.spaceX.Space.X.Rocket.Entity.LaunchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaunchRepository extends JpaRepository<LaunchEntity, String> {

    // methods

}
