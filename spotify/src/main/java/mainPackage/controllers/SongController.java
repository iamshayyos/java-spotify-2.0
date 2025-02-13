package mainPackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import mainPackage.spotify.SpotifyService;
import mainPackage.spotify.Song;
import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/")
    public String showSongs(Model model) throws Exception {
        List<Song> songs = spotifyService.getAllSongs();
        model.addAttribute("songs", songs);
        return "song-list"; // מקושר לקובץ JSP להצגת השירים
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("song", new Song());
        return "song-form"; // טופס הוספת שיר
    }

    @PostMapping("/save")
    public String saveSong(@ModelAttribute("song") Song song) throws Exception {
        spotifyService.addSong(song);
        return "redirect:/songs/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) throws Exception {
        Song song = spotifyService.getSongById(id);
        model.addAttribute("song", song);
        return "song-form";
    }

    @PostMapping("/update")
    public String updateSong(@ModelAttribute("song") Song song) throws Exception {
        spotifyService.updateSong(song);
        return "redirect:/songs/";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable int id) throws Exception {
        spotifyService.deleteSong(id);
        return "redirect:/songs/";
    }
}
