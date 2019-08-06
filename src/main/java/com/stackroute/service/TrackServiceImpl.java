package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
//@profile is used to compose @Bean

//@Primary is used to give preference when more than one beans are present
//@Primary
//@Qualifier is used to specify which should be autowired on field
//@Qualifier("main")
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())) {
            throw new TrackAlreadyExistsException("User already exists");
        }
        Track savedTrack = trackRepository.save(track);
        if(trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("track already exists");
        }
        else{
            savedTrack = trackRepository.save(track);
            if(savedTrack == null){
                throw new TrackAlreadyExistsException("user already exists");
            }
        }
        return savedTrack;

    }


    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("not found");
        }
        Track retriveTrack = trackRepository.findById(id).get();
        return retriveTrack;
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()) {
            throw new Exception("Internal server error");
        } else {
            List<Track> allTracks = trackRepository.findAll();
            return allTracks;
        }

    }


    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException{
        if (trackRepository.existsById(id)) {
            Track retrivedTrack = trackRepository.findById(id).get();
            trackRepository.deleteById(id);
            return retrivedTrack;
        } else {
            throw new TrackNotFoundException("Track not found");
        }
    }

    @Override
    public Track updateTrackById(int id, Track track) throws TrackNotFoundException {
        if (!trackRepository.existsById(id)) {
            throw new TrackNotFoundException("track not found");
        }
        Track updateTrackById = trackRepository.findById(id).get();
        updateTrackById.setId(track.getId());
        updateTrackById.setName(track.getName());
        updateTrackById.setComments(track.getComments());
        return updateTrackById;

    }

   @Override
    public List<Track> getByName(String name) throws Exception {
       if (trackRepository.findByName(name).isEmpty()) {
           throw new Exception("no track with this name");
       } else {
           List<Track> allTracksByName = trackRepository.findByName(name);
           return allTracksByName;
       }
   }
    }
