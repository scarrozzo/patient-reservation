package com.patient.core.assembler;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

@Slf4j
public abstract class BaseRepresentationModelAssemblerSupport <T, D extends BaseRepresentationModel<D>> extends RepresentationModelAssemblerSupport<T, D> {

    @Getter
    private final Class<?> controllerClass;

    public BaseRepresentationModelAssemblerSupport(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
        this.controllerClass = controllerClass;
    }
}
