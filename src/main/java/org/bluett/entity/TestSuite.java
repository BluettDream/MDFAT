package org.bluett.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestSuite implements Serializable {
    @Serial
    private static final long serialVersionUID = -7015566039515718297L;
    private Integer id;
    private String name;
    private String description;
    private TestResult status;
}
