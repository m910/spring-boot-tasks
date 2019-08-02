package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
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
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.OK);
        }
        catch(TrackAlreadyExistsException ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            ex.printStackTrace();

        }
        return responseEntity;
    }

    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable int id) {
        ResponseEntity responseEntity;
        try {
            trackService.getTrackById(id);
            responseEntity = new ResponseEntity("successfully got", HttpStatus.CREATED);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("tracks")
    public ResponseEntity<?> getAllTracks(){
        ResponseEntity responseEntity;
        try {
            trackService.getAllTracks();
            responseEntity = new ResponseEntity("successfully got", HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            ex.printStackTrace();

        }
        return responseEntity;
    }

    @DeleteMapping("track/{id}")
     public ResponseEntity<?> deleteTrackById(@PathVariable int id){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrackById(id);
            responseEntity = new ResponseEntity("successfully got", HttpStatus.CREATED);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;

    }

    @PutMapping("track/{id}")
    public ResponseEntity<?> updateTrackById(@PathVariable int id,@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.updateTrackById(id,track);
            responseEntity = new ResponseEntity("successfully got", HttpStatus.CREATED);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }
    @GetMapping("tracks/{name}")
    public ResponseEntity<?> getTrackByName(@PathVariable String name){
        ResponseEntity responseEntity;
        try {
            trackService.getTrackByName(name);
            responseEntity = new ResponseEntity("successfully got", HttpStatus.CREATED);
        } catch (TrackNotFoundException ex) {
            responseEntity = new ResponseEntity(ex.getMessage(), HttpStatus.CONFLICT);
            ex.printStackTrace();
        }
        return responseEntity;
    }

}
