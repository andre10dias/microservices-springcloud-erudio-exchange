package com.github.andre10dias.model;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String author;
    private String title;
    private Date lounchDate;
    private Double price;
    private Double currency;
    private String environment;

}
