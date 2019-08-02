package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }


    @Override
    public Track saveTrack(Track track) {

        Track saveTrack= trackRepository.save(track);
        return saveTrack;
    }

    @Override
    public Track getTrackById(int id) {
        Track retrivedTrack = trackRepository.findById(id).get();
        return retrivedTrack;
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
}
