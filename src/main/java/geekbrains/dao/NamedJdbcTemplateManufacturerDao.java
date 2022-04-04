package geekbrains.dao;

import geekbrains.entity.Manufacturer;
import geekbrains.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//@Component
@RequiredArgsConstructor
public class NamedJdbcTemplateManufacturerDao implements ManufacturerDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Iterable<Manufacturer> findAll() {
        String sql = "SELECT * FROM MANUFACTURER";
        return namedParameterJdbcTemplate.query(sql, new ManufacturerMapper());
    }

    @Override
    public String findNameById(Long id) {
        String sql = "SELECT name FROM manufacturer WHERE id = :manufacturerId";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturerId", id);
        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
    }

    @Override
    public Manufacturer findById(Long id) {
        String sql = "SELECT NAME, p.id as product_id, TITLE, COST, MANUFACTURE_DATE, MANUFACTURER_ID\n" +
                "FROM MANUFACTURER m\n" +
                "INNER JOIN PRODUCT p on m.ID = p.MANUFACTURER_ID\n" +
                "WHERE m.id = :manufacturerId;";
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("manufacturerId", id);
        return namedParameterJdbcTemplate.query(sql, namedParameters, new manufacturerWithProductExtractor());
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

    private static class ManufacturerMapper implements RowMapper<Manufacturer> {

        @Override
        public Manufacturer mapRow(ResultSet rs, int rowNum) throws SQLException {
           return Manufacturer.builder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }

    private static class manufacturerWithProductExtractor implements ResultSetExtractor<Manufacturer> {

        @Override
        public Manufacturer extractData(ResultSet rs) throws SQLException, DataAccessException {
            Manufacturer manufacturer = null;
            while (rs.next()) {
                if(manufacturer == null) {
                    manufacturer = Manufacturer.builder()
                            .id(rs.getLong("manufacturer_id"))
                            .name(rs.getString("name"))
                            .build();
                }

                final Product product = Product.builder()
                        .id(rs.getLong("product_id"))
                        .title(rs.getString("title"))
                        .cost(rs.getBigDecimal("cost"))
                        .date(rs.getDate("manufacture_date").toLocalDate())
                        .build();
                manufacturer.addProduct(product);
            }
            return manufacturer;
        }
    }
}
