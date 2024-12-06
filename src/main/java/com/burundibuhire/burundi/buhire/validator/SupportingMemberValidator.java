package com.burundibuhire.burundi.buhire.validator;

import com.burundibuhire.burundi.buhire.dto.SupportingMemberDto;
import com.burundibuhire.burundi.buhire.dto.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupportingMemberValidator {

    public static List<String> validate(UserDto userDto){
        List<String> errors = new ArrayList<>();

        if(userDto == null) {
            errors.add("Veuillez renseigner le type de membre");
            errors.add("Veuillez renseigner le grade");
            errors.add("Veuillez renseigner l'ID membre");
            errors.add("Veuillez renseigner le nom");

            errors.add("Veuillez renseigner le nom");
            errors.add("Veuillez renseigner le prénom");
            errors.add("Veuillez renseigner la date de naissance");
            errors.add("Veuillez renseigner le sexe");
            errors.add("Veuillez selectionner le pays de naissance");
            errors.add("Veuillez renseigner le lieu de naissance");
            errors.add("Veuillez renseigner le nom d'utilisateur");

            errors.add("Veuillez renseigner l'email");
            errors.add("Veuillez renseigner le mot de passe");
            errors.add("Veuillez renseigner le numero de telephone");
            errors.add("Veuillez renseigner le nom d'utilisateur");
            return errors;
        }

        if (!StringUtils.hasLength(userDto.getMemberType().toString())){
            errors.add("Veuillez renseigner le type de membre");
        }
        if (!StringUtils.hasLength(userDto.getMemberGrade().toString())){
            errors.add("Veuillez renseigner le grade");
        }

        if (!StringUtils.hasLength(userDto.getMemberIdNumber())){
            errors.add("Veuillez renseigner l'ID membre");
        }

        if (!StringUtils.hasLength(userDto.getFirstName())){
            errors.add("Veuillez renseigner le nom");
        }

        if (!StringUtils.hasLength(userDto.getLastName())){
            errors.add("Veuillez renseigner le prénom");
        }

        if (!StringUtils.hasLength(String.valueOf(userDto.getDateOfBirth()))){
            errors.add("Veuillez renseigner la date de naissance");
        }

        if (!StringUtils.hasLength(userDto.getGender().toString())){
            errors.add("Veuillez renseigner le sexe");
        }

        if(userDto.getCountryOfBirth() == null || userDto.getCountryOfBirth().getId() == null) {
            errors.add("Veuillez selectionner le pays de naissance ");
        }

        if(userDto.getCountryOfBirth() == null || userDto.getCountryOfBirth().getId() == null) {
            errors.add("Veuillez selectionner le pays de naissance ");
        }

        if (!StringUtils.hasLength(userDto.getPlaceOfBirth())){
            errors.add("Veuillez renseigner le lieu de naissance");
        }

        if (!StringUtils.hasLength(userDto.getEmail())){
            errors.add("Veuillez renseigner l'email");
        }

        if (!isValidEmail(userDto.getEmail())){
            errors.add("Email invalide");
        }

        if (!StringUtils.hasLength(userDto.getPassword())){
            errors.add("Veuillez renseigner le mot de passe");
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())){
            errors.add("Les mots de passe ne correspondent pas.");
        }
        if (!StringUtils.hasLength(userDto.getPhoneNumber())){
            errors.add("Veuillez renseigner le numero de telephone");
        }

        if (!StringUtils.hasLength(userDto.getWhatsappNumber())){
            errors.add("Veuillez renseigner le numero de telephone");
        }

        return errors;
    }

    private static boolean isValidEmail(String email) {

        String EMAIL_REGEX ="^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPassword(String password) {
        // Regular expression for password validation
        String PASSWORD_REGEX =
                "^(?=.*[0-9])" +         // at least one digit
                        "(?=.*[a-z])" +          // at least one lowercase letter
                        "(?=.*[A-Z])" +          // at least one uppercase letter
                        "(?=.*[@#$%^&+=])" +     // at least one special character
                        "(?=\\S+$)" +            // no whitespace allowed
                        ".{8,}$";                // at least 8 characters

        // Compile the regex
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);

        // Return whether the password matches the regex
        return matcher.matches();
    }
}
