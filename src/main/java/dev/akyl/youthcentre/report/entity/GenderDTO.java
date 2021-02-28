package dev.akyl.youthcentre.report.entity;

public class GenderDTO {
    private String gender;
    private Long count;

    public GenderDTO() {
    }

    public GenderDTO(String gender, Long count) {
        this.gender = gender;
        this.count = count;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
