package university;

import java.util.HashMap;

/**
 * The UniversityRepository class acts as a central repository for all universities,
 * storing information such as each university's name, acronym, and available spots.
 * It provides a static method to retrieve a university by its index.
 */
public class UniversityRepository {
    // HashMap storing universities with an Integer index as the key and University as the value
    private static HashMap<Integer, University> universityMap = new HashMap<>();

    // Static block to initialize the repository with predefined universities
    static {
        initialiseMap();
    }

    /**
     * Initialises the map of universities in this UniRepo class.
     */
    private static void initialiseMap() {
        universityMap.put(1, new University("The Uni of Western Australia", "UWA",1 ));
        universityMap.put(2, new University("The University of Melbourne", "UM",1));
        universityMap.put(3, new University("The Australian National Uni", "TANU",1));
        universityMap.put(4, new University("Katholieke Universiteit Leuven", "KUL",1));
        universityMap.put(5, new University("University of Waterloo", "Waterloo",2));
        universityMap.put(6, new University("Simon Fraser University", "SFU",2));
        universityMap.put(7, new University("University of Ottawa", "Ottawa",2));
        universityMap.put(8, new University("McGill University", "McGill",2));
        universityMap.put(9, new University("University of Calgary", "Calgary",1));
        universityMap.put(10, new University("Concordia University", "Concordia",1));
        universityMap.put(11, new University("The Uni of British Columbia", "UBC",1));
        universityMap.put(12, new University("Tongji University", "Tongji",1));
        universityMap.put(13, new University("Chongqing University", "Chongqing",1));
        universityMap.put(14, new University("Zhejiang University", "Zhejiang",1));
        universityMap.put(15, new University("Tsinghua University", "Tsinghua",1));
        universityMap.put(16, new University("Peking University", "Peking",1));
        universityMap.put(17, new University("Nanjing University", "Nanjing",1));
        universityMap.put(18, new University("Shanghai Jiao Tong University", "SJTU",1));
        universityMap.put(19, new University("Fudan University", "Fudan",1));
        universityMap.put(20, new University("Aarhus University", "Aarhus",1));
        universityMap.put(21, new University("Technical Uni of Denmark", "DTU",1));
        universityMap.put(22, new University("Tallinn Uni of Technology", "TUT",1));
        universityMap.put(23, new University("CentSup - Ecole Centrale Paris", "ECP",1));
        universityMap.put(24, new University("Inst Nat des Sci Appl de Lyon", "INSA Lyon",2));
        universityMap.put(25, new University("TELECOM ParisTech", "ParisTech",2));
        universityMap.put(26, new University("Ecole Nat Sup des Mines Paris", "Mines Paris",1));
        universityMap.put(27, new University("Universite Grenoble Alpes", "UGA",2));
        universityMap.put(28, new University("Hamburg Uni of Technology", "HUT",1));
        universityMap.put(29, new University("Technical Uni of Munich", "TUM",2));
        universityMap.put(30, new University("University of Stuttgart", "Stuttgart",1));
        universityMap.put(31, new University("Karlsruhe Inst of Technology", "KIT",1));
        universityMap.put(32, new University("Technical Uni of Darmstadt", "TUD",1));
        universityMap.put(33, new University("The Hong Kong Polytechnic Uni", "HKPU",1));
        universityMap.put(34, new University("Hong Kong Uni of Sci & Tech", "HKUST",2));
        universityMap.put(35, new University("City University of Hong Kong", "TCUHK",2));
        universityMap.put(36, new University("The University of Hong Kong", "HKU",2));
        universityMap.put(37, new University("The Chinese Uni of Hong Kong", "TCUHK",1));
        universityMap.put(38, new University("University College Dublin", "UCD",1));
        universityMap.put(39, new University("Tohoku University", "Tohoku",1));
        universityMap.put(40, new University("Kyushu University", "Kyushu",1));
        universityMap.put(41, new University("The University of Tokyo", "UTokyo",1));
        universityMap.put(42, new University("Nagoya University", "Nagoya",1));
        universityMap.put(43, new University("Keio University", "Keio",1));
        universityMap.put(44, new University("Korea University", "KU",1));
        universityMap.put(45, new University("Korea Adv Inst S&T (KAIST)", "KAIST",1));
        universityMap.put(46, new University("Yonsei University", "Yonsei",1));
        universityMap.put(47, new University("Eindhoven Uni of Technology", "EUT",2));
        universityMap.put(48, new University("Delft University of Technology", "DUT",2));
        universityMap.put(49, new University("Victoria Uni of Wellington", "VUW",1));
        universityMap.put(50, new University("Norwegian U of Sci&Tech (NTNU)", "NTNU",1));
        universityMap.put(51, new University("University of Oslo", "UO",1));
        universityMap.put(52, new University("Universidad Autonoma de Madrid", "UAM",1));
        universityMap.put(53, new University("Chalmers Uni of Technology", "Chalmers",1));
        universityMap.put(54, new University("Uppsala University", "Uppsala",1));
        universityMap.put(55, new University("Stockholm University", "Stockholm",1));
        universityMap.put(56, new University("Lund University", "Lund",2));
        universityMap.put(57, new University("University of Gothenburg", "Gothenburg",1));
        universityMap.put(58, new University("ETH Zurich", "Zurich",1));
        universityMap.put(59, new University("Ecole Poly Federale Lausanne", "EPFL",1));
        universityMap.put(60, new University("University of Geneva", "Geneva",1));
        universityMap.put(61, new University("National Tsing Hua University", "NTHU",1));
        universityMap.put(62, new University("National Cheng Kung University", "NCKU",1));
        universityMap.put(63, new University("National Yang Ming Chiao Tung", "NYCU",2));
        universityMap.put(64, new University("Chulalongkorn University", "CU",1));
        universityMap.put(65, new University("Sabanci University", "Sabanci",1));
        universityMap.put(66, new University("King's College London", "KCL",2));
        universityMap.put(67, new University("The University of Manchester", "Manchester",1));
        universityMap.put(68, new University("The University of Sheffield", "Sheffield",1));
        universityMap.put(69, new University("The University of Nottingham", "Nottingham",1));
        universityMap.put(70, new University("University of Liverpool", "Liverpool",1));
        universityMap.put(71, new University("University of Leeds", "Leeds",1));
        universityMap.put(72, new University("University of Glasgow", "Glasgow",1));
        universityMap.put(73, new University("The University of Edinburgh", "Edinburgh",1));
        universityMap.put(74, new University("University of Bristol", "Bristol",1));
        universityMap.put(75, new University("University of Birmingham", "Birmingham",2));
        universityMap.put(76, new University("Boston College", "Boston College",1));
        universityMap.put(77, new University("University of Washington", "UW",1));
        universityMap.put(78, new University("University of Virginia", "UV",1));
        universityMap.put(79, new University("The Uni of Texas at Austin", "UTA",1));
        universityMap.put(80, new University("Purdue University", "Purdue",1));
        universityMap.put(81, new University("Pennsylvania State University", "Penn State",2));
        universityMap.put(82, new University("Uni of Michigan, Ann Arbor", "UMich",1));
        universityMap.put(83, new University("Georgia Inst of Technology", "GT",2));
        universityMap.put(84, new University("University of Connecticut", "UConn",2));
        universityMap.put(85, new University("Case Western Reserve Uni", "Case Western",1));
        universityMap.put(86, new University("Carnegie Mellon University", "CMU",1));
        universityMap.put(87, new University("University of California", "UC",5));
        universityMap.put(88, new University("Brandeis University", "Brandeis",1));
        universityMap.put(89, new University("Boston University", "Boston U",2));
        universityMap.put(90, new University("Tulane University", "Tulane",1));
        universityMap.put(91, new University("Clarkson University", "Clarkson",1));
        universityMap.put(92, new University("University of Georgia", "UG",1));
    }

    /**
     * Retrieves a university from the repository based on the provided index.
     * 
     * @param index The index of the university to retrieve.
     * @return The University object corresponding to the given index, or null if no such university exists.
     */
    public static University getUniversityByIndex(int index) {
        return universityMap.get(index);
    }

    public static void resetMap() {
        universityMap = new HashMap<>();
        initialiseMap();
    }
}

