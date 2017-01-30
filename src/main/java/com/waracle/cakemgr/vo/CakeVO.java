package com.waracle.cakemgr.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.auto.value.AutoValue;
import com.waracle.cakemgr.formater.CakeDeserializer;

import javax.validation.constraints.NotNull;

@AutoValue
@JsonDeserialize(builder = com.waracle.cakemgr.vo.AutoValue_CakeVO.Builder.class, using = CakeDeserializer.class)
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class CakeVO {

    protected CakeVO() {
    }

    public static Builder builder() {
        return new com.waracle.cakemgr.vo.AutoValue_CakeVO.Builder();
    }

    @NotNull
    @JsonProperty("title")
    public abstract String getTitle();

    @NotNull
    @JsonProperty("desc")
    public abstract String getDesc();

    @NotNull
    @JsonProperty("image")
    public abstract String getImage();

    @AutoValue.Builder
    public abstract static class Builder {

        @JsonProperty("title")
        public abstract Builder setTitle(String title);

        @JsonProperty("desc")
        public abstract Builder setDesc(String desc);

        @JsonProperty("image")
        public abstract Builder setImage(String image);

        public abstract CakeVO build();

    }

}