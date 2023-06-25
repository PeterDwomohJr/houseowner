package com.houseowner.edge.aggregates.entities;

import java.util.List;

public record UserRecord(
  String username,
  String fullName,
  String surname,
  List<String> roles
) {}
