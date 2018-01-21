package application.neo4j.repository;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.junit.runner.RunWith;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest()
@Configuration
public class GenericNeo4jTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    VoteRepository voteRepository;

    // @Test
    public void testRepositories() {
        Book book = new Book("book");
        book = bookRepository.save(book);

        Book sameBook = bookRepository.findOne(book.getId());
        assertThat(sameBook).isNotNull();
    }

    interface BookRepository extends GraphRepository<Book> {
    }

    interface PersonRepository extends GraphRepository<Person> {
    }

    interface VoteRepository extends GraphRepository<Vote> {
    }

    @NodeEntity
    @Data
    @RequiredArgsConstructor
    @NoArgsConstructor
    class Book {
        @NonNull
        public String title;
        @GraphId
        private Long id;
    }

    @NodeEntity
    @Data
    @RequiredArgsConstructor
    @NoArgsConstructor
    class Person {
        @NonNull
        public String name;
        @GraphId
        private Long id;
    }

    @RelationshipEntity(type = "VOTE")
    @Data
    @RequiredArgsConstructor
    class Vote {
        public int vote;
        @GraphId
        private Long id;
    }
}
