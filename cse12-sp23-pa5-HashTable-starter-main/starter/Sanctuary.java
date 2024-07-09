/* 
  Name: John Zhou
  Email: jozhou@ucsd.edu
  PID: A16347820
  Sources Used: https://www.geeksforgeeks.org/collections-sort-java-examples/#
  https://github.com/CaoAssignments/cse12-sp23-pa5-HashTable-starter
   
  This file is for CSE 12 PA5 in Spring 2023,
  and contains definitions of Sanctuary for storing Animals by Species 
  in the form of a HashMap.
*/

import java.util.HashMap;

// Determines base functionalities for Sanctuary class
public class Sanctuary {
    HashMap<String, Integer> sanctuary;
    private final int maxAnimals;
    private final int maxSpecies;

    /**
     * Initialize Sanctuary information in addition to exceptions for null
     * Also creates the sanctuary HashMap
     * @param maxAnimals maximum number of Animals within the Species
     * @param maxSpecies maximum number of Species in the Sanctuary
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
        if (maxAnimals <= 0 || maxSpecies <= 0 || maxSpecies > maxAnimals) {
            throw new IllegalArgumentException();
        }
        this.maxAnimals = maxAnimals;
        this.maxSpecies = maxSpecies;
        sanctuary = new HashMap<>();
    }
    /** 
     * Accessor for the number of Animals within the Species
     * @param species the family of Animal to look for 
     * @return number of Animals in the Species in the Sanctuary
     */
    public int countForSpecies(String species) {
        if (species == null) { throw new IllegalArgumentException(); }
        return sanctuary.getOrDefault(species, 0);
    }
    /** 
     * Accessor for the total number of Animals within the Sanctuary 
     * @return total number of Animals in the Sanctuary
     */
    public int getTotalAnimals() {
        int total = 0;
        for (int i : sanctuary.values()) { total += i; }
        return total;
    }
    /** 
     * Accessor for the number of Species within the Sanctuary
     * @return number of Species in the Sanctuary
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }
    /** 
     * Accessor for the max allowed number of Animals within the Species
     * @return int maxAnimals data of the Species in the Sanctuary
     */
    public int getMaxAnimals() {
        return this.maxAnimals;
    }
    /** 
     * Accessor for the max allowed number of Species within the Sanctuary
     * @return int maxSpecies data of the Species in the Sanctuary
     */
    public int getMaxSpecies() {
        return this.maxSpecies;
    }
    /**
     * Function to add more Animals into Species within the Sanctuary
     * Throws exception when species is null or num is <= 0
     * @param species the species to add Animals to
     * @param num number of Animals to be added
     * @return number of Animals that couldn't be added into the Sanctuary
     */
    public int rescue(String species, int num) {
        if (num <= 0 || species == null) { throw new IllegalArgumentException(); }
        if ((this.getTotalSpecies() >= this.getMaxSpecies() && !sanctuary.containsKey(species)) || 
        (this.getTotalAnimals() >= this.getMaxAnimals() && sanctuary.containsKey(species))) {
            return num;
        }
        int openSpaces = this.getMaxAnimals() - this.getTotalAnimals();
        int leftOut = num - openSpaces;
        int currAmt = sanctuary.getOrDefault(species, 0);
        if (leftOut > 0) {
            sanctuary.put(species, currAmt + openSpaces);
            return leftOut;
        }
        else {
            sanctuary.put(species, currAmt = num);
            return 0;
        }
    }
    /**
     * Function to remove Animals from Species within the Sanctuary
     * Throws exception when species is null or num is <= 0 or 
     * num is >= available Animals
     * @param species the species to add Animals to
     * @param num number of Animals to be added
     */
    public void release(String species, int num) {
        if (num <= 0 || num > this.getTotalAnimals() || species == null || 
        !sanctuary.containsKey(species)) {
            throw new IllegalArgumentException();
        }
        int update = sanctuary.getOrDefault(species, 0) - num;
        if (update <= 0) { sanctuary.remove(species); }
        else { sanctuary.put(species, update); }
    }
}
