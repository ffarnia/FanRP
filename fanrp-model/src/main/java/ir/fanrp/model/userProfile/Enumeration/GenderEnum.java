package ir.fanrp.model.userProfile.Enumeration;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by vzf on 10/13/2015.
 */
public enum GenderEnum {

    MALE(1L, "„—œ"),
    FEMALE(2L, "“‰");

    private final Long value;
    private final String label;


    GenderEnum(Long value, String label) {
        this.value = value;
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }


    public static EnumSet<GenderEnum> getItems() {
        return EnumSet.allOf(GenderEnum.class);
    }

    public static GenderEnum getItem(String label) {
        for (GenderEnum item : getItems()) {
            if (item.getLabel().equals(label.trim()))
                return item;
        }
        return null;
    }

    public static GenderEnum getItem(Long value) {
        for (GenderEnum item : getItems()) {
            if (item.getValue() != null && value != null && item.getValue().equals(value) || (item.getValue() == null && value == null))
                return item;
        }
        return null;
    }
    public static EnumSet<GenderEnum> getWithoutItems(GenderEnum... excluded) {
        EnumSet<GenderEnum> enumSet = getItems();
        enumSet.removeAll(Arrays.asList(excluded));
        return enumSet;
    }

    public static EnumSet<GenderEnum> getWithItems(GenderEnum... items) {
        EnumSet<GenderEnum> enumSet = EnumSet.noneOf(GenderEnum.class);
        enumSet.addAll(Arrays.asList(items));
        return enumSet;
    }

}
