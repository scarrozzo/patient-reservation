package com.patient.core.assembler;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.EmbeddedWrapper;

import java.time.LocalDate;
import java.time.LocalTime;

@JsonPropertyOrder(value = {"id", "uid"}, alphabetic = true)
public abstract class BaseRepresentationModel <D extends RepresentationModel<? extends D>> extends RepresentationModel<D> {
    private Long id;

    @Setter
    @Getter
    private String creator;

    @Setter
    @Getter
    private String modifier;

    @Setter
    @Getter
    private LocalDate createdDate;

    @Setter
    @Getter
    private LocalTime createdTime;

    @Setter
    @Getter
    private LocalDate lastModifiedDate;

    @Setter
    @Getter
    private LocalTime lastModifiedTime;

    @Setter
    @Getter
    private String uid;

    @Getter
    @Setter
    @JsonUnwrapped
    // The @JsonUnwrapped annotation is required as EmbeddedWrappers are by default serialized in a "_embedded" container,
    // that has to be added directly into the top level object
    private CollectionModel<EmbeddedWrapper> embeddeds;
}
