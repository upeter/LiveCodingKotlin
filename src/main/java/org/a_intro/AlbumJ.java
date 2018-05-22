package org.a_intro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlbumJ {

    final static Logger LOG = LoggerFactory.getLogger(CopyableJ.class);


    private final List<PhotoJ> photos;

    public AlbumJ(List<PhotoJ> photos) {
        this.photos = photos;
    }

    public List<PhotoJ> getPhotos() {
        return photos;
    }

    public Double averageRating() {
        return photos.stream().flatMap(p -> p.getRatings().stream()).mapToDouble(i -> i).average().getAsDouble();
    }

    public AlbumJ add(PhotoJ photo) {
        photos.add(photo);
        return new AlbumJ(photos);
    }

    public AlbumJ combine(AlbumJ other) {
        Set<PhotoJ> unique = new HashSet<>(photos);
        unique.addAll(other.photos);
        List<PhotoJ> all = new ArrayList<>();
        all.addAll(unique);
        return new AlbumJ(all);
    }


    public List<PhotoJ> filter(Predicate<PhotoJ> predicate) {
        return photos.stream().filter(predicate).collect(Collectors.toList());
    }

    public void forEach(Consumer<PhotoJ> consumer) {
        photos.stream().forEach(consumer);
    }

    public void copyAllTo(File path) {
        if (!path.isDirectory()) {
            throw new IllegalArgumentException("Path must be directory");
        }
        this.photos.forEach(photo -> {
            try {
                photo.copyTo(path);
                LOG.info("Succesfully copied " + photo.getURL());
            } catch (IOException ex) {
                LOG.error("Failed to copy " + photo.getURL(), ex);
            }

        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumJ that = (AlbumJ) o;
        return Objects.equals(photos, that.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photos);
    }

}
