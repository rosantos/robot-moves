package com.github.rosantos.robotmoves.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum EnumMovement {
  MOVE("M", null), RIGHT("R", "L"), LEFT("L", "R");

  private String label;
  private String reverseLabel;

  private EnumMovement(String label, String reverseLabel) {
    this.label = label;
    this.reverseLabel = reverseLabel;
  }

  public String getLabel() {
    return label;
  }

  public static EnumMovement getAxisMovement(String label) {
    return Arrays.asList(EnumMovement.values()).stream()
        .filter(axisMovement -> axisMovement.getLabel().equalsIgnoreCase(label)).findFirst().get();
  }

  public static List<String> getLabels() {
    return Arrays.asList(EnumMovement.values()).stream().map(EnumMovement::getLabel)
        .collect(Collectors.toList());
  }

  public static EnumMovement getInverseAxisMovement(EnumMovement axisMovement) {
    return Objects.nonNull(axisMovement.reverseLabel) ? getAxisMovement(axisMovement.reverseLabel)
        : null;
  }

  public static Map<EnumMovement, EnumMovement> getAxisAndReverses() {
    Map<EnumMovement, EnumMovement> map = new HashMap<>();
    Arrays.asList(EnumMovement.values()).stream()
        .filter(axisMovement -> Objects.nonNull(axisMovement.reverseLabel))
        .forEach(axisMovement -> map.put(axisMovement, getInverseAxisMovement(axisMovement)));
    return map;
  }

}
