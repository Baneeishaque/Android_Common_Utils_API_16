package ndk.utils;

public class String_Utils {

    private static String get_serial_number_suffixed_name(String name, String index, Character divider) {
        return name + divider + index;
    }

    private static String get_length_constrained_serial_number_suffixed_name(String name, int index, int length, Character divider, Character zero_character) {
        if (String.valueOf(index).length() == length) {
            return get_serial_number_suffixed_name(name, String.valueOf(index), divider);
        } else if (String.valueOf(index).length() < length) {
            return get_serial_number_suffixed_name(name, generate_zero_character_prefix(zero_character, length - String.valueOf(index).length()) + String.valueOf(index), divider);
        } else {
            //TODO : Throw Exception
            return "";
        }
    }

    private static String get_zero_filled_length_constrained_serial_number_suffixed_name(String name, int index, int length, Character divider) {
        return get_length_constrained_serial_number_suffixed_name(name, index, length, divider, '0');
    }

    private static String get_hyphen_divided_zero_filled_length_constrained_serial_number_suffixed_name(String name, int index, int length) {
        return get_zero_filled_length_constrained_serial_number_suffixed_name(name, index, length, '_');
    }

    public static String get_two_digited_hyphen_divided_zero_filled_length_constrained_serial_number_suffixed_name(String name, int index) {
        return get_hyphen_divided_zero_filled_length_constrained_serial_number_suffixed_name(name, index, 2);
    }

    private static String generate_zero_character_prefix(Character zero_character, int length) {
        String generated_prefix = "";
        for (int i = 1; i <= length; i++) {
            generated_prefix = generated_prefix + zero_character;
        }
        return generated_prefix;
    }
}
