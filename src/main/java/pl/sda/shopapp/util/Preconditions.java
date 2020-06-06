package pl.sda.shopapp.util;

import java.util.Objects;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-06
 */
public class Preconditions {

    public static void requireNonNulls(Object ...args) {
        for (Object arg : args) {
            Objects.requireNonNull(arg);
        }
    }
}
