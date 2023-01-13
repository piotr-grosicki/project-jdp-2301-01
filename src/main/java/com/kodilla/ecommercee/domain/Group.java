package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_GROUPS")
public class Group {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "GROUP_ID", unique = true)
    private Long groupId;
    @Column(name = "NAME")
    private String groupName;
    @Column(name = "DESCRIPTION")
    private String groupDescription;

    @OneToMany(
            targetEntity = Product.class,
            mappedBy = "group",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Group(String groupName,String groupDescription){
        this.groupName = groupName;
        this.groupDescription = groupDescription;
    }
}
