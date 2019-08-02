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
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track deleteTrackById(int id) {
         Optional<Track> optionalTrack= trackRepository.findById(id);
        trackRepository.deleteById(id);
         return optionalTrack.get();
    }

    @Override
    public Track updateTrackById(int id,Track track) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        Track updateTrack= trackRepository.save(track);
        return updateTrack;

    }

    @Override
    public List<Track> getTrackByName(String name) {
        List<Track> listOfTracks = trackRepository.getTrackByName(name);
        return listOfTracks;
    }
}