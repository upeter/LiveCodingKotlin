package org.a_intro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AlbumJ_V02 {

    private final List<PhotoJ> photos;

    public AlbumJ_V02(List<PhotoJ> photos) {
        this.photos = photos;
    }

    public List<PhotoJ> getPhotos() {
        return photos;
    }

    public Double averageRating() {
        return photos.stream().flatMap(p -> p.getRatings().stream()).mapToDouble(i -> i).average().getAsDouble();
    }

    public AlbumJ_V02 add(PhotoJ photo) {
        photos.add(photo);
        return new AlbumJ_V02(photos);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumJ_V02 that = (AlbumJ_V02) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }


    public static void main(String [] args) {
        PhotoJ photo1 = new PhotoJ("https://en.wikipedia.org/wiki/Java_(programming_language)", LocalDateTime.now());
        PhotoJ photo2 = new PhotoJ("https://www.youtube.com/user/java/videos", LocalDateTime.now());
        AlbumJ_V02 album1 = new AlbumJ_V02(new ArrayList<>()).add(photo1).add(photo2);
    }

}
