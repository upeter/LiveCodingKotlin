package org.a_intro;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlbumJ_V01 {

    private final List<PhotoJ> photos;

    public AlbumJ_V01(List<PhotoJ> photos) {
        this.photos = photos;
    }

    public List<PhotoJ> getPhotos() {
        return photos;
    }

    public Double averageRating() {
        return photos.stream().flatMap(p -> p.getRatings().stream()).mapToDouble(i -> i).average().getAsDouble();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumJ_V01 that = (AlbumJ_V01) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }
}
