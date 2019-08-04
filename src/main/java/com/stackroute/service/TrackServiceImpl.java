package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
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
        return savedTrack;

    }


    @Override
    public Track getTrackById(int id) {
        Track retrieveTrackById = trackRepository.findById(id).get();
        return retrieveTrackById;
    }

    @Override
    public List<Track> getAllTracks() {
        List<Track> retrieveTrack = trackRepository.findAll();
        return retrieveTrack;

    }


    @Override
    public Track deleteTrackById(int id) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        trackRepository.deleteById(id);
        return optionalTrack.get();
    }

    @Override
    public Track updateTrackById(int id, Track track) {
        Optional<Track> optionalTrack = trackRepository.findById(id);
        Track updateTrack = trackRepository.save(track);
        return updateTrack;

    }

    @Override
    public List<Track> getTrackByName(String name) {
        List<Track> trackList = trackRepository.getTrackByName(name);
        return trackList;

    }
}