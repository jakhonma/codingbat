package codingbat.uz.codingbat.payload;

import codingbat.uz.codingbat.entity.Language;
import jakarta.validation.constraints.NotNull;

public class CategoryDto {

    @NotNull(message = "name bo'sh bulishi mumkin emas")
    private String name;

    @NotNull(message = "text bo'sh bulishi mumkin emas")
    private String text;

    @NotNull(message = "languageId bo'sh bulishi mumkin emas")
    private Long languageId;

    public CategoryDto(String name, String text, Long languageId) {
        this.name = name;
        this.text = text;
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }
}
