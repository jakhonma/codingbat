package codingbat.uz.codingbat.entity;

import jakarta.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String call;

    @Column(nullable = false)
    private String run;

    @ManyToOne(optional = false)
    private Matter matter;

    public Result() {
    }

    public Result(Long id, String call, String run, Matter matter) {
        this.id = id;
        this.call = call;
        this.run = run;
        this.matter = matter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCall() {
        return call;
    }

    public void setCall(String call) {
        this.call = call;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public Matter getMatter() {
        return matter;
    }

    public void setMatter(Matter matter) {
        this.matter = matter;
    }
}
