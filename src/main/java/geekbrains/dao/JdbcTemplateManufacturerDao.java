package geekbrains.dao;

import geekbrains.entity.Manufacturer;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class JdbcTemplateManufacturerDao implements ManufacturerDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Iterable<Manufacturer> findAll() {
        return null;
    }

    @Override
    public String findNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT name FROM manufacturer WHERE id = ?", String.class, id);

    }

    @Override
    public Manufacturer findById(Long id) {
        return null;
    }

    @Override
    public void insert(Manufacturer manufacturer) {

    }

    @Override
    public void update(Manufacturer manufacturer) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
