package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

class CopyableJUtil {
    /**
     * Utility method
     */
    public static void copyToDir(File dir, List<CopyableJ> copyables) throws MalformedURLException, IOException {
        if (dir.isDirectory()) {
            for (CopyableJ copyable : copyables) {
                copyable.copyTo(dir);
            }
        }
    }

}
