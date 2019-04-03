package net.japca.shop.file;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by Jakub krhovják on 4/3/19.
 */
@Slf4j
public class FileService {

    public void print(String fileContent) {
        log.info("File content: \n {}", fileContent.substring(0, 20));
    }

}
