package com.monopoly.shared.domain.utils;

import com.monopoly.shared.domain.utils.validations.*;

import java.util.Collection;

public class Validator {

  private Validator() {}

  public static void validateString(String value, String fieldName) {
    ValidateString.execute(value, fieldName);
  }

  public static void validateMax(Double value, Double max, String fieldName) {
    ValidateMax.execute(value, max, fieldName);
  }

  public static void validateNull(Object value, String fieldName) {
    ValidateNull.execute(value, fieldName);
  }

  public static void validateAlias(String alias, String fieldName) {
    ValidateAlias.execute(alias, fieldName);
  }

  public static <E extends Enum<E>> void validateEnum(Class<E> enumClass, E value, String fieldName) {
    ValidateEnum.excute(enumClass, value, fieldName);
  }

  public static void validateNegative(Double number, String fieldName) {
    ValidateNegative.execute(number, fieldName);
  }

  public static void validatePropertyGroup(String group) {
    ValidatePropertyGroup.execute(group);
  }

  public static void validateUUID(String uuid, String fieldName) {
    ValidateUUID.execute(uuid, fieldName);
  }

  public static void validateCollection(Collection<?> collection, String fieldName) {
    ValidateCollection.execute(collection, fieldName);
  }

  public static void validateHousesCount(Integer houses, String fieldName) {
    ValidateHousesCount.execute( houses, fieldName);
  }

  public static void validateHotelConversion(Integer houses, Boolean hasHotel) {
    ValidateHotelConversion.validate(houses, hasHotel);
  }

  public static void validateRange(Integer value, Integer min, Integer max, String fieldName) {
    ValidateRange.execute(value, min, max, fieldName);
  }
}