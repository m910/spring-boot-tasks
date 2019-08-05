package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;

import java.util.List;


public interface TrackService {

    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public Track getTrackById(int id);

    public List<Track> getAllTracks();

    public Track deleteTrackById(int id);

    public Track updateTrackById(int id, Track track);

   public List<Track> getByName(String name);

}
