package com.stackroute.seeddata;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
    private TrackRepository trackRepository;

    @Autowired
    public ApplicationRunnerImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Track track1 = new Track(70, "Megha", "fbdbgfx");
        trackRepository.save(track1);
        Track track2 = new Track(71, "Arijit", "nice  song");
        trackRepository.save(track2);
        Track track3 = new Track(72, "Ankit", "good");
        trackRepository.save(track3);


    }
}
