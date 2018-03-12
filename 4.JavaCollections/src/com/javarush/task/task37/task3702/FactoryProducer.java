package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {

    public static AbstractFactory getFactory(HumanFactoryType type) {
        if (type.equals(HumanFactoryType.FEMALE))
            return new FemaleFactory();
        else if (type.equals(HumanFactoryType.MALE))
            return new MaleFactory();
        return null;
    }

    public static enum HumanFactoryType {
        MALE, FEMALE;
    }
}
