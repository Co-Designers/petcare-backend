package com.petcare.center.platform.contexts.petmanagement.domain.model.commands;

public record CreatePetCommand(Long ownerId, String name, String species) {}
