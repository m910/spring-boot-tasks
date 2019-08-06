package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class TrackController {
    private TrackService trackService;
    private TrackRepository trackRepository;

//    @Autowired
//    public TrackController( @Qualifier("dummy") TrackService trackService) {
//        this.trackService = trackService;
//    }

    @Autowired
    public TrackController(TrackService trackService){this.trackService=trackService;}
    @PostMapping("track")
    public ResponseEntity<?> setTrack(@RequestBody Track track) throws TrackAlreadyExistsException{
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Created", HttpStatus.CREATED);
        } catch (TrackAlreadyExistsException e) {
            System.out.println("msg" + e.getMessage());
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
            e.printStackTrace();
        }
        return responseEntity;

    }

    @GetMapping("tracks")
    //to get the List of track
    public ResponseEntity<?> getAllTracks() throws Exception {
        ResponseEntity responseEntity;
        responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
        return responseEntity;
    }
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Track retrivedTrack = trackService.getTrackById(id);
        return new ResponseEntity<>(retrivedTrack, HttpStatus.CREATED);
    }

    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") int id) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Track retrivedTrack = trackService.deleteTrackById(id);
        return new ResponseEntity<>(retrivedTrack, HttpStatus.NO_CONTENT);
    }
    @PutMapping("track/{id}")
    //to update the track
    public ResponseEntity<?> updateTrackById(@PathVariable int id,Track track) throws TrackNotFoundException {
        ResponseEntity responseEntity;
        Track trackUpdated = trackService.updateTrackById(id,track);
        responseEntity = new ResponseEntity<>(trackUpdated, HttpStatus.UPGRADE_REQUIRED);
        return responseEntity;
    }
    @GetMapping("tracks/{name}")
    //to get the track by name
    public ResponseEntity<?> getFindByName(@PathVariable String name) throws Exception {
        ResponseEntity responseEntity;
        System.out.println(name);
        List<Track> retrivedTrack = trackService.getByName(name);
        return new ResponseEntity<>(retrivedTrack, HttpStatus.CREATED);
    }
}
