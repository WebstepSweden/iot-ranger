package se.webstep.iotr.api;


import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;

@SuppressWarnings({"unused", "WeakerAccess", "SimplifiableIfStatement"})
public class RangerGetSomething {

    @JsonInclude(ALWAYS)
    @NotNull
    private UUID uuid;

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


    @JsonInclude(ALWAYS)
    @NotNull
    private boolean resident;




    public RangerGetSomething() {
    }







    public UUID getUuid() {
        return uuid;
    }




    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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




    public boolean isResident() {
        return resident;
    }




    public void setResident(boolean resident) {
        this.resident = resident;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RangerGetSomething that = (RangerGetSomething) o;

        if (resident != that.resident) return false;
        if (!uuid.equals(that.uuid)) return false;
        if (!name.equals(that.name)) return false;
        if (!description.equals(that.description)) return false;
        return deadline.equals(that.deadline);
    }




    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + deadline.hashCode();
        result = 31 * result + (resident ? 1 : 0);
        return result;
    }




    @Override
    public String toString() {
        return "TimerFetch{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", deadline=" + deadline +
                ", resident=" + resident +
                '}';
    }
}
