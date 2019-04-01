package net.japca.order.file;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 4/1/19.
 */
@Slf4j
public class LogService {

    public void logFile(File file) {
        log.error("File: {}", file);
    }
}
