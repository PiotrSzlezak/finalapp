package pl.sda.finalapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.sda.finalapp.playlists.Movie;
import pl.sda.finalapp.playlists.Music;
import pl.sda.finalapp.playlists.Playlist;

class PlaylistTest {

    @Test
    public void shouldPlaySequentially() {
        Playlist playlist = new Playlist();
        Movie movie1 = new Movie("Studniówka");
        Music music1 = new Music("Kazik", "12 groszy");
        Movie movie2 = new Movie("Kler");
        Playlist subPlaylist = new Playlist();
        subPlaylist.addElement(movie2);
        playlist.addElement(movie1);
        playlist.addElement(music1);
        playlist.addElement(subPlaylist);

        System.out.println(playlist.play());
    }

    @Test
    public void shouldPlayRandomly() {
        Playlist playlist = new Playlist();
        Movie movie1 = new Movie("Studniówka");
        Music music1 = new Music("Kazik", "12 groszy");
        Movie movie2 = new Movie("Kler");
        Playlist subPlaylist = new Playlist();
        subPlaylist.addElement(movie2);
        playlist.addElement(movie1);
        playlist.addElement(music1);
        playlist.addElement(subPlaylist);
        playlist.setRandom();

        System.out.println(playlist.play());
    }

    @Test
    public void shouldPlayInLoop() {
        Playlist playlist = new Playlist();
        Movie movie1 = new Movie("Studniówka");
        Music music1 = new Music("Kazik", "12 groszy");
        Movie movie2 = new Movie("Kler");
        Playlist subPlaylist = new Playlist();
        subPlaylist.addElement(movie2);
        playlist.addElement(movie1);
        playlist.addElement(music1);
        playlist.addElement(subPlaylist);
        playlist.setLoop(3);

        String result = "Studniówka\n" +
                "12 groszy Kazik\n" +
                "Kler\n" +
                "Studniówka\n" +
                "12 groszy Kazik\n" +
                "Kler\n" +
                "Studniówka\n" +
                "12 groszy Kazik\n" +
                "Kler";
        Assertions.assertEquals(playlist.play(), result);
    }
}