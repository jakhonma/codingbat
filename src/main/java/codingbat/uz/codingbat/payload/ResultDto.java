package codingbat.uz.codingbat.payload;

import codingbat.uz.codingbat.entity.Matter;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

public class ResultDto {

    @NotNull(message = "call bo'sh bulishi mumkin emas")
    private String call;

    @NotNull(message = "run bo'sh bulishi mumkin emas")
    private String run;

    @NotNull(message = "matterId bo'sh bulishi mumkin emas")
    private Long matterId;

    public ResultDto(String call, String run, Long matterId) {
        this.call = call;
        this.run = run;
        this.matterId = matterId;
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

    public Long getMatterId() {
        return matterId;
    }

    public void setMatterId(Long matterId) {
        this.matterId = matterId;
    }
}
