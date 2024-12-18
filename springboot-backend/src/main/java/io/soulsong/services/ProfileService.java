package io.soulsong.services;

import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.requests.SpotifyRequest;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    
    private final SpotifyRequest spotifyRequest;
    
    public ProfileService(SpotifyRequest spotifyRequest) {
        this.spotifyRequest = spotifyRequest;
    }
    
    public void addSongToProfile(User user, String trackId) {
        Profile profile = user.getProfile();
        SongEssence songEssence = spotifyRequest.getSongEssence(trackId);
        
        profile.addFavoriteSong(songEssence);
    }
}
