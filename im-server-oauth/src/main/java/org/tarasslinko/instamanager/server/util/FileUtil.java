package org.tarasslinko.instamanager.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void write(String content, Path file) {
        try {
            Files.write(file, content.getBytes(), CREATE, TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.warn("Cannot write to file: {}", file, e);
        }
    }

    public static Optional<String> read(Path file) {
        if (!file.toFile().exists()) {
            logger.warn("File {} does not exists", file);
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(Files.readString(file));
        } catch (IOException e) {
            logger.warn("Cannot read file: {}", file, e);
        }
        return Optional.empty();
    }

}
