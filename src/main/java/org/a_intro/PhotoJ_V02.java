package org.a_intro;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoJ_V2
 */
public class PhotoJ_V02 {

	private final String url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V02(String url, LocalDateTime createdAt, List<Integer> ratings) {
		this.createdAt = createdAt;
		this.url = url;
		this.ratings = ratings;
	}

	public PhotoJ_V02(String url, LocalDateTime createdAt) {
		this.createdAt = createdAt;
		this.url = url;
		ratings = new ArrayList<Integer>();
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
