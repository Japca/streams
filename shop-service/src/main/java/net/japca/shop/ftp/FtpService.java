package net.japca.shop.ftp;

import java.io.File;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 4/4/19.
 */
@Slf4j
public class FtpService {

    public void print(File file) {
        log.info("File recceived: {}", file);
    }

}
