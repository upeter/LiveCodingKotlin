package org.a_intro;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * PhotoJ
 */
public class PhotoJ_V04 {

	private final URL url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V04(String path, LocalDateTime createdAt, List<Integer> ratings) {
		this.url = convert(path);
		this.createdAt = createdAt;
		this.ratings = ratings;
	}

	public PhotoJ_V04(String path, LocalDateTime createdAt) {
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
		if (!ratings.isEmpty()) {
			return new RatingResult(Collections.max(ratings),
					Collections.min(ratings));
		} else {
			// return null?
			// return empty object?
			// return Optional
			// throw runtime exception?
			return null;
		}
	}

	/**
	 * A whole class for two properties...
	 */
	static class RatingResult {
		int maxRate;
		int minRate;

		public RatingResult(int maxRate, int minRate) {
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


	public static void main(String[] args) {
		List<Integer> ratings = IntStream.rangeClosed(2,10).filter(i -> i % 2 == 0).boxed().collect(Collectors.toList());
		PhotoJ_V04 photo = new PhotoJ_V04("https://en.wikipedia.org/wiki/Java_(programming_language)", LocalDateTime.now(), ratings);

		RatingResult result = null;
		try {
			result = photo.getMaxAndMinRate();
			if(result == null) {
				result = new RatingResult(-1, -1);
			}
		} catch(RuntimeException ex) {
			result = new RatingResult(-1, -1);
		}
		System.out.println("Min Rating: " + result.getMinRate() + " Max Rating: " + result.getMaxRate());



	}

}
