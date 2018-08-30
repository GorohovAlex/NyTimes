package pojo;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity(foreignKeys = @ForeignKey(entity = ResultRoom.class,parentColumns = "url",childColumns = "ownerId"))
public class MediumRoom{
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String ownerId;
    private String type;
    private String subtype;
    private String caption;
    private String copyright;
    private Integer approvedForSyndication;

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getApprovedForSyndication() {
        return approvedForSyndication;
    }

    public void setApprovedForSyndication(Integer approvedForSyndication) {
        this.approvedForSyndication = approvedForSyndication;
    }
}

