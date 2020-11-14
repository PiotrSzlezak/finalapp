package pl.sda.finalapp.playlists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Playlist implements Playable {

    private List<Playable> elements;
    private PlayMode playMode = PlayMode.SEQUENTIALLY;
    private int times;

    @Override
    public String play() {

        if (playMode == PlayMode.SEQUENTIALLY) {
            return play(elements);
        }

        if (playMode == PlayMode.RANDOM) {
            List<Playable> tempElements = new ArrayList<>(elements);
            Collections.shuffle(tempElements);
            return play(tempElements);
        }

        if (playMode == PlayMode.LOOP) {
//            Stream.

            return IntStream.iterate(1, a -> a + 1)
                    .limit(times)
                    .mapToObj(e -> play(elements))
                    .collect(Collectors.joining("\n"));
        }

        return null;
    }

    private String play(List<Playable> tempElements) {
        return tempElements.stream()
                .map(e -> e.play())
                .collect(Collectors.joining("\n"));
    }

    public void addElement(Playable playable) {
        if (elements == null) {
            elements = new ArrayList<>();
        }
        elements.add(playable);
    }

    public void setRandom() {
        this.playMode = PlayMode.RANDOM;
    }

    public void setLoop(int times) {
        this.playMode = PlayMode.LOOP;
        this.times = times;
    }

    public void setSequentially() {
        this.playMode = PlayMode.SEQUENTIALLY;
    }

}
