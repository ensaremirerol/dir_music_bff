package com.dir_music.bff.feign_controller.playlist_controller;


import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignCreateInput;
import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignRequesterUserIdInput;
import com.dir_music.bff.feign_controller.playlist_controller.input.PlaylistControllerFeignUpdateInput;
import com.dir_music.bff.feign_controller.playlist_controller.output.PlaylistControllerFeignListOutput;
import com.dir_music.bff.feign_controller.playlist_controller.output.PlaylistControllerFeignPlaylistOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "dir-playlist-service")
public interface PlaylistControllerFeign {
    @PostMapping(path = "playlists/create", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> createPlaylist(@RequestBody PlaylistControllerFeignCreateInput input);

    @DeleteMapping(path = "playlists/{playlistId}/delete", consumes = "application/json", produces = "application/json")
    ResponseEntity<?> deletePlaylist(@PathVariable String playlistId, @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

    @PutMapping(path = "playlists/{playlistId}/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> updatePlaylist(@PathVariable String playlistId,
                                                                         @RequestBody PlaylistControllerFeignUpdateInput input);


    @PostMapping(path = "playlists/{playlistId}/add/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> addSongToPlaylist(@PathVariable String playlistId,
                                                                            @PathVariable Long songId,
                                                                            @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

    @PostMapping(path = "playlists/{playlistId}/remove/{songId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> removeSongFromPlaylist(@PathVariable String playlistId,
                                                                                 @PathVariable Long songId,
                                                                                 @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

    @PostMapping(path = "playlists/{playlistId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignPlaylistOutput> getPlaylistById(@PathVariable String playlistId,
                                                                          @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

    @PostMapping(path = "playlists/user/{userId}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignListOutput> getPlaylistsByUserId(@PathVariable Long userId,
                                                                           @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

    @PostMapping(path = "playlists/search/{query}", consumes = "application/json", produces = "application/json")
    ResponseEntity<PlaylistControllerFeignListOutput> searchPlaylists(@PathVariable String query,
                                                                      @RequestBody PlaylistControllerFeignRequesterUserIdInput input);

}
