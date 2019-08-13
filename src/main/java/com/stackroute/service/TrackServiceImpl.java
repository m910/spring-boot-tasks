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
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        if(trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("user already exists exception");
        }

        Track saveTrack=trackRepository.save(track);
        if(saveTrack==null){
            throw new TrackAlreadyExistsException("user already exists");
        }
        return saveTrack;
    }


    @Override
    public Track getTrackById(int id) throws TrackNotFoundException{

        if(!trackRepository.existsById(id)){
            throw new TrackNotFoundException("track not found exception");
        }
        Track getTrackById= trackRepository.getOne(id);
        if(getTrackById==null) {

            throw new TrackNotFoundException("track not found");
        }
        return getTrackById;
    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        if (trackRepository.findAll().isEmpty()) {

            throw new Exception("exception");
        }
        return getAllTracks();
    }


    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        if(trackRepository.existsById(id)){
            throw new TrackNotFoundException("track not found exception");
        }
        Track deleteTrackById= trackRepository.getOne(id);
        if(deleteTrackById==null) {

            throw new TrackNotFoundException("track not found");
        }
        return deleteTrackById;


    }

    @Override
    public Track updateTrackById(int id,Track track) throws TrackNotFoundException {
        if(trackRepository.existsById(id)){
            throw new TrackNotFoundException("track not found exception");
        }
        Track updateTrackById= trackRepository.getOne(id);
        if(updateTrackById==null) {

            throw new TrackNotFoundException("track not found");
        }
        return updateTrackById;

    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {
        if (trackRepository.findAll().isEmpty()) {

            throw new TrackNotFoundException("track not found");
        }
        Track getTrackByName = (Track) trackRepository.getTrackByName(name);
        if(getTrackByName==null) {

            throw new TrackNotFoundException("track not found");
        }
        return (List<Track>) getTrackByName;

    }
}
