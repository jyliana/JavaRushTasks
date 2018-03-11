package com.javarush.task.task35.task3507;

import java.io.File;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        File[] list = new File(pathToAnimals).listFiles();

        for (File file : list) {
            if (file.isFile() && file.getName().endsWith("class")) {
                Class clazz = new ClassLoad().load(file.toPath());
                boolean hasAnimalInterface = false;
                boolean hasDefaultConstructor = false;

                if (Animal.class.isAssignableFrom(clazz)) {
                    hasAnimalInterface = true;
                }

              /* или такая проверка для интерфейса
                Class[] interfaces = clazz.getInterfaces();
                for (Class element : interfaces) {
                    if (element.getSimpleName().equals("Animal")) {
                        hasAnimalInterface = true;
                        break;
                    }
                }*/

                Constructor[] constructors = clazz.getConstructors();
                for (Constructor element : constructors) {
                    if (element.getParameterCount() == 0) {
                        hasDefaultConstructor = true;
                        break;
                    }
                }
                if (hasAnimalInterface && hasDefaultConstructor) {
                    try {
                        Animal animal = (Animal) clazz.newInstance();
                        set.add(animal);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return set;
    }

    public static class ClassLoad extends ClassLoader {

        public Class<?> load(Path path) {
            try {
                byte[] b = Files.readAllBytes(path);
                return defineClass(null, b, 0, b.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
