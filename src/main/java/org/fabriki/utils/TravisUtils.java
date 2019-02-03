package org.fabriki.utils;

public class TravisUtils {
    public static final String TRAVIS_BASE_URL = "https://travis-ci.org";
    public static final String TRAVIS_YML_FILE_PATH = ".travis.yml";

    public static String recuperarBuildUrl(String buildId, String repositorySlug) {
        return TRAVIS_BASE_URL + "/" + repositorySlug + "/builds/" + buildId; 
    }

}
