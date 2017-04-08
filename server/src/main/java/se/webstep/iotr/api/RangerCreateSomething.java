package se.webstep.iotr.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;

@SuppressWarnings({"unused", "WeakerAccess"})
public class RangerCreateSomething {


    @JsonInclude(ALWAYS)
    @NotNull
//    @Size(min = NAME_MIN_SIZE, max = NAME_MAX_SIZE)
    private String name;


    @JsonInclude(ALWAYS)
    @NotNull
//    @Size(max = DESCRIPTION_MAX_SIZE)
    private String description;

    @JsonInclude(ALWAYS)
    @NotNull
//    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime deadline;




    public RangerCreateSomething() {
    }




    public RangerCreateSomething(String name, String description, LocalDateTime deadline) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
    }




    public String getName() {
        return name;
    }




    public void setName(String name) {
        this.name = name;
    }




    public String getDescription() {
        return description;
    }




    public void setDescription(String description) {
        this.description = description;
    }




    public LocalDateTime getDeadline() {
        return deadline;
    }




    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }




    @Override
    public String toString() {
        return "TimerCreate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                '}';
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RangerCreateSomething that = (RangerCreateSomething) o;

        return name.equals(that.name) && description.equals(that.description) && deadline.equals(that.deadline);
    }




    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + deadline.hashCode();
        return result;
    }
}