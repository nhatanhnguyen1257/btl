package vn.dev.na.btl.data.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**Môn học*/
@Entity(tableName = "subject")
public class Subject {

    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "code")
    private String code;
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "note")
    private String note;
    @ColumnInfo(name = "number")
    private Integer number;
    @ColumnInfo(name="required")
    private boolean required;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
