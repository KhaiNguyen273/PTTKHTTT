package util;

public interface DataRowMapper<T> {
    T mapRow(Object[] rowData);
}
