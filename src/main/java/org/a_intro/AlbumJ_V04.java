package org.a_intro;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AlbumJ_V04 {

    private final List<PhotoJ> photos;

    public AlbumJ_V04(List<PhotoJ> photos) {
        this.photos = photos;
    }

    public List<PhotoJ> getPhotos() {
        return photos;
    }

    public Double averageRating() {
        return photos.stream().flatMap(p -> p.getRatings().stream()).mapToDouble(i -> i).average().getAsDouble();
    }

    public AlbumJ_V04 add(PhotoJ photo) {
        photos.add(photo);
        return new AlbumJ_V04(photos);
    }

    public AlbumJ_V04 combine(AlbumJ_V04 other) {
        Set<PhotoJ> unique = new HashSet<>(photos);
        unique.addAll(other.photos);
        return new AlbumJ_V04(new ArrayList<>(unique));
    }

    public List<PhotoJ> filter(Predicate<PhotoJ> predicate) {
        return photos.stream().filter(predicate).collect(Collectors.toList());
    }

    public void forEach(Consumer<PhotoJ> consumer) {
        photos.stream().forEach(consumer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumJ_V04 that = (AlbumJ_V04) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }


    public static void main(String [] args) {
        PhotoJ photo1 = new PhotoJ("https://en.wikipedia.org/wiki/Java_(programming_language)", LocalDateTime.now());
        PhotoJ photo2 = new PhotoJ("https://www.youtube.com/user/java/videos", LocalDateTime.now());
        AlbumJ_V04 album1 = new AlbumJ_V04(Arrays.asList(photo1, photo2));

        album1.filter(photo -> photo.getCreatedAt().getNano() > LocalDateTime.now().minusDays(7).getNano());



    }
}
