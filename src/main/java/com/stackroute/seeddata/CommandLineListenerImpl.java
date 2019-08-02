package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineListenerImpl implements CommandLineRunner {
    private TrackRepository trackRepository;
    @Autowired
    public CommandLineListenerImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Track track1=new Track(70,"Megha","fbdbgfx");
        trackRepository.save(track1);
        Track track2=new Track(71,"Arijit","nice song");
        trackRepository.save(track2);
        Track track3=new Track(72,"Ankit","good");
        trackRepository.save(track3);
    }

    }

