package com.indium.capstone.model;

public class Skill {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    private String category;
    private int experience;

    public Skill(int id, String name, String description, String category, int experience) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.experience = experience;
    }

    public void viewDetails() {
        System.out.println("Skill ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Category: " + category);
        System.out.println("Experience (months): " + experience);
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Skill.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
