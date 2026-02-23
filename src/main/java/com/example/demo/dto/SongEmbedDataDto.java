package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

// JSONの中に、クラスで定義していない項目があっても無視する設定（エラー防止）
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class SongEmbedDataDto{

    private String title;
    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
    private String type;
}