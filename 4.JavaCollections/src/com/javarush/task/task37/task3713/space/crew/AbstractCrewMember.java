package com.javarush.task.task37.task3713.space.crew;

public abstract class AbstractCrewMember {
    protected CompetencyLevel competencyLevel;
    protected AbstractCrewMember nextCrewMember;

    public void setNextCrewMember(AbstractCrewMember nextCrewMember) {
        this.nextCrewMember = nextCrewMember;
    }

    public void handleRequest(CompetencyLevel competencyLevel, String request) {
        if (this.competencyLevel == competencyLevel) {
            doTheJob(request);
            return;
        } else if (nextCrewMember != null) {
            nextCrewMember.handleRequest(competencyLevel, request);
        }
    }

    protected abstract void doTheJob(String request);

    public enum CompetencyLevel {
        NOVICE,
        INTERMEDIATE,
        ADVANCED,
        EXPERT
    }
}
