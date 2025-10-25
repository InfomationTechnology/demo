package com.example.demo.text.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User implements Cloneable {

    private long id;

    private String name;

    private Integer age;

    private Address address;

    private boolean isClock;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
