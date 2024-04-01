package com.exam.its.web.issue;

import lombok.Data;

@Data
public class IssueForm {

    // 本来は@Dataで、getter, setterが生成されるが、
    // なぜか生成されないため、ハードコーディングする
    private String summary;
    private String description;

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }


}
