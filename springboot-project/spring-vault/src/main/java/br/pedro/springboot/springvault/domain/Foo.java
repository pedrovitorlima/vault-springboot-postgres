package br.pedro.springboot.springvault.domain;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="foo", schema = "public")
public class Foo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    public BigInteger getId() { return this.id; }

    public void setId(BigInteger id) {this.id = id;}

}
