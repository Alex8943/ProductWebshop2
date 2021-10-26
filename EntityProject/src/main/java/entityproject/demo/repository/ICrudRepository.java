package entityproject.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface ICrudRepository<T> { //<T> = generisk klasse, koden i denne klasse er genbrugelig.
    //generisk klasser er derfor utrolig magtfulde fordi du nu kan bruge den samme kode til forskellige typer.

    //create()
    //ReadAll()
    //getPruductById()
    //delete()
    //update()

    void create(T t);

    List<T> readAll();

    T getProductById(long id);

    void update(T t);

    void delete(long id);

}
