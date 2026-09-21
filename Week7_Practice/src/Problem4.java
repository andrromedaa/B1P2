import java.util.Scanner;

interface Playable {

    String play();

    String play(int fromSecond);

    String pause();
}

abstract class MediaFile {

    private static int counter = 1000;
    private final String fileId;

    MediaFile() {
        counter++;
        fileId = "MF-" + counter;
    }

    public String getFileId() {
        return fileId;
    }

    public abstract String getFormatInfo();
}

class AudioFile extends MediaFile implements Playable {

    private String title;

    AudioFile(String title) {
        this.title = title;
    }

    @Override
    public String play() {
        return "Playing audio: " + title;
    }

    @Override
    public String play(int fromSecond) {

        int minutes = fromSecond / 60;
        int seconds = fromSecond % 60;

        return "Playing audio: " + title +
                " from " + minutes + ":" + seconds;
    }

    @Override
    public String pause() {
        return "Audio paused";
    }

    @Override
    public String getFormatInfo() {
        return "Audio file, ID: " + getFileId();
    }
}

class Podcast implements Playable {

    private String showName;
    private int episodeNumber;

    Podcast(String showName, int episodeNumber) {
        this.showName = showName;
        this.episodeNumber = episodeNumber;
    }

    @Override
    public String play() {
        return "Streaming episode " +
                episodeNumber + " of " + showName;
    }

    @Override
    public String play(int fromSecond) {
        return "Streaming episode " +
                episodeNumber + " of " + showName +
                " from " + fromSecond + " seconds";
    }

    @Override
    public String pause() {
        return "Podcast paused";
    }
}

public class Problem4 {

    static void launchAll(Playable[] items) {

        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].play());
        }
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.print("Enter audio title: ");
        String title = in.nextLine();

        System.out.print("Enter podcast name: ");
        String showName = in.nextLine();

        System.out.print("Enter episode number: ");
        int episode = in.nextInt();

        AudioFile a = new AudioFile(title);
        Podcast p = new Podcast(showName, episode);

        System.out.println("\nAudio:");
        System.out.println(a.play());
        System.out.println(a.play(30));
        System.out.println(a.getFormatInfo());

        System.out.println("\nPodcast:");
        System.out.println(p.play());

        // Upcasting
        Playable ref = a;

        Playable[] items = {ref, p};

        System.out.println("\nLaunching all:");
        launchAll(items);

        in.close();
    }
}
