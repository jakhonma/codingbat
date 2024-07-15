package codingbat.uz.codingbat.entity;

import jakarta.persistence.*;

@Entity
public class Matter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String code;

    private String hint;

    private boolean isTrue;

    @ManyToOne(optional = false)
    private Category category;

    public Matter() {
    }

    public Matter(Long id, String question, String code, String hint, boolean isTrue, Category category) {
        this.id = id;
        this.question = question;
        this.code = code;
        this.hint = hint;
        this.isTrue = isTrue;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
