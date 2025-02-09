package spotify;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // Create the Spring context container
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the SpotifyService bean from the container
        SpotifyService spotifyService = context.getBean("spotifyService", SpotifyService.class);

        // Use try-with-resources to ensure the scanner is closed properly
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                // Display menu options
            	System.out.println("\nMenu:");
            	System.out.println("1. Show all songs");
            	System.out.println("2. Show single song");
            	System.out.println("3. Add a song");
            	System.out.println("4. Update a song");
            	System.out.println("5. Delete a song");
            	System.out.println("6. Save songs to file");
            	System.out.println("7. Load songs from file");
            	System.out.println("8. Sort songs by name and save order");
            	System.out.println("9. Sort songs by artist and save order");
            	System.out.println("10. Sort songs by length and save order");
            	System.out.println("11. Sort songs by genre and save order");
            	System.out.println("0. Exit");

                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                try {
                    switch (choice) {
                        case 1: { // Show all songs
                            System.out.println("\nSong List:");
                            List<Song> songs = spotifyService.getAllSongs();
                            if (songs.isEmpty()) {
                                System.out.println("No songs available.");
                            } else {
                                songs.forEach(System.out::println);
                            }
                            break;
                        }
                        case 2: { // Show single song
                            System.out.print("Enter song ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            Song song = spotifyService.getSongById(id);
                            if (song != null) {
                                System.out.println("\n" + song);
                            } else {
                                System.out.println("Song with ID " + id + " not found.");
                            }
                            break;
                        }
                        case 3: { // Add a song
                            System.out.print("Enter song name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter artist name: ");
                            String artist = scanner.nextLine();
                            System.out.print("Enter genre: ");
                            String genre = scanner.nextLine();
                            System.out.print("Enter length: ");
                            double length = scanner.nextDouble();
                            scanner.nextLine(); // Consume newline
                            spotifyService.addSong(new Song(name, artist, genre, length));
                            System.out.println("Song added successfully.");
                            break;
                        }
                        case 4: { // Update a song
                            System.out.print("Enter song ID to update: ");
                            int id = scanner.nextInt();
                            scanner.nextLine(); // Consume newline
                            Song song = spotifyService.getSongById(id);
                            if (song != null) {
                                System.out.print("Enter new name: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter new artist name: ");
                                String artist = scanner.nextLine();
                                System.out.print("Enter new genre: ");
                                String genre = scanner.nextLine();
                                System.out.print("Enter new length: ");
                                double length = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline

                                song.setName(name);
                                song.setArtistName(artist);
                                song.setGenre(genre);
                                song.setLength(length);

                                spotifyService.updateSong(song);
                                System.out.println("Song updated successfully.");
                            } else {
                                System.out.println("Song with ID " + id + " not found.");
                            }
                            break;
                        }
                        case 5: { // Delete a song
                            deleteSong(spotifyService, scanner);
                            break;
                        }
                        case 6: { // Save songs to file
                            spotifyService.saveSongsToFile();
                            System.out.println("Songs saved to file.");
                            break;
                        }
                        case 7: { // Load songs from file
                            spotifyService.loadSongsFromFile();
                            System.out.println("Songs loaded from file.");
                            break;
                        }
                        case 8: { // Sort songs by name and save order
                            spotifyService.sortSongsByNameAndSave();
                            System.out.println("Songs sorted by name and saved.");
                            break;
                        }
                        case 9: { // Sort songs by artist and save order
                            spotifyService.sortSongsByArtistAndSave();
                            System.out.println("Songs sorted by artist and saved.");
                            break;
                        }
                        case 10: { // Sort songs by length and save order
                            spotifyService.sortSongsByLengthAndSave();
                            System.out.println("Songs sorted by length and saved.");
                            break;
                        }
                        case 11: { // Sort songs by genre and save order
                            spotifyService.sortSongsByGenreAndSave();
                            System.out.println("Songs sorted by genre and saved.");
                            break;
                        }
                  
                        case 0: { // Exit
                            running = false;
                            System.out.println("Exiting the program.");
                            break;
                        }
                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        } finally {
            context.close(); // Ensure Spring context is closed
        }
    }

	private static void deleteSong(SpotifyService spotifyService, Scanner scanner) throws Exception {
		System.out.print("Enter song ID to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine(); // Consume newline
		spotifyService.deleteSong(id);
		System.out.println("Song deleted successfully.");
	}
}
