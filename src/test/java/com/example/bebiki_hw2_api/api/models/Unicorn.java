package com.example.bebiki_hw2_api.api.models;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Unicorn {
    private String name;
    private String colourTail;
    @SerializedName("_id")
    private String id;
}
