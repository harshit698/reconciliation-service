package repository;

import java.util.List;

public interface Repository<T> {
    List<T> read(String entityIdentifier);
}