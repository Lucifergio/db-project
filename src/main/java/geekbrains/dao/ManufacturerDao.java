package geekbrains.dao;

import geekbrains.entity.Manufacturer;

public interface ManufacturerDao {
    Iterable<Manufacturer> findAll();
    String findNameById(Long id);
    Manufacturer findById(Long id);
    void insert(Manufacturer manufacturer);
    void update(Manufacturer manufacturer);
    void deleteById(Long id);
}
