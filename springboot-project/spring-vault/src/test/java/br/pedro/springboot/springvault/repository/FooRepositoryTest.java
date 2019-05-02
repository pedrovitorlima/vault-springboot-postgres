package br.pedro.springboot.springvault.repository;

import br.pedro.springboot.springvault.domain.Foo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FooRepositoryTest {

    @Autowired
    private FooRepository fooRepository;

    @Test
    public void testFindAllShouldReturnSomething() {
        Foo foo = new Foo();
        fooRepository.save(foo);
        Iterable<Foo> allElements = fooRepository.findAll();
        assertTrue(allElements.iterator().hasNext());
    }

}
