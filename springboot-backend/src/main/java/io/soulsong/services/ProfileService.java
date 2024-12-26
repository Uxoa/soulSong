package io.soulsong.services;

import io.soulsong.dtos.FavoriteSongDTO;
import io.soulsong.dtos.ProfileDTO;
import io.soulsong.entities.FavoriteSong;
import io.soulsong.entities.Profile;
import io.soulsong.entities.SongEssence;
import io.soulsong.entities.User;
import io.soulsong.mappers.FavoriteSongMapper;
import io.soulsong.mappers.ProfileMapper;
import io.soulsong.repositories.FavoriteSongRepository;
import io.soulsong.repositories.ProfileRepository;
import io.soulsong.repositories.SongEssenceRepository;
import io.soulsong.repositories.UserRepository;
import io.soulsong.services.external.SpotifyService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final SongEssenceRepository songEssenceRepository;
    private final FavoriteSongRepository favoriteSongRepository;
    private final SpotifyService spotifyService;
    private final FavoriteSongService favoriteSongService; // Dependencia añadida
    
    public ProfileService(UserRepository userRepository,
                          ProfileRepository profileRepository,
                          SongEssenceRepository songEssenceRepository,
                          FavoriteSongRepository favoriteSongRepository,
                          SpotifyService spotifyService,
                          FavoriteSongService favoriteSongService) { // Constructor actualizado
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.songEssenceRepository = songEssenceRepository;
        this.favoriteSongRepository = favoriteSongRepository;
        this.spotifyService = spotifyService;
        this.favoriteSongService = favoriteSongService;
    }
    
    @Transactional
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        User user = userRepository.findById(profileDTO.getUserId())
              .orElseThrow(() -> new RuntimeException("Para crear un perfil, debe registrarse"));
        
        Profile profile = ProfileMapper.toEntity(profileDTO);
        profile.setUser(user);
        
        Profile savedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(savedProfile);
    }
    
    @Transactional
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new EntityNotFoundException("Perfil no encontrado");
        }
        profileRepository.deleteById(id);
    }
    
    public Optional<ProfileDTO> getProfile(Long id) {
        return profileRepository.findById(id)
              .map(ProfileMapper::toDTO);
    }
    
    public List<ProfileDTO> getAllProfiles() {
        return profileRepository.findAll().stream()
              .map(ProfileMapper::toDTO)
              .collect(Collectors.toList());
    }
    
    @Transactional
    public ProfileDTO updateProfile(Long id, ProfileDTO profileDTO) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        profile.setUserName(profileDTO.getUserName());
        
        Profile updatedProfile = profileRepository.save(profile);
        return ProfileMapper.toDTO(updatedProfile);
    }
    
    @Transactional
    public FavoriteSongDTO addFavoriteSong(Long profileId, FavoriteSongDTO favoriteSongDTO) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        SongEssence songEssence = songEssenceRepository.findById(favoriteSongDTO.getSongEssence().getId())
              .orElseGet(() -> songEssenceRepository.save(favoriteSongDTO.getSongEssence().toEntity()));
        
        FavoriteSong favoriteSong = new FavoriteSong();
        favoriteSong.setProfile(profile);
        favoriteSong.setSongEssence(songEssence);
        favoriteSong.setAddedDate(LocalDateTime.now());
        
        FavoriteSong savedFavoriteSong = favoriteSongService.addFavorite(favoriteSong);
        
        return FavoriteSongMapper.toDTO(savedFavoriteSong);
    }
    
    @Transactional
    public void removeFavoriteSong(Long profileId, Long favoriteSongId) {
        FavoriteSong favoriteSong = favoriteSongRepository.findById(favoriteSongId)
              .orElseThrow(() -> new EntityNotFoundException("Canción favorita no encontrada"));
        
        if (!favoriteSong.getProfile().getId().equals(profileId)) {
            throw new RuntimeException("La canción favorita no pertenece al perfil especificado");
        }
        
        favoriteSongRepository.delete(favoriteSong);
    }
    
    @Transactional
    public void emptyDataProfile(Long id) {
        Profile profile = profileRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
        
        favoriteSongRepository.deleteAll(profile.getFavoriteSongs());
    }
    
    public List<FavoriteSongDTO> getFavoriteSongs(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
              .orElseThrow(() -> new EntityNotFoundException("Perfil no encontrado"));
        
        return profile.getFavoriteSongs().stream()
              .map(FavoriteSongMapper::toDTO)
              .collect(Collectors.toList());
    }
}
