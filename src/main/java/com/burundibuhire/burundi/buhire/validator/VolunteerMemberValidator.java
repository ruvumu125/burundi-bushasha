package com.burundibuhire.burundi.buhire.validator;

import com.burundibuhire.burundi.buhire.dto.VolunteerMemberDto;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class VolunteerMemberValidator {

    private static final int MAX_IMAGE_SIZE = 1048576; // 1 MB in bytes

    public static List<String> validate(VolunteerMemberDto volunteerMemberDto){
        List<String> errors = new ArrayList<>();

        if(volunteerMemberDto == null) {

            errors.add("Veuillez fournir les informations de l'utilisateur");
            errors.add("Veuillez télécharger la photo du passeport");
            errors.add("Veuillez télécharger la photo de la face avant de la carte d'identité");
            errors.add("Veuillez télécharger la photo de la face arrière de la carte d'identité");


            return errors;
        }

        if (volunteerMemberDto.getUserId()==null){
            errors.add("Veuillez fournir les informations de l'utilisateur");
        }

        if (volunteerMemberDto.getPassportPhoto() == null || volunteerMemberDto.getPassportPhoto().isEmpty()) {
            errors.add("Veuillez télécharger la photo du passeport");
        }


        if (volunteerMemberDto.getPassportPhoto() != null){

            // Decode the Base64 string to byte array
            byte[] passportPhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getPassportPhoto());

            // Check the size of the byte array
            if (passportPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo du passeport ne doit pas dépasser 1MB");
            }

        }


        if (volunteerMemberDto.getIdentityCardFrontSidePicture() == null || volunteerMemberDto.getIdentityCardFrontSidePicture().isEmpty()) {
            errors.add("Veuillez télécharger la photo de la face avant de la carte d'identité");
        }
        if (volunteerMemberDto.getIdentityCardFrontSidePicture() != null) {

            // Decode the Base64 string to byte array
            byte[] idFrontPhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardFrontSidePicture());

            // Check the size of the byte array
            if (idFrontPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo de la face avant de la carte d'identité ne doit pas dépasser 1MB");
            }
        }

        if (volunteerMemberDto.getIdentityCardBackSidePicture() == null || volunteerMemberDto.getIdentityCardBackSidePicture().isEmpty()) {
            errors.add("Veuillez télécharger la photo de la face arrière de la carte d'identité");
        }
        if (volunteerMemberDto.getIdentityCardBackSidePicture() != null) {

            // Decode the Base64 string to byte array
            byte[] idBackPhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardBackSidePicture());

            // Check the size of the byte array
            if (idBackPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo de la face arrière de la carte d'identité ne doit pas dépasser 1MB");
            }

        }

        return errors;
    }
}
