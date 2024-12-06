package com.burundibuhire.burundi.buhire.model;

import com.burundibuhire.burundi.buhire.repository.CandidacyFieldRepository;
import com.burundibuhire.burundi.buhire.repository.CountryRepository;
import com.burundibuhire.burundi.buhire.repository.NationalityRepository;
import com.burundibuhire.burundi.buhire.repository.VolunteeringAreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private VolunteeringAreaRepository volunteeringAreaRepository;

    @Autowired
    private CandidacyFieldRepository candidacyFieldRepository;

    @Override
    public void run(String... args) throws Exception {

        // List of nationalities
        List<String> nationalities = Arrays.asList(
                "Afghan", "Albanais", "Algérien", "Andorran", "Angolais",
                "Antiguayen et Barbudien", "Argentin", "Arménien", "Australien", "Autrichien",
                "Azerbaïdjanais", "Bahaméen", "Bahreïni", "Bangladais", "Barbadien",
                "Biélorusse", "Belge", "Bélizien", "Béninois", "Bhoutanais",
                "Bolivien", "Bosniaque", "Botswanais", "Brésilien", "Brunéien",
                "Bulgare", "Burkinabé", "Burundais", "Cap-Verdien", "Cambodgien",
                "Camerounais", "Canadien", "Centrafricain", "Tchadien", "Chilien",
                "Chinois", "Colombien", "Comorien", "Congolais (République Démocratique du)",
                "Congolais (République du)", "Costaricien", "Croate", "Cuban", "Chypriote",
                "Tchèque", "Danois", "Djiboutien", "Dominicain", "Dominicain (République)",
                "Équatorien", "Égyptien", "Salvadorien", "Équato-guinéen", "Érythréen",
                "Estonien", "Eswatinien", "Éthiopien", "Fidjien", "Finlandais",
                "Français", "Gabonais", "Gambien", "Géorgien", "Allemand",
                "Ghanéen", "Grec", "Grenadien", "Guatémaltèque", "Guinéen",
                "Guinéen-Bissau", "Guyanien", "Haïtien", "Hondurien", "Hongrois",
                "Islandais", "Indien", "Indonésien", "Iranien", "Irakien",
                "Irlandais", "Israélien", "Italien", "Jamaïcain", "Japonais",
                "Jordanien", "Kazakh", "Kényan", "Kiribatien", "Coréen du Nord",
                "Coréen du Sud", "Kuwaitien", "Kirghiz", "Laotien", "Letton",
                "Libanais", "Lesothan", "Libérien", "Libyen", "Liechtensteinois",
                "Lituanien", "Luxembourgeois", "Malgache", "Malawien", "Malais",
                "Maldivien", "Malien", "Maltais", "Marshallais", "Mauritanien",
                "Mauricien", "Mexicain", "Micronésien", "Moldave", "Monégasque",
                "Mongol", "Monténégrin", "Marocain", "Mozambicain", "Birman",
                "Namibien", "Nauruan", "Népalais", "Néerlandais", "Néo-Zélandais",
                "Nicaraguayen", "Nigerien", "Nigérian", "Macédonien", "Norvégien",
                "Omanien", "Pakistanais", "Palausien", "Palestinien", "Panaméen",
                "Papouasien", "Paraguayen", "Péruvien", "Philippin", "Polonais",
                "Portugais", "Qatarien", "Roumain", "Russe", "Rwandais",
                "Kittitien et Nevisien", "Lucien", "Vincentais", "Samoan", "Saint-Marin",
                "Sao Toméen et Príncipien", "Saoudien", "Sénégalais", "Serbe", "Seychellois",
                "Sierra-Léonais", "Singapourien", "Slovaque", "Slovène", "Salomonais",
                "Somalien", "Sud-Africain", "Sud-Soudanais", "Espagnol", "Sri-lankais",
                "Soudanais", "Surinamais", "Suédois", "Suisse", "Syrien",
                "Taïwanais", "Tadjik", "Tanzanien", "Thaïlandais", "Timorais",
                "Togolais", "Tongien", "Trinidadien et Tobagien", "Tunisien", "Turc",
                "Turkmène", "Tuvaluan", "Ougandais", "Ukrainien", "Émirati",
                "Britannique", "Américain", "Uruguayen", "Ouzbek", "Vanuatuan",
                "Vaticanais", "Vénézuélien", "Vietnamien", "Yéménite", "Zambien", "Zimbabwéen"
        );

        // List of countries (assuming some example country list)
        List<String> countries = Arrays.asList(
                "Afghanistan", "Albanie", "Algérie", "Andorre", "Angola",
                "Antigua-et-Barbuda", "Argentine", "Arménie", "Australie", "Autriche",
                "Azerbaïdjan", "Bahamas", "Bahreïn", "Bangladesh", "Barbade",
                "Biélorussie", "Belgique", "Belize", "Bénin", "Bhoutan",
                "Bolivie", "Bosnie-Herzégovine", "Botswana", "Brésil", "Brunei",
                "Bulgarie", "Burkina Faso", "Burundi", "Cap-Vert", "Cambodge",
                "Cameroun", "Canada", "République Centrafricaine", "Tchad", "Chili",
                "Chine", "Colombie", "Comores", "Congo (République Démocratique du)",
                "Congo (République du)", "Costa Rica", "Croatie", "Cuba", "Chypre",
                "République Tchèque", "Danemark", "Djibouti", "Dominique", "République Dominicaine",
                "Équateur", "Égypte", "Salvador", "Guinée Équatoriale", "Érythrée",
                "Estonie", "Eswatini", "Éthiopie", "Fidji", "Finlande",
                "France", "Gabon", "Gambie", "Géorgie", "Allemagne",
                "Ghana", "Grèce", "Grenade", "Guatemala", "Guinée",
                "Guinée-Bissau", "Guyana", "Haïti", "Honduras", "Hongrie",
                "Islande", "Inde", "Indonésie", "Iran", "Irak",
                "Irlande", "Israël", "Italie", "Jamaïque", "Japon",
                "Jordanie", "Kazakhstan", "Kenya", "Kiribati", "Corée du Nord",
                "Corée du Sud", "Koweït", "Kirghizistan", "Laos", "Lettonie",
                "Liban", "Lesotho", "Libéria", "Libye", "Liechtenstein",
                "Lituanie", "Luxembourg", "Madagascar", "Malawi", "Malaisie",
                "Maldives", "Mali", "Malte", "Îles Marshall", "Mauritanie",
                "Maurice", "Mexique", "Micronésie", "Moldavie", "Monaco",
                "Mongolie", "Monténégro", "Maroc", "Mozambique", "Myanmar",
                "Namibie", "Nauru", "Népal", "Pays-Bas", "Nouvelle-Zélande",
                "Nicaragua", "Niger", "Nigéria", "Macédoine du Nord", "Norvège",
                "Oman", "Pakistan", "Palaos", "Palestine", "Panama",
                "Papouasie-Nouvelle-Guinée", "Paraguay", "Pérou", "Philippines", "Pologne",
                "Portugal", "Qatar", "Roumanie", "Russie", "Rwanda",
                "Saint-Christophe-et-Niévès", "Sainte-Lucie", "Saint-Vincent-et-les-Grenadines", "Samoa", "Saint-Marin",
                "Sao Tomé-et-Principe", "Arabie Saoudite", "Sénégal", "Serbie", "Seychelles",
                "Sierra Leone", "Singapour", "Slovaquie", "Slovénie", "Îles Salomon",
                "Somalie", "Afrique du Sud", "Soudan du Sud", "Espagne", "Sri Lanka",
                "Soudan", "Suriname", "Suède", "Suisse", "Syrie",
                "Taïwan", "Tadjikistan", "Tanzanie", "Thaïlande", "Timor oriental",
                "Togo", "Tonga", "Trinité-et-Tobago", "Tunisie", "Turquie",
                "Turkménistan", "Tuvalu", "Ouganda", "Ukraine", "Émirats arabes unis",
                "Royaume-Uni", "États-Unis", "Uruguay", "Ouzbékistan", "Vanuatu",
                "Vatican", "Venezuela", "Vietnam", "Yémen", "Zambie", "Zimbabwe"
        );

        // List of volunteer area
        List<String> volunteerAreas = Arrays.asList(
                "Enregistrer membres", "Administration/Bureautique", "Communication/Média", "Transport/Logistique", "Angolais",
                "Sécurité/Protocole", "Mobilisation/Propagande", "Mandataire/Observateur politique", "Rejoindre nos organes dirigeants"
        );

        // List of candidacy fields
        List<String> candidacyFields = Arrays.asList(
                "Candidat Député", "Candidat Conseiller Communal", "Candidat Sénateur", "Candidat Conseiller Collinaire", "Candidat Chef de Quartier"
        );


        // Add nationalities to the database
        for (String nationalityName : nationalities) {
            if (!nationalityRepository.existsByNationalityName(nationalityName)) {
                Nationality nationality = new Nationality();
                nationality.setNationalityName(nationalityName);
                nationalityRepository.save(nationality);
                System.out.println("Added nationality: " + nationalityName);
            } else {
                System.out.println("Nationality already exists: " + nationalityName);
            }
        }

        // Add countries to the database
        for (String countryName : countries) {
            if (!countryRepository.existsByCountryName(countryName)) {
                Country country = new Country();
                country.setCountryName(countryName);
                countryRepository.save(country);
                System.out.println("Added country: " + countryName);
            } else {
                System.out.println("Country already exists: " + countryName);
            }
        }

        // Add volunteering area to the database
        for (String volunteer : volunteerAreas) {
            if (!volunteeringAreaRepository.existsByVolunteeringAreaName(volunteer)) {
                VolunteeringArea volunteeringArea = new VolunteeringArea();
                volunteeringArea.setVolunteeringAreaName(volunteer);
                volunteeringAreaRepository.save(volunteeringArea);
            } else {
                System.out.println("Volunteer area already exists: " + volunteer);
            }
        }

        // Add Candidacy field to the database
        for (String candidacy : candidacyFields) {
            if (!candidacyFieldRepository.existsByCandidacyFieldName(candidacy)) {
                CandidacyField candidacyField = new CandidacyField();
                candidacyField.setCandidacyFieldName(candidacy);
                candidacyFieldRepository.save(candidacyField);
            } else {
                System.out.println("Candidacy field already exists: " + candidacy);
            }
        }
    }
}