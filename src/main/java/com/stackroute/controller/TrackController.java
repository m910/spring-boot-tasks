package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TrackController {
    TrackService trackService;

    public TrackController() {
    }
    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> setTrack(@RequestBody Track track){
        Track savedTrack= trackService.saveTrack(track);
        return new ResponseEntity<>(savedTrack, HttpStatus.OK);
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable("id") int id){
        Track retrivedTrack = trackService.getTrackById(id);
        return new ResponseEntity<Track>(retrivedTrack,HttpStatus.OK);
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<List<Track>>( trackService.getAllTracks(), HttpStatus.OK);

    }

    @DeleteMapping("track/{id}")
     public ResponseEntity<?> deleteTrackById(@PathVariable int id){
         Track deleteTrack = trackService.deleteTrackById(id);
         return new ResponseEntity<>(deleteTrack,HttpStatus.OK);
    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id,@RequestBody Track track){
        Track updateTrack = trackService.updateTrackById(id,track);
        return new ResponseEntity<>(updateTrack,HttpStatus.OK);
    }

}
