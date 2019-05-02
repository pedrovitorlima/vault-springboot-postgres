package br.pedro.springboot.springvault.repository;

import br.pedro.springboot.springvault.domain.Foo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface FooRepository extends CrudRepository<Foo, BigInteger> {
}
