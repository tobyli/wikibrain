package org.wikibrain.cookbook.sr;

import au.com.bytecode.opencsv.CSVWriter;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.json.JSONObject;
import org.wikibrain.atlasify.AtlasifyResource;
import org.wikibrain.conf.ConfigurationException;
import org.wikibrain.conf.Configurator;
import org.wikibrain.core.cmd.Env;
import org.wikibrain.core.cmd.EnvBuilder;
import org.wikibrain.core.dao.DaoException;
import org.wikibrain.core.lang.Language;
import org.wikibrain.core.lang.LocalId;
import org.wikibrain.sr.SRMetric;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: toby
 * Date: 6/13/15
 * Time: 4:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class AtlasifySRSampler {
    public static void main(String args[]) throws ConfigurationException, DaoException, IOException, Exception {
        Env env = EnvBuilder.envFromArgs(args);
        Configurator conf = env.getConfigurator();
        SRMetric sr = conf.get(SRMetric.class, "milnewitten","language", "en");
        List<String> mapFeatures = new LinkedList<String>();
        String[] featuredArticleArray = {"Ice hockey", "Association football", "Table tennis", "Cricket", "Badminton", "NASCAR", "Yao Ming", "Baseball", "Polar Bear", "Kangaroo", "Monkey", "Koala", "Tiger", "Lion", "Bear", "Mormonism", "Taoism", "Jews", "Lutheranism", "Crusades", "Islam", "Sushi", "Coca-Cola", "Kimchi", "Milk", "Salmon", "Pasta", "Fajita", "World War I", "Civil war", "Korean War", "Arab Spring", "Toyota", "Chevrolet", "Lenovo", "IKEA", "Nokia", "Country music", "Hip-hop", "Star Wars", "Star Trek", "Whisky", "Brandy", "Beer", "Wine", "Bourbon", "Tequila", "Heroin", "Assasination", "Nuclear power", "Nuclear weapon"};
        List<String> featuredArticle = new LinkedList<String>(Arrays.asList(featuredArticleArray));
        String[] mapFeaturesArray = {"Aruba"  ,"Antigua"  ,"Barbuda"  ,"Afghanistan"  ,"Angola"  ,"Anguilla"  ,"Albania"  ,"Aland"  ,"Andorra"  ,"United Arab Emirates"  ,"Argentina"  ,"Armenia"  ,"American Samoa"  ,"Antarctica"  ,"Ashmore and Cartier Is."  ,"Fr. S. Antarctic Lands"  ,"Australia"  ,"Austria"  ,"Azerbaijan"  ,"Brussels"  ,"Burundi"  ,"Benin"  ,"Burkina Faso"  ,"Flemish"  ,"Bangladesh"  ,"Bulgaria"  ,"Fed. of Bos. & Herz."  ,"Bahrain"  ,"Bahamas"  ,"Rep. Srpska"  ,"St-BarthÃ©lemy"  ,"Belarus"  ,"Belize"  ,"Bermuda"  ,"Bolivia"  ,"Brazil"  ,"Barbados"  ,"Brunei"  ,"Bhutan"  ,"Botswana"  ,"Walloon"  ,"Central African Rep."  ,"Canada"  ,"Cocos Is."  ,"Switzerland"  ,"Chile"  ,"China"  ,"Ivory Coast"  ,"Cameroon"  ,"Dem. Rep. Congo"  ,"Congo"  ,"Cook Is."  ,"Colombia"  ,"Comoros"  ,"Cape Verde"  ,"Costa Rica"  ,"Cuba"  ,"CuraÃ§ao"  ,"Christmas I."  ,"Cayman Is."  ,"N. Cyprus"  ,"Cyprus"  ,"Czech Rep."  ,"Germany"  ,"Djibouti"  ,"Dominica"  ,"Denmark"  ,"Dominican Rep."  ,"Algeria"  ,"Ecuador"  ,"Egypt"  ,"England"  ,"Eritrea"  ,"Spain"  ,"Estonia"  ,"Ethiopia"  ,"Finland"  ,"Fiji"  ,"Falkland Is."  ,"Micronesia"  ,"France"  ,"Gabon"  ,"Gaza"  ,"Georgia (country)"  ,"Guernsey"  ,"Ghana"  ,"Guinea"  ,"Guadeloupe"  ,"Gambia"  ,"Guinea-Bissau"  ,"Eq. Guinea"  ,"Greece"  ,"Grenada"  ,"Greenland"  ,"Guatemala"  ,"French Guiana"  ,"Guam"  ,"Guyana"  ,"Hong Kong"  ,"Heard I. and McDonald Is."  ,"Honduras"  ,"Croatia"  ,"Haiti"  ,"Hungary"  ,"Indonesia"  ,"Isle of Man"  ,"India"  ,"Br. Indian Ocean Ter."  ,"Ireland"  ,"Iran"  ,"Iraq"  ,"Iceland"  ,"Israel"  ,"Italy"  ,"Jamaica"  ,"Jersey"  ,"Jordan"  ,"Japan"  ,"Siachen Glacier"  ,"Kazakhstan"  ,"Kenya"  ,"Kyrgyzstan"  ,"Cambodia"  ,"Kiribati"  ,"St. Kitts and Nevis"  ,"Korea"  ,"Kosovo"  ,"Kuwait"  ,"Lao PDR"  ,"Lebanon"  ,"Liberia"  ,"Libya"  ,"Saint Lucia"  ,"Liechtenstein"  ,"Sri Lanka"  ,"Lesotho"  ,"Lithuania"  ,"Luxembourg"  ,"Latvia"  ,"Macao"  ,"St-Martin"  ,"Morocco"  ,"Monaco"  ,"Moldova"  ,"Madagascar"  ,"Maldives"  ,"Mexico"  ,"Marshall Is."  ,"Macedonia"  ,"Mali"  ,"Malta"  ,"Myanmar"  ,"Montenegro"  ,"Mongolia"  ,"N. Mariana Is."  ,"Mozambique"  ,"Mauritania"  ,"Montserrat"  ,"Martinique"  ,"Mauritius"  ,"Malawi"  ,"Malaysia"  ,"Mayotte"  ,"Namibia"  ,"New Caledonia"  ,"Niger"  ,"Norfolk Island"  ,"Nigeria"  ,"Nicaragua"  ,"N. Ireland"  ,"Niue"  ,"Jan Mayen I."  ,"Netherlands"  ,"Caribbean Netherlands"  ,"Norway"  ,"Nepal"  ,"Nauru"  ,"Svalbard Is."  ,"New Zealand"  ,"Oman"  ,"Pakistan"  ,"Panama"  ,"Azores"  ,"Pitcairn Is."  ,"Peru"  ,"Philippines"  ,"Palau"  ,"Madeira"  ,"Bougainville"  ,"Papua New Guinea"  ,"Poland"  ,"Puerto Rico"  ,"Dem. Rep. Korea"  ,"Portugal"  ,"Paraguay"  ,"Fr. Polynesia"  ,"Qatar"  ,"Reunion"  ,"Romania"  ,"Russia"  ,"Rwanda"  ,"W. Sahara"  ,"Saudi Arabia"  ,"Scotland"  ,"Sudan"  ,"S. Sudan"  ,"Senegal"  ,"Singapore"  ,"S. Geo. and S. Sandw. Is."  ,"Saint Helena"  ,"Solomon Is."  ,"Sierra Leone"  ,"El Salvador"  ,"San Marino"  ,"Somaliland"  ,"Somalia"  ,"St. Pierre and Miquelon"  ,"Serbia"  ,"Vojvodina"  ,"SÃ£o TomÃ© and Principe"  ,"Suriname"  ,"Slovakia"  ,"Slovenia"  ,"Sweden"  ,"Swaziland"  ,"Sint Maarten"  ,"Seychelles"  ,"Syria"  ,"Turks and Caicos Is."  ,"Chad"  ,"Togo"  ,"Thailand"  ,"Tajikistan"  ,"Tokelau"  ,"Turkmenistan"  ,"Timor-Leste"  ,"Tonga"  ,"Trinidad and Tobago"  ,"Tunisia"  ,"Turkey"  ,"Taiwan"  ,"Tanzania"  ,"Zanzibar"  ,"Uganda"  ,"Ukraine"  ,"Uruguay"  ,"USA"  ,"United States"  ,"Uzbekistan"  ,"Vatican"  ,"St. Vin. and Gren."  ,"Venezuela"  ,"British Virgin Is."  ,"U.S. Virgin Is."  ,"Vietnam"  ,"Vanuatu"  ,"West Bank"  ,"Wallis and Futuna Is."  ,"Wales"  ,"Samoa"  ,"Yemen"  ,"South Africa"  ,"Zambia"  ,"Zimbabwe"  , "Belgium"   };
        AtlasifyResource atlasifyResource = new AtlasifyResource();
        CSVWriter writer = new CSVWriter(new FileWriter(new File("atlasifyEarthNUSample.csv"), true), ',');
        String[] row = new String[3];
        row[0] = "Entity 1";
        row[1] = "Entity 2";
        row[2] = "SR";
        writer.writeNext(row);
        writer.flush();
        for(String keyword : featuredArticleArray){
            LocalId queryID = new LocalId(Language.EN, 0);
            try {
                queryID = AtlasifyResource.wikibrainPhaseResolution(keyword);
            } catch (Exception e) {
                System.out.println("Failed to resolve keyword " + keyword);
            }
            // LocalId queryID = new LocalId(Language.EN, 19908980);
            try {
                Map<LocalId, Double> srValues = new HashMap<LocalId, Double>();
                boolean srValueLoaded = false;



                for (int i = 0; i < mapFeaturesArray.length; i++) {


                    //TODO: background loading wikibrain SR for all the keyword entered
                    //only need to load data from NU if any of the <keyword, feature> pair is not cached

                            if(srValueLoaded == false){
                                srValues = atlasifyResource.accessNorthwesternAPI(queryID, -1, false);
                                srValueLoaded = true;
                            }


                        System.out.println("Got NU SR data for keyworld " + keyword);

                    LocalId featureID = new LocalId(Language.EN, 0);

                    try {
                        featureID = AtlasifyResource.wikibrainPhaseResolution(mapFeaturesArray[i]);
                        System.out.println("Feature ID for " + mapFeaturesArray[i] + " is " + featureID.getId());
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("Failed to resolve " + mapFeaturesArray[i]);
                        continue;
                        //do nothing
                    }

                    try {
                        if((!srValues.containsKey(featureID) || srValues.get(featureID) == null)){
                            System.out.println("can't get SR value for" + featureID);
                            throw new Exception("can't get SR value for " + featureID);

                        }
                        row[0] = mapFeaturesArray[i];
                        row[1] = keyword;
                        row[2] = srValues.get(featureID).toString();
                        System.out.println("get sr " + row[2]);
                        writer.writeNext(row);

                        writer.flush();

                    } catch (Exception e) {
                       e.printStackTrace();
                        continue;
                        //do nothing
                    }
                }
                if(srValueLoaded == false){
                    System.out.println("All SR data for keyword " + keyword + " is loaded from WikiBrain SR cache");
                }
            }
            catch (Exception e){}
        }
        /*
        CSVWriter writer = new CSVWriter(new FileWriter(new File("atlasifyEarthSample.csv"), true), ',');
        String[] row = new String[3];
        row[0] = "Entity 1";
        row[1] = "Entity 2";
        row[2] = "SR";
        writer.writeNext(row);
        writer.flush();
        for(String feature :featuredArticle){
            for(String mapFeature :mapFeaturesArray){
                double srValue = sr.similarity(feature, mapFeature, false).getScore();
                row[0] = feature;
                row[1] = mapFeature;
                row[2] = String.valueOf(srValue);
                writer.writeNext(row);
                writer.flush();
            }
        }
        writer.close();
        String[] periodicTableArray = {"Hydrogen" ,	"Helium" ,	"Lithium" ,	"Beryllium" ,	"Boron" ,	"Carbon" ,	"Nitrogen" ,	"Oxygen" ,	"Fluorine" ,	"Neon" ,	"Sodium" ,	"Magnesium" ,	"Aluminium" ,	"Silicon" ,	"Phosphorus" ,	"Sulfur" ,	"Chlorine" ,	"Argon" ,	"Potassium" ,	"Calcium" ,	"Scandium" ,	"Titanium" ,	"Vanadium" ,	"Chromium" ,	"Manganese" ,	"Iron" ,	"Cobalt" ,	"Nickel" ,	"Copper" ,	"Zinc" ,	"Gallium" ,	"Germanium" ,	"Arsenic" ,	"Selenium" ,	"Bromine" ,	"Krypton" ,	"Rubidium" ,	"Strontium" ,	"Yttrium" ,	"Zirconium" ,	"Niobium" ,	"Molybdenum" ,	"Technetium" ,	"Ruthenium" ,	"Rhodium" ,	"Palladium" ,	"Silver" ,	"Cadmium" ,	"Indium" ,	"Tin" ,	"Antimony" ,	"Tellurium" ,	"Iodine" ,	"Xenon" ,	"Caesium" ,	"Barium" ,	"Lanthanum" ,	"Cerium" ,	"Praseodymium" ,	"Neodymium" ,	"Promethium" ,	"Samarium" ,	"Europium" ,	"Gadolinium" ,	"Terbium" ,	"Dysprosium" ,	"Holmium" ,	"Erbium" ,	"Thulium" ,	"Ytterbium" ,	"Lutetium" ,	"Hafnium" ,	"Tantalum" ,	"Tungsten" ,	"Rhenium" ,	"Osmium" ,	"Iridium" ,	"Platinum" ,	"Gold" ,	"Mercury (element)" ,	"Thallium" ,	"Lead" ,	"Bismuth" ,	"Polonium" ,	"Astatine" ,	"Radon" ,	"Francium" ,	"Radium" ,	"Actinium" ,	"Thorium" ,	"Protactinium" ,	"Uranium" ,	"Neptunium" ,	"Plutonium" ,	"Americium" ,	"Curium" ,	"Berkelium" ,	"Californium" ,	"Einsteinium" ,	"Fermium" ,	"Mendelevium" ,	"Nobelium" ,	"Lawrencium" ,	"Rutherfordium" ,	"Dubnium" ,	"Seaborgium" ,	"Bohrium" ,	"Hassium" ,	"Meitnerium" ,	"Darmstadtium" ,	"Roentgenium" ,	"Copernicium" ,	"Ununtrium" ,	"Flerovium" ,	"Ununpentium" ,	"Livermorium" ,	"Ununseptium" ,	"Ununoctium" };
        writer = new CSVWriter(new FileWriter(new File("atlasifyChemistrySample.csv"), true), ',');
        row[0] = "Entity 1";
        row[1] = "Entity 2";
        row[2] = "SR";
        writer.writeNext(row);
        writer.flush();
        for(String feature :featuredArticle){
            for(String mapFeature :periodicTableArray){
                double srValue = sr.similarity(feature, mapFeature, false).getScore();
                row[0] = feature;
                row[1] = mapFeature;
                row[2] = String.valueOf(srValue);
                writer.writeNext(row);
                writer.flush();
            }
        }
        writer.close();
        */
    }


}