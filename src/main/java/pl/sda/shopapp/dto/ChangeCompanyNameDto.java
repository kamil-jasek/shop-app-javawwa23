package pl.sda.shopapp.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ... comment class...
 *
 * @author kamil.jasek@gmail.com
 * @since 2020-06-20
 */
public final class ChangeCompanyNameDto {

    @NotNull
    @NotBlank
    private String name;

    public ChangeCompanyNameDto(String name) {
        this.name = name;
    }

    ChangeCompanyNameDto() {}

    public String getName() {
        return name;
    }
}
