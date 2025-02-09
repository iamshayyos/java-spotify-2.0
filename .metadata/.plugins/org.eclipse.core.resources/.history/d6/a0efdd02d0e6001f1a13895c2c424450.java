package spotify.dao;

import spotify.Song;

import java.util.List;

public interface SongDao {
    List<Song> findAll() throws Exception; // Retrieve list of songs
    void save(Song song) throws Exception; // Save a song
    void update(Song song) throws Exception; // Update a song
    void delete(int id) throws Exception; // Delete a song by ID
    Song getById(int id) throws Exception; // Get a song by ID
    
    void saveToFile() throws Exception; // Save songs to file
    void loadFromFile() throws Exception; // Load songs from file
    void replaceAll(List<Song> songs) throws Exception;

}