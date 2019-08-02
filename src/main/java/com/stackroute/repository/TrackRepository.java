package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends JpaRepository<Track, Integer> {
    @Query( value = "select t from Track t where t.name='?'", nativeQuery = true )
     public List<Track> getTrackByName(String name);

}
