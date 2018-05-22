package org.a_intro;

import java.time.LocalDateTime;
import java.util.List;

/**
 * PhotoJ_V1
 */
public class PhotoJ_V01 {

	private final String url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V01(String url, LocalDateTime createdAt, List<Integer> ratings) {
		this.createdAt = createdAt;
		this.url = url;
		this.ratings = ratings;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public String getURL() {
		return url;
	}

}
