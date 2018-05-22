package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyURLToFile;

public interface CopyableJ {

	public URL getURL();

	default File copyTo(File target) throws MalformedURLException, IOException {
			File to = target;
			if (target.isDirectory()) {
				String[] pathElements = getURL().getFile().split("/");
				to = new File(target, pathElements[pathElements.length - 1]);
			}
			copyURLToFile(getURL(), to);
			return to;
		}
}
