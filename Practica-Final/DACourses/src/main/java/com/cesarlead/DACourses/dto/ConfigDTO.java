package com.cesarlead.DACourses.dto;

public record ConfigDTO(
    String version,
    FeatureTogglesDTO featureToggles) {
}
