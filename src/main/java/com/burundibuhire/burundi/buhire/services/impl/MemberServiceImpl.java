package com.burundibuhire.burundi.buhire.services.impl;

import com.burundibuhire.burundi.buhire.dto.*;
import com.burundibuhire.burundi.buhire.exceptions.EntityNotFoundException;
import com.burundibuhire.burundi.buhire.exceptions.ErrorCodes;
import com.burundibuhire.burundi.buhire.exceptions.InvalidEntityException;
import com.burundibuhire.burundi.buhire.model.*;
import com.burundibuhire.burundi.buhire.repository.*;
import com.burundibuhire.burundi.buhire.services.MailSenderService;
import com.burundibuhire.burundi.buhire.services.MemberService;
import com.burundibuhire.burundi.buhire.validator.CandidateMemberValidator;
import com.burundibuhire.burundi.buhire.validator.SupportingMemberValidator;
import com.burundibuhire.burundi.buhire.validator.VolunteerMemberValidator;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SupportingMemberRepository supportingMemberRepository;
    private final VolunteerMemberRepository volunteerMemberRepository;
    private final CandidateMemberRepository candidateMemberRepository;
    private final VolunteeringAreaRepository volunteeringAreaRepository;
    private final CandidacyFieldRepository candidacyFieldRepository;
    private final NationalityMemberRepository nationalityMemberRepository;
    private final NationalityRepository nationalityRepository;
    private final DegreeRepository degreeRepository;
    private final ExperienceRepository experienceRepository;
    private final PoliticalHistoryRepository politicalHistoryRepository;
    private final VolunteeringAreaVolunteerMemberRepository volunteeringAreaVolunteerMemberRepository;
    private final CandidacyFieldCandidateMemberRepository candidacyFieldCandidateMemberRepository;
    private final MailSenderService mailService;

    public MemberServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, SupportingMemberRepository supportingMemberRepository, VolunteerMemberRepository volunteerMemberRepository, CandidateMemberRepository candidateMemberRepository, NationalityMemberRepository nationalityMemberRepository, NationalityRepository nationalityRepository, VolunteeringAreaRepository volunteeringAreaRepository, CandidacyFieldRepository candidacyFieldRepository, DegreeRepository degreeRepository, ExperienceRepository experienceRepository, PoliticalHistoryRepository politicalHistoryRepository, VolunteeringAreaVolunteerMemberRepository volunteeringAreaVolunteerMemberRepository, CandidacyFieldCandidateMemberRepository candidacyFieldCandidateMemberRepository, MailSenderService mailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.supportingMemberRepository = supportingMemberRepository;
        this.volunteerMemberRepository = volunteerMemberRepository;
        this.candidateMemberRepository = candidateMemberRepository;
        this.nationalityMemberRepository = nationalityMemberRepository;
        this.nationalityRepository = nationalityRepository;
        this.volunteeringAreaRepository = volunteeringAreaRepository;
        this.candidacyFieldRepository = candidacyFieldRepository;
        this.degreeRepository = degreeRepository;
        this.experienceRepository = experienceRepository;
        this.politicalHistoryRepository = politicalHistoryRepository;
        this.volunteeringAreaVolunteerMemberRepository = volunteeringAreaVolunteerMemberRepository;
        this.candidacyFieldCandidateMemberRepository = candidacyFieldCandidateMemberRepository;
        this.mailService = mailService;
    }

    @Override
    public UserDto saveSupportingMember(UserDto userDto) {

        List<String> errors = SupportingMemberValidator.validate(userDto);
        if(!errors.isEmpty()) {
            ///Log.e(TAG, "registerAsSympathisant: ", );.error("Admin is not valid {}", dto);
            throw new InvalidEntityException("Le membre sympathisant n'est pas valide", ErrorCodes.SUPPORT_MEMBER_NOT_VALID, errors);
        }

        if (userEmailAlreadyExists(userDto.getEmail())){

            throw new InvalidEntityException("Un autre membre avec le même  email existe déjà ", ErrorCodes.SUPPORT_MEMBER_ALREADY_EXISTS,
                    Collections.singletonList("Un autre membre avec le même  email existe déjà  dans la BDD"));

        }

        if (userUserNameAlreadyExists(userDto.getUsername())){

            throw new InvalidEntityException("Un autre membre avec le même  nom d'utilisateur existe déjà ", ErrorCodes.SUPPORT_MEMBER_ALREADY_EXISTS,
                    Collections.singletonList("Un autre membre avec le même  nom d'utilisateur existe déjà  dans la BDD"));

        }

        List<String> nationalityErrors = new ArrayList<>();

        if (userDto.getNationalityMembers() != null) {
            userDto.getNationalityMembers().forEach(ligMemberNat -> {
                if (ligMemberNat.getNationality() != null) {
                    Optional<Nationality> nationality = nationalityRepository.findById(ligMemberNat.getNationality().getId());
                    if (nationality.isEmpty()) {
                        nationalityErrors.add("La nationalité avec l'ID " + ligMemberNat.getNationality().getId() + " n'existe pas");
                    }
                } else {
                    nationalityErrors.add("Impossible d'enregister un membre avec une nationalité NULL");
                }
            });
        }

        if (!nationalityErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException("Nationalité n'existe pas dans la BDD", ErrorCodes.NATIONALITY_NOT_FOUND, nationalityErrors);
        }

        //Save user
        userDto.setIsEmailVerified(false);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User savedUser = userRepository.save(supportingMemberData(userDto));

        if (userDto.getNationalityMembers() != null) {
            userDto.getNationalityMembers().forEach(ligMemberNat -> {
                NationalityMember nationalityMember = NationalityMemberDto.toEntity(ligMemberNat);
                nationalityMember.setUser(savedUser);
                nationalityMemberRepository.save(nationalityMember);

                //send
//                String token = UUID.randomUUID().toString();
//                savedUser.setVerificationToken(token);
//                mailService.sendVerificationEmail(userDto.getEmail().trim(), token, savedUser.getFirstName(),savedUser.getPassword());

                //send email
                String token = UUID.randomUUID().toString();
                String verificationLink = "http://localhost:8084/members/burundibushasha/v1/verify/" + token;
                Map<String, Object> data = new HashMap<>();
                data.put("name", userDto.getFirstName()+" "+userDto.getLastName());
                data.put("confirmationLink", verificationLink);

                try {
                    savedUser.setVerificationToken(token);
                    mailService.sendEmail(userDto.getEmail().trim(), "Confirm Your Email", data, "email-template.html");
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
                //gggggggg

                ///effectuerSortie(savedLigneCmd);
            });
        }

        //Save supporting member
        SupportingMember supportingMember = new SupportingMember();
        supportingMember.setUser(savedUser);
        supportingMemberRepository.save(supportingMember);

        return UserDto.fromEntity(savedUser);
    }

    @Override
    public VolunteerMemberDto upgradeToVolontaire(VolunteerMemberDto volunteerMemberDto) {

        List<String> errors = VolunteerMemberValidator.validate(volunteerMemberDto);
        if(!errors.isEmpty()) {
            ///Log.e(TAG, "registerAsSympathisant: ", );.error("Admin is not valid {}", dto);
            throw new InvalidEntityException("Le membre volontaire n'est pas valide", ErrorCodes.VOLUNTEER_MEMBER_NOT_VALID, errors);
        }

        List<String> volunteeringAreaMembersErrors = new ArrayList<>();
        if (volunteerMemberDto.getVolunteeringAreaMembers() != null) {
            volunteerMemberDto.getVolunteeringAreaMembers().forEach(ligMemberNat -> {
                if (ligMemberNat.getVolunteerId() != null) {
                    Optional<VolunteeringArea> volunteeringArea = volunteeringAreaRepository.findById(ligMemberNat.getVolunteeringArea().getId());
                    if (volunteeringArea.isEmpty()) {
                        volunteeringAreaMembersErrors.add("Le domaine de volontariat avec l'ID " + ligMemberNat.getVolunteeringArea().getId() + " n'existe pas");
                    }
                } else {
                    volunteeringAreaMembersErrors.add("Impossible d'enregister un membre volontaire avec un domaine de volontariat NULL");
                }
            });
        }

        if (!volunteeringAreaMembersErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Le domaine de volontariat n'existe pas dans la BDD", ErrorCodes.VOLUNTEERING_AREA_NOT_FOUND, volunteeringAreaMembersErrors);
        }

        // Validate degrees
        List<String> degreeErrors = new ArrayList<>();
        if (volunteerMemberDto.getDegrees() != null) {
            volunteerMemberDto.getDegrees().forEach(degreeDto -> {

                if (degreeDto.getSchoolName() == null || degreeDto.getSchoolName().isEmpty()) {
                    degreeErrors.add("Le champ établissement de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getMemberDegree() == null) {
                    degreeErrors.add("Le champ diplôme de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getFieldOfStudy() == null || degreeDto.getFieldOfStudy().isEmpty()) {
                    degreeErrors.add("Le champ domaine d'étude de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getStartDate() == null) {
                    degreeErrors.add("Le champ année de début de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getEndDate() == null) {
                    degreeErrors.add("Le champ année de fin de Éducation est requis pour le degree ID " + degreeDto.getId());
                }


                // Check if the experience exists in the repository, if required
//                if (degreeDto.getId() != null) {
//                    Optional<Degree> degree = degreeRepository.findById(degreeDto.getId());
//                    if (degree.isEmpty()) {
//                        degreeErrors.add("Le degree avec l'ID " + degreeDto.getId() + " n'existe pas");
//                    }
//                }
            });
        }

        if (!degreeErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Le diplôme n'existe pas dans la BDD", ErrorCodes.DEGREE_NOT_FOUND, degreeErrors);
        }

        // Validate experiences
        List<String> experienceErrors = new ArrayList<>();
        if (volunteerMemberDto.getExperience() != null) {

            volunteerMemberDto.getExperience().forEach(experienceDto -> {

                if (experienceDto.getEmployerName() == null || experienceDto.getEmployerName().isEmpty()) {
                    experienceErrors.add("L'employeur de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getJobCategory() == null || experienceDto.getJobCategory().toString().isEmpty()) {
                    experienceErrors.add("La catégorie de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }

                if (experienceDto.getJobTitle() == null || experienceDto.getJobTitle().isEmpty()) {
                    experienceErrors.add("Le titre du poste de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getStartDate() == null) {
                    experienceErrors.add("La date de début de Expérience professionelle est requise pour la ligne N°  " + experienceDto.getId()+1);
                }
                if (experienceDto.getEndDate() == null) {
                    experienceErrors.add("La date de fin de Expérience professionelle est requise pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getStartDate() != null && experienceDto.getEndDate() != null &&
                        experienceDto.getStartDate().isAfter(experienceDto.getEndDate())) {
                    experienceErrors.add("La date de début doit être avant la date de fin pour l'expérience N° " + experienceDto.getId()+1);
                }

                // Check if the experience exists in the repository, if required
//                if (experienceDto.getId() != null) {
//                    Optional<Experience> experience = experienceRepository.findById(experienceDto.getId());
//                    if (experience.isEmpty()) {
//                        experienceErrors.add("L'expérience avec l'ID " + experienceDto.getId() + " n'existe pas");
//                    }
//                }
            });
        }

        if (!experienceErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Experience n'existe pas dans la BDD", ErrorCodes.EXPERIENCE_NOT_FOUND, experienceErrors);
        }

        // SAVE VOLUNTEER MEMBER
        User user = userRepository.findById(volunteerMemberDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Aucune utilisateur avec l'ID = " +volunteerMemberDto.getUserId()+ " n' a ete trouve dans la BDD",ErrorCodes.SUPPORT_MEMBER_NOT_FOUND));
        user.setMemberType(MemberTypeEnum.MEMBRE_VOLONTAIRE);

        VolunteerMember volunteerMember = new VolunteerMember();
        volunteerMember.setUser(user);
        if (volunteerMemberDto.getPassportPhoto() != null) {
            byte[] passportPhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getPassportPhoto());
            user.setPassportPhoto(passportPhotoBytes);
        }

        if (volunteerMemberDto.getIdentityCardFrontSidePicture() != null) {
            byte[] frontSidePhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardFrontSidePicture());
            user.setIdentityCardFrontSidePicture(frontSidePhotoBytes);
        }

        if (volunteerMemberDto.getIdentityCardBackSidePicture() != null) {
            byte[] backSidePhotoBytes = Base64.getDecoder().decode(volunteerMemberDto.getIdentityCardBackSidePicture());
            user.setIdentityCardBackSidePicture(backSidePhotoBytes);
        }

        VolunteerMember savedVolunteerMember=volunteerMemberRepository.save(volunteerMember);

        if (volunteerMemberDto.getVolunteeringAreaMembers() != null) {

            volunteerMemberDto.getVolunteeringAreaMembers().forEach(ligMemberNat -> {

                VolunteeringAreaVolunteerMember volunteeringAreaVolunteerMember = VolunteeringAreaVolunteerMemberDto.toEntity(ligMemberNat);
                volunteeringAreaVolunteerMember.setVolunteerMember(savedVolunteerMember);
                volunteeringAreaVolunteerMember.setVolunteeringArea(VolunteeringAreaDto.toEntity(ligMemberNat.getVolunteeringArea()));
                volunteeringAreaVolunteerMemberRepository.save(volunteeringAreaVolunteerMember);
            });
        }

        if (volunteerMemberDto.getDegrees() != null) {

            volunteerMemberDto.getDegrees().forEach(ligDegree -> {

                Degree degree = DegreeDto.toEntity(ligDegree);
                degree.setSchoolName(ligDegree.getSchoolName());
                degree.setMemberDegree(ligDegree.getMemberDegree());
                degree.setFieldOfStudy(ligDegree.getFieldOfStudy());
                degree.setStartDate(ligDegree.getStartDate());
                degree.setEndDate(ligDegree.getEndDate());
                degree.setVolunteerMember(savedVolunteerMember);
                degreeRepository.save(degree);
            });
        }

        if (volunteerMemberDto.getExperience() != null) {

            volunteerMemberDto.getExperience().forEach(ligExperience -> {

                Experience experience = ExperienceDto.toEntity(ligExperience);
                experience.setEmployerName(ligExperience.getEmployerName());
                experience.setJobCategory(ligExperience.getJobCategory());
                experience.setJobTitle(ligExperience.getJobTitle());
                experience.setStartDate(ligExperience.getStartDate());
                experience.setEndDate(ligExperience.getEndDate());
                experience.setVolunteerMember(savedVolunteerMember);
                experienceRepository.save(experience);
            });
        }

        return VolunteerMemberDto.fromEntity(savedVolunteerMember);
    }

    @Override
    public CandidateMemberDto upgradeToCandidate(CandidateMemberDto candidateMemberDto) {

        List<String> errors = CandidateMemberValidator.validate(candidateMemberDto);
        if(!errors.isEmpty()) {
            ///Log.e(TAG, "registerAsSympathisant: ", );.error("Admin is not valid {}", dto);
            throw new InvalidEntityException("Le membre candidat n'est pas valide", ErrorCodes.VOLUNTEER_MEMBER_NOT_VALID, errors);
        }

        List<String> candidacyFieldMembersErrors = new ArrayList<>();
        if (candidateMemberDto.getCandidacyFieldCandidateMembers() != null) {
            candidateMemberDto.getCandidacyFieldCandidateMembers().forEach(ligMemberNat -> {
                if (ligMemberNat.getCandidateId() != null) {
                    Optional<CandidacyField> candidacyField = candidacyFieldRepository.findById(ligMemberNat.getCandidacyField().getId());
                    if (candidacyField.isEmpty()) {
                        candidacyFieldMembersErrors.add("Le domaine de candidature avec l'ID " + ligMemberNat.getCandidacyField().getId() + " n'existe pas");
                    }
                } else {
                    candidacyFieldMembersErrors.add("Impossible d'enregister un membre candidat avec un domaine de candidature NULL");
                }
            });
        }

        if (!candidacyFieldMembersErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Le domaine de candidature n'existe pas dans la BDD", ErrorCodes.CANDIDACY_FIELD_NOT_FOUND, candidacyFieldMembersErrors);
        }

        // Validate degrees
        List<String> degreeErrors = new ArrayList<>();
        if (candidateMemberDto.getDegrees() != null) {
            candidateMemberDto.getDegrees().forEach(degreeDto -> {

                if (degreeDto.getSchoolName() == null || degreeDto.getSchoolName().isEmpty()) {
                    degreeErrors.add("Le champ établissement de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getMemberDegree() == null) {
                    degreeErrors.add("Le champ diplôme de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getFieldOfStudy() == null || degreeDto.getFieldOfStudy().isEmpty()) {
                    degreeErrors.add("Le champ domaine d'étude de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getStartDate() == null) {
                    degreeErrors.add("Le champ année de début de Éducation est requis pour la ligne N° " + degreeDto.getId()+1);
                }

                if (degreeDto.getEndDate() == null) {
                    degreeErrors.add("Le champ année de fin de Éducation est requis pour le degree ID " + degreeDto.getId());
                }


                // Check if the experience exists in the repository, if required
//                if (degreeDto.getId() != null) {
//                    Optional<Degree> degree = degreeRepository.findById(degreeDto.getId());
//                    if (degree.isEmpty()) {
//                        degreeErrors.add("Le degree avec l'ID " + degreeDto.getId() + " n'existe pas");
//                    }
//                }
            });
        }

        if (!degreeErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Le diplôme n'existe pas dans la BDD", ErrorCodes.DEGREE_NOT_FOUND, degreeErrors);
        }

        // Validate experiences
        List<String> experienceErrors = new ArrayList<>();
        if (candidateMemberDto.getExperience() != null) {

            candidateMemberDto.getExperience().forEach(experienceDto -> {

                if (experienceDto.getEmployerName() == null || experienceDto.getEmployerName().isEmpty()) {
                    experienceErrors.add("L'employeur de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getJobCategory() == null || experienceDto.getJobCategory().toString().isEmpty()) {
                    experienceErrors.add("La catégorie de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }

                if (experienceDto.getJobTitle() == null || experienceDto.getJobTitle().isEmpty()) {
                    experienceErrors.add("Le titre du poste de Expérience professionelle est requis pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getStartDate() == null) {
                    experienceErrors.add("La date de début de Expérience professionelle est requise pour la ligne N°  " + experienceDto.getId()+1);
                }
                if (experienceDto.getEndDate() == null) {
                    experienceErrors.add("La date de fin de Expérience professionelle est requise pour la ligne N° " + experienceDto.getId()+1);
                }
                if (experienceDto.getStartDate() != null && experienceDto.getEndDate() != null &&
                        experienceDto.getStartDate().isAfter(experienceDto.getEndDate())) {
                    experienceErrors.add("La date de début doit être avant la date de fin pour l'expérience N° " + experienceDto.getId()+1);
                }

                // Check if the experience exists in the repository, if required
//                if (experienceDto.getId() != null) {
//                    Optional<Experience> experience = experienceRepository.findById(experienceDto.getId());
//                    if (experience.isEmpty()) {
//                        experienceErrors.add("L'expérience avec l'ID " + experienceDto.getId() + " n'existe pas");
//                    }
//                }
            });
        }

        if (!experienceErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Experience n'existe pas dans la BDD", ErrorCodes.EXPERIENCE_NOT_FOUND, experienceErrors);
        }

        // Validate political history
        List<String> politicalHistoryErrors = new ArrayList<>();
        if (candidateMemberDto.getPoliticalHistories() != null) {

            candidateMemberDto.getPoliticalHistories().forEach(politicHistoryDto -> {

                if (politicHistoryDto.getPoliticalOrganisation() == null || politicHistoryDto.getPoliticalOrganisation().isEmpty()) {
                    politicalHistoryErrors.add("Le nom de la formation politique  est requis pour la ligne N° " + politicHistoryDto.getId()+1);
                }
                if (politicHistoryDto.getPoliticalCategory() == null || politicHistoryDto.getPoliticalCategory().toString().isEmpty()) {
                    politicalHistoryErrors.add("La catégorie politique est requis pour la ligne N° " + politicHistoryDto.getId()+1);
                }

                if (politicHistoryDto.getFunction() == null || politicHistoryDto.getFunction().isEmpty()) {
                    experienceErrors.add("Poste/Responsabilités est requis pour la ligne N° " + politicHistoryDto.getId()+1);
                }
                if (politicHistoryDto.getStartDate() == null) {
                    experienceErrors.add("La date de début est requise pour la ligne N°  " + politicHistoryDto.getId()+1);
                }
                if (politicHistoryDto.getEndDate() == null) {
                    experienceErrors.add("La date de fin est requise pour la ligne N° " + politicHistoryDto.getId()+1);
                }
                if (politicHistoryDto.getStartDate() != null && politicHistoryDto.getEndDate() != null &&
                        politicHistoryDto.getStartDate().isAfter(politicHistoryDto.getEndDate())) {
                    experienceErrors.add("La date de début doit être avant la date de fin pour l'expérience N° " + politicHistoryDto.getId()+1);
                }

                // Check if the experience exists in the repository, if required
//                if (experienceDto.getId() != null) {
//                    Optional<Experience> experience = experienceRepository.findById(experienceDto.getId());
//                    if (experience.isEmpty()) {
//                        experienceErrors.add("L'expérience avec l'ID " + experienceDto.getId() + " n'existe pas");
//                    }
//                }
            });
        }

        if (!politicalHistoryErrors.isEmpty()) {
            //log.warn("");
            throw new InvalidEntityException(" Passé politique n'existe pas dans la BDD", ErrorCodes.EXPERIENCE_NOT_FOUND, experienceErrors);
        }

        // SAVE VOLUNTEER MEMBER
        User user = userRepository.findById(candidateMemberDto.getUserId()).orElseThrow(() -> new EntityNotFoundException("Aucune utilisateur avec l'ID = " +candidateMemberDto.getUserId()+ " n' a ete trouve dans la BDD",ErrorCodes.SUPPORT_MEMBER_NOT_FOUND));
        user.setMemberType(MemberTypeEnum.MEMBRE_CANDIDAT);

        CandidateMember candidateMember = new CandidateMember();
        candidateMember.setUser(user);
        if (candidateMemberDto.getPassportPhoto() != null) {
            byte[] passportPhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getPassportPhoto());
            user.setPassportPhoto(passportPhotoBytes);
        }

        if (candidateMemberDto.getIdentityCardFrontSidePicture() != null) {
            byte[] frontSidePhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getIdentityCardFrontSidePicture());
            user.setIdentityCardFrontSidePicture(frontSidePhotoBytes);
        }

        if (candidateMemberDto.getIdentityCardBackSidePicture() != null) {
            byte[] backSidePhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getIdentityCardBackSidePicture());
            user.setIdentityCardBackSidePicture(backSidePhotoBytes);
        }

        if (candidateMemberDto.getIdentityCardBackSidePicture() != null) {
            byte[] backSidePhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getIdentityCardBackSidePicture());
            user.setIdentityCardBackSidePicture(backSidePhotoBytes);
        }

        if (candidateMemberDto.getCertificateOfRegistration() != null) {
            byte[] certificateRegPhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getCertificateOfRegistration());
            candidateMember.setCertificateOfRegistration(certificateRegPhotoBytes);
        }

        if (candidateMemberDto.getCertificateOfResidence() != null) {
            byte[] certificateResidencePhotoBytes = Base64.getDecoder().decode(candidateMemberDto.getCertificateOfResidence());
            candidateMember.setCertificateOfResidence(certificateResidencePhotoBytes);
        }

        CandidateMember savedCandidateMember=candidateMemberRepository.save(candidateMember);

        if (candidateMemberDto.getCandidacyFieldCandidateMembers() != null) {

            candidateMemberDto.getCandidacyFieldCandidateMembers().forEach(ligMemberNat -> {

                CandidacyFieldCandidateMember candidacyFieldCandidateMember = CandidacyFieldCandidateMemberDto.toEntity(ligMemberNat);
                candidacyFieldCandidateMember.setCandidateMember(savedCandidateMember);
                candidacyFieldCandidateMember.setCandidacyField(CandidacyFieldDto.toEntity(ligMemberNat.getCandidacyField()));
                candidacyFieldCandidateMemberRepository.save(candidacyFieldCandidateMember);

            });
        }

        if (candidateMemberDto.getDegrees() != null) {

            candidateMemberDto.getDegrees().forEach(ligDegree -> {

                Degree degree = DegreeDto.toEntity(ligDegree);
                degree.setSchoolName(ligDegree.getSchoolName());
                degree.setMemberDegree(ligDegree.getMemberDegree());
                degree.setFieldOfStudy(ligDegree.getFieldOfStudy());
                degree.setStartDate(ligDegree.getStartDate());
                degree.setEndDate(ligDegree.getEndDate());
                degree.setCandidateMember(savedCandidateMember);
                degreeRepository.save(degree);
            });
        }

        if (candidateMemberDto.getExperience() != null) {

            candidateMemberDto.getExperience().forEach(ligExperience -> {

                Experience experience = ExperienceDto.toEntity(ligExperience);
                experience.setEmployerName(ligExperience.getEmployerName());
                experience.setJobCategory(ligExperience.getJobCategory());
                experience.setJobTitle(ligExperience.getJobTitle());
                experience.setStartDate(ligExperience.getStartDate());
                experience.setEndDate(ligExperience.getEndDate());
                experience.setCandidateMember(savedCandidateMember);
                experienceRepository.save(experience);
            });
        }

        if (candidateMemberDto.getPoliticalHistories() != null) {

            candidateMemberDto.getPoliticalHistories().forEach(ligPolitical -> {

                PoliticalHistory politicalHistory = PoliticalHistoryDto.toEntity(ligPolitical);
                politicalHistory.setPoliticalOrganisation(ligPolitical.getPoliticalOrganisation());
                politicalHistory.setPoliticalCategory(ligPolitical.getPoliticalCategory());
                politicalHistory.setFunction(ligPolitical.getFunction());
                politicalHistory.setStartDate(ligPolitical.getStartDate());
                politicalHistory.setEndDate(ligPolitical.getEndDate());
                politicalHistory.setCandidateMember(savedCandidateMember);
                politicalHistoryRepository.save(politicalHistory);
            });
        }
        return CandidateMemberDto.fromEntity(savedCandidateMember);
    }

    @Override
    public boolean verifyToken(String token) {

        Optional<User> userOptional = userRepository.findByVerificationToken(token);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setIsEmailVerified(true);
            user.setVerificationToken(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }


    private boolean userEmailAlreadyExists(String email) {
        Optional<User> member = userRepository.findByEmail(email);
        return member.isPresent();
    }

    private boolean userUserNameAlreadyExists(String username) {
        Optional<User> member = userRepository.findByUsername(username);
        return member.isPresent();
    }

    private static String generateMemberId(int length) {
        StringBuilder result = new StringBuilder(length);
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom RANDOM = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            result.append(CHARACTERS.charAt(index));
        }
        return result.toString();
    }

    private User supportingMemberData(UserDto dto){

        User member = new User();
        member.setMemberIdNumber(generateMemberId(8));
        member.setFirstName(dto.getFirstName());
        member.setLastName(dto.getLastName());
        member.setDateOfBirth(dto.getDateOfBirth());
        member.setGender(dto.getGender());
        member.setCountryOfBirth(CountryDto.toEntity(dto.getCountryOfBirth()));
        member.setPlaceOfBirth(dto.getPlaceOfBirth());
        member.setEmail(dto.getEmail());
        member.setIsEmailVerified(dto.getIsEmailVerified());
        member.setVerificationToken(dto.getVerificationToken());
        member.setPhoneNumber(dto.getPhoneNumber());
        member.setWhatsappNumber(dto.getWhatsappNumber());
        member.setUsername(dto.getUsername());
        member.setPassword(dto.getPassword());
        member.setMemberType(MemberTypeEnum.MEMBRE_SYMPATHISANT);
        member.setMemberGrade(MemberGradeEnum.JUNIOR_MEMBER);

        return member;

    }

}

