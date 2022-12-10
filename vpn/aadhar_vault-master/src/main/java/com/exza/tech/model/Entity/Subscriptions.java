package com.exza.tech.model.Entity;

import javax.persistence.*;

@Entity(name="subscriptions")
@Table(name="subscriptions")
public class Subscriptions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscriptionId")
    private int subscriptionId;

    @Column(name = "productId")
    private String productId ;

    @ManyToOne
    @JoinColumn(name = "clientCode")
    private Clients clientCode;

    @Column(name = "hash_key")
    private String hash_key;


}
