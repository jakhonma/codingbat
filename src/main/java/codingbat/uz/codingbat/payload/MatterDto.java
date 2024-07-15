package codingbat.uz.codingbat.payload;

import jakarta.validation.constraints.NotNull;

public class MatterDto {

    @NotNull(message = "question bo'sh bulishi mumkin emas")
    private String question;

    @NotNull(message = "code bo'sh bulishi mumkin emas")
    private String code;

    private String hint;

    private boolean isTrue;

    @NotNull(message = "categoryId bo'sh bulishi mumkin emas")
    private Long categoryId;

    public MatterDto(String question, String code, String hint, boolean isTrue, Long categoryId) {
        this.question = question;
        this.code = code;
        this.hint = hint;
        this.isTrue = isTrue;
        this.categoryId = categoryId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
