package io.wangyuec15.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Project {
    
    @Id
    @GeneratedValue
    private Long id;
}
