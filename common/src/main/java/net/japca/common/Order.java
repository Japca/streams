package net.japca.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jakub krhovják on 3/22/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private String description;
    private int count;



}