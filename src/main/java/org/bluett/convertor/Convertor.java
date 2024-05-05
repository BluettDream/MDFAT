package org.bluett.convertor;

public interface Convertor<T, R> {
    R convert(T source);

    T reverseConvert(R source);
}
