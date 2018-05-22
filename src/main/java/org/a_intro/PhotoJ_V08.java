package org.a_intro;

import static org.apache.commons.io.FileUtils.copyURLToFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PhotoJ
 */
public class PhotoJ_V08 implements CopyableJ {

	/** logger.. */
	final static Logger LOG = LoggerFactory.getLogger(CopyableJ.class);
	private final URL url;
	private final LocalDateTime createdAt;
	private final List<Integer> ratings;

	public PhotoJ_V08(String path, LocalDateTime createdAt, List<Integer> ratings) {
		this.url = convert(path);
		this.createdAt = createdAt;
		this.ratings = ratings;
	}

	public PhotoJ_V08(String path, LocalDateTime createdAt) {
		this.url = convert(path);
		this.createdAt = createdAt;
		ratings = new ArrayList<Integer>();
	}

	public File copyTo(File target) throws MalformedURLException, IOException {
		File to = target;
		if (target.isDirectory()) {
			String[] pathElements = url.getFile().split("/");
			to = new File(target, pathElements[pathElements.length - 1]);
			LOG.debug("Copy to dir using filename: " + to);
		}
		if (LOG.isDebugEnabled()) {
			LOG.info("Copy to: " + to + " ...");
		}
		copyURLToFile(url, to);
		if (LOG.isInfoEnabled()) {
			LOG.info("Copied successfully copied to: " + to);
		}
		return to;
	}

	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			LOG.error("Path is no valid URL: " + path, e);
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

	public List<Integer> sortedRatings(){
		return ratings.stream().sorted().collect(Collectors.toList());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhotoJ_V08 that = (PhotoJ_V08) o;
		return Objects.equals(url, that.url) &&
				Objects.equals(createdAt, that.createdAt) &&
				Objects.equals(ratings, that.ratings);
	}

	@Override
	public int hashCode() {

		return Objects.hash(url, createdAt, ratings);
	}


}



