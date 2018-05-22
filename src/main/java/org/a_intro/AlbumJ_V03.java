package org.a_intro;

import java.time.LocalDateTime;
import java.util.*;

public class AlbumJ_V03 {

    private final List<PhotoJ> photos;

    public AlbumJ_V03(List<PhotoJ> photos) {
        this.photos = photos;
    }

    public List<PhotoJ> getPhotos() {
        return photos;
    }

    public Double averageRating() {
        return photos.stream().flatMap(p -> p.getRatings().stream()).mapToDouble(i -> i).average().getAsDouble();
    }

    public AlbumJ_V03 add(PhotoJ photo) {
        photos.add(photo);
        return new AlbumJ_V03(photos);
    }

    public AlbumJ_V03 combine(AlbumJ_V03 other) {
        Set<PhotoJ> unique = new HashSet<>(photos);
        unique.addAll(other.photos);
        List<PhotoJ> all = new ArrayList<>();
        all.addAll(unique);
        return new AlbumJ_V03(all);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumJ_V03 that = (AlbumJ_V03) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }


    public static void main(String [] args) {
        PhotoJ photo1 = new PhotoJ("https://en.wikipedia.org/wiki/Java_(programming_language)", LocalDateTime.now());
        PhotoJ photo2 = new PhotoJ("https://www.youtube.com/user/java/videos", LocalDateTime.now());
        PhotoJ photo3 = new PhotoJ("https://centerforcreativity.net/event/introduction-to-programming-with-java/", LocalDateTime.now());

        AlbumJ_V03 album1 = new AlbumJ_V03(new ArrayList<>()).add(photo1).add(photo2);
        AlbumJ_V03 album2 = new AlbumJ_V03(Arrays.asList(photo3));

        AlbumJ_V03 combined = album1.combine(album2);



    }

}
