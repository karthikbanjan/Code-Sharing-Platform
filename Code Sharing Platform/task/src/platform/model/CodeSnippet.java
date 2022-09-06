package platform.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Entity
@Table(name = "code_snippet")
public class CodeSnippet {

    @JsonIgnore
    @Transient
    private static final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy/MM/dd hh:mm:ss")
            .withZone(ZoneId.systemDefault());

    @JsonIgnore
    @Id
    @Column(name = "uuid", nullable = false)
    private UUID uuid;

    @JsonIgnore
    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "code", nullable = false)
    @Type(type = "org.hibernate.type.MaterializedClobType")
    private String code;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "date", nullable = false)
    private Instant date;

    @JsonProperty("time")
    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    @JsonProperty("views")
    @Column(name = "view_limit", nullable = false)
    private Long viewLimit;

    @JsonIgnore
    @Column(name = "views", nullable = false)
    private Long views;

    @JsonIgnore
    @Column(name = "view_restricted", nullable = false)
    private Character viewRestricted;

    @JsonIgnore
    @Column(name = "time_restricted", nullable = false)
    private Character timeRestricted;

    public CodeSnippet() {
    }

    @JsonGetter("date")
    public String getStringDate() {
        return formatter.format(date);
    }

    public Character getTimeRestricted() {
        return timeRestricted;
    }

    public void setTimeRestricted(Character timeRestricted) {
        this.timeRestricted = timeRestricted;
    }

    public Character getViewRestricted() {
        return viewRestricted;
    }

    public void setViewRestricted(Character viewRestricted) {
        this.viewRestricted = viewRestricted;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public Long getViewLimit() {
        return viewLimit;
    }

    public void setViewLimit(Long viewLimit) {
        this.viewLimit = viewLimit;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    @JsonIgnore
    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "uuid = " + uuid + ", " +
                "title = " + title + ", " +
                "code = " + code + ", " +
                "date = " + getStringDate() + ", " +
                "timeLimit = " + timeLimit + ", " +
                "viewLimit = " + viewLimit + ", " +
                "views = " + views + ")";
    }

}