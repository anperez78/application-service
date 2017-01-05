package demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotNull;

public class Application {

    @NotNull
    @JsonProperty
    private Long applicationId;

    @NotNull
    @JsonProperty
    private String fullName;

    public Long getApplicationId() {
        return applicationId;
    }

    public Application setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Application setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (!getApplicationId().equals(that.getApplicationId()))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).
                append(this.applicationId).
                toHashCode();
    }

    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}