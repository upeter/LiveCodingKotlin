package org.a_intro;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoJ_V3
 */
public class PhotoJ_V03 {

	private final URL url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V03(String path, LocalDateTime createdAt, List<Integer> ratings) {
		this.url = convert(path);
		this.createdAt = createdAt;
		this.ratings = ratings;
	}

	public PhotoJ_V03(String path, LocalDateTime createdAt) {
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
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public URL getURL() {
		return url;
	}



}
