package org.a_intro;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

class TextDocumentJ implements CopyableJ {
	private final URL url;

	public TextDocumentJ(URL url) {
		this.url = url;  
	}

	public URL getURL() {
		return url;
	}

}

/**
 * PhotoJ
 */
public class PhotoJ_V07 implements CopyableJ {

	private final URL url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V07(String path, LocalDateTime createdAt, List<Integer> ratings) {
		this.url = convert(path);
		this.createdAt = createdAt;
		this.ratings = ratings;
	}

	public PhotoJ_V07(String path, LocalDateTime createdAt) {
		this.url = convert(path);
		this.createdAt = createdAt;
		ratings = new ArrayList<Integer>();
	}


	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public RatingResult getMaxAndMinRate() {
		return !ratings.isEmpty() ? new RatingResult(Collections.max(ratings),
				Collections.min(ratings)) : null;
	}

	class RatingResult {
		int maxRate;
		int minRate;

		public RatingResult(int maxRate, int minRate) {
			super();
			this.maxRate = maxRate;
			this.minRate = minRate;
		}

		public int getMaxRate() {
			return maxRate;
		}

		public int getMinRate() {
			return minRate;
		}

	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public URL getURL() {
		return url;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhotoJ_V07 that = (PhotoJ_V07) o;
		return Objects.equals(url, that.url) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(ratings, that.ratings);
	}

	@Override
	public int hashCode() {

		return Objects.hash(url, createdAt, ratings);
	}
}
