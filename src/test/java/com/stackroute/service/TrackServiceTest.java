package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TrackServiceTest {
    Track track;

    //crating mock for user repository
    @Mock
    TrackRepository trackRepository;
    // injecting mock as dependency into TrackService
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;

    @Before
    public void setUp() throws Exception {
        //initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setId(10);
        track.setName("Arijit");
        track.setComments("Melodies song");
        list = new ArrayList<>();
        list.add(track);
    }
    
    @After
    public void tearDown() throws Exception {
        track =null;

    }


    @Test
    public void givenInputToCheckSaveTrackTestSuccessAndGetOutput() throws TrackAlreadyExistsException {

        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack = trackService.saveTrack(track);
        Assert.assertEquals(track, savedTrack);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test(expected = TrackAlreadyExistsException.class)
    public void givenInputToCheckSaveTrackTestFailureAndGetOutput() throws TrackAlreadyExistsException {
        when(trackRepository.save((Track) any())).thenReturn(null);
        Track savedTrack = trackService.saveTrack(track);
        verify(trackRepository, times(1)).save(track);
        System.out.println("savedTrack" + savedTrack);

    }

    @Test
    public void givenInputToGetAllTracksAsOutput() throws Exception {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist = trackService.getAllTracks();
        Assert.assertEquals(list, tracklist);
        verify(trackRepository, times(1)).save(track);
    }

    @Test
    public void givenInputAsIdToGetTrackByIdInOutput() throws TrackNotFoundException {
        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn(Optional.ofNullable(track));
        Track gettrack = trackService.getTrackById(10);
        Assert.assertEquals(track, gettrack);
         verify(trackRepository, times(1)).save(track);
   
    }

    @Test
    public void givenInputToGetDeletedTrackById() throws TrackNotFoundException {
        trackRepository.save(track);
        when(trackRepository.existsById(10)).thenReturn(true);
        when(trackRepository.findById(10)).thenReturn(Optional.of(track));
        Track deleteTrack=trackService.deleteTrackById(10);
        Assert.assertEquals(track,deleteTrack);
        verify(trackRepository, times(1)).save(track);


    }
    @Test
    public void givenNewInputToGetTheUpdatedTrackById() throws TrackNotFoundException{
        trackRepository.save(track);
        Track t1 = new Track();
        t1.setId(10);
        t1.setName("Ankit");
        t1.setComments("Silent song");
        when(trackRepository.existsById(t1.getId())).thenReturn(true);
        when(trackRepository.findById(t1.getId())).thenReturn(Optional.of(track));
        Track trackUpdated =  trackService.updateTrackById(10,t1);
       Assert.assertEquals(trackUpdated,t1);
         verify(trackRepository, times(1)).save(track);


    }


    @Test
    public void givenInputToGetTheTrackByName() throws Exception {
        trackRepository.save(track);
        when(trackRepository.findByName("Arijit")).thenReturn(Collections.singletonList(track));
        List<Track> trackList = trackService.getByName("Arijit");
        Assert.assertEquals(list, trackList);
         verify(trackRepository, times(1)).save(track);

    }


}


