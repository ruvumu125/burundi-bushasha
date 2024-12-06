package com.burundibuhire.burundi.buhire.validator;

import com.burundibuhire.burundi.buhire.dto.CandidateMemberDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class CandidateMemberValidator {

    private static final int MAX_IMAGE_SIZE = 1048576; // 1 MB in bytes

    public static List<String> validate(CandidateMemberDto candidateMemberDto){
        List<String> errors = new ArrayList<>();

        if(candidateMemberDto == null) {

            errors.add("Veuillez fournir les informations de l'utilisateur");
            errors.add("Veuillez télécharger la photo du passeport");
            errors.add("Veuillez télécharger la photo de la face avant de la carte d'identité");
            errors.add("Veuillez télécharger la photo de la face arrière de la carte d'identité");
            errors.add("Veuillez télécharger la photo du bulletin d'Inscription CENI");
            errors.add("Veuillez télécharger la photo de l 'attestation de Résidence");


            return errors;
        }

        if (candidateMemberDto.getUserId()==null){
            errors.add("Veuillez fournir les informations de l'utilisateur");
        }

        if (!StringUtils.hasLength(candidateMemberDto.getEthnicity().toString())){
            errors.add("Veuillez renseigner l'appartenance ethnique");
        }

        if (candidateMemberDto.getPassportPhoto() == null || candidateMemberDto.getPassportPhoto().isEmpty()) {
            errors.add("Veuillez télécharger la photo du passeport");
        }


        if (candidateMemberDto.getPassportPhoto() != null){

            // Decode the Base64 string to byte array
            byte[] passportPhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getPassportPhoto());

            // Check the size of the byte array
            if (passportPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo du passeport ne doit pas dépasser 1MB");
            }

        }


        if (candidateMemberDto.getIdentityCardFrontSidePicture() == null || candidateMemberDto.getIdentityCardFrontSidePicture().isEmpty()) {
            errors.add("Veuillez télécharger la photo de la face avant de la carte d'identité");
        }
        if (candidateMemberDto.getIdentityCardFrontSidePicture() != null) {

            // Decode the Base64 string to byte array
            byte[] idFrontPhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getIdentityCardFrontSidePicture());

            // Check the size of the byte array
            if (idFrontPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo de la face avant de la carte d'identité ne doit pas dépasser 1MB");
            }
        }

        if (candidateMemberDto.getIdentityCardBackSidePicture() == null || candidateMemberDto.getIdentityCardBackSidePicture().isEmpty()) {
            errors.add("Veuillez télécharger la photo de la face arrière de la carte d'identité");
        }
        if (candidateMemberDto.getIdentityCardBackSidePicture() != null) {

            // Decode the Base64 string to byte array
            byte[] idBackPhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getIdentityCardBackSidePicture());

            // Check the size of the byte array
            if (idBackPhotoBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo de la face arrière de la carte d'identité ne doit pas dépasser 1MB");
            }

        }


        if (candidateMemberDto.getCertificateOfRegistration() == null || candidateMemberDto.getCertificateOfRegistration().isEmpty()) {
            errors.add("Veuillez télécharger la photo du bulletin d'Inscription CENI");
        }
        if (candidateMemberDto.getCertificateOfRegistration() != null) {

            // Decode the Base64 string to byte array
            byte[] idBulletinInscriptionBytes = Base64.getDecoder().decode(candidateMemberDto.getCertificateOfRegistration());

            // Check the size of the byte array
            if (idBulletinInscriptionBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo du bulletin d'Inscription CENI ne doit pas dépasser 1MB");
            }

        }

        if (candidateMemberDto.getCertificateOfResidence() == null || candidateMemberDto.getCertificateOfResidence().isEmpty()) {
            errors.add("Veuillez télécharger la photo de l 'attestation de Résidence");
        }
        if (candidateMemberDto.getCertificateOfResidence() != null) {

            // Decode the Base64 string to byte array
            byte[] idResidenceBytes = Base64.getDecoder().decode(candidateMemberDto.getCertificateOfResidence());

            // Check the size of the byte array
            if (idResidenceBytes.length > MAX_IMAGE_SIZE) {
                errors.add("La photo de l 'attestation de Résidence ne doit pas dépasser 1MB");
            }

        }

        return errors;
    }
}

