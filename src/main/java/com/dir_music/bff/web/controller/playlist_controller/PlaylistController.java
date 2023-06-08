package com.dir_music.bff.web.controller.playlist_controller;


import com.dir_music.bff.feign_controller.playlist_controller.PlaylistControllerFeign;
import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignCreateInput;
import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignRequesterUserIdInput;
import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignUpdateInput;
import com.dir_music.bff.feign_controller.playlist_controller.output.PlaylistControllerFeignListOutput;
import com.dir_music.bff.feign_controller.playlist_controller.output.PlaylistControllerFeignPlaylistOutput;
import com.dir_music.bff.web.controller.playlist_controller.input.PlaylistControllerCreateInput;
import com.dir_music.bff.web.controller.playlist_controller.input.PlaylistControllerUpdateInput;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController("PlaylistController")
@RequestMapping("/playlists")
public class PlaylistController {

    final PlaylistControllerFeign playlistControllerFeign;

    @Autowired
    public PlaylistController(PlaylistControllerFeign playlistControllerFeign) {
        this.playlistControllerFeign = playlistControllerFeign;
    }

    @PostMapping(path = "/create", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> createPlaylist(@RequestBody PlaylistControllerCreateInput input) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignCreateInput playlistControllerFeignCreateInput = PlaylistControllerFeignCreateInput.builder()
                .playlistName(input.getPlaylistName())
                .userName(input.getUserName())
                .isPublic(input.isPublic())
                .userId(requesterUserId)
                .build();
        return playlistControllerFeign.createPlaylist(playlistControllerFeignCreateInput);
    }

    @DeleteMapping(path = "playlists/{playlistId}/delete", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> deletePlaylist(@PathVariable String playlistId) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();
        return playlistControllerFeign.deletePlaylist(playlistId, playlistControllerFeignRequesterUserIdInput);
    }

    @PutMapping(path = "/{playlistId}/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> updatePlaylist(@PathVariable String playlistId,
                                                                         @RequestBody PlaylistControllerUpdateInput input) {

        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignUpdateInput playlistControllerFeignUpdateInput = PlaylistControllerFeignUpdateInput.builder()
                .playlistName(input.getPlaylistName())
                .isPublic(input.isPublic())
                .requesterUserId(requesterUserId)
                .userName(input.getUserName())
                .build();

        return playlistControllerFeign.updatePlaylist(playlistId, playlistControllerFeignUpdateInput);
    }

    @PostMapping(path = "/{playlistId}/add/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> addSongToPlaylist(@PathVariable String playlistId,
                                                                            @PathVariable Long songId) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();

        return playlistControllerFeign.addSongToPlaylist(playlistId, songId, playlistControllerFeignRequesterUserIdInput);

    }

    @PostMapping(path = "/{playlistId}/remove/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> removeSongFromPlaylist(@PathVariable String playlistId,
                                                                                 @PathVariable Long songId) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();
        return playlistControllerFeign.removeSongFromPlaylist(playlistId, songId, playlistControllerFeignRequesterUserIdInput);
    }

    @PostMapping(path = "/{playlistId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> getPlaylistById(@PathVariable String playlistId) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();
        return playlistControllerFeign.getPlaylistById(playlistId, playlistControllerFeignRequesterUserIdInput);
    }

    @PostMapping(path = "/user/{userId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignListOutput> getPlaylistsByUserId(@PathVariable Long userId) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();
        return playlistControllerFeign.getPlaylistsByUserId(userId, playlistControllerFeignRequesterUserIdInput);
    }

    @PostMapping(path = "playlists/search/{query}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignListOutput> searchPlaylists(@PathVariable String query) {
        final long requesterUserId = (long) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final PlaylistControllerFeignRequesterUserIdInput playlistControllerFeignRequesterUserIdInput = PlaylistControllerFeignRequesterUserIdInput.builder()
                .requesterUserId(requesterUserId)
                .build();
        return playlistControllerFeign.searchPlaylists(query, playlistControllerFeignRequesterUserIdInput);
    }

    @ExceptionHandler(FeignException.class)
    ResponseEntity<?> handleFeignException(FeignException e) {
        return ResponseEntity.status(e.status()).body(e.contentUTF8());
    }
}
