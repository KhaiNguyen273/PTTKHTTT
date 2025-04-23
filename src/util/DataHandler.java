package util;

public interface DataHandler<T> {
    boolean handleData(T dto) throws Exception;
}
