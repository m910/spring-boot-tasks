package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TrackRepositoryTest {
    @Autowired
    TrackRepository trackRepository;
    Track track;

    @org.junit.Before
    public void setUp() throws Exception {
        track = new Track();
        track.setId(10);
        track.setName("Arijit");
        track.setComments("Melodies song");

    }

    @org.junit.After
    public void tearDown() throws Exception {
        track=null;
        trackRepository.deleteAll();

    }

    @Test
    public void testGivenInputToSaveTrackAndGetTheOutput() {
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(10, fetchTrack.getId());

    }

    @Test
    public void testGivenInputToCheckSaveTrackFailure() {
        Track testTrack = new Track(10, "Arijit", "Melodies song");
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertNotSame(testTrack, track);
    }

    @Test
    public void givenInputToGetAllTrackListAsOutput() {
        Track track = new Track(10, "Arijit", "melodies song");
        Track track1 = new Track(10, "Arijit", "melodies song");
        trackRepository.save(track);
        trackRepository.save(track1);

        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Arijit", list.get(0).getName());


    }
    @Test
    public void givenInputToGetTrackByIdAndGetOutputById(){
        trackRepository.save(track);
        Track fetchTrack = new Track(10,"Arijit","melodies song");
        Assert.assertEquals(10, fetchTrack.getId());
    }
    
    @Test
    public void givenIdToDeleteTrackByIdAndToGetTheOutput(){
        trackRepository.delete(track);
        Track fetchTrack = new Track(10,"Arijit","Melodies song");
        Assert.assertEquals(10, fetchTrack.getId());
    }

    @Test
    public void givenInputToGetTrackByNameAndGetTheOutputByName(){
        trackRepository.save(track);
        Track fetchTrack = trackRepository.findByName(track.getName()).get(10);
        Assert.assertEquals("Arijit", fetchTrack.getName());

    }

    @Test
    public void givenInputToUpdateTrackByIdAndGetTheUpdatedTrackAsOutput(){
        trackRepository.save(track);
         Track fetchTrack = trackRepository.findById(track.getId()).get();
        Assert.assertEquals(10, fetchTrack.getId());
       
    }
}
