package mainPackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import mainPackage.spotify.SpotifyService;

@Controller
public class SongListController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/songs")
    public String showSongs(Model model) {
        try {
            // 1) קבל את רשימת השירים:
            model.addAttribute("songs", spotifyService.getAllSongs());
            // 2) חזור ל-JSP בשם song-list.jsp
            return "song-list"; 
        } catch (Exception e) {
            // במקרה של שגיאה, לנווט לדף Error כלשהו
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page"; 
        }
    }

    // PostMapping שיטפל בלחצנים: Show, Add, Edit, Delete
    @PostMapping("/songs/action")
    public String handleActions(
            @RequestParam(name="selectedSongId", required=false) Integer selectedSongId,
            @RequestParam(name="action", required=true) String action,
            Model model) {
        try {
            switch (action) {
                case "Show":
                    // הפניית המשתמש לדף פרטי השיר (READ-ONLY)
                    return "redirect:/song/show?songId=" + selectedSongId;

                case "Add":
                    // הפניית המשתמש לדף הוספת שיר חדש
                    return "redirect:/song/add";

                case "Edit":
                    return "redirect:/song/edit?songId=" + selectedSongId;

                case "Delete":
                    spotifyService.deleteSong(selectedSongId);
                    return "redirect:/songs"; // חזרה לדף הראשי, הטבלה תתעדכן

                default:
                    // אם הערך של action לא מוכר - חוזרים לטבלה
                    return "redirect:/songs";
            }
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error-page";
        }
    }
}
