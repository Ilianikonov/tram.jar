package com.metro.tram.dao;

import com.metro.tram.entity.TramRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TramRouteRepository extends JpaRepository<TramRoute,Long> {

}
