package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyURLToFile;

public interface CopyableJ {

    public URL getURL();

    default File copyTo(File target) throws MalformedURLException, IOException {
        return copyTo(getURL(), target);
    }


    static File copyTo(URL url, File target) throws IOException {
        File to = target;
        if (target.isDirectory()) {
            String[] pathElements = url.getFile().split("/");
            to = new File(target, pathElements[pathElements.length - 1]);
        }
        copyURLToFile(url, to);
        return to;
    }
}
