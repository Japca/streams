package net.japca.integration.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Jakub krhovják on 3/30/19.
 */
@Data
@Accessors(chain = true)
public class Item {

    private Long id;
    private String name;
    private String description;

}
