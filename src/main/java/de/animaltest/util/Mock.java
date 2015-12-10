package de.animaltest.util;

import de.animaltest.animalverwaltung.domain.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Siam Gundermann on 10.12.2015.
 */
public class Mock {

    public static Animal findAnimalsById(Long id) {
        if (id > 99) {
            return null;
        }

        final Animal animal = new Animal();
        animal.setId(id);
        animal.setName("Name" + id);
        animal.setDescription(animal.getName() + " ist ein netter Hund.");
        animal.setVermittelt(false);

        return animal;
    }

    public static List<Animal> findAllAnimals() {
        final int anzahl = 8;
        final List<Animal> animals = new ArrayList<>(anzahl);
        for (int i = 1; i <= anzahl; i++) {
            final Animal animal = findAnimalsById(Long.valueOf(i));
            animals.add(animal);
        }
        return animals;
    }

    public static List<Animal> findAnimalByName(String name) {
        final int anzahl = name.length();
        final List<Animal> animals = new ArrayList<>(anzahl);
        for (int i = 1; i <= anzahl; i++) {
            final Animal animal = findAnimalsById(Long.valueOf(i));
            animal.setName(name);
            animals.add(animal);
        }
        return animals;
    }

    public static <T extends Animal> T createAnimal(T animal) {
        final String name = animal.getName();
        animal.setId(Long.valueOf(name.length()));
        animal.getDescription();
        animal.isVermittelt();

        return animal;
    }

    public static void updateAnimal(Animal animal) {
        //Daten werden verändert,deswegen passiert nix in der Mock
    }

    public static void deleteAnimal(Animal animal) {
        //Daten werden verändert,deswegen passiert nix in der Mock
    }
}