package vn.dev.na.btl.data.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "major_subject"
        ,foreignKeys = {
                @ForeignKey(
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE,
                        entity = Subject.class,
                        parentColumns = {"id"},
                        childColumns = {"subject_id"}),
                @ForeignKey(
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE,
                        entity = Major.class,
                        parentColumns = {"id"},
                        childColumns = {"major_id"})
        }
        )
public class MajorSubject {

    @PrimaryKey(autoGenerate = true )
    @ColumnInfo(name = "id")
    private Integer id;
    @ColumnInfo(name = "subject_id")
    private Integer subjectId;
    @ColumnInfo(name = "major_id")
    private Integer majorId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
