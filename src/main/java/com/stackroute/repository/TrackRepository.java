package com.stackroute.repository;

import com.stackroute.domain.Track;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends MongoRepository<Track, Integer> {
   // @Query(value = "select t from Track t where t.name=?")
   public List<Track> findByName(String name);


}
