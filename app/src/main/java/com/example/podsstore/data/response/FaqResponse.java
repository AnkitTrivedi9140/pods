package com.example.podsstore.data.response;

import com.google.gson.annotations.SerializedName;

public class FaqResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("question")
    private String question;

    @SerializedName("answer")
    private String answer;

    @SerializedName("status")
    private String status;


    @SerializedName("createdat")
    private String createdat;

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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    @Override
    public String toString() {
        return "FaqResponse{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", status='" + status + '\'' +
                ", createdat='" + createdat + '\'' +
                '}';
    }
}
