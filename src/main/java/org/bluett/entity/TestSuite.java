package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestSuite implements Serializable {
    @Serial
    private static final long serialVersionUID = -7015566039515718297L;
    private String name;
    private String description;
    private String status;
    private TreeSet<TestCase> testCases;
}
