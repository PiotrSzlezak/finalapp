package pl.sda.finalapp.playlists;

public abstract class PlayableWithTitle implements Playable{

    private String title;

    public PlayableWithTitle(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public String play(){
        return title;
    }
}
